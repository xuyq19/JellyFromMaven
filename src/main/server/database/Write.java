package main.server.database;
import main.server.user.User;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * Write is a class that is used to write to the database.
 * @author xuyuqi
 * @version 1.0
 * @since 2021-12-22
 */
public class Write {
    /**
     * The connection to the database.
     */
    private Connection conn;
    /**
     * The statement to write to the database.
     */
    private Statement stmt;
    /**
     * The result set to read from the database.
     */
    private ResultSet rs;
    /**
     * The constructor of the class.
     * @param conn the connection to the database
     */
    public Write(Connection conn) {
        this.conn = conn;
    }
    /**
     * The method to write to the database.
     * @param sql the sql statement to write to the database
     * @return the result set
     */
    /**
     * The method to write to the database.
     */
    public void write(User user) {
        try{
            stmt = conn.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
