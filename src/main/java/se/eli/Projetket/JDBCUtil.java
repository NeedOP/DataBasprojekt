package se.eli.Projetket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
    private static String url;
    private static String user;
    private static String password;


    public static void loadProperties(String filename) {
        Properties props = new Properties();
        try (InputStream input = JDBCUtil.class.getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                throw new IOException(filename);

            }
            props.load(input);
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Misslyckades att läsa test.properties, använder fallback.");
            url = "jdbc:hsqldb:hsql://localhost/jdbclab";
            user = "sa";
            password = "";
        }
    }
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Kunde inte skapa databasanslutning!");
            e.printStackTrace();
            return null;
        }
    }
    public static void close(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
