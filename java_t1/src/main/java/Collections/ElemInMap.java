package Collections;

import java.util.*;

public class ElemInMap {
    public static void main(String[] args) {
        Map<Integer, String> maps = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите кол-во элементов: ");
        int n = scanner.nextInt();
        int i = 0;
        System.out.print("Введите ключ и значение через пробел: ");
        while (i < n){
            maps.put(scanner.nextInt(), scanner.next());
            i++;
        }
        System.out.print("Введите интересующее значение: ");
        String val = scanner.next();
        System.out.println(maps.containsValue(val));
    }
}
