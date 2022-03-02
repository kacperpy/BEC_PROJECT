package dk.bec.bookanything.controller;


import dk.bec.bookanything.dto.UserCreateDto;
import dk.bec.bookanything.mapper.UserMapper;
import dk.bec.bookanything.model.UserEntity;
import dk.bec.bookanything.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

    //TODO responses

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
    public ResponseEntity<UserCreateDto> addUser(@Valid @RequestBody UserCreateDto userCreateDto){
        try {
            userService.addUser(userMapper.mapUserCreateDtoToUserEntity(userCreateDto, null));
            return new ResponseEntity<>(userCreateDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserCreateDto> updateUser(@PathVariable("id") Long id,@Valid @RequestBody UserCreateDto userCreateDto){
        try {
            userService.updateUser(id, userMapper.mapUserCreateDtoToUserEntity(userCreateDto, id));
            return new ResponseEntity<>(userCreateDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}