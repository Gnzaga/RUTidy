package com.AAACE.RUTidy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

import com.AAACE.RUTidy.model.*;
import com.AAACE.RUTidy.service.*;
import com.AAACE.RUTidy.service.group.GroupService;
import com.AAACE.RUTidy.service.group.UsersInGroupService;
import com.AAACE.RUTidy.service.user.UserService;
import com.AAACE.RUTidy.dto.*;


@SpringBootTest
@AutoConfigureMockMvc
public class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService groupService;

    @MockBean
    private UserService userService;

    @MockBean
    private UsersInGroupService usersInGroupService;

    // ... (other test methods) ...

    @Test
    public void getGroupsInTest() throws Exception {
        // Assuming UsersInGroup has a constructor or a method to populate its data
        List<UsersInGroup> usersInGroups = Arrays.asList(new UsersInGroup(), new UsersInGroup());
        when(groupService.getGroupsIn(1)).thenReturn(usersInGroups);

        mockMvc.perform(get("/group/in?userID=1"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getAllUserRolesTest() throws Exception {
        HashMap<Integer, Integer> roles = new HashMap<>();
        roles.put(1, 2); // Assuming 1 is userID and 2 is roleID
        when(usersInGroupService.getAllUsersRoleInGroup(1)).thenReturn(roles);

        mockMvc.perform(get("/group/get-all-user-roles?groupID=1"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$[1]", is(2)));
    }

    @Test
    public void getJoinedGroupsTest() throws Exception {
        List<Group> joinedGroups = Arrays.asList(new Group(), new Group());
        when(groupService.getJoinedGroups(1)).thenReturn(joinedGroups);

        mockMvc.perform(get("/group/joined-groups?userID=1"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$", hasSize(2)));
    }
/* 
    @Test
    public void getMyGroupsTest() throws Exception {
        User user = new User();
        user.setName("User");
        user.setPassword("password");

        UsersInGroup groupIn = new UsersInGroup();
        List<UsersInGroup> groupsIn = new ArrayList<>();
        groupsIn.add(groupIn); // Add your groupIn populated with test data
        when(userService.getUser(1)).thenReturn(user);
        when(groupService.getGroupsIn(1)).thenReturn(groupsIn);

        mockMvc.perform(get("/group/myGroups?userID=1"))
                .andExpect(status().isAccepted());
                
    }
*/
    @Test
    public void joinGroupTest() throws Exception {
        when(groupService.joinGroup(anyInt(), anyInt())).thenReturn("Joined group successfully");

        mockMvc.perform(put("/group/join?groupID=1&userID=1"))
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Joined group successfully")));
    }

    @Test
    public void leaveGroupTest() throws Exception {
        doNothing().when(groupService).leaveGroup(anyInt(), anyInt());

        mockMvc.perform(delete("/group/leave?userID=1&groupID=1"))
                .andExpect(status().isOk()) // Assuming the status is OK for success
                .andExpect(content().string(containsString("Success!")));
    }

    @Test
    public void removeUserFromGroupTest() throws Exception {
        doNothing().when(groupService).removeUserFromGroup(anyInt(), anyInt());

        mockMvc.perform(delete("/group/removeUserFromGroup?userID=1&groupID=1"))
                .andExpect(status().isOk()) // Assuming the status is OK for success
                .andExpect(content().string(containsString("Success!")));
    }

    @Test
    public void getUsersInGroupTest() throws Exception {
        List<User> users = Arrays.asList(new User(), new User());
        users.get(0).setName("User1");
        users.get(1).setName("User2");

        when(groupService.findUsersInGroup(1)).thenReturn(users);

        mockMvc.perform(get("/group/listUsersInGroup?groupID=1"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("User1")))
                .andExpect(jsonPath("$[1].name", is("User2")));
    }
/* 
    @Test
    public void createGroupTest() throws Exception {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setName("New Group");
        Response response = new Response("Group created successfully", null);
        when(groupService.createGroup(any(GroupDTO.class))).thenReturn(response);

        mockMvc.perform(post("/group/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New Group\",\"ownerID\":1}"))
                .andExpect(status().isOk()) // Assuming the status is OK for success
                .andExpect(content().string(containsString("Group created successfully")));
    }
*/
    @Test
    public void addUserToGroupTest() throws Exception {
        Response response = new Response("User added to group", null);
        when(groupService.addUserToGroup(anyInt(), anyString())).thenReturn(response);

        mockMvc.perform(post("/group/addUserToGroup?groupID=1&textEntry=User1"))
                .andExpect(status().isOk()) // Assuming the status is OK for success
                .andExpect(content().string(containsString("User added to group")));
    }

    @Test
    public void updateUserPermissionTest() throws Exception {
        Response response = new Response("User permissions updated", null);
        when(groupService.updateUserPermission(anyInt(), anyInt(), anyInt())).thenReturn(response);

        mockMvc.perform(put("/group/updateUserPermission?groupID=1&userID=1&roles=2"))
                .andExpect(status().isOk()) // Assuming the status is OK for success
                .andExpect(content().string(containsString("User permissions updated")));
    }

    // ... (other test methods) ...
}
