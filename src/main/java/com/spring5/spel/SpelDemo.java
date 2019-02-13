package com.spring5.spel;

import com.spring5.entity.Inventor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.GregorianCalendar;

/**
 * 详细用法见https://docs.spring.io/spring/docs/5.1.4.RELEASE/spring-framework-reference/core.html#expressions-language-ref
 */
public class SpelDemo {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

// invokes 'getBytes().length'
        Expression exp = parser.parseExpression("'Hello World'.bytes.length");

        System.out.println(exp.getValue(Integer.class));
        Expression exp1 = parser.parseExpression("new String('hello world').toUpperCase()");
        System.out.println(exp1.getValue(String.class));
        complexDemo();
    }

    private static void complexDemo() {
        // Create and set a calendar
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);

// The constructor arguments are name, birthday, and nationality.
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

        ExpressionParser parser = new SpelExpressionParser();

        Expression exp = parser.parseExpression("name");
//        相当于tesla.getName();
        String name = (String) exp.getValue(tesla);
        System.out.println(name);
        //相当于 tesla.getName() == "Nikola Tesla"
        exp = parser.parseExpression("name == 'Nikola Tesla'");
        boolean result = exp.getValue(tesla, Boolean.class);
        System.out.println(result);
    }
}
