package Collections;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class SumArray {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите кол-во элементов: ");
        int n = scanner.nextInt();
        int i = 0;
        System.out.print("Введите элементы через enter: ");
        while (i < n){
            list.add(scanner.nextInt());
            i++;
        }
        Optional<Integer> sum = list.stream().reduce(Integer::sum);

        sum.ifPresent(System.out::println);
    }
}
