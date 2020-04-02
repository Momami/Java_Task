package Managers;

import Classes.Account;
import Classes.Operation;
import Classes.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationManager {
    private static Connection con;

    public static void addConnection(Connection con){
        OperationManager.con = con;
    }

    // Добавление новой операции
    public static void createOperation(Operation operation) throws SQLException {
        String createSQL;
        createSQL = "INSERT INTO Operation (dateOfOperation, accCode, accIn, accOut, amount," +
                "amountBefore, amountAfter) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement psstmt = con.prepareStatement(createSQL);
        psstmt.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis()));
        psstmt.setString(2, operation.getAccCode());
        psstmt.setLong(3, operation.getAccIn());
        psstmt.setLong(4, operation.getAccOut());
        psstmt.setBigDecimal(5, operation.getAmount());
        psstmt.setBigDecimal(6, operation.getAmountBefore());
        psstmt.setBigDecimal(7, operation.getAmountAfter());
        psstmt.executeUpdate();
    }

    // Получение списка операций по всем аккаунтам юзера
    public static List<Operation> selectAllOperationUser(User user) throws SQLException {
        List<Account> accountList = AccountManager.selectAccountUsers(user.getId());
        List<Operation> operations = new ArrayList<>();
        for (Account account: accountList) {
            String selectSql = "SELECT * FROM Operation where accIn = ?";
            PreparedStatement stmt = con.prepareStatement(selectSql);
            stmt.setLong(1, account.getId());
            operations.addAll(getAccountFromDB(stmt));
        }
        return operations;
    }

    // Возвращает все операции по заданному запросу к бд в виде списка
    private static List<Operation> getAccountFromDB(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        List<Operation> result = new ArrayList<>();
        while (rs.next()) {
            Operation operation = new Operation(rs.getLong("id"), rs.getDate("dateOfOperation").toLocalDate(),
                    rs.getString("accCode"), rs.getLong("accIn"),
                    rs.getLong("accOut"), rs.getBigDecimal("amount"), rs.getBigDecimal("amountBefore"),
                    rs.getBigDecimal("amountAfter"));
            result.add(operation);
        }
        return result;
    }
}
