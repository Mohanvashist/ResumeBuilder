package com.ResumeBuilder.ResumeBuilder.controller;

import com.ResumeBuilder.ResumeBuilder.model.StringResponse;
import com.ResumeBuilder.ResumeBuilder.model.User;
import com.ResumeBuilder.ResumeBuilder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    @Autowired
    private UserService userService;

    @PutMapping("/api/admin/user-update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User existUser = userService.findByUsername(user.getUsername());
        Long id = Long.valueOf(user.getId());
        if(existUser!=null && !id.equals(Long.valueOf(user.getId()))){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.updateUser(user),HttpStatus.CREATED);
    }

    @PostMapping("/api/admin/user-delete")
    public ResponseEntity<?> deleteUser(@RequestBody User user){
        userService.deleteUser(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/admin/user-all")
    public ResponseEntity<?> findAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/api/admin/user-count")
    public ResponseEntity<?> numberOfUsers(){
        Long number = userService.numberOfUsers();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
