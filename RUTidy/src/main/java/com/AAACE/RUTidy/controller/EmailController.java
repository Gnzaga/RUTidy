package com.AAACE.RUTidy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AAACE.RUTidy.components.ScheduledEmails;
import com.AAACE.RUTidy.dto.Response;
import com.AAACE.RUTidy.service.email.EmailService;

@RestController
@CrossOrigin
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private ScheduledEmails scheduledEmails;

    @PostMapping("/send-email")
    public Response sendEmail(@RequestParam String toEmail, @RequestParam String subject, @RequestParam String body){
        return emailService.sendEmail(toEmail, subject, body);
    }

    @PostMapping("/force-daily-email")
    public Response forceDailyEmail(@RequestParam int userID){
        return emailService.generateDailyEmail(userID);
    }

    @PostMapping("force-all-daily")
    public Response forceAllDailyEmails(){
        scheduledEmails.sendDailyEmails();
        return new Response("Success", null);
    }
    

}

