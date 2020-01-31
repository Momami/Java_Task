package PartThree;

import java.util.Scanner;

public class TaskTwo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите A: ");
        int A = scanner.nextInt();
        System.out.print("Введите B: ");
        int B = scanner.nextInt();
        for(int i = A + 1; i < B; i++){
            System.out.print(i + " ");
        }
        System.out.println("\nКоличество: " + (B - A - 1));
    }
}
