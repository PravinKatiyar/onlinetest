package com.pravinkatiyar.onlinetest.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,TestInfoEmail testInfoEmail) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("electricitybillpayment99@gmail.com");
        message.setTo(toEmail);
        String body="Hi, "+
        			testInfoEmail.getCandidateName()+"\n"+
        			"You have scored "+testInfoEmail.getMarksScored()+"/"+testInfoEmail.getMaximumMarks()+
        			" in the quiz name: "+testInfoEmail.getQuizName()+"\n"+
        			"Your final Result is: "+testInfoEmail.getResult()+"\n \n"+
        			"Thanks & Regards \n OnlineQuizTeam";
        message.setText(body);
        message.setSubject("Quiz Details");

        mailSender.send(message);
        System.out.println("Mail Send...");
    }

   
}
