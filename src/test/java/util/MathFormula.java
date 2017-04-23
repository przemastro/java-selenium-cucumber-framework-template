package util;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import org.openqa.selenium.WebElement;

import java.math.RoundingMode;
import java.text.DecimalFormat;


/**
 * Created by Przemek on 23.04.2017.
 */
public class MathFormula {

    public double odd;
    public double result;

    /**
     * usage of shunting-yard algorithm to "read" math formula. We take data_num/data_denom attributes
     */
    public String singleBetFormula(Double data_num, Double data_denom, String toReturn, Double bet) {
        odd = data_num/data_denom;
        try {
            Calculable calc = new ExpressionBuilder(toReturn)
                    .withVariable("odd", odd)
                    .withVariable("bet", bet)
                    .build();
            result=calc.calculate();
        }
        catch (UnparsableExpressionException e) {
            System.out.println(e);
        }
        catch (UnknownFunctionException e) {
            System.out.println(e);
        }
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);
        return df.format(result).replace(',','.');
    }
}
