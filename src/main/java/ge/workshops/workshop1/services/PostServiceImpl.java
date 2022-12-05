package ge.workshops.workshop1.services;

import ge.workshops.workshop1.entities.Post;
import ge.workshops.workshop1.entities.PostSearchParams;
import java.security.InvalidParameterException;
import ge.workshops.workshop1.exceptions.NotFoundException;
import ge.workshops.workshop1.repositories.PostRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAll (PostSearchParams searchParams)
    {
        return postRepository.findAll();
    }

    public Post add(Post post) {
        post.setId(null);
        return postRepository.save(post);
    }

    public Post update(int id, Post post) {
        var foundPost = getPost(id);
        foundPost.setTitle(post.getTitle());
        foundPost.setBody(post.getBody());
        foundPost.setUser_id(post.getUser_id());
        foundPost.setCreate_date(post.getCreate_date());
        return postRepository.save(foundPost);
    }

    public void delete(int id) {
        postRepository.deleteById(id);
    }

    public Post getPost(int id) {
        if (id < 1) {
            throw new InvalidParameterException("Id must be positive integer");
        }
        return  postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found"));
    }
}
