package com.AAACE.RUTidy.components;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.AAACE.RUTidy.dto.Response;
import com.AAACE.RUTidy.model.User;
import com.AAACE.RUTidy.service.email.EmailService;
import com.AAACE.RUTidy.service.user.UserService;

import java.util.List;

@Component
public class ScheduledEmails {

    @Autowired 
    UserService userService;

    @Autowired 
    EmailService emailService;
    
    @Autowired
    ExecutorService taskExecutor; // for multithreading


    @Scheduled(cron = "0 0 08 * * ?")  // runs every day at 10 AM
    public void sendDailyEmails() {
        Response getUserResponse = userService.getAllUsers();
        List<User> users = (List<User>) getUserResponse.getObject();
        
        if(users == null) {
            return;
        }

        for (User user : users) {
            taskExecutor.execute(() -> {
            emailService.generateDailyEmail(user.getUserID());
        });
    }
    }
}
