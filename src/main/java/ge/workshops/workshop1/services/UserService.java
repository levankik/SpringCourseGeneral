package ge.workshops.workshop1.services;

import ge.workshops.workshop1.entity.User;

import java.util.List;


public interface UserService {
    List<User> getUsers();
    User getUserById(int id);
    User getUserByUserName(String userName);// UserServiceImpl-ში არის ამ სახელის მეთOდი
    User  addUser(User user);
}
