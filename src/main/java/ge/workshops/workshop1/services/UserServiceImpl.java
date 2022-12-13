package ge.workshops.workshop1.services;

import ge.workshops.workshop1.exceptions.NotFoundException;
import ge.workshops.workshop1.repository.UserRepository;
import ge.workshops.workshop1.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow (() -> new NotFoundException("User not found"));
    }

    @Override
    public User getUserByUserName(String username) {
        return  userRepository.findByUserName(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public User addUser(User user) {
        //user.setId(null); ვანოს აქ დაკომენტარებული არ აქვს.
        return userRepository.save(user);
    }
}
