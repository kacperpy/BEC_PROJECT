package dk.bec.bookanything.controller;


import dk.bec.bookanything.dto.UserCreateDto;
import dk.bec.bookanything.mapper.UserMapper;
import dk.bec.bookanything.model.UserEntity;
import dk.bec.bookanything.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @SuppressWarnings("SameReturnValue")
    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model) {
        UserCreateDto userDto = new UserCreateDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @GetMapping("/")
    public List<UserEntity> users() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/")
    public ResponseEntity<UserCreateDto> addUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        try {
            userService.addUser(userMapper.mapUserCreateDtoToUserEntity(userCreateDto, null));
            return new ResponseEntity<>(userCreateDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserCreateDto> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserCreateDto userCreateDto) {
        try {
            userService.updateUser(id, userMapper.mapUserCreateDtoToUserEntity(userCreateDto, id));
            return new ResponseEntity<>(userCreateDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}