package ge.workshops.workshop1.services;

import ge.workshops.workshop1.entities.User;
import ge.workshops.workshop1.entities.UserSearchParams;
import ge.workshops.workshop1.exceptions.NotFoundException;
import ge.workshops.workshop1.repositories.UserRepository;
import java.security.InvalidParameterException;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll (UserSearchParams searchParams) {
        return userRepository.findAll();
    }

    public User add(User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    public User update(int id, User user) {
        var foundUser = getUser(id);
        foundUser.setUsername(user.getUsername());
        foundUser.setPassword(user.getPassword());
        foundUser.setEmail(user.getEmail());
        foundUser.setCreate_date(user.getCreate_date());
        foundUser.setActive(user.getActive());
        return userRepository.save(foundUser);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public User getUser(int id) {
        if (id < 1) {
            throw new InvalidParameterException("Id must be positive integer");
        }
        return  userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

    }

}
