package PartTwo;

import java.util.Scanner;

public class TaskFour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите N: ");
        int n = scanner.nextInt();
        int f1 = 1, f2 = 1;
        System.out.print(f1 + " ");
        while(f2 <= n){
            int f = f1;
            f1 = f2;
            f2 += f;
            System.out.print(f1 + " ");
        }
    }
}
