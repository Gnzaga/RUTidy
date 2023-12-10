package com.AAACE.RUTidy.service.email;

import com.AAACE.RUTidy.dto.Response;

public interface EmailService {

    public Response sendEmail(String toEmail, String subject, String body);

    public Response generateDailyEmail(int userID);

    public Response generateWeeklyEmail(int userID);

    public Response forceEmailToUsersOfGroup(int groupID);

    public Response forceEmailToThisUserInGroup(int groupID, int userID);

    

    
}
