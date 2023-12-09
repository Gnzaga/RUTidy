package com.AAACE.RUTidy.service.email;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.AAACE.RUTidy.model.Task;
import com.AAACE.RUTidy.service.group.GroupService;

@Component
public class EmailContentProvider {

    @Autowired
    private GroupService groupService;

    public String createHtmlEmailContent(Map<Integer, Map<String, List<Task>>> organizedTasks) {
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("<html><body>");

        // Iterate through the groups and tasks to build HTML email content
        for (Map.Entry<Integer, Map<String, List<Task>>> groupEntry : organizedTasks.entrySet()) {
            emailBody
                .append("<h2>Group: ")
                .append(groupService.groupNameByID(groupEntry.getKey()))
                .append("</h2>");
            for (Map.Entry<String, List<Task>> statusEntry : groupEntry.getValue().entrySet()) {
                emailBody.append("<h3>Status: ").append(statusEntry.getKey()).append("</h3><ul>");
                for (Task task : statusEntry.getValue()) {
                    emailBody
                            .append("<li><strong>")
                            .append(task.getName())
                            .append("</strong> - Due Date: ")
                            .append(", Description: ")
                            .append(task.getDescription())
                            .append(task.getDueDate())
                            .append(", Priority: ")
                            .append(task.getPriority())
                            .append("</li>");
                }
                emailBody.append("</ul>");
            }
        }

        emailBody.append("</body></html>");
        return emailBody.toString();
    }


}
