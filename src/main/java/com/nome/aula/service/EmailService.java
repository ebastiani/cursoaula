package com.nome.aula.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.nome.aula.entity.ServidorEntity;

@Service
public class EmailService {
	
	@Value("${email.remetente}")
	private String rementente;
	
	@Autowired
	private MailSender mailSender;

	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;

	public void enviarEmail(ServidorEntity obj) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(obj.getEmail());
		message.setFrom(rementente);
		message.setSubject("Cadastro de usuário");
		message.setText(obj.toString());
		message.setSentDate(new Date(System.currentTimeMillis()));
		
		System.out.println(message.toString());
		
		mailSender.send(message);
		
		
	}
	
	public void enviarEmailHtml(ServidorEntity obj) throws MessagingException{
		
		Context context = new Context();
		context.setVariable("servidor", obj);
		String template =  templateEngine.process("emailNovoServidor", context);
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);				
		
		helper.setTo(obj.getEmail());
		helper.setFrom(rementente);
		helper.setSubject("Cadastro de usuário");
		helper.setText(template, true);
		helper.setSentDate(new Date(System.currentTimeMillis()));
		
		System.out.println(message.toString());	
		
		javaMailSender.send(message);				
	}
	
	
	//https://accounts.google.com/b/0/DisplayUnlockCaptcha
	//https://myaccount.google.com/lesssecureapps

}
