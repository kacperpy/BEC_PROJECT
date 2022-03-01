package dk.bec.bookanything.controller;


import dk.bec.bookanything.model.UserEntity;
import dk.bec.bookanything.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserEntity> users(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<UserEntity> getUserById(@PathVariable("id")Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

    @PostMapping("users")
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity userEntity){
        try {
            userService.addUser(userEntity);
            return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable("id") Long id, UserEntity userEntity){
        try {
            userService.updateUser(id, userEntity);
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}