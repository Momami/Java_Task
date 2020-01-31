package PartOne;

import java.util.Scanner;

public class PartOneTaskOne {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите a = ");
        double a = scanner.nextDouble();
        System.out.print("Введите b = ");
        double b = scanner.nextDouble();
        double sumQuad = a * a + b * b;
        double quadSum = (a + b) * (a + b);
        if (sumQuad > quadSum){
            System.out.println("Сумма квадратов больше квадрата суммы.");
        }
        else if (quadSum > sumQuad){
            System.out.println("Квадрат суммы больше суммы квадратов.");
        }
        else{
            System.out.println("Они равны.");
        }
    }
}
