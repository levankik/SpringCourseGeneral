package ge.workshops.workshop1;

import com.fasterxml.jackson.databind.ObjectMapper;
import ge.workshops.workshop1.entity.Post;
import ge.workshops.workshop1.entity.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@SpringBootTest

class Workshop1ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    @Test
    @WithMockUser(value = "vano", authorities = {"POST_READ"})
    void testSearchingPosts () throws Exception {
        var user = new User("username", "password", "email");
        em.persistAndFlush(user);
        em.refresh(user);
        for (var i = 0; i < 20; i++) {
            var post = new Post("title", "body", user);
            em.persist(post);
        }
        em.flush();

        mockMvc.perform(get("/posts")
                .param("size", "5")
                .param("page", "0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.length()", equalTo(5)))
                .andExpect(jsonPath("$.first", is(true)))
                .andExpect(jsonPath("$.content[0].createDate", matchesPattern(".+")));
    }

    @Test
    @WithMockUser("vano")
    void testSearchingPosts_whith_no_authority () throws Exception {
        mockMvc.perform(get("/posts")
                        .param("size", "5")
                        .param("page", "0"))
                .andExpect(status().isForbidden());
    }

    @Test
    void testSearchingPosts_whith_no_user () throws Exception {
        mockMvc.perform(get("/posts")
                        .param("size", "5")
                        .param("page", "0"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(value = "vano", authorities = {"POST_READ", "POST_ADD"})
    void testAddingPosts () throws Exception {
                var user = new User("username", "password", "email");
                var post = new Post("title", "body", user);
                var body = objectMapper.writeValueAsString(post);
                mockMvc.perform(post("/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().exists("location"))
                .andExpect(jsonPath("$.id", not(nullValue())));

                Query query = em.getEntityManager().createQuery("select p from Post p");
                List <Post> posts = query.getResultList();
                Assertions.assertEquals(1, posts.size());
    }

    @Test
    @WithMockUser(value = "vano", authorities = {"POST_READ"})
    void testAddingPosts_with_no_post_add_authority () throws Exception {
        var user = new User("username", "password", "email");
        var post = new Post("title", "body", user);
        var body = objectMapper.writeValueAsString(post);
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isForbidden());
    }
}
