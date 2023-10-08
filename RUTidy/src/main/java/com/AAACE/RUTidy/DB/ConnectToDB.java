package com.AAACE.RUTidy.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.AAACE.RUTidy.Users.User;

public class ConnectToDB {
    
    Connection conn = null;
    public ConnectToDB() throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mariadb://localhost:3306/temp0"; //cs431 is the database name; 3306 is the port number
        String user = "Engi"; //user name you created
        String password = "aaace"; //password associated with the user name
        conn = DriverManager.getConnection(url, user, password);
    }
    /* 
    public static void main(String[] args) throws Exception {
        
        ConnectToDB db = new ConnectToDB();
        ArrayList<User> users = db.getAllUsers();
         
        for(int i = 0; i < users.size(); i++)
        {
            System.out.println(users.get(i));
        }
        boolean a = db.isUserPassCorrect("ari12", "apples");
        System.out.println("\nThe pass check is : " + a);

        User temp = new User("Kyle Joe", "ky@gmail", "54", "kyler10");
        //db.addUser(temp);   
        System.out.println("Email temp@temp exist in db: " + db.isEmailInUse("temp@temp"));
        System.out.println("Email tememp exist in db: " + db.isEmailInUse("tememp"));

        System.out.println("Username ari12 exist in db: " + db.isUsernameInUse("ari12"));
        System.out.println("Username tememp exist in db: " + db.isUsernameInUse("tememp"));
        
      
    }
    */
    
    //returns an arraylists of all the users in the DB
    public ArrayList<User> getAllUsers() throws Exception
    {
        ArrayList<User> uList = new ArrayList<User>();
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery("select * from Users");
       
        while (result.next())
        {
            User temp = new User(result.getString(3) + " "+ result.getString(4), result.getString(5), result.getString(2), result.getString(1));
            uList.add(temp);
        }
        return uList;
    }
    // checks if the given username and password match an user in the DB
    public boolean isUserPassCorrect(String username, String password) throws Exception
    {
        
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery("SELECT Password FROM Users WHERE Username = '" + username + "'");
        result.next();
        String actualPass = result.getString(1);
        if(actualPass.equals(password))
        {
            return true;
        }
        else return false;
    }

    //Given a User type, adds it to the DB if not already added, throws a exception is user is already in DB
    public void addUser(User user) throws SQLException
    {
        String query = "INSERT INTO Users (Username, Password, FirstName, LastName, Email)  VALUES (" + "?, ?, ?, ?, ?)";

        try 
        {
        // set all the preparedstatement parameters
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, user.getUsername());
        st.setString(2, user.getPassword());
        String[] tempStr = user.getName().split("\\s+");
        st.setString(3, tempStr[0]);
        st.setString(4, tempStr[1]);
        st.setString(5, user.getEmail());

        // execute the preparedstatement insert
        st.executeUpdate();
        st.close();
        } 
        catch (SQLException se)
        {
          throw se;
        }
    }
    
    //not tested yet
    public void addUser(String username, String password, String fName, String lName, String email) throws SQLException
    {
        String query = "INSERT INTO Users (Username, Password, FirstName, LastName, Email)  VALUES (" + "?, ?, ?, ?, ?)";

        try 
        {
        // set all the preparedstatement parameters
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);
        st.setString(2, password);
        st.setString(3, fName);
        st.setString(4, lName);
        st.setString(5, email);

        // execute the preparedstatement insert
        st.executeUpdate();
        st.close();
        } 
        catch (SQLException se)
        {
         // log exception
         throw se;
        }
    }
    // returns true if given email is in already in DB false otherwise
    public boolean isEmailInUse(String email) throws SQLException
    {
        try
        {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Users WHERE Email = '" + email + "'");
            if(result.next())
            {
                return true;
            }
            else return false;
        }
        catch (SQLException se)
        {
         // log exception
         throw se;
        }
    }
    // returns true if given username is in DB false otherwise
    public boolean isUsernameInUse(String username) throws SQLException
    {
        try
        {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Users WHERE Username = '" + username + "'");
            if(result.next())
            {
                return true;
            }
            else return false;
        }
        catch (SQLException se)
        {
         // log exception
         throw se;
        }
    }

}