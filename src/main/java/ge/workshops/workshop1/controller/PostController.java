package ge.workshops.workshop1.controller;

import ge.workshops.workshop1.services.PostService;
import ge.workshops.workshop1.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public Page<Post>  getPosts(@RequestParam(required = false, defaultValue = "0") int page,
                               @RequestParam(required = false, defaultValue = "10") int size,
                               @RequestParam(required = false, defaultValue = "DESC") Sort.Direction direction,
                               @RequestParam(required = false, defaultValue = "id") String field) {
        Sort sorter = Sort.by(direction, field);
        return postService.getPosts(PageRequest.of(page, size, sorter));
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable int id) {
        return postService.getPost(id);
    }

    @PostMapping
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        postService.addPost(post);
        var location = UriComponentsBuilder.fromPath("/posts/" + post.getId()).build().toUri();
        return ResponseEntity.created(location).body(post);

    }
}
