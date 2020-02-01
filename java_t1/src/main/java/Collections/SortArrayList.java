package Collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class SortArrayList {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите кол-во элементов: ");
        int n = scanner.nextInt();
        int i = 0;
        System.out.print("Введите элементы через enter: ");
        while (i < n){
            list.add(scanner.next());
            i++;
        }
        list.sort(Comparator.reverseOrder());
        System.out.println(list);
    }
}
