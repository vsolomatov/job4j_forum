package forum.control;

import forum.Main;
import forum.model.Comment;
import forum.model.Post;
import forum.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class CommentControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    @WithMockUser
    public void shouldReturnCommentsPage() throws Exception {
        Mockito.doReturn(Mockito.mock(Post.class)).when(postService).findByIdPost(1);
        this.mockMvc.perform(get("/comments").param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/post"));
    }

    @Test
    @WithMockUser
    public void shouldReturnAddPage() throws Exception {
        Mockito.doReturn(Mockito.mock(Post.class)).when(postService).findByIdPost(1);
        this.mockMvc.perform(get("/comments/add").param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("comment/edit"));
    }

}