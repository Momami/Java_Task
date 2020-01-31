package PartFour;

import java.util.Scanner;

public class TaskSeven {
    public static void main(String[] args) {
        String[] mas = new String[] {"ноль", "один", "два", "три", "четыре",
                              "пять", "шесть", "семь", "восемь", "девять"};
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число от 0 до 9: ");
        int n = scanner.nextInt();
        if (n < 0 || n > 9){
            System.out.println("Неверно введено число!");
        }
        else{
            System.out.println(mas[n]);
        }
    }
}
