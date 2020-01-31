package PartOne;

import java.util.Scanner;

public class PartOneTaskFour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите a: ");
        double a = scanner.nextDouble();
        System.out.print("Введите b: ");
        double b = scanner.nextDouble();
        System.out.print("Введите c: ");
        double c = scanner.nextDouble();
        boolean isQuadTriangle = false;
        if (a > b && a > c){
            System.out.println(b * b + c * c == a * a);
        }
        else if (b > a && b > c) {
            System.out.println(a * a + c * c == b * b);
        }
        else{
            System.out.println(b * b + a * a == c * c);
        }
    }
}
