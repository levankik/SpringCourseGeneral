package ge.workshops.workshop1.services;

import ge.workshops.workshop1.dto.PostSearchParams;
import ge.workshops.workshop1.entity.Post;
import ge.workshops.workshop1.entity.User;
import ge.workshops.workshop1.exceptions.NotFoundException;
import ge.workshops.workshop1.repository.PostRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository,
                           UserService userService) {
        this.postRepository = postRepository;

        this.userService = userService;
    }

    @Override
    //@Transactional(readOnly = true)
    public Page<Post> getPosts(PostSearchParams params, Pageable pageable) {
        return postRepository.findAll( ((root, query, cb) -> {
            // 1 = 1
            Predicate predicate = cb.conjunction();
            // and id = :id
            if(params.getId() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("id"), params.getId()));
            }
            if(StringUtils.isNotEmpty(params.getTitle())) {
                // and title like '%:title%'
                predicate = cb.and(predicate, cb.like(root.get("title"), '%' + params.getTitle() + '%'));
            }
            if(StringUtils.isNotEmpty(params.getBody())) {
                // and body like '%:body%'
                predicate = cb.and(predicate, cb.like(root.get("body"), '%' + params.getBody() + '%'));
            }
            if(params.getCreateDateFrom() != null) {
                // and createDate like >= :createDate%
                var createDateFrom = params.getCreateDateFrom().atStartOfDay();
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("Post_"), createDateFrom));
            }
            if(params.getCreateDateTo() != null) {
                // and createDate like <= :createDate%
                var createDateTo = params.getCreateDateTo().atTime(23, 59, 59);
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("createDate"), createDateTo));
            }
            if(StringUtils.isNotEmpty(params.getUsername())) {
                Join<Post, User> user = root.join("user", JoinType.LEFT);
                predicate = cb.and(predicate, cb.like(user.get("username"), params.getUsername() + '%'));
            }
            return  predicate;
        }), pageable);
    }

    @Override
    public Post getPost(int id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found"));
    }

    @Override
    public  List<Post> getPostsById(int userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Post addPost(Post post) {
        post.setId(null);
        if(post.getUser().getId() != 0) {
            System.out.println("creating new user: " + post.getUser().getUserName());
            userService.addUser(post.getUser());
        }
        userService.addUser(post.getUser());
        return postRepository.save(post);
    }

    @Override
    @Async
    public void  startProcessing() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("processing finished");
    }

    @Scheduled(initialDelay = 30, fixedRate = 10_000_00)
    public void scheduled() {
        System.out.println("it should be logged in every 10000 seconds");
    }
}
