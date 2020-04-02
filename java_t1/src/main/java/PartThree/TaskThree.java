package PartThree;

import java.util.Scanner;

public class TaskThree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите A: ");
        int A = scanner.nextInt();
        System.out.print("Введите целое число N (N > 0): ");
        int N = scanner.nextInt();
        if (N <= 0){
            System.out.println("N <= 0. Ошибка!");
            return;
        }
        int res = 1;
        for(int i = 1; i <= N; i++){
            res *= A;
        }
        System.out.println(A + " в степени " + N + " = " + res);
    }
}
