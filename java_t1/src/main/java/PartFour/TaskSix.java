package PartFour;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TaskSix {
    private static int sumSquare(int number){
        int res = 0;
        while(number > 0){
            int tmp = number % 10;
            res += (tmp * tmp);
            number /= 10;
        }
        return res;
    }

    private static boolean isHappyNumber(int number){
        Set<Integer> seed = new HashSet<Integer>();
        while(number > 1 && !seed.contains(number)){
            seed.add(number);
            number = sumSquare(number);
        }
        return number == 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов массива: ");
        int n = scanner.nextInt();
        int[] mas = new int[n];
        System.out.println("Введите элементы массива: ");
        for (int i = 0; i < n; i++){
            mas[i] = scanner.nextInt();
        }
        System.out.println("Счастливые числа: ");
        for (int i = 0; i < n; i++){
            if (isHappyNumber(mas[i])){
                System.out.print(mas[i] + " ");
            }
        }
    }
}
