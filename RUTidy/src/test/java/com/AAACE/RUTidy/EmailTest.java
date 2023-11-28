package com.AAACE.RUTidy;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.management.Notification;

import org.checkerframework.checker.units.qual.N;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.AAACE.RUTidy.model.User;
import com.AAACE.RUTidy.service.user.UserService;

@MockBean
    
public class EmailTest {
    private UserService userService;
    @Test
    public void userReceivedNotificationTest() throws Exception {
        //Notification is not yet implemented, however this is the object that will be used to send emails
        Notification noti = new Notification();

        //User to receiver the notification through their email, this case user with userID = 1
        int receiverID = 1;
        User receiver = userService.getUser(receiverID);
        /*
         * The Notification object is not yet implemented, so neither is the sendEmailTo method.
         * The sendEmailTo method will send a notification to the given receiver's/user's email.
         * sendEmailTo is a method that returns a boolean, True if the email was sent, False otherwise.
         */

        boolean status = noti.sendEmailTo(receiver);

        //If status is true it means the email was sent, so to test if the method works, we assert true.
        assertTrue(status);
    }


//non-functional outline of our Notification Object
    public class Notification{
        //This is the constructor for the Notification object
        String senderEmail = "";
        String content = "";
        User sender = null;
        Notification(){
            //This is where the email will be sent from, in this case it is the RUTidy email
            String senderEmail = "";
            String content = "";
            User sender = new User();
            
        }
            public boolean sendEmailTo(User Receiver){
                return false;
            }


            
        }
    
    
}