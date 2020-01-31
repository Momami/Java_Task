package PartTwo;

import java.util.Scanner;

public class TaskThree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите N: ");
        int n = scanner.nextInt();
        while (n % 2 == 0 && n > 2){
            n /= 2;
        }
        if (n == 2 || n == 1){
            System.out.println("Да");
        }
        else{
            System.out.println("Нет");
        }
    }
}
