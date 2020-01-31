package PartFour;

import java.util.Scanner;

public class TaskFive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов массива: ");
        int n = scanner.nextInt();
        int[] mas = new int[n];
        System.out.println("Введите элементы массива: ");
        for (int i = 0; i < n; i++){
            mas[i] = scanner.nextInt();
        }
        System.out.println("Простые числа: ");
        for (int i = 0; i < n; i++){
            int tmp = mas[i] / 2;
            for (int j = 2; j <= tmp; j++) {
                if (mas[i] % j == 0) {
                    mas[i] = 0;
                    break;
                }
            }
            if(mas[i] != 0){
                System.out.println(mas[i] + " ");
            }
        }
    }
}
