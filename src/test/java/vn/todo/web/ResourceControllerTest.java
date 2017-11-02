package vn.todo.web;

import org.junit.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static vn.todo.TestUtil.userAuth;
import static vn.todo.UserTestData.USER;

public class ResourceControllerTest extends AbstractControllerTest {

    @Test
    public void testResources() throws Exception {
        mockMvc.perform(get("/css/style.css")
                .with(userAuth(USER)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.valueOf("text/css")))
                .andExpect(status().isOk());
    }
}