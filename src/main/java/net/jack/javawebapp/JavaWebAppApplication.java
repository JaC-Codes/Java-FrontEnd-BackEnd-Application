package net.jack.javawebapp;

import net.jack.javawebapp.database.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;


@SpringBootApplication
public class JavaWebAppApplication {

	static Database database;

	static {
		try {
			database = new Database();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaWebAppApplication.class, args);
		database.connect();
	}

}
