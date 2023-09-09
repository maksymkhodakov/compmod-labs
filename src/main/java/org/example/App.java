package org.example;

import java.util.function.Function;

import static java.lang.Math.*;

/**
 * Lab work
 */
public class App {

    public static double calculateApproximationForMiddleRectangular(double a,
                                                                    double b,
                                                                    double h,
                                                                    Function<Double, Double> function) {
        double stepX = h / 2;
        double x = a;
        double approximationSum = 0;
        int counter = 0;
        while (x <= b) {
            if (counter % 2 != 0) {
                approximationSum += function.apply(x);
            }
            x += stepX;
            counter++;
        }
        return h * approximationSum;
    }

    public static double calculateApproximationForRightRectangular(double a,
                                                                   double b,
                                                                   double h,
                                                                   Function<Double, Double> function) {
        double x = a;
        double approximationSum = 0;
        int counter = 0;
        while (x <= b) {
            if (counter == 0) {
                x += h;
            }
            approximationSum += function.apply(x);
            x += h;
            counter++;
        }
        return h * approximationSum;
    }

    public static void main(String[] args) {
        double a = 0;
        double b = 1;
        double h = 0.025;
        final Function<Double, Double> function = t -> (1 / t) * ((sin(-log(t))) / (2 + pow((-log(t)), 2))) * pow(E, (-pow(-log(t), 2)) / 2);
        final double division = pow(2, 4) - 1;
        final double EPSILON = 0.0001;

        System.out.println("############ Task 1 ############");
        System.out.println("Runge rule:");
        double delta;
        int iterationNumber = 1;
        double I_h_1;
        double I_h_2;
        do {
            I_h_1 = calculateApproximationForMiddleRectangular(a, b, h, function);
            I_h_2 = calculateApproximationForMiddleRectangular(a, b, h / 2, function);
            System.out.println("Task1 Iteration# " + iterationNumber + ": I_h_1: " + I_h_1);
            System.out.println("Task1 Iteration# " + iterationNumber + ": I_h_2: " + I_h_2);
            delta = abs(I_h_1 - I_h_2) / division;
            System.out.println("Delta: " + delta);
            h /= 2;
        } while (delta > EPSILON);
        System.out.println("Task1 Approximation: " + I_h_2);

        System.out.println();

        double a2 = 0.01;
        double b2 = 1;
        double h2 = 0.001;
        final Function<Double, Double> function2 = x -> 1 / sqrt(x * (1 + pow(x, 2)));
        final double division2 = pow(2, 1) - 1;
        final double EPSILON2 = 0.3;

        System.out.println("############ Task 2 ############");
        System.out.println("Runge rule:");
        double delta2;
        int iterationNumber2 = 1;
        double I_h_1_2;
        double I_h_2_2;
        do {
            I_h_1_2 = calculateApproximationForRightRectangular(a2, b2, h2, function2);
            I_h_2_2 = calculateApproximationForRightRectangular(a2, b2, h2 / 2, function2);
            System.out.println("Task2 Iteration# " + iterationNumber2 + ": I_h_1_2: " + I_h_1_2);
            System.out.println("Task2 Iteration# " + iterationNumber2 + ": I_h_2_2: " + I_h_2_2);
            delta2 = abs(I_h_1_2 - I_h_2_2) / division2;
            System.out.println("Delta2: " + delta2);
            h2 /= 2;
        } while (delta2 > EPSILON2);
        System.out.println("Task2 Approximation: " + I_h_2_2);
    }
}
