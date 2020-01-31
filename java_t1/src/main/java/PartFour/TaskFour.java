package PartFour;

import java.util.Scanner;

public class TaskFour {
    private static int gcd(int a, int b)
    {
        while (a != 0 && b != 0)
            if (a >= b)
                a %= b;
            else
                b %= a;
        return a | b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов массива: ");
        int n = scanner.nextInt();
        int[] mas = new int[n];
        System.out.println("Введите элементы массива: ");
        for (int i = 0; i < n; i++) {
            mas[i] = scanner.nextInt();
        }
        int lcm = mas[0], gcd_v = mas[0];
        for (int i = 1; i < n; i++) {
            gcd_v = gcd(mas[i], gcd_v);
            int gcd_tmp = gcd(mas[i], lcm);
            lcm = Math.abs(lcm * mas[i]) / gcd_tmp;
        }
        System.out.println("НОД: " + gcd_v);
        System.out.println("НОК: " + lcm);
    }
}
