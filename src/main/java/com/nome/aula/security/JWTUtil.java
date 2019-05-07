package com.nome.aula.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	//gera o token a partir do email.
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis()+expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}


	public boolean isValidToken(String token) {		
		Claims claims = getClaims(token);
		if(claims!=null) {
			String username = claims.getSubject();
			Date dataExp = claims.getExpiration();
			Date dataAtual = new Date(System.currentTimeMillis());
			if(username!=null && dataExp!=null && dataAtual.before(dataExp)) {
				return true;
			}
				
		}
		return false;
	}


	public String getUserName(String token) {		
		Claims claims = getClaims(token);
		if(claims!=null) {
			return claims.getSubject();
		}
		return null;
		
	}
	
	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}
		catch (Exception e) {
			return null;
		}
	}
	
	
}
