package com.AAACE.RUTidy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import com.AAACE.RUTidy.service.email.EmailImplementation;

import static org.mockito.Mockito.*;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailImplementation emailService;

    @MockBean
    private JavaMailSender mailSender;

    @Test
    public void testSendEmail() {
        // Arrange
        String to = "amg573@scarletmail.rutgers.edu";
        String subject = "Test Subject";
        String body = "Test Body";

        // Act
        emailService.sendEmail(to, subject, body);

        
        // Assert
     
    }
}
