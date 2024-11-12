import org.jasypt.util.password.StrongPasswordEncryptor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class EmployeeInserter {
    public static void main(String[] args) {
        // Employee data
        String email = "classta@email.edu";
        String password = "classta";
        String fullname = "TA CS122B";

        // Encrypt the password
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(password);

        // Database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/moviedb";
        String jdbcUser = "mytestuser";
        String jdbcPassword = "My6$Password";

        // SQL to insert employee
        String sql = "INSERT INTO employees (email, password, fullname) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, encryptedPassword); // Store the encrypted password
            preparedStatement.setString(3, fullname);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee inserted successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
