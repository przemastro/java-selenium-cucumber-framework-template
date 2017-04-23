package util;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

import java.text.DecimalFormat;

/**
 * Created by Przemek on 23.04.2017.
 */
public class MathFormula {

    public double odd;
    public double result;

    public String singleBetFormula(Double data_num, Double data_denom, String toReturn, Double bet) {
        odd = data_num/data_denom;
        System.out.println(toReturn);
        System.out.println(odd);
        System.out.println(bet);
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

        System.out.println(result);
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println(df.format(result));
        return df.format(result);
    }
}
