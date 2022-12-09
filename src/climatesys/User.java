package climatesys;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class User {
    private String userName;
    private String passwordHash;

    private String bookmarks = "";

    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, String password) {
        this.userName = userName;
        try {
            this.passwordHash = SHA256(password);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(String bookmarks) {
        this.bookmarks = bookmarks;
    }

    public boolean signUp(Connection c, Statement stmt) {
        try {
            String sql = "SELECT * FROM USERS WHERE USERNAME = '" + this.getUserName() +"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.getString("USERNAME") == null) {
                sql = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES ('" + this.getUserName() + "', '" + this.getPasswordHash() + "')";
                System.out.println("[DB]CREATE USER SUCCESSFULLY");
                System.out.println("[DB]USERNAME: " + this.getUserName());
                stmt.executeUpdate(sql);
            }else {
                System.out.println("[DB]CANNOT CREATE USER: " + this.getUserName());
                return false;
            }
        }catch (Exception err) {
            System.err.println(err.getClass().getName() + ": " + err.getMessage());
        }
        return true;
    }

    public boolean signIn(Connection c, Statement stmt) {
        try {
            String sql = "SELECT * FROM USERS WHERE USERNAME = '" + this.getUserName() +"' AND PASSWORD = '" + this.getPasswordHash() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("[DB]USERNAME: " + rs.getString("USERNAME"));
            System.out.println("[DB]BOOKMARKS: " + rs.getString("BOOKMARKS"));
            if(rs.getString("USERNAME") == null) return false;
            this.setBookmarks(rs.getString("BOOKMARKS"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    public String addBookmark(Connection c, Statement stmt, String place) {
        try{
            String sql = "SELECT * FROM USERS WHERE USERNAME = '" + this.getUserName() +"'";
            ResultSet rs = stmt.executeQuery(sql);
            String oldBookmarks = rs.getString("BOOKMARKS");
            String newBookmarks;
            if(oldBookmarks == null) newBookmarks = place;
            else newBookmarks = oldBookmarks + place;
            sql = "UPDATE USERS SET BOOKMARKS = '" + newBookmarks + "' WHERE USERNAME = '" + this.getUserName() +"'";
            stmt.executeUpdate(sql);
            System.out.println("[DB]ADD BOOKMARKS: " + newBookmarks);
            return newBookmarks;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    public boolean delBookmark(Connection c, Statement stmt, String bookmarks) {
        try{
            String sql = "UPDATE USERS SET BOOKMARKS = '" + bookmarks + "' WHERE USERNAME = '" + this.getUserName() + "'";
            System.out.println("[DB]DELETE BOOKMARKS: " + bookmarks);
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    private String SHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while(hexString.length() < 64) hexString.insert(0, '0');
        return hexString.toString();
    }
}