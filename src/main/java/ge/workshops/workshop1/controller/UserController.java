package ge.workshops.workshop1.controller;

import ge.workshops.workshop1.config.SecUser;
import ge.workshops.workshop1.entity.Post;
import ge.workshops.workshop1.entity.User;
import ge.workshops.workshop1.services.PostService;
import ge.workshops.workshop1.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PrePersist;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER_READ')")
public class UserController {
    private final UserService userService;
    private final PostService postService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("")
    public List<User> getUsers() { return userService.getUsers();}

    @GetMapping("/current")
    public SecUser getCurrentUser(@AuthenticationPrincipal SecUser secUser) {
        return secUser;
    }

    @GetMapping("/{id}")
    public User  getUser(@PathVariable int id)  {
        System.out.println(passwordEncoder.encode("nata"));
        return userService.getUserById(id);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('USER_ADD')")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{id}/posts")
    public List<Post> getUserPosts(@PathVariable int id) {
        return postService.getPostsById(id);
    }
}


