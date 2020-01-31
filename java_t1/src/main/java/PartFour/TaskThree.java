package PartFour;

import java.util.Scanner;

public class TaskThree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов массива: ");
        int n = scanner.nextInt();
        int[] mas = new int[n];
        System.out.println("Введите элементы массива: ");
        for (int i = 0; i < n; i++){
            mas[i] = scanner.nextInt();
        }
        System.out.println("Числа, которые делятся на 5 или на 10: ");
        for (int i = 0; i < n; i++){
            if (mas[i] % 5 == 0) {
                System.out.print(mas[i] + " ");
            }
        }
    }
}
