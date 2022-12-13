package ge.workshops.workshop1.services;

import ge.workshops.workshop1.repository.PostRepository;
import ge.workshops.workshop1.entity.Post;
import ge.workshops.workshop1.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;

        this.userService = userService;
    }

    @Override
    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post getPost(int id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found"));
    }

    @Override
    public List<Post> getPostsById(int userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public Post addPost(Post post) {
        post.setId(null);
        if(post.getUser().getId() != 0) {
            userService.addUser(post.getUser());
        }
        userService.addUser(post.getUser());
        return postRepository.save(post);

    }
}
