package be.intecbrussel.repository;

import be.intecbrussel.config.EMFProvider;
import be.intecbrussel.config.MySQLConfig;
import be.intecbrussel.modal.Account;
import jakarta.persistence.EntityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//public class AccountRepository implements IAccountRepo {
//    @Override
//    public boolean createAccount(Account account) {
//        EntityManager em = EMFProvider.getEMF().createEntityManager();
//
//        boolean dbResult = false;
//
//        em.getTransaction().begin();
//
//        if (account.getEmail() == null) {
//            em.persist(account);
//            dbResult = true;
//        } else {
//            System.err.println("ERROR: COULD NOT CREATE ACCOUNT! (┬┬﹏┬┬)");
//            throw new RuntimeException();
//        }
//
//        em.getTransaction().commit();
//        em.close();
//
//        return dbResult;
//    }
//
//    @Override
//    public Optional<Account> getAccount(String email) {
//        Connection connection = MySQLConfig.getConnection();
//
//        try (connection) {
//            PreparedStatement ps = connection.prepareStatement("select * from account where email like ?");
//            ps.setString(1, email);
//
//            ResultSet resultSet = ps.executeQuery();
//
//            if (resultSet.next()) {
//
//                String dbEmail = resultSet.getString("email");
//                String dbPassw = resultSet.getString("passw");
//
//                return Optional.of(new Account(dbEmail, dbPassw));
//            }
//
//        } catch (SQLException e) {
//            System.err.println("ERROR: COULD NOT GET ACCOUNT! (┬┬﹏┬┬)");
//            throw new RuntimeException(e);
//        }
//
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean updateEmail(String email, String newEmail) {
//        Connection connection = MySQLConfig.getConnection();
//        int dbResult = 0;
//
//        try (connection) {
//            PreparedStatement ps = connection.prepareStatement("update account set email = ? where email = ?");
//            ps.setString(1, newEmail);
//            ps.setString(2, email);
//
//            dbResult = ps.executeUpdate();
//
//        } catch (SQLException e) {
//            System.err.println("ERROR: COULD NOT CHANGE EMAIL! (┬┬﹏┬┬)");
//            throw new RuntimeException(e);
//        }
//
//        return dbResult == 1;
//    }
//
//    @Override
//    public boolean updatePassword(String email, String newPass) {
//        Connection connection = MySQLConfig.getConnection();
//        int dbResult = 0;
//
//        try (connection) {
//            PreparedStatement ps = connection.prepareStatement("update account set passw = ? where email = ?");
//            ps.setString(1, newPass);
//            ps.setString(2, email);
//
//            dbResult = ps.executeUpdate();
//
//        } catch (SQLException e) {
//            System.err.println("ERROR: COULD NOT CHANGE PASSWORD! (┬┬﹏┬┬)");
//            throw new RuntimeException(e);
//        }
//
//        return dbResult == 1;
//    }
//
//    @Override
//    public boolean wipeAccount(String email) {
//        Connection connection = MySQLConfig.getConnection();
//        int dbResult = 0;
//
//        try (connection) {
//            PreparedStatement ps = connection.prepareStatement("delete from account where email = ?");
//            ps.setString(1, email);
//
//            dbResult = ps.executeUpdate();
//
//        } catch (SQLException e) {
//            System.err.println("ERROR: COULD NOT DELETE ACCOUNT! (┬┬﹏┬┬)");
//            throw new RuntimeException(e);
//        }
//
//        return dbResult == 1;
//    }
//
//    @Override
//    public boolean wipeUser(String email) {
//        Connection connection = MySQLConfig.getConnection();
//        int dbResult = 0;
//
//        try (connection) {
//            PreparedStatement ps = connection.prepareStatement("delete from user where accEmail = ?");
//            ps.setString(1, email);
//
//            dbResult = ps.executeUpdate();
//
//        } catch (SQLException e) {
//            System.err.println("ERROR: COULD NOT DELETE USER! (┬┬﹏┬┬)");
//            throw new RuntimeException(e);
//        }
//
//        return dbResult == 1;
//    }
//
//    @Override
//    public boolean createManyAccounts(List<Account> accountList) {
//        Connection connection = MySQLConfig.getConnection();
//        boolean succes = false;
//
//        try (connection) {
//
//            PreparedStatement ps = connection.prepareStatement("INSERT INTO Account VALUES (?, ?);");
//
//            for (Account account : accountList) {
//                ps.setString(1, account.getEmail());
//                ps.setString(2, account.getPassw());
//                ps.addBatch();
//            }
//
//            ps.executeBatch();
//            succes = true;
//
//        } catch (SQLException e) {
//            System.err.println("IT FAILED! (┬┬﹏┬┬)");
//            throw new RuntimeException(e);
//        }
//
//        return succes;
//    }
//}

// JDBC IMPLEMENTATION

public class AccountRepository implements IAccountRepo {
    @Override
    public boolean createAccount(Account account) {
        Connection connection = MySQLConfig.getConnection();
        int dbResult = 0;

        try (connection) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO account_tb values(?, ?)");
            ps.setString(1, account.getEmail());
            ps.setString(2, account.getPassw());

            dbResult = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("ERROR: COULD NOT CREATE ACCOUNT! (┬┬﹏┬┬)");
            throw new RuntimeException(e);
        }

        return dbResult == 1;
    }

    @Override
    public Optional<Account> getAccount(String email) {
        Connection connection = MySQLConfig.getConnection();

        try (connection) {
            PreparedStatement ps = connection.prepareStatement("select * from account_tb where email like ?");
            ps.setString(1, email);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {

                String dbEmail = resultSet.getString("email");
                String dbPassw = resultSet.getString("passw");

                return Optional.of(new Account(dbEmail, dbPassw));
            }

        } catch (SQLException e) {
            System.err.println("ERROR: COULD NOT GET ACCOUNT! (┬┬﹏┬┬)");
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public boolean updateEmail(String email, String newEmail) {
        Connection connection = MySQLConfig.getConnection();
        int dbResult = 0;

        try (connection) {
            PreparedStatement ps = connection.prepareStatement("update account_tb set email = ? where email = ?");
            ps.setString(1, newEmail);
            ps.setString(2, email);

            dbResult = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("ERROR: COULD NOT CHANGE EMAIL! (┬┬﹏┬┬)");
            throw new RuntimeException(e);
        }

        return dbResult == 1;
    }

    @Override
    public boolean updatePassword(String email, String newPass) {
        Connection connection = MySQLConfig.getConnection();
        int dbResult = 0;

        try (connection) {
            PreparedStatement ps = connection.prepareStatement("update account_tb set passw = ? where email = ?");
            ps.setString(1, newPass);
            ps.setString(2, email);

            dbResult = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("ERROR: COULD NOT CHANGE PASSWORD! (┬┬﹏┬┬)");
            throw new RuntimeException(e);
        }

        return dbResult == 1;
    }

    @Override
    public boolean wipeAccount(String email) {
        Connection connection = MySQLConfig.getConnection();
        int dbResult = 0;

        try (connection) {
            PreparedStatement ps = connection.prepareStatement("delete from account_tb where email = ?");
            ps.setString(1, email);

            dbResult = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("ERROR: COULD NOT DELETE ACCOUNT! (┬┬﹏┬┬)");
            throw new RuntimeException(e);
        }

        return dbResult == 1;
    }

    @Override
    public boolean wipeUser(String email) {
        Connection connection = MySQLConfig.getConnection();
        int dbResult = 0;

        try (connection) {
            PreparedStatement ps = connection.prepareStatement("delete from user_tb where accEmail = ?");
            ps.setString(1, email);

            dbResult = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("ERROR: COULD NOT DELETE USER! (┬┬﹏┬┬)");
            throw new RuntimeException(e);
        }

        return dbResult == 1;
    }

    @Override
    public boolean createManyAccounts(List<Account> accountList) {
        Connection connection = MySQLConfig.getConnection();
        boolean succes = false;

        try (connection) {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Account_tb VALUES (?, ?);");

            for (Account account : accountList) {
                ps.setString(1, account.getEmail());
                ps.setString(2, account.getPassw());
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
