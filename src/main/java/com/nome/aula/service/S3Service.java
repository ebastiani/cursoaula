package com.nome.aula.service;

import java.io.File;
import java.io.InputStream;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3cliente;

	@Value("${s3.bucket}")
	private String bucket;

	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String nomeArquivo = multipartFile.getOriginalFilename();

			InputStream stream = multipartFile.getInputStream();

			String contentType = multipartFile.getContentType();

			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);

			System.out.println(bucket);
			s3cliente.putObject(new PutObjectRequest(bucket, nomeArquivo, stream, meta));

			return s3cliente.getUrl(bucket, nomeArquivo).toURI();

		} catch (AmazonServiceException e) {
			System.out.println(e.toString());
			return null;
		}

		catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}

	}

}
