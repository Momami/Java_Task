package Collections;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class FindElemInArray {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите кол-во элементов: ");
        int n = scanner.nextInt();
        int i = 0;
        System.out.print("Введите элементы через enter: ");
        while (i < n){
            list.add(scanner.next());
            i++;
        }
        System.out.println("Введите элемент для поиска: ");
        String elemFind = scanner.next();
        Optional<String> elem = list.stream().filter(el -> el.equals(elemFind)).findFirst();
        System.out.println(elem.orElse("Не найдено!"));
    }
}
