package LogicWork;

import Classes.Account;
import Classes.Operation;
import Classes.User;
import Managers.AccountManager;
import Managers.ConnectionDB;
import Managers.OperationManager;
import Managers.UserManager;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogicWork {
    private static Map<String, Float> converter;
    private static Object SQLException;

    private static void initConverter(){
        converter = new HashMap<>();
        converter.put("RUB", 1f);
        converter.put("USD", 65.21f);
        converter.put("EUR", 70.95f);
    }


    public static void preparedAction() throws Throwable {
        try {
            initConverter();
            String Url = "jdbc:sqlserver://DESKTOP-5PEJHGA\\MSSQLSERVER1;databaseName=BankDB;integratedSecurity=true;";
            Connection con = ConnectionDB.createConn(Url);
            UserManager.addConnection(con);
            AccountManager.addConnection(con);
            OperationManager.addConnection(con);
        }
        catch (SQLException | ClassNotFoundException e){
            System.out.println("Не удалось подключиться к базе!");
            throw (Throwable) SQLException;
        }
    }

    public static void createUser(String login, String password, String address, String phone) throws SQLException {
        User user = new User(login, password, address, phone);
        UserManager.createUserDB(user);
    }

    public static void createAccount(long idUser, String accCode, boolean defAcc) throws SQLException {
        Account account = new Account(idUser, accCode, defAcc);
        AccountManager.createAccountDB(account);
    }

    public static void addMoneyBalance(float sum, String accCode, Account account) throws SQLException {
        sum = sum * converter.get(accCode);
        // TO DO сделать перевод валют в зависимости от currency
        float conv = converter.get(account.getAccCode());
        BigDecimal amount = new BigDecimal((sum + account.getAmount().doubleValue() * conv) / conv);
        account.setAmount(amount);
        AccountManager.updateAccountAmount(account);
    }

    public static String transferMoney(User userIn, User userOut, String accCode, BigDecimal amount)
            throws SQLException, ParseException {
        List<Account> accIn = AccountManager.selectAccountForCurrency(userIn.getId(), accCode);
        Account acc;
        if (accIn.isEmpty()){
            acc = AccountManager.selectDefaultAccountUser(userIn.getId());
        }
        else{
            acc = accIn.get(0);
        }
        // Если нет дефолтного акка
        if (acc == null){
            return "У Вас еще нет счетов для списания!";
        }
        Account accOut = AccountManager.selectDefaultAccountUser(userOut.getId());
        // Если нет у получателя нет дефолтного акка
        if (accOut == null){
            return "У получателя еще нет счетов для получения средств!";
        }
        // Получить дату
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Date dateOfOperation = new Date(dateFormat.parse((new java.util.Date()).toString()).getTime());
        BigDecimal amountRub = new BigDecimal(amount.doubleValue() * converter.get(accCode));
        // Рассчитать остаток на счете
        BigDecimal amountBefore = acc.getAmount();
        BigDecimal amountBalance = new BigDecimal(acc.getAmount().doubleValue() * converter.get(acc.getAccCode())
                - amountRub.doubleValue());
        if (amountBalance.doubleValue() < 0){
            return "Недостаточно средств для перевода!";
        }
        // Обновить счет отправителя
        acc.setAmount(new BigDecimal(amountBalance.doubleValue() / converter.get(acc.getAccCode())));
        AccountManager.updateAccountAmount(acc);
        // Обновить счет получателя
        double tmp = accOut.getAmount().doubleValue() * converter.get(accOut.getAccCode());
        BigDecimal addBalance = new BigDecimal((tmp + amountRub.doubleValue())
                / converter.get(accOut.getAccCode()));
        accOut.setAmount(addBalance);
        AccountManager.updateAccountAmount(accOut);
        // Добавление в журнал операций
        Operation operation = new Operation(null, accCode, acc.getId(), accOut.getId(), amount,
                amountBefore, acc.getAmount());
        OperationManager.createOperation(operation);
        return "Успешно!";
    }
}
