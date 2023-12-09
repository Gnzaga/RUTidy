package com.AAACE.RUTidy.service.email;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.AAACE.RUTidy.constants.ResponseConstants;
import com.AAACE.RUTidy.dto.Response;
import com.AAACE.RUTidy.model.Task;
import com.AAACE.RUTidy.model.User;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.AAACE.RUTidy.service.task.TaskImplementation;
import com.AAACE.RUTidy.service.user.UserService;

import jakarta.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Collector;


@Service
public class EmailImplementation implements EmailService{

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TaskImplementation taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailContentProvider ecp;

    public Response sendEmail(String toEmail, String subject, String body) {
    try {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("aaace.rutidy.test@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body, true); // Set to 'true' to send HTML

        mailSender.send(message);
        System.out.println("Email sent to: " + toEmail);
        return new Response(ResponseConstants.SUCCESS, message.toString());
    } catch (Exception e) {
        System.out.println("Error in sending to email: " + toEmail);  
        return new Response(ResponseConstants.ERROR, null);
    }
}
    /*
     * This method will generate a daily email for the user that reminds them of all the tasks they have to do still, grouped by status, sorted by due date then priority
     * @param userID
     */
    public Response generateDailyEmail(int userID){
        User user = userService.getUser(userID);
        //check if we can get the user  
        if(user == null){
            return new Response(ResponseConstants.ERROR, null);
        }
        return this.generateDailyEmail(user);
    }



    public Response generateDailyEmail(User user){
        Response userTasksResponse = taskService.getUserTasks(user.getUserID());
        //check if we can get the user's tasks  
        if(!userTasksResponse.getMessage().equals(ResponseConstants.SUCCESS) 
            || user == null){
            return new Response(ResponseConstants.ERROR, null);
        }
        
        List<Task> userTasks = (List<Task>) userTasksResponse.getObject();

        TaskProcessor taskProcessor = new TaskProcessor();
        Map<Integer, Map<String, List<Task>>> organizedTasks = taskProcessor.processTasks(userTasks);


        String emailContent = ecp.createHtmlEmailContent(organizedTasks);
        return this.sendEmail(user.getEmail(), "Daily Task Summary", emailContent);

    }

    public Map<Integer, Map<String, List<Task>>> processTasks(List<Task> tasks) {
        // Group by Group ID
        Map<Integer, List<Task>> groupedByGroup = tasks.stream()
            .collect(Collectors.groupingBy(task -> (Integer) task.getGroup().getGroupID()));

        // Within each group, group by completion status and sort
        Map<Integer, Map<String, List<Task>>> groupedAndSorted = new HashMap<>();
        for (Map.Entry<Integer, List<Task>> groupEntry : groupedByGroup.entrySet()) {
            Map<String, List<Task>> sortedByStatus = groupEntry.getValue().stream()
                .collect(Collectors.groupingBy(Task::getStatus,
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            list.sort(Comparator.comparing(Task::getDueDate)
                                                .thenComparing(Task::getPriority));
                            return list;
                        }
                    )));
            groupedAndSorted.put(groupEntry.getKey(), sortedByStatus);
        }

        return groupedAndSorted;
    }
    

    /*
     * This method will generate a weekly email for the user that reminds them of all the tasks they have to do still, grouped by group, sorted by due date then priority
     * @param userID
     */
    public Response generateWeeklyEmail(int userID){
        User user = userService.getUser(userID);
        //check if we can get the user  
        if(user == null){
            return new Response(ResponseConstants.ERROR, null);
        }
        return this.generateWeeklyEmail(user);
    }

    public Response generateWeeklyEmail(User user){
        Response userTasksResponse = taskService.getUserTasks(user.getUserID());
        //check if we can get the user's tasks  
        if(!userTasksResponse.getMessage().equals(ResponseConstants.SUCCESS) 
            || user == null){
            return new Response(ResponseConstants.ERROR, null);
        }
        
        List<Task> userTasks = (List<Task>) userTasksResponse.getObject();

        TaskProcessor taskProcessor = new TaskProcessor();
        Map<Integer, Map<String, List<Task>>> organizedTasks = taskProcessor.processTasks(userTasks);


        String emailContent = ecp.createHtmlEmailContent(organizedTasks);

        return this.sendEmail(user.getEmail(), "Daily Task Summary", emailContent);
    }


    
                    
}
