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

    private final UserRepository userRepository;

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

    public Optional<UserEntity> getUserById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUserById(Long id){
        Optional<UserEntity> userEntityOptional = getUserById(id);
        userEntityOptional.ifPresent(userRepository::delete);
    }

    public void updateUser(Long id, UserEntity userEntity){
        Optional<UserEntity> userEntityOptional = getUserById(id);
        userEntityOptional.ifPresent(userEntity1 -> userRepository.save(userEntity));
    }
}
