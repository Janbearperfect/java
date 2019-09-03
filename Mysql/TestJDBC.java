package Mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC {
public static void main(String[] args) throws Exception {

delete();
   
}



private static void delete() throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver");
	   String url="jdbc:mysql://localhost:3306/db_chat";
	   String user="root";
	   String password="111111";
	   Connection conn=DriverManager.getConnection(url, user, password);
	   String sql="delete from t_user where id=2";
	   Statement stat=conn.createStatement();
	   stat.execute(sql);
	   stat.close();
	   conn.close();
}

private static void insert() throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver");
	String url="jdbc:mysql://localhost:3306/db_chat";
	String user="root";
	String password="111111";
	
	Connection conn= DriverManager.getConnection(url,user,password);
	String sql="insert into t_user(name,age) values('ww',30)";
   Statement stat=conn.createStatement();
   stat.executeUpdate(sql);
   stat.close();
   conn.close();
}
}

