package com.nome.aula.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nome.aula.security.JWTAuthenticationFilter;
import com.nome.aula.security.JWTAuthorizationFilter;
import com.nome.aula.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	//https://domineospring.wordpress.com/2016/07/13/guia-das-annotations-do-spring/ 
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	private static final String[] CAMINHOS_PUBLICOS_GET = {
			"/cursos/**",
			"/servidores/**"
			
	};
		
	private static final String[] CAMINHOS_PUBLICOS_POST = {
			"/servidores/**"
			
	};

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http.cors().and().csrf().disable(); // habilita o cors (método abaixo) e desabilita CSRF, pois a abordagem é stateless
		
		// libera os caminhos dos vetores acima e qualquer outro, desde que o usuário esteja autenticado.
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, CAMINHOS_PUBLICOS_GET).permitAll()
		.antMatchers(HttpMethod.POST, CAMINHOS_PUBLICOS_POST).permitAll()
		.anyRequest()
		.authenticated();
		
		//define que a aplicação não manterá sessão de usuários
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
		// adiciona os filtros de autenticação
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		
		// adiciona os filtros de autorização
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
	}
	
	
	
	  //bean para permitir o acesso de múltiplas fontes	
	  @Bean
	  CorsConfigurationSource corsConfigurationSource() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
	  }
	  
	  //bean para utilizar o mecanismo de criptografia para a senha. 
	  @Bean
	  public BCryptPasswordEncoder bCryptPasswordEncoder() {
		  return new BCryptPasswordEncoder();
	  }

	  
	//Configuração que permite ao spring boot utilize um user details service customizado
	 //Define qual é o UDS e o qual é o algoritmo de codificação da senha.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	  
	  
	

}
