package be.intecbrussel.repository;

import be.intecbrussel.config.MySQLConfig;
import be.intecbrussel.modal.Account;
import be.intecbrussel.modal.User;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserRepository implements UserRepo {

    @Override
    public boolean createUser(User user) {
        int dbResult = 0;
        Connection connection = MySQLConfig.getConnection();

        try (connection) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user values(?, ?, ?, ?);");
            ps.setLong(1, user.getId());
            ps.setString(2, user.getFname());
            ps.setString(3, user.getLname());
            ps.setString(4, user.getAccount().getEmail());

            dbResult = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("ERROR: COULD NOT CREATE USER! (┬┬﹏┬┬)");
            throw new RuntimeException(e);
        }

        return dbResult == 1;
    }

    @Override
    public Optional<User> findUser(Account account) {
        Connection connection = MySQLConfig.getConnection();

        try (connection) {
            PreparedStatement ps = connection.prepareStatement("select * from user where accEmail like ?");

            ps.setString(1, account.getEmail());

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                long dbId = resultSet.getLong("id");
                String dbFName = resultSet.getString("fname");
                String dbLName = resultSet.getString("lname");

                return Optional.of(new User(dbId, dbFName, dbLName, account));
            }

        } catch (SQLException e) {
            System.err.println("ERROR: COULD NOT FIND USER! (┬┬﹏┬┬)");
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public boolean changeName(String firstName, String lastName, String email) {
        int dbResult = 0;
        Connection connection = MySQLConfig.getConnection();

        try (connection) {
            PreparedStatement ps = connection.prepareStatement("update user set fname = ?, lname = ? where accEmail = ?;");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);

            dbResult = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("ERROR: COULD NOT CHANGE NAME! (┬┬﹏┬┬)");
            throw new RuntimeException(e);
        }

        return dbResult == 1;
    }

    @Override
    public boolean createManyUsers(List<User> userList) {
        Connection connection = MySQLConfig.getConnection();
        boolean succes = false;

        try (connection) {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?);");

            for (User user : userList) {
                ps.setLong(1, user.getId());
                ps.setString(2, user.getFname());
                ps.setString(3, user.getLname());
                ps.setString(4, user.getAccount().getEmail());
                ps.addBatch();
            }

            ps.executeBatch();
            succes = true;

        } catch (SQLException e) {
            System.err.println("IT FAILED! (┬┬﹏┬┬)");
            throw new RuntimeException(e);
        }

        return succes;
    }
}
