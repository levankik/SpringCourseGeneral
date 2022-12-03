package ge.workshops.workshop1.services;



import ge.workshops.workshop1.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    //CustomerSearchParams searchParams
    User add(User user);
    User getUser(int id);
    User update(int id, User user);
    void delete(int id);
    User getUserPosts(int id);
}


