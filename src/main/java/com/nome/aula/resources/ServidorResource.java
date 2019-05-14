package com.nome.aula.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nome.aula.DTO.ServidorDTO;
import com.nome.aula.DTO.ServidorNewDTO;
import com.nome.aula.entity.ServidorEntity;
import com.nome.aula.service.EmailService;
import com.nome.aula.service.ServidorService;

@RestController
@RequestMapping(value = "/servidores")
public class ServidorResource {

	@Autowired
	ServidorService service;

	@Autowired
	EmailService email;

	@Autowired
	BCryptPasswordEncoder encoder;

	@RequestMapping(method = RequestMethod.GET)
	public List<ServidorDTO> buscar() {
		List<ServidorEntity> listaServidors = service.buscar();
		List<ServidorDTO> listaDTO = listaServidors.stream().map(obj -> new ServidorDTO(obj))
				.collect(Collectors.toList());
		return listaDTO;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Void> atualizar(@Valid @RequestBody ServidorDTO objDTO, @PathVariable Integer id) {

		ServidorEntity obj = new ServidorEntity(id, objDTO.getNome(), objDTO.getEmail(), null);

		obj = service.atualizar(obj);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<ServidorEntity> buscar(@PathVariable Integer id) {
		ServidorEntity ojb = service.buscar(id);
		return ResponseEntity.ok(ojb);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody ServidorNewDTO objDTO) {

		ServidorEntity obj = new ServidorEntity(null, objDTO.getNome(), objDTO.getEmail(),
				encoder.encode(objDTO.getSenha()));

		// obj = service.salvar(obj);
		try {
			if (obj != null) {
				email.enviarEmailHtml(obj);
			}
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId())
					.toUri();

			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.apagar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/paginacao", method = RequestMethod.GET)
	public ResponseEntity<Page<ServidorDTO>> listarPaginas(@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "qtd", defaultValue = "15") Integer qtdLinhas,
			@RequestParam(value = "ordem", defaultValue = "nome") String orderBy,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		Page<ServidorEntity> listaCursos = service.buscar(nome, pagina, qtdLinhas, orderBy, dir);

		Page<ServidorDTO> listaDTO = listaCursos.map(obj -> new ServidorDTO(obj));

		return ResponseEntity.ok().body(listaDTO);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestParam(name = "arquivo") MultipartFile file) {

		URI uri = service.uploadArquivo(file);

		return ResponseEntity.created(uri).build();

	};

}
