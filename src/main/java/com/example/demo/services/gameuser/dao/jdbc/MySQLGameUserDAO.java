//package com.example.demo.services.gameuser.dao.jdbc;
//
//import com.example.demo.entities.GameUserEntity;
//import com.example.demo.services.gameuser.dao.GameUserDAO;
//
//import java.util.List;
//import java.sql.*;
//import java.util.ArrayList;
//
////@Repository
//public class MySQLGameUserDAO implements GameUserDAO {
//
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/square_games";
//    private static final String DB_USER = "root";
//    private static final String DB_PASSWORD = "root";
//
//    private final Connection connection;
//
//    public MySQLGameUserDAO() {
//        try {
//            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to connect to the database ", e);
//        }
//    }
//
//    @Override
//    public List<GameUserEntity> getAllUsers() {
//        String query = "SELECT * FROM USERS";
//        List<GameUserEntity> gameUserEntities = new ArrayList<>();
//        try (Statement stmt = connection.createStatement();
//             ResultSet rs = stmt.executeQuery(query)) {
//            while (rs.next()) {
//                GameUserEntity gameUserEntity = new GameUserEntity(
//                        rs.getInt("id"),
//                        rs.getString("username"),
//                        rs.getString("email"),
//                        rs.getString("password")
//                        rs.getString("role")
//                );
//                gameUserEntities.add(gameUserEntity);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return gameUserEntities;
//    }
//
//    @Override
//    public GameUserEntity getUserById(int id) {
//        String query = "SELECT * FROM USERS WHERE id = ? ";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setInt(1, id);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return new GameUserEntity(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getString("email")
//                    );
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public void addUser(GameUserEntity gameUserEntity) {
//        String query = "INSERT INTO USERS (name, email) VALUES (?, ?)";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setString(1, gameUserEntity.getUsername());
//            stmt.setString(2, gameUserEntity.getEmail());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void updateUser(GameUserEntity gameUserEntity) {
//        String query = "UPDATE USERS SET name = ?, email = ? WHERE id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setString(1, gameUserEntity.getUsername());
//            stmt.setString(2, gameUserEntity.getEmail());
//            stmt.setInt(3, gameUserEntity.getId());
//            stmt.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void deleteUser(int id) {
//        String query = "DELETE FROM USERS WHERE id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setInt(1, id);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Close the connection when the DAO is no longer needed
//    public void close() {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//}