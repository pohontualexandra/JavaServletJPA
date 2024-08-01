package util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseUtil {

    private static final String DB_DRIVER = "db.driver";
    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";

    private static final Logger logger = Logger.getLogger(DatabaseUtil.class.getName());
    private static Properties properties = new Properties();

    static {
        try (InputStream inputStream = Files.newInputStream(Paths.get("C:/Users/VON_hp_02/Desktop/JavaTirocinio/SomeProjects/Servlet/GroceryEcommerce/src/main/resources/database.properties"))) {
            properties.load(inputStream);
            Class.forName(properties.getProperty(DB_DRIVER));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading database properties file", e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error loading database driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty(DB_URL),
                properties.getProperty(DB_USERNAME),
                properties.getProperty(DB_PASSWORD)
        );
    }
}
