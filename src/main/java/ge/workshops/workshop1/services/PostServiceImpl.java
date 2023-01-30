package ge.workshops.workshop1.services;

import ge.workshops.workshop1.config.JSONPlaceholderProperties;
import ge.workshops.workshop1.dto.PostJP;
import ge.workshops.workshop1.dto.PostSearchParams;
import ge.workshops.workshop1.entity.Post;
import ge.workshops.workshop1.entity.User;
import ge.workshops.workshop1.exceptions.NotFoundException;
import ge.workshops.workshop1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final RestTemplateBuilder restTemplateBuilder;
    private final JSONPlaceholderProperties jsonPlaceholderProperties;

    @Value("${jsonplaceholder.host}")
    private String jsonPlaceholderHost;

    //@Value("${server.port}")
    //private String serverPort;

    //@Value("${my.custom.property}")
   // private String customProperty;


    @Value("${jsonplaceholder.username")
    private String username;


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
            log.debug("creating new user: " + post.getUser().getUserName());
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

    @Override
    public void fill() {
        List<PostJP> posts = fetchPostsFromJsonPlaceHolder();
        for (var postJP : posts) {
            var postEntity = new Post(postJP);
            var user = new User("test", "passowrd","test@gmail.com");
            postEntity.setUser(user);
            addPost(postEntity);
        }
    }

    public List<PostJP> fetchPostsFromJsonPlaceHolder() {
        var restTemplate = restTemplateBuilder.build();
        var url = UriComponentsBuilder
                .fromHttpUrl(jsonPlaceholderProperties.getHost())
                .pathSegment("posts")
                .build().toUri();
        var response =  restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PostJP>>() {});
        return response.getBody();
    }

    @Scheduled(initialDelay = 30, fixedRate = 10_000_00)
    public void scheduled() {
        System.out.println("it should be logged in every 10000 seconds");
    }
}
