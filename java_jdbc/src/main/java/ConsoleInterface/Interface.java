package ConsoleInterface;

import Classes.Account;
import Classes.Operation;
import Classes.User;
import LogicWork.LogicWork;
import Managers.AccountManager;
import Managers.OperationManager;
import Managers.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class Interface {
    private static BufferedReader reader;

    // Ввод валюты
    private static String inputCurrency() throws IOException {
        String[] currency = {"RUB", "USD", "EUR"};
        System.out.println("Выберите валюту:\n0 - RUB\n1 - USD\n2 - EUR");
        int index = Integer.parseInt(reader.readLine());
        while (index < 0 || index > 2){
            System.out.println("Неверный ввод, повторите попытку!");
            System.out.println("Выберите валюту:\n0 - RUB\n1 - USD\n2 - EUR");
            index = Integer.parseInt(reader.readLine());
        }
        return currency[index];
    }

    // Интерфейс создания пользователя
    private static void createUserInterface() throws IOException, SQLException {
        System.out.print("Введите логин: ");
        String login = reader.readLine();
        System.out.print("Введите пароль: ");
        String password = reader.readLine();
        System.out.print("Введите номер телефона: ");
        String phone = reader.readLine();
        System.out.print("Введите адрес: ");
        String address = reader.readLine();
        if (UserManager.checkNoUserInDb(login, phone)){
            LogicWork.createUser(login, password, address, phone);
            System.out.println("Пользователь успешно создан!");
        }
        else{
            System.out.println("Пользователь с таким логином и/или телефоном уже зарегистрирован!");
        }
    }

    // Интерфейс создания аккаунта
    private static void createAccount(User user) throws IOException, SQLException {
        String currency = inputCurrency();
        if (AccountManager.checkNoAccCurrency(user.getId(), currency)){
            System.out.println("Сделать этот счет основным (y/n)?");
            boolean defAcc = reader.readLine().toLowerCase().equals("y");
            LogicWork.createAccount(user.getId(), currency, defAcc);
            System.out.println("Аккаунт успешно создан!");
        }
        else{
            System.out.println("Аккаунт с такой валютой уже зарегистрирован!");
        }
    }

    // Пополнение счета пользователя
    private static void addMoneyOnAccount(User user) throws SQLException, IOException {
        List<Account> accList = AccountManager.selectAccountUsers(user.getId());
        if (accList.isEmpty()){
            System.out.println("У Вас еще нет ни одного счёта для пополнения!");
            return;
        }
        System.out.println("Выберите счет для пополнения: ");
        for(int i = 0; i < accList.size(); i++){
            System.out.println(i + ". Валюта: " + accList.get(i).getAccCode() + "; Накопления: " +
                    accList.get(i).getAmount().floatValue());
        }
        int account = Integer.parseInt(reader.readLine());
        while (account < 0 || account >= accList.size()){
            System.out.println("Нет такого счета! Пожалуйста, повторите ввод!");
            account = Integer.parseInt(reader.readLine());
        }
        String cur = inputCurrency();
        System.out.print("Введите сумму для пополнения: ");
        float sum = Float.parseFloat(reader.readLine());
        while (sum < 0){
            System.out.println("Сумма должна быть >= 0!");
            System.out.print("Введите сумму для пополнения: ");
            sum = Float.parseFloat(reader.readLine());
        }
        LogicWork.addMoneyBalance(sum, cur, accList.get(account));
    }

    // Показ операций пользователя
    private static void browseOperations(User user) throws SQLException {
        List<Operation> operations = OperationManager.selectAllOperationUser(user);
        System.out.println("Операции пользователя: ");
        int i = 1;
        for (Operation operation : operations){
            System.out.println(i + ".");
            System.out.println("Дата: " + operation.getDateOfOperation());
            System.out.println("Кому: " + AccountManager.getUserOfAccount(operation.getAccOut()).getPhone());
            System.out.println("Валюта: " + operation.getAccCode());
            System.out.println("Сумма перевода: " + operation.getAmount());
            System.out.println("Сумма на счете до перевода: " + operation.getAmountBefore());
            System.out.println("Остаток на счете: " + operation.getAmountAfter());
            i++;
        }
    }

    // Интерфейс для перевода денег
    private static void transferMoneyInterface(User user) throws IOException, SQLException, ParseException {
        // Перевод но номеру
        System.out.print("Введите номер телефона получателя: ");
        String phone = reader.readLine();
        List<User> users = UserManager.selectUserForPhone(phone);
        if (users.isEmpty()){
            System.out.println("Пользователя с таким номером телефона не существует!");
            return;
        }
        String accCode = inputCurrency();
        System.out.print("Введите сумму: ");
        BigDecimal amount = new BigDecimal(reader.readLine());
        String message = LogicWork.transferMoney(user, users.get(0), accCode, amount);
        System.out.println(message);
    }

    // Все операции пользователя (после входа)
    private static void operationsUsers(User user) throws IOException, SQLException, ParseException {
        System.out.println("Вы вошли в систему!");
        String message = "Выберите действие:\n1 - Создать аккаунт\n2 - Пополнить счёт\n" +
                "3 - Перевести средства по номера телефону другому пользователю\n" +
                "4 - Просмотреть историю операций\n0 - Выход";
        int action = -1;
        while(action != 0) {
            System.out.println(message);
            action = Integer.parseInt(reader.readLine());
            switch (action){
                case 1: {
                    createAccount(user);
                    break;
                }
                case 2: {
                    addMoneyOnAccount(user);
                    break;
                }
                case 3: {
                    transferMoneyInterface(user);
                    break;
                }
                case 4: {
                    browseOperations(user);
                    break;
                }
                default: break;
            }
        }
    }

    // Вход в систему
    private static void inputIntoSystem() throws IOException, SQLException, ParseException {
        System.out.print("Введите логин или телефон: ");
        String loginOrPhone = reader.readLine();
        System.out.print("Введите пароль: ");
        String password = reader.readLine();
        User user = UserManager.getUserForLoginOrPhone(loginOrPhone, password);
        if (user == null){
            System.out.println("Неверный логин и/или пароль!");
        }
        else{
            operationsUsers(user);
        }
    }

    // Главный интерфейс (создание, вход или выход)
    public static void mainInterface(){
        try {
            LogicWork.preparedAction();
            String message = "Выберите действие:\n1 - Создать пользователя\n2 - Войти в систему\n0 - Выйти";
            reader = new BufferedReader(new InputStreamReader(System.in));
            int action = -1;
            while(action != 0) {
                System.out.println(message);
                action = Integer.parseInt(reader.readLine());
                switch (action){
                    case 1: {
                        createUserInterface();
                        break;
                    }
                    case 2: {
                        inputIntoSystem();
                        break;
                    }
                    default: break;
                }
            }
        } catch(Throwable e){
            e.printStackTrace();
        }
    }
}
