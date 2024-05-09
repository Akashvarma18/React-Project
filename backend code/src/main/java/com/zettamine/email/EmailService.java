package com.zettamine.email;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.zettamine.constants.AppConstants;
import com.zettamine.entity.User;

import jakarta.activation.URLDataSource;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	
	
	

	public boolean sendEmail(User user) {
		
		String content = getEmailContents(user.getFirstName(), user.getLastName(), user.getDesignation(), user.getEmail(),user.getTempPwd());
		String subject =AppConstants.WELCOME_MAIL;
		String from =AppConstants.EMAIL_FROM;
		String to = user.getEmail();
		
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
			
			helper.setSubject(subject);
			helper.setFrom(from);
			helper.setTo(to);;
			helper.setText(content, true);
			URLDataSource dataSource = new URLDataSource(this.getClass().getResource("/images/logo.png"));
			helper.addInline("akash", dataSource);
			
			mailSender.send(mimeMessage);
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return false;
	}

	private String getEmailContents(String fName, String lName, String role, String email, String password) {
		try (InputStream fis = new ClassPathResource("templates/user-email.html").getInputStream()) {
            String str = IOUtils.toString(fis, "UTF-8");
            return String.format(str, fName, lName, role, email, password);
        } catch (IOException e) {
            
            return "";
        }
	}

	

}
