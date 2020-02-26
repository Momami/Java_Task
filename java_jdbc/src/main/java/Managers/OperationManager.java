package Managers;

import Classes.Operation;
import Classes.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationManager {
    public static Connection con;

    public static void addConnection(Connection con){
        OperationManager.con = con;
    }

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

    public static List<Operation> selectAllOperationUser(User user) throws SQLException {
        String selectSql = "SELECT * FROM Operation where accIn = ?";
        PreparedStatement stmt = con.prepareStatement(selectSql);
        stmt.setLong(1, user.getId());
        // stmt.setLong(2, user.getId());
        return getAccountFromDB(stmt);
    }

    private static List<Operation> getAccountFromDB(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        List<Operation> result = new ArrayList<>();
        while (rs.next()) {
            Operation operation = new Operation(rs.getLong("id"), rs.getDate("dateOfOperation"),
                    rs.getString("accCode"), rs.getLong("accIn"),
                    rs.getLong("accOut"), rs.getBigDecimal("amount"), rs.getBigDecimal("amountBefore"),
                    rs.getBigDecimal("amountAfter"));
            result.add(operation);
        }
        return result;
    }
}
