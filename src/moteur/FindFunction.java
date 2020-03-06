package moteur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindFunction {

	public ResultSet find(String s) {
		
		ResultSet rst = null;
		PreparedStatement ps = null;
		
		try {
			
			Connection c = ConnexionJM.connecterDB();; 
			ps = c.prepareStatement("select * from user where nom = ?");
			ps.setString(1, s);
			rst = ps.executeQuery();
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rst;	
	}
}
