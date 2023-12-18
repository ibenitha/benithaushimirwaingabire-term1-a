import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.client.TestRestTemplateBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testDoMathMultiplication() {
        DoMathRequest request = new DoMathRequest(5, 4, "*");
        MathResponse response = restTemplate.postForObject("/doMath", request, MathResponse.class);
        assertEquals(20, response.getCalcResponse());
    }

    @Test
    public void testDoMathDivision() {
        DoMathRequest request = new DoMathRequest(8, 2, "/");
        MathResponse response = restTemplate.postForObject("/doMath", request, MathResponse.class);
        assertEquals(4, response.getCalcResponse());
    }

    @Test
    public void testDoMathAddition() {
        DoMathRequest request = new DoMathRequest(15, 7, "+");
        MathResponse response = restTemplate.postForObject("/doMath", request, MathResponse.class);
        assertEquals(22, response.getCalcResponse());
    }

    @Test
    public void testDoMathSubtraction() {
        DoMathRequest request = new DoMathRequest(10, 3, "-");
        MathResponse response = restTemplate.postForObject("/doMath", request, MathResponse.class);
        assertEquals(7, response.getCalcResponse());
    }

    @Test
    public void testDoMathMultipleOperations() {
        // Test a sequence of operations: 5 * 4 / 2 + 3 = 13
        DoMathRequest request1 = new DoMathRequest(5, 4, "*");
        DoMathRequest request2 = new DoMathRequest(2, 3, "/");
        DoMathRequest request3 = new DoMathRequest(request2.getCalcResponse(), 3, "+");

        restTemplate.postForObject("/doMath", request1, MathResponse.class);
        restTemplate.postForObject("/doMath", request2, MathResponse.class);
        MathResponse response = restTemplate.postForObject("/doMath", request3, MathResponse.class);

        assertEquals(13, response.getCalcResponse());
    }

}
