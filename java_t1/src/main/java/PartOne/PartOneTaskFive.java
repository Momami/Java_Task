package PartOne;

import java.util.Scanner;

public class PartOneTaskFive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число a: ");
        double a = scanner.nextDouble();
        System.out.print("Введите число b: ");
        double b = scanner.nextDouble();
        System.out.print("Введите число c: ");
        double c = scanner.nextDouble();
        System.out.println(a > 0 ? a * a : a);
        System.out.println(b > 0 ? b * b : b);
        System.out.println(c > 0 ? c * c : c);
    }
}
