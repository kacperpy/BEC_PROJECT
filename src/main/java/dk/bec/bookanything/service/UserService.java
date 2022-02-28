package dk.bec.bookanything.service;

import dk.bec.bookanything.model.UserEntity;
import dk.bec.bookanything.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.Optional;

@Service
@ApplicationScope
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserEntity addUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long id){
        return userRepository.findUserById(id);
    }

    public UserEntity getUserByName(String name){
        return userRepository.findUserByName(name);
    }

    public UserEntity getUserBySurname(String surname){
        return userRepository.findUserBySurname(surname);
    }

    public void deleteUser(UserEntity userEntity){
        userRepository.delete(userEntity);
    }

    public void deleteUserById(Long id){
        Optional<UserEntity> userEntityOptional = Optional.ofNullable(getUserById(id));
        userEntityOptional.ifPresent(userEntity1 -> userRepository.delete(userEntity1));
    }

    public void deleteUserByName(String name){
        Optional<UserEntity> userEntityOptional = Optional.ofNullable(getUserByName(name));
        userEntityOptional.ifPresent(userEntity1 -> userRepository.delete(userEntity1));
    }

    public void deleteUserBySurname(String surname){
        Optional<UserEntity> userEntityOptional = Optional.ofNullable(getUserBySurname(surname));
        userEntityOptional.ifPresent(userEntity1 -> userRepository.delete(userEntity1));
    }

    public void updateUser(Long id, UserEntity userEntity){
        Optional<UserEntity> userEntityOptional = Optional.ofNullable(getUserById(id));
        userEntityOptional.ifPresent(userEntity1 -> userRepository.save(userEntity1));
    }
}
