/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDAO extends DBContext {

    public Vector<User> getAll() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Vector<User> users = new Vector<>();
        String sql = "select * from [user]";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int role_id = rs.getInt("role_id");

                User u = new User(id, username, password, fullname, email, phone, address, role_id);
                users.add(u);
            }
            return users;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                rs.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public User getOne(String username, String password) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select * from [user]\n"
                + "where [username] = ?\n"
                + "and [password] = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(username);
                u.setPassword(password);
                u.setFullname(rs.getString("fullname"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setRole_id(rs.getInt("role_id"));
                u.setBanned(rs.getInt("banned"));
                System.out.println(u);
                return u;

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    //get all customer (role_id = 1)
    public Vector<User> getAllCustomer() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Vector<User> users = new Vector<>();
        String sql = "select * from [user] where role_id = 1";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int role_id = rs.getInt("role_id");
                int banned = rs.getInt("banned");

                User u = new User(id, username, password, fullname, email, phone, address, role_id, banned);
                users.add(u);
            }
            return users;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                rs.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public String getUserRegister(String username, String pass, String name, String email, String phone, String address) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //chuan bi string sql
            String sql = " INSERT INTO [dbo].[user]\n"
                    + "           ([username]\n"
                    + "           ,[password]\n"
                    + "           ,[fullname]\n"
                    + "           ,[email]\n"
                    + "           ,[phone]\n"
                    + "           ,[address]\n"
                    + "           ,[role_id])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,1)";
            ps = connection.prepareStatement(sql);
            //set bien dungs voiw thuw tu bien trong string tren
            ps.setString(1, username);
            ps.setString(2, pass);
            ps.setString(3, name);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, address);

            //goi cau lenh execute
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void insert(User user) {
        PreparedStatement stm = null;

        String sql = "INSERT INTO [dbo].[user]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[fullname]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[address]\n"
                + "           ,[role_id])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getFullname());
            stm.setString(4, user.getEmail());
            stm.setString(5, user.getPhone());
            stm.setString(6, user.getAddress());
            stm.setInt(7, user.getRole_id());
            stm.executeUpdate();

            System.out.println("Insert OK");

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public User getUserById(int userId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select * from [user]\n"
                + "where [id] = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            rs = stm.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setFullname(rs.getString("fullname"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setRole_id(rs.getInt("role_id"));
                System.out.println(u);
                return u;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Vector<User> getCustomerByName(String name) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Vector<User> customers = new Vector<>();
        String sql = "select * from [user]\n"
                + "where role_id = 1 and [fullname] LIKE ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            rs = stm.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setFullname(rs.getString("fullname"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setRole_id(rs.getInt("role_id"));
                System.out.println(u);

                customers.add(u);
            }
            return customers;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void banAnUser(int userId) {
        PreparedStatement stm = null;

        String sql = "UPDATE [dbo].[user] SET [banned] = 1 WHERE id = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.executeUpdate();

            System.out.println("Banned userId = " + userId);

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void unbanAnUser(int userId) {
        PreparedStatement stm = null;

        String sql = "UPDATE [dbo].[user] SET [banned] = 0 WHERE id = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.executeUpdate();

            System.out.println("Banned userId = " + userId);

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public User updateProfileUser(String username, String password, String fullname, String address, String email, String phone) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        User updatedUser = null;
        try {
            String sql = "UPDATE [dbo].[user]\n"
                    + "   SET \n"
                    + "      [password] = ?,\n"
                    + "      [fullname] = ?,\n"
                    + "      [email] = ?,\n"
                    + "      [phone] = ?,\n"
                    + "      [address] = ?,\n"
                    + "	  [username] = ?,\n"
                    + "update_at = CURRENT_TIMESTAMP where [username] = ?";

            ps = connection.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, fullname);
            ps.setString(3, address);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, username);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                // If the update was successful, retrieve the updated user information
                String selectSql = "select * from [dbo].[user] where username = ?";
                ps = connection.prepareStatement(selectSql);
                ps.setString(1, username);
                rs = ps.executeQuery();

                if (rs.next()) {
                    // Create a User object with the updated information
                    updatedUser = new User();
                    updatedUser.setPassword(rs.getString("password"));
                    updatedUser.setFullname(rs.getString("fullname"));
                    updatedUser.setAddress(rs.getString("address"));
                    updatedUser.setEmail(rs.getString("email"));
                    updatedUser.setPhone(rs.getString("phone"));
                    updatedUser.setUsername(rs.getString("username"));

                }

                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources (Connection, PreparedStatement, etc.)
        }

        return updatedUser;
    }
}
