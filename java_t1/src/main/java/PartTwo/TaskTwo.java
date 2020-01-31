package PartTwo;

import java.util.Scanner;

public class TaskTwo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите N: ");
        int n = scanner.nextInt();
        int i = 5;
        while (i <= n){
            System.out.print(i + " ");
            i += 5;
        }
    }
}
