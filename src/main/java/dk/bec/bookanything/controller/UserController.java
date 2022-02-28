package dk.bec.bookanything.controller;


import dk.bec.bookanything.model.UserEntity;
import dk.bec.bookanything.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API")
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
    public UserEntity getUserById(@PathVariable("id")Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/users/{name}")
    public UserEntity getUserByName(@PathVariable("name")String name){
        return userService.getUserByName(name);
    }

    @GetMapping("/users/{surname}")
    public UserEntity getUserBySurname(@PathVariable("surname")String surname){
        return userService.getUserBySurname(surname);
    }

    @DeleteMapping("/users")
    public void deleteUser(@RequestBody UserEntity userEntity){
        userService.deleteUser(userEntity);
    }

    @DeleteMapping("/users/{name}")
    public void deleteUserByName(@PathVariable("name") String name){
        userService.deleteUserByName(name);
    }

    @DeleteMapping("/users/{surname}")
    public void deleteUserBySurname(@PathVariable("surname") String surname){
        userService.deleteUserBySurname(surname);
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


