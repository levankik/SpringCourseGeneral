package ge.workshops.workshop1.controllers;

import ge.workshops.workshop1.entities.Post;
import ge.workshops.workshop1.entities.PostSearchParams;
import ge.workshops.workshop1.services.PostService;
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

    @GetMapping()
    public List<Post> getAll(PostSearchParams searchParams)
    {
        return postService.getAll(searchParams);
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable int id)
    {
        return postService.getPost(id);
    }

    @PostMapping()
    public ResponseEntity<Post> add(@RequestBody Post post) {
        postService.add(post);
        var location = UriComponentsBuilder.fromPath("/post/" + post.getId()).build().toUri();
        return ResponseEntity.created(location).body(post);
    }

    @PutMapping("/{id}")
    public Post update(@RequestBody Post post, @PathVariable int id) {

        return postService.update(id, post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> delete(@PathVariable int id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
