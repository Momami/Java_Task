package PartOne;

import java.util.Scanner;


public class PartOneTaskThree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите координаты точки A: \nx0 = ");
        double xA = scanner.nextDouble();
        System.out.print("y0 = ");
        double yA = scanner.nextDouble();
        System.out.print("Введите координаты точки B: \nx1 = ");
        double xB = scanner.nextDouble();
        System.out.print("y1 = ");
        double yB = scanner.nextDouble();

        double lenA = Math.sqrt(xA * xA + yA * yA);
        double lenB = Math.sqrt(xB * xB + yB * yB);

        if (lenA > lenB){
            System.out.println("A дальше, чем B");
        }
        else if (lenB > lenA){
            System.out.println("B дальше, чем A");
        }
        else{
            System.out.println("A и B равноудалены");
        }

    }
}
