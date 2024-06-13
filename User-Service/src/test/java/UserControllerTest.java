import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.ponte.UserServiceApplication;
import hu.ponte.dto.UserDTO;
import hu.ponte.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = UserServiceApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Any setup needed before each test
    }

    @Test
    public void testGetById() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L);
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");

        userService.save(userDTO);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andDo(print());
    }

    @Test
    public void testGetAllPersons() throws Exception {
        UserDTO user1 = new UserDTO();
        user1.setUserId(1L);
        user1.setFirstName("John");
        user1.setLastName("Doe");

        UserDTO user2 = new UserDTO();
        user2.setUserId(2L);
        user2.setFirstName("Jane");
        user2.setLastName("Doe");


        userService.save(user1);
        userService.save(user2);

        mockMvc.perform(get("/api/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(1L))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].userId").value(2L))
                .andExpect(jsonPath("$[1].name").value("Jane Doe"))
                .andDo(print());
    }

    @Test
    public void testCreateUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L);
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");

        mockMvc.perform(post("/api/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andDo(print());

        UserDTO createdUser = userService.findById(1L);
        assertNotNull(createdUser);
        assertEquals("John Doe", createdUser.getFirstName() + " " + createdUser.getLastName());
    }

    @Test
    public void testUpdatePerson() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L);
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userService.save(userDTO);

        userDTO.setFirstName("John");
        userDTO.setLastName("Smith");

        mockMvc.perform(put("/api/users/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Smith"))
                .andDo(print());

        UserDTO updatedUser = userService.findById(1L);
        assertNotNull(updatedUser);
        assertEquals("John Smith", updatedUser.getFirstName() + " " + updatedUser.getLastName());
    }

    @Test
    public void testDeletePerson() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L);
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");

        userService.save(userDTO);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk())
                .andDo(print());

        UserDTO deletedUser = userService.findById(1L);
        assertNull(deletedUser);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}