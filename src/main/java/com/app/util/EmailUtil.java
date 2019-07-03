package com.app.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(
			String to,
			String subject,
			String text,
			//String[] cc,
			//String[] Bcc,
			FileSystemResource file
			) {
		boolean flag=false;
		try {
			//1.create mime message object
			MimeMessage message=mailSender.createMimeMessage();
			//2.Helper class Object
			MimeMessageHelper helper=new MimeMessageHelper(message,file!=null?true:false);
			//3.set deatils
			helper.setTo(to);
			helper.setFrom("abc8790248241@gmail.com");
			helper.setSubject(subject);
			helper.setText(text);
			//helper.setCc(cc); //array Inputs
			//helper.setBcc(bcc);
			if(file!=null)
				helper.addAttachment(file.getFilename(),file);
			
			//4.send mail
			mailSender.send(message);
			
			flag=true;
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}

}
