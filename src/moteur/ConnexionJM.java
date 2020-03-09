package moteur;
import java.sql.*;

public class ConnexionJM {
		/*
		public static void main(String[] args) {
			
			try {
			Connection c = connecterDB(); 
			Statement st;
			ResultSet rst;
			
			
			st=c.createStatement();
			rst=st.executeQuery("SELECT * FROM user");
			
				while(rst.next()) {
					System.out.print(rst.getInt("id")+"\t");
					System.out.print(rst.getString("nom")+"\t");
					System.out.print(rst.getInt("age")+"\t");
				}
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		*/
	
		public static Connection connecterDB() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver marche !");
				String url="jdbc:mysql://localhost/test?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
				String user="root";
				String pw="";
				
				Connection c = DriverManager.getConnection(url,user,pw);
				System.out.println("Connexion en cours... Veuillez patienter.");
				System.out.println("Vous êtes connecter !");
				System.out.println("********************");
				return c;
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("Oups... ça marche pas ! ");
				return null;
			}
		}
}
