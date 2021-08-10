package com.lucascordoba.backendchallenge.services;

import com.lucascordoba.backendchallenge.dto.UserDTO;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.UserDataHandler;

import java.io.IOException;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService{

    @Value("${spring.sendgrid.api-key:}")
    private String api_key;
    @Value("${spring.sendgrid.fromEmail}")
    private String fromEmail;


    public void sendEmail(UserDTO user) {
        Email from = new Email(fromEmail);
        String subject = "Backend Alkemy Challenge";
        Email to = new Email(user.getEmail());
        Content content = new Content("text/plain", "Welcome "+user.getName()+".\nYour username is: "+user.getUsername()+"\nThanks for register!");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(api_key);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
