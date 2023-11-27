package com.AAACE.RUTidy.constants;

public class ResponseConstants {

    public static final String SUCCESS="Success";

    public static final String FAILURE="Failure";

    public static final String ERROR="Error";

//for does not exist failure
    public static final String GROUP_NOT_FOUND="Group not found";

    public static final String USER_NOT_FOUND="User not found";

    public static final String INVITATION_NOT_FOUND="Invitation not found";

    public static final String TASK_NOT_FOUND = "Task not found";

    public static final String TASK_COMMENT_NOT_FOUND = "Task comment not found";


        
 //for when * exists failure   
    public static final String USER_ALREADY_EXISTS="User already exists";

    public static final String GROUP_ALREADY_EXISTS="Group already exists";

    public static final String INVITATION_ALREADY_EXISTS="Invitation already exists";

    public static final String TASK_ALREADY_EXISTS="Task already exists";

// not in * failure
    public static final String USER_NOT_IN_GROUP="User not in group";
    public static final String TASK_NOT_IN_GROUP="Task not in group";
    public static final String USER_NOT_ASSIGNED_TASK="User not assigned task";
    public static final String USER_NOT_INVITED="User not invited";
    
// already in * failure
    public static final String USER_ALREADY_IN_GROUP="User already in group";
    public static final String USER_ALREADY_ASSIGNED_TASK="User already assigned task";
    public static final String USER_ALREADY_INVITED="User already invited";
    public static final String TASK_ALREADY_IN_GROUP="Task already in group";

    
}
