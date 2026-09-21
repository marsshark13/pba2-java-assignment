/*
 * PBA 2 - Employee Management System
 *
 * Name: SHAFIQ ARIF BIN SHAMSUL ARIF
 * Registration Number: 01DIT24F1203
 *
 * Name: MUHAMMAD AMSYAR ADAM BIN SHARUDIN
 * Registration Number: 01DIT24F1224
 *
 * Name: MUHAMMAD ADAM DANIAL BIN MOHAMMAD RIDZUAN
 * Registration Number: 01DIT24F11995
 *
 * Class: DITP5B
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
    private DBConnection() { }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("The Derby client driver is not available.", ex);
        }
        return DriverManager.getConnection(
                "jdbc:derby://localhost:1527/employee", "app", "app");
    }
}
