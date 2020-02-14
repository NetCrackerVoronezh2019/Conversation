package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.DTO.UserDTO;
import ru.domen.User;
import ru.repos.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDTO getUserDTOById(Integer id){
        return UserDTO.getUserDTO(userRepository.findById(id).get());
    }

    @Transactional
    public User getUserById(Integer id){
        return userRepository.findById(id).get();
    }

    @Transactional
    public void addUser(User dialog) {
        userRepository.save(dialog);
    }

    @Transactional
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }
}
