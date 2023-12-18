import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class MathControllerEndToEndTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDoMathMultiplication() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/doMath")
                        .content("{\"operand1\": 5, \"operand2\": 4, \"operation\": \"*\"}")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.calcResponse").value(20));
    }

    @Test
    public void testDoMathDivision() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/doMath")
                        .content("{\"operand1\": 8, \"operand2\": 2, \"operation\": \"/\"}")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.calcResponse").value(4));
    }

    @Test
    public void testDoMathAddition() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/doMath")
                        .content("{\"operand1\": 15, \"operand2\": 7, \"operation\": \"+\"}")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.calcResponse").value(22));
    }

    @Test
    public void testDoMathSubtraction() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/doMath")
                        .content("{\"operand1\": 10, \"operand2\": 3, \"operation\": \"-\"}")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.calcResponse").value(7));
    }

    @Test
    public void testDoMathMultipleOperations() throws Exception {
        // Test a sequence of operations: 5 * 4 / 2 + 3 = 13
        mockMvc.perform(MockMvcRequestBuilders.post("/doMath")
                        .content("{\"operand1\": 5, \"operand2\": 4, \"operation\": \"*\"}")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/doMath")
                        .content("{\"operand1\": 2, \"operand2\": 3, \"operation\": \"/\"}")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/doMath")
                        .content("{\"operand1\": 4, \"operand2\": 3, \"operation\": \"+\"}")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.calcResponse").value(13));
    }
}
