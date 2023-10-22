package com.AAACE.RUTidy.controller;


import com.AAACE.RUTidy.dto.UpdateUserPermission;
import com.AAACE.RUTidy.dto.GroupDTO;
import com.AAACE.RUTidy.service.GroupDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
@RequestMapping(value = "/groupdetails", method = {RequestMethod.GET, RequestMethod.POST})
public class GroupDetailsController {

    @Autowired
    private GroupDetailsService groupdetailsService;

    @PostMapping(path = "/updateUserPermission")
    public UpdateUserPermission updateUserPermission(@RequestBody GroupDTO groupDTO){
        return groupdetailsService.updateUserPermission(groupDTO);
    }

    
    
}

