public interface MathOperator {
    double doMath(double operand1, double operand2, String operation) throws InvalidOperationException;
}

public class MathOperatorImpl implements MathOperator {

    @Override
    public double doMath(double operand1, double operand2, String operation) throws InvalidOperationException {
        switch (operation) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new InvalidOperationException("Division by zero is not allowed.");
                }
                return operand1 / operand2;
            default:
                throw new InvalidOperationException("Unsupported operation: " + operation);
        }
    }
}
