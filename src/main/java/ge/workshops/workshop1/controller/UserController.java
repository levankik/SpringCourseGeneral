package ge.workshops.workshop1.controller;

import ge.workshops.workshop1.services.PostService;
import ge.workshops.workshop1.services.UserService;
import ge.workshops.workshop1.entity.Post;
import ge.workshops.workshop1.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PostService postService;

    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("")
    public List<User> getUsers() { return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User  getUser(@PathVariable int id) throws InterruptedException {
        var user = userService.getUserById(id);
        Thread.sleep(5000);
        var posts = user.getPosts();
        System.out.println(posts.size());
        return user;
    }

    @GetMapping("/{id}/posts")
    public List<Post> getUserPosts(@PathVariable int id) {
        return postService.getPostsById(id);
    }
}


