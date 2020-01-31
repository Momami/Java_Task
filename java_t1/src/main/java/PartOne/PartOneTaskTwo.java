package PartOne;

import java.util.Scanner;

public class PartOneTaskTwo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите зарплату: ");
        double a = scanner.nextDouble();
        System.out.print("Введите стаж: ");
        double b = scanner.nextDouble();
        double procent = 0;
        if (b >= 2 && b < 5){
            procent = 0.02;
        }
        else if (b >= 5 && b <= 10) {
            procent = 0.05;
        }
        double plus = a * procent;
        System.out.println("Надбавка: " + plus);
        System.out.println("Сумма к выплате: " + (plus + a));
    }
}
