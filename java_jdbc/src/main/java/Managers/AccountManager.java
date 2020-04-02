package Managers;

import Classes.Account;
import Classes.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private static Connection con;

    public static void addConnection(Connection con){
        AccountManager.con = con;
    }

    // Добавление нового счета в базу
    public static void createAccountDB(Account account) throws SQLException {
        List<Account> accList = AccountManager.selectAccountUsers(account.getIdUser());
        // Если есть счета у пользователя и добавляемый будет по умолчанию,
        // то остальные делаем не дефолтными
        if (!accList.isEmpty() && account.isDefAccount()){
            updateAccountDefault(account.getIdUser(), false);
        }
        // Если счетов нет, то этот становится по умолчанию
        if (accList.isEmpty()){
            account.setDefAccount(true);
        }
        String createSQL;
        createSQL = "INSERT INTO Account (idUser, accCode, defAccount) VALUES " +
                "(?, ?, ?);";

        PreparedStatement psstmt = con.prepareStatement(createSQL);
        psstmt.setLong(1, account.getIdUser());
        psstmt.setString(2, account.getAccCode());
        psstmt.setBoolean(3, account.isDefAccount());
        psstmt.executeUpdate();

    }

    // Обновление дефолтности у счетов юзера
    private static void updateAccountDefault(Long idUser, boolean defAcc) throws SQLException {
        String updateSql = "UPDATE Account SET defAccount = ? where idUser = ?";
        PreparedStatement stmt = con.prepareStatement(updateSql);
        stmt.setBoolean(1, defAcc);
        stmt.setLong(2, idUser);
        stmt.executeUpdate();
    }

    // Обновление баланса аккаунта
    public static void updateAccountAmount(Account account) throws SQLException {
        String updateSql = "UPDATE Account SET amount = ? where idUser = ? and accCode = ?";
        PreparedStatement stmt = con.prepareStatement(updateSql);
        stmt.setBigDecimal(1, account.getAmount());
        stmt.setLong(2, account.getIdUser());
        stmt.setString(3, account.getAccCode());
        stmt.executeUpdate();
    }

    // Выборка счета юзера по валюте
    public static List<Account> selectAccountForCurrency(long idUser, String currency) throws SQLException {
        String selectSql = "SELECT * FROM Account where idUser = ? and accCode = ?";
        PreparedStatement stmt = con.prepareStatement(selectSql);
        stmt.setString(1, Long.toString(idUser));
        stmt.setString(2, currency);
        return getAccountFromDB(stmt);
    }

    // Выборка всех счетов юзера
    public static List<Account> selectAccountUsers(long idUser) throws SQLException {
        String selectSql = "SELECT * FROM Account where idUser = ?";
        PreparedStatement stmt = con.prepareStatement(selectSql);
        stmt.setString(1, Long.toString(idUser));
        return getAccountFromDB(stmt);
    }

    // Выборка дефолтного счета юзера
    public static Account selectDefaultAccountUser(long idUser) throws SQLException {
        List<Account> accList = AccountManager.selectAccountUsers(idUser);
        for (Account acc : accList){
            if (acc.isDefAccount()){
                return acc;
            }
        }
        return null;
    }

    // Выборка аккаунтов и возврат списка аккаунтов по запросу
    private static List<Account> getAccountFromDB(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        List<Account> result = new ArrayList<>();
        while (rs.next()) {
            Account account = new Account(rs.getLong("id"), rs.getLong("idUser"), rs.getBigDecimal("amount"),
                    rs.getString("accCode"), rs.getBoolean("defAccount"));
            result.add(account);
        }
        return result;
    }

    // Проверка на несуществование счета
    public static boolean checkNoAccCurrency(long idUser, String currency) throws SQLException {
        return selectAccountForCurrency(idUser, currency).isEmpty();
    }

    // Ищем пользователя данного аккаунта
    public static User getUserOfAccount(long idAccount) throws SQLException {
        String selectSql = "SELECT idUser FROM Account where id = ?";
        PreparedStatement stmt = con.prepareStatement(selectSql);
        stmt.setLong(1, idAccount);
        ResultSet rs = stmt.executeQuery();
        long idUser = 0;
        while (rs.next()) {
            idUser = rs.getLong("idUser");
        }
        return UserManager.selectUserForId(idUser);
    }
}
