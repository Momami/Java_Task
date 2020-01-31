package PartTwo;

import java.util.Scanner;

public class TaskOne {
    private static int sumNumber(int number){
        int sum = 0;
        while(number > 0){
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите максимальное число: ");
        int n = scanner.nextInt();
        int i = 3;
        while (i <= n){
            if (i % 5 != 0 && sumNumber(i) % 5 != 0){
                System.out.print(i + " ");
            }
            i += 3;
        }
    }
}
