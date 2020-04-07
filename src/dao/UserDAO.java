package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import utility.ConnectionManager;
import model.User;

public class UserDAO implements UserDaoInterface{

	ConnectionManager con=new ConnectionManager();
	
	@Override
	public int signUp(User user) throws SQLException, Exception {
		String email=user.getEmail();
		String password=user.getPassword();
	//	String confirmPassword=user.getConfirmpassword();
		LocalDate date_of_signup=user.getDate();
		
		String sql="insert into USERDATA(email,password,date_of_signup)VALUES(?,?,?)";
		
		PreparedStatement st=con.getConnection().prepareStatement(sql);
		
		st.setString(1,email);
		st.setString(2,password);
		st.setDate(3,Date.valueOf(date_of_signup));
		st.executeQuery();
		
		return 0;
	}

	@Override
	public boolean loginUser(User user) throws SQLException, Exception {
		String email=user.getEmail();
		String password=user.getPassword();
		
		
		Statement st = con.getConnection().createStatement();
		
		ResultSet rs=st.executeQuery("SELECT *  from  USERDATA");
		
		while(rs.next()) {
			if(email.equals(rs.getString("email"))&& password.equals(rs.getString("PASSWORD"))) {
				con.getConnection().close();
				return true;
			}
		}
				con.getConnection().close();
				return false;
				
		
	}
}
