package Collections;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class PhoneDirectory {
    public static void main(String[] args) {
        ArrayList<PhoneInfo> phoneDir = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int exit = 1;
        while(exit != 0){
            System.out.println("Выберите действие:\n1 - Добавить абонента\n2 - Удалить абонента" +
                    "\n3 - Изменить номер абонента\n4 - Изменить город абонента\n" +
                    "5 - Вывести справочник\n" +
                    "0 - Выход");
            exit = scanner.nextInt();
            Scanner sc = new Scanner(System.in);
            switch (exit){
                case 1:
                {

                    System.out.println("Введите имя абонента: ");
                    String name = sc.nextLine();
                    System.out.println("Введите номер: ");
                    String number = sc.nextLine();
                    System.out.println("Введите фамилию абонента: ");
                    String lastName = sc.nextLine();
                    System.out.println("Введите город: ");
                    String city = sc.nextLine();
                    phoneDir.add(new PhoneInfo(name, number, city, lastName));
                    break;
                }
                case 2:
                {
                    System.out.println("Введите имя абонента: ");
                    String name = sc.nextLine();
                    phoneDir.removeIf(info -> info.getName().equals(name));
                    break;
                }
                case 3:
                {
                    System.out.println("Введите имя абонента: ");
                    String name = sc.nextLine();
                    System.out.println("Введите новый номер: ");
                    String number = sc.nextLine();
                    Optional<PhoneInfo> el = phoneDir.stream().filter(info -> info.getName().equals(name)).findFirst();
                    if(el.isPresent()){
                        el.get().setNumberPhone(number);
                    }
                    else{
                        System.out.println("Нет такого абонента!\n");
                    }
                    break;
                }
                case 4:
                {
                    System.out.println("Введите имя абонента: ");
                    String name = sc.nextLine();
                    System.out.println("Введите новый город: ");
                    String city = sc.nextLine();
                    Optional<PhoneInfo> el = phoneDir.stream().filter(info -> info.getName().equals(name)).findFirst();
                    if(el.isPresent()){
                        el.get().setCity(city);
                    }
                    else{
                        System.out.println("Нет такого абонента!\n");
                    }
                    break;
                }
                case 5:
                    if (phoneDir.isEmpty())
                        System.out.println("Справочник пуст!\n");
                    else
                        phoneDir.forEach(System.out::println);
                default: break;
            }
        }
    }
}
