package net.jack.javawebapp.userapplications;

import net.jack.javawebapp.database.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDataHandling {

    private final Database database;

    public UserDataHandling(Database database) throws SQLException {
        this.database = new Database();
    }

    public void writeUserData(String forename, String surname, String email, String password, String gender, String dob) throws SQLException {
        PreparedStatement ps = database.getConnection().prepareStatement("INSERT INTO user_repo VALUES (?, ?, ?, ?, ?, ?)");
        ps.setString(1, forename);
        ps.setString(2, surname);
        ps.setString(3, email);
        ps.setString(4, password);
        ps.setString(5, gender);
        ps.setString(6, dob);
        ps.executeUpdate();

    }
}
