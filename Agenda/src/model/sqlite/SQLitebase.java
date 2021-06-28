package model.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLitebase {
    protected Connection conn;

    public Connection open(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:my_database");
            return conn;
        } catch (SQLException e){
                e.printStackTrace();
            }
        return null;
    }

    public void close() {
        try {
            if (conn != null)
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}