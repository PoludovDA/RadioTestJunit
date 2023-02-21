package core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseTest extends BaseSelenideTest{

    protected Statement statement;

    @BeforeEach
    public void initConnect() {
        try {
            System.getProperties().load(ClassLoader.getSystemResourceAsStream("database.properties"));
            String typeSql = System.getProperty("typeSql");
            String loginConnection = System.getProperty("loginConnection");
            String passwordConnection = System.getProperty("passwordConnection");
            String server = System.getProperty("server");
            String port = System.getProperty("port");
            String database = System.getProperty("database");

            log.info("Создание подключения к бд");
            Class.forName("org." + typeSql + ".Driver");
            String url = "jdbc:" + typeSql + "://" + server + ":" + port + "/" + database;

            // Создание соединения с базой данных
            Connection connection = DriverManager.getConnection(url, loginConnection, passwordConnection);

            // Создание оператора доступа к базе данных
            statement = connection.createStatement();
            log.info("Подключение завершено");

        } catch (Exception e) {
            System.err.println("Error accessing database!");
            e.printStackTrace();
        }
    }

    @AfterEach
    public void closeConnection() {
        try {
            statement.close();
            log.info("Соединение с бд закрыто");
        } catch (SQLException e) {
            log.info("Ошибка в закрытии подключения БД");
            e.printStackTrace();
        }
    }
}
