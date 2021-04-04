package basics.quadratic_equation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class QuadraticEquation {

    public static void main(String[] args) throws IOException {
        System.out.print("Input coefficients: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        double[] numbers =  Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();

        if(numbers.length == 3) {

            double d = Discriminant.calculate(numbers[0], numbers[1], numbers[2]);

            Double x1 = null;
            Double x2 = null;

            // a*x^2 + b*x + c = 0
            if (numbers[0] == 0) { // a = 0
                if (numbers[1] == 0) // a = 0, b = 0
                    System.out.println("No solutions");
                else // a = 0, b != 0
                    x1 = x2 = (-numbers[2]) / numbers[1];
            } else if (d > 0) {
                x1 = (Math.sqrt(d) - numbers[1]) / (2 * numbers[0]);
                x2 = (-Math.sqrt(d) - numbers[1]) / (2 * numbers[0]);
            } else if (d == 0) {
                x1 = x2 = (-numbers[1]) / (2 * numbers[0]);
            } else {
                System.out.println("No solutions");
            }
            if(x1 != null) {
                System.out.println("Solutions:" + '\n' + "x1 = " + x1 + '\n' + "x2 = " + x2);
            }
        }
        else
            System.out.println("Incorrect input");
    }

    private static class Discriminant {

        private static double calculate(double a, double b, double c) {
            return b*b - 4*a*c;
        }
    }
}
