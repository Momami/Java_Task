package Collections;

import java.util.LinkedList;
import java.util.Scanner;

public class AddInLinkedList {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите кол-во элементов для вставки: ");
        int n = scanner.nextInt();
        int i = 0;
        int where;
        while (i < n) {
            System.out.println("Куда вставить элемент:\n1 - Начало списка;\n2 - Конец списка");
            where = scanner.nextInt();
            System.out.print("Введите элемент: ");
            if (where == 1){
                list.addFirst(scanner.nextInt());
            }
            else if (where == 2){
                list.addLast(scanner.nextInt());
            }
            else{
                System.out.println("Неверный ввод! Элемент не вставлен!\n");
            }
            i++;
        }
        System.out.println(list);
    }
}
