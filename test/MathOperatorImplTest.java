import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MathOperatorImplTest {

    @Mock
    private InvalidOperationException mockException;

    @InjectMocks
    private MathOperatorImpl mathOperator;

    @Test
    public void testDoMathMultiplication() throws InvalidOperationException {
        double result = mathOperator.doMath(2, 3, "*");
        assertEquals(6, result);
    }

    @Test
    public void testDoMathDivision() throws InvalidOperationException {
        double result = mathOperator.doMath(6, 2, "/");
        assertEquals(3, result);
    }

    @Test
    public void testDoMathAddition() throws InvalidOperationException {
        double result = mathOperator.doMath(4, 7, "+");
        assertEquals(11, result);
    }

    @Test
    public void testDoMathSubtraction() throws InvalidOperationException {
        double result = mathOperator.doMath(10, 3, "-");
        assertEquals(7, result);
    }

    @Test
    public void testDoMathUnknownOperation() {
        assertThrows(RuntimeException.class, () -> mathOperator.doMath(2, 3, "%"));
    }

    @Test
    public void testDoMathDivisionByZero() {
        when(mockException.getMessage()).thenReturn("Cannot divide by 0");
        assertThrows(InvalidOperationException.class, () -> mathOperator.doMath(5, 0, "/"), mockException.getMessage());
    }
}
