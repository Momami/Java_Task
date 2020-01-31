package PartThree;

import java.util.Scanner;

public class TaskFour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите A: ");
        int A = scanner.nextInt();
        System.out.print("Введите целое число N: ");
        int N = scanner.nextInt();
        int res = 1;
        for(int i = 1; i <= N; i++){
            res *= A;
            System.out.println(A + " в степени " + i  + " = " + res);
        }

    }
}
