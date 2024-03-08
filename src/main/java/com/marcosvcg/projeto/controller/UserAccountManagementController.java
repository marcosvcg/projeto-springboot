package com.marcosvcg.projeto.controller;

import com.marcosvcg.projeto.service.UserAccountManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "projeto")
public class UserAccountManagementController {

    private final UserAccountManagementService userAccountManagementService;

    @Autowired
    public UserAccountManagementController(UserAccountManagementService userCredentialsChangeService) {
        this.userAccountManagementService = userCredentialsChangeService;
    }

    @PutMapping("/change-username/{userId}")
    public void changeUsername(@PathVariable("userId") Long userId, @RequestBody String username) {
        userAccountManagementService.changeUsername(userId, username);
    }

    @PutMapping("/change-email/{userId}")
    public void changeEmail(@PathVariable("userId") Long userId, @RequestBody  String email) {
        userAccountManagementService.changeEmail(userId, email);
    }

    @PutMapping("/change-password/{userId}")
    public void changePassword(@PathVariable("userId") Long userId, @RequestBody String password) {
        userAccountManagementService.changePassword(userId, password);
    }
}
