import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import lombok.Data;
@RestController
public class MathController {

    private final MathOperator mathOperator;

    @Autowired
    public MathController(MathOperator mathOperator) {
        this.mathOperator = mathOperator;
    }

    @PostMapping("/doMath")
    public MathResponse doMath(@RequestBody DoMathRequest request) {
        double result = mathOperator.doMath(request.getOperand1(), request.getOperand2(), request.getOperation());
        return new MathResponse(result);
    }
}
