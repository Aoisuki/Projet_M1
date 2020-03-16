package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import moteur.ConnexionJM;
import moteur.FindFunction;
import moteur.ImagePanel;
import moteur.User;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import javax.imageio.*;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JComboBox;


public class WinScreenTest1 extends JFrame {

	private int i = 1;
	private int j = 1903; //la date commence en 1903
	private String s;
	private JPanel contentPane;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTable table2 = new JTable();
	private JLabel lblMsgErreur = new JLabel();
	private JLabel lblMsgErreur2 = new JLabel();
	private JPanel affichePanel = new JPanel();
	private JComboBox cboxDateA = new JComboBox();
	private JComboBox cboxDateB = new JComboBox();
	private JComboBox cboxPays = new JComboBox();
	private String pays[] = new String[10];
	private String annee[] = new String[120];
	private String title[] = new String[7]; //changer la taille max pour plus de valeur
    // read a text file from resources folder that is parallel to src folder
   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinScreenTest1 frame = new WinScreenTest1(); 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws URISyntaxException 
	 */
	public WinScreenTest1() {
		cherchePays(); //appel a la fonction pour chercher les pays dans la BDD
		genererAnnee(); //appel a la fonction pour generer la date a partir de 1903
		chercheNomDuChamp(); //chercher les champs pour ensuite afficher sur le tableau
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1025, 662);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel boutonPanel = new JPanel();
		boutonPanel.setBounds(24, 45, 218, 497);
		contentPane.add(boutonPanel);
		boutonPanel.setLayout(null);
		boutonPanel.setBackground(new Color(0,0,0,0));
		
		JLabel lblTrierPar = new JLabel("Trier par :");
		lblTrierPar.setForeground(Color.WHITE);
		lblTrierPar.setBounds(10, 11, 98, 14);
		boutonPanel.add(lblTrierPar);
		
		JButton btnNewButton = new JButton("Date");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(10, 36, 98, 34);
		boutonPanel.add(btnNewButton);
		
		JButton btnNom = new JButton("Name");
		btnNom.setBackground(Color.WHITE);
		btnNom.setBounds(10, 81, 98, 34);
		boutonPanel.add(btnNom);
		
		JButton btnPays = new JButton("Country");
		btnPays.setBackground(Color.WHITE);
		btnPays.setBounds(10, 126, 98, 34);
		boutonPanel.add(btnPays);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(266, 45, 616, 168);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);
		searchPanel.setBackground(new Color(0,0,0,0)); //Background panel tranparent !! <3 contraire : il faut mettre Color(0,0,0,100)
		
		textField = new JTextField();
		textField.setBounds(26, 11, 471, 50);
		searchPanel.add(textField);
		textField.setColumns(10);
		
		
		
		
		affichePanel.setBounds(266, 303, 616, 239);
		contentPane.add(affichePanel);
		affichePanel.setLayout(null);
		affichePanel.setBackground(new Color(0,0,0,0));
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 616, 238);
		//scrollPane.setBackground(new Color(0,0,0,0));
		affichePanel.add(scrollPane);
		
		
		table2 = new JTable();
		table2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			title
		));
		scrollPane.setViewportView(table2);
		
		
		
		lblMsgErreur = new JLabel("Information introuvable ! Veuillez recommencer ! ");
		lblMsgErreur.setBounds(162, 63, 322, 14);
		lblMsgErreur.setForeground(Color.white);
		searchPanel.add(lblMsgErreur);
		lblMsgErreur.setVisible(false);
		
		lblMsgErreur2 = new JLabel("Veuillez saisir les informations dans la barre de recherche");
		lblMsgErreur2.setBounds(120, 63, 382, 14);
		lblMsgErreur2.setForeground(Color.white);
		searchPanel.add(lblMsgErreur2);
		lblMsgErreur2.setVisible(false);
		 
		JButton btnShowAllData = new JButton("Show all data ! ");
		btnShowAllData.setBackground(Color.WHITE);
		btnShowAllData.setIcon(new ImageIcon(WinScreenTest1.class.getResource("/imgs/logo/icons8-bookmark-64.png")));
		btnShowAllData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				initScreen(); //enlever les messages d'erreur 
				clearTable(); //initialisé le tableau 
				showUserData(); //afficher tout les informations dans la BDD
					
			}
			
		});
		
		btnShowAllData.setBounds(26, 88, 199, 72);
		searchPanel.add(btnShowAllData);
		
		JButton btnOk = new JButton("");
		btnOk.setBackground(Color.WHITE);
		btnOk.setIcon(new ImageIcon(WinScreenTest1.class.getResource("/imgs/logo/icons8-search-64.png")));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				initScreen();
				clearTable(); 
				showUserSearch(); //afficher les informations cherché
				
			}
			
		});
		btnOk.setBounds(507, 11, 97, 75);
		searchPanel.add(btnOk);
		cboxDateA = new JComboBox(annee); //annee est generer par un boucle for 
		cboxDateA.setBounds(349, 110, 85, 20);
		searchPanel.add(cboxDateA);
		
		cboxDateB = new JComboBox(annee);
		cboxDateB.setBounds(504, 110, 85, 20);
		searchPanel.add(cboxDateB);
		
		JLabel lblSelectDate = new JLabel("Date between : ");
		lblSelectDate.setBounds(246, 113, 93, 14);
		lblSelectDate.setForeground(Color.white);
		searchPanel.add(lblSelectDate);
		
		JLabel lblAnd = new JLabel("and");
		lblAnd.setForeground(Color.WHITE);
		lblAnd.setBounds(456, 113, 46, 14);
		searchPanel.add(lblAnd);
		
		JLabel lblPays = new JLabel("Country :");
		lblPays.setForeground(Color.WHITE);
		lblPays.setBounds(246, 143, 69, 14);
		searchPanel.add(lblPays);
		
		cboxPays = new JComboBox(pays);
		cboxPays.setBounds(349, 140, 85, 20);
		searchPanel.add(cboxPays);
		
		
		
		JLabel lblBgImg = new JLabel();
		//String path="/imgs/stars3.jpg";
		lblBgImg.setIcon(new ImageIcon(WinScreenTest1.class.getResource("/imgs/stars3-conv.jpg")));
		lblBgImg.setBounds(0, 0, 1019, 633);
		contentPane.add(lblBgImg);
		
	
		affichePanel.setVisible(false);
	
	}
	
	//une méthode pour stocker les Users, on utilise ici une ArrayList, chaque instance "User" créé sera insérer dans la liste "usersList"
	public ArrayList<User> ListUsers(){ 
		ArrayList<User> usersList = new ArrayList<User>();
		Statement st;
        ResultSet rs;
        PreparedStatement ps = null;
        if((this.cboxDateA.getModel().getSelectedItem().equals("") && this.cboxDateB.getModel().getSelectedItem().equals(""))||(this.cboxDateA.getModel().getSelectedItem().equals("") || this.cboxDateB.getModel().getSelectedItem().equals(""))){
        	if(this.cboxPays.getModel().getSelectedItem().equals("")){ //on cherche dans la data avec la date = null et pays = null  
		        try{
		        	System.out.println("Bouton Show Data : Les Dates et Pay ne sont pas séléctioné(s) dans les comboboxs !");
		        	
		        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
		            st = con.createStatement(); //on creer des statements pour extraire les données
		            String searchQuery = "SELECT * FROM user"; //Ici notre requêtes
		            rs = st.executeQuery(searchQuery); //exécuter
		            
		            /*
		            User user;
		            
		            while(rs.next()) //inserer les données récupérés depuis la bdd dans User
		            {
		                user = new User( rs.getInt("id"),
		                				 rs.getString("nom"),
		                				 rs.getInt("age"),
		                				 rs.getDate("date"),
		                				 rs.getBoolean("covid-19"),
		                				 rs.getString("pays"),
		                				 rs.getInt("distance"));
		                usersList.add(user); //ensuite ajouter user dans la liste
		            }
		            */
		            
		            usersList = addTuUsersList(rs, usersList);
		        }catch(Exception ex){
		            System.out.println(ex.getMessage());
		        }
		        
		        return usersList;
        	}else { //on cherche dans la data avec la date = null et pays = non null (sinon)
        		try {
        			System.out.println("Bouton Show Data : Les Dates ne sont pas séléctioné(s) dans les comboboxs, pays = "+this.cboxPays.getModel().getSelectedItem()+" !");
        			
	        		Connection con = ConnexionJM.connecterDB(); //connexion au bdd
	  	            st = con.createStatement(); //on creer des statements pour extraire les données
	  	            String searchQuery = "SELECT * FROM user WHERE pays ='"+this.cboxPays.getModel().getSelectedItem()+"'"; //Ici notre requêtes
	  	            rs = st.executeQuery(searchQuery); //exécuter
	  	            
	  	           
	  	            usersList = addTuUsersList(rs, usersList);
	  	        }catch(Exception ex){
	  	            System.out.println(ex.getMessage());
	  	        }
	  	        
	  	        return usersList;
        	}
  	        	
        }else{
        	if(this.cboxPays.getModel().getSelectedItem().equals("")){ //on cherche dans la data avec la date = non null et pays = null 
        
	        	 try{
	        		 System.out.println("Bouton Show Data : Les Dates sont séléctioné(s) : "+cboxDateA.getModel().getSelectedItem()+" et "+cboxDateB.getModel().getSelectedItem()+" dans les comboboxs, pays non !");
	        		 
	 	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
	 	            st = con.createStatement(); //on creer des statements pour extraire les données
	 	            String searchQuery = "SELECT * FROM user WHERE date BETWEEN '"+cboxDateA.getModel().getSelectedItem()+"-01-01' AND '"+cboxDateB.getModel().getSelectedItem()+"-01-01'"; //Ici notre requêtes
	 	            rs = st.executeQuery(searchQuery); //exécuter
	 	           
	 	            usersList = addTuUsersList(rs, usersList);
	 	        }catch(Exception ex){
	 	            System.out.println(ex.getMessage());
	 	        }
	 	        
	 	        return usersList;
        	}else { //on cherche dans la data avec la date = non null et pays = non null (sinon)
	        	 try{
	        		 System.out.println("Bouton Show Data : Les Dates sont séléctioné(s) : "+cboxDateA.getModel().getSelectedItem()+" et "+cboxDateB.getModel().getSelectedItem()+" dans les comboboxs, pays ='"+this.cboxPays.getModel().getSelectedItem()+"' !");
	        		 
	  	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
	  	            st = con.createStatement(); //on creer des statements pour extraire les données
	  	            String searchQuery = "SELECT * FROM user WHERE date BETWEEN '"+cboxDateA.getModel().getSelectedItem()+"-01-01' AND '"+cboxDateB.getModel().getSelectedItem()+"-01-01' AND pays = '"+this.cboxPays.getModel().getSelectedItem()+"'"; //Ici notre requêtes
	  	            rs = st.executeQuery(searchQuery); //exécuter
	  	            
	  	           
	  	          usersList = addTuUsersList(rs, usersList);
	  	        }catch(Exception ex){
	  	            System.out.println(ex.getMessage());
	  	        }
	  	        
	  	        return usersList;
        	}
        }
	}
	
	public ArrayList<User> addTuUsersList (ResultSet rs, ArrayList<User> usersList){
		  User user;
            
           
                try { 
                	System.out.println("Adding values to instance User...");
	                while(rs.next()) //inserer les données récupérés depuis la bdd dans User
	                {
						user = new User( rs.getInt("id"),
										 rs.getString("nom"),
										 rs.getInt("age"),
										 rs.getDate("date"),
										 rs.getBoolean("covid-19"),
										 rs.getString("pays"),
										 rs.getInt("distance")); 
						usersList.add(user); //ensuite ajouter user dans la liste
	                }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              	return usersList;
            }
	
	public void showUserData(){ //afficher les données récupérés dans JTable
		ArrayList<User> users = ListUsers();
		DefaultTableModel model = (DefaultTableModel)table2.getModel();
		Object[] row = new Object[7];
        
        for(int i = 0; i < users.size(); i++) 
        {
            row[0] = users.get(i).getId();
            row[1] = users.get(i).getName();
            row[2] = users.get(i).getAge();
            row[3] = users.get(i).getDate();  
            row[4] = users.get(i).getCovid19();
            row[5] = users.get(i).getPays();  
            row[6] = users.get(i).getDistance();  
            model.addRow(row);
        }
       // table2.setModel(model);
       
	}
	
	/*******************************************************************************************************************************/
	
	public ArrayList<User> ListUserSearch(String ValToSearch)
    {
        ArrayList<User> usersList = new ArrayList<User>();
        
        Statement st;
        ResultSet rs;
        if((this.cboxDateA.getModel().getSelectedItem().equals("") && this.cboxDateB.getModel().getSelectedItem().equals(""))||(this.cboxDateA.getModel().getSelectedItem().equals("") || this.cboxDateB.getModel().getSelectedItem().equals(""))){
        	if(this.cboxPays.getModel().getSelectedItem().equals("")){
		        try{
		        	System.out.println("Bouton Search : Les Dates et Pay ne sont pas séléctioné(s) dans les Comboboxs !");
		        	
		        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
		            st = con.createStatement(); //on creer des statements pour extraire les données
		            String searchQuery = "SELECT * FROM user WHERE CONCAT(`id`, `nom`, `age`) LIKE '%"+ValToSearch+"%'"; //Ici notre requêtes
		            rs = st.executeQuery(searchQuery); //exécuter
		            /*
		            User user;
		            
		            if(isNullOrEmpty(ValToSearch)) {
			        	   System.out.println("-----> Error : No text found\n");
			        	   lblMsgErreur2.setVisible(true);
			        	   affichePanel.setVisible(false);
			           }else {
				            if (!rs.isBeforeFirst()) {  
				            	System.out.println("Scanning text...");
				            	System.out.println("Text : "+ValToSearch);
				            	System.out.println("Searching in data...");
				                System.out.println("-----> Error : No data found\n");
				                lblMsgErreur.setVisible(true);
				                lblMsgErreur2.setVisible(false);
				                affichePanel.setVisible(false);
				            }else { 
			             
					            while(rs.next()) {
					                user = new User(
					                                 rs.getInt("id"),
					                                 rs.getString("nom"),
					                                 rs.getInt("age"),
					                                 rs.getDate("date"),
					                                 rs.getBoolean("covid-19"),
					                                 rs.getString("pays"),
					                                 rs.getInt("distance")
					                                );
					                usersList.add(user);
					            }
				            }
			           }
			           */
		            usersList = addTuUsersListWithSearchBar(rs,usersList,ValToSearch);
		        }catch(Exception ex){
		            System.out.println(ex.getMessage());
		        }
		        
		        return usersList;
        	}else {
        		try {
        			System.out.println("Bouton Search : Les Dates ne sont pas séléctioné(s) dans les comboboxs, pays = "+this.cboxPays.getModel().getSelectedItem()+" !");
        			
	        		Connection con = ConnexionJM.connecterDB(); //connexion au bdd
	  	            st = con.createStatement(); //on creer des statements pour extraire les données
	  	            String searchQuery = "SELECT * FROM WHERE CONCAT(`id`, `nom`, `age`) LIKE '%"+ValToSearch+"%' AND pays ='"+this.cboxPays.getModel().getSelectedItem()+"'"; //Ici notre requêtes
	  	            rs = st.executeQuery(searchQuery); //exécuter
	  	            
	  	           
	  	          usersList = addTuUsersListWithSearchBar(rs,usersList,ValToSearch);
	        }catch(Exception ex){
	            System.out.println(ex.getMessage());
	        }
	        
	        return usersList;
        	}
  	        	
        }else{
        	if(this.cboxPays.getModel().getSelectedItem().equals("")){
        
	        	 try{
	        		 System.out.println("Bouton Show Data : Les Dates sont séléctioné(s) : "+cboxDateA.getModel().getSelectedItem()+" et "+cboxDateB.getModel().getSelectedItem()+" dans les comboboxs, pays non !");
		        		
	 	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
	 	            st = con.createStatement(); //on creer des statements pour extraire les données
	 	            String searchQuery = "SELECT * FROM user WHERE CONCAT(`id`, `nom`, `age`) LIKE '%"+ValToSearch+"%' AND date BETWEEN '"+cboxDateA.getModel().getSelectedItem()+"-01-01' AND '"+cboxDateB.getModel().getSelectedItem()+"-12-31'"; //Ici notre requêtes
	 	            rs = st.executeQuery(searchQuery); //exécuter
	 	            
	 	           
	 	            usersList = addTuUsersListWithSearchBar(rs,usersList,ValToSearch);
	        }catch(Exception ex){
	            System.out.println(ex.getMessage());
	        }
	        
	        return usersList;
        	}else {
	        	 try{
	        		 System.out.println("Bouton Show Data : Les Dates sont séléctioné(s) : "+cboxDateA.getModel().getSelectedItem()+" et "+cboxDateB.getModel().getSelectedItem()+" dans les comboboxs, pays ='"+this.cboxPays.getModel().getSelectedItem()+"' !");
	        		 
	  	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
	  	            st = con.createStatement(); //on creer des statements pour extraire les données
	  	            String searchQuery = "SELECT * FROM user WHERE CONCAT(`id`, `nom`, `age`) LIKE '%"+ValToSearch+"%' AND  date BETWEEN '"+cboxDateA.getModel().getSelectedItem()+"-01-01' AND '"+cboxDateB.getModel().getSelectedItem()+"-01-01' AND pays = '"+this.cboxPays.getModel().getSelectedItem()+"'"; //Ici notre requêtes
	  	            rs = st.executeQuery(searchQuery); //exécuter
	  	           
	  	            usersList = addTuUsersListWithSearchBar(rs,usersList,ValToSearch);
	        }catch(Exception ex){
	            System.out.println(ex.getMessage());
	        }
	        
	        return usersList;
        	}
        }
       
}  	/*******************************************************************************************************************************/
	public ArrayList<User> addTuUsersListWithSearchBar(ResultSet rs, ArrayList<User> usersList, String ValToSearch){
		User user;
	       try {
	    	   
	          if(isNullOrEmpty(ValToSearch)) {
	      	   System.out.println("-----> Error : No text found\n");
	      	   lblMsgErreur2.setVisible(true);
	      	   affichePanel.setVisible(false);
	         }else {
		            if (!rs.isBeforeFirst()) {  
		            	System.out.println("Scanning text...");
		            	System.out.println("Text : "+ValToSearch);
		            	System.out.println("Searching in data...");
		                System.out.println("-----> Error : No data found\n");
		                lblMsgErreur.setVisible(true);
		                lblMsgErreur2.setVisible(false);
		                affichePanel.setVisible(false);
		            }else { 
		            	System.out.println("Adding values to instance User...");
			            while(rs.next()) {
			                user = new User(
			                                 rs.getInt("id"),
			                                 rs.getString("nom"),
			                                 rs.getInt("age"),
			                                 rs.getDate("date"),
			                                 rs.getBoolean("covid-19"),
			                                 rs.getString("pays"),
			                                 rs.getInt("distance")
			                                );
			                usersList.add(user);
			            }
		            }
	          }
	       }catch(Exception ex){
	            System.out.println(ex.getMessage());
	        }
	       return usersList;
	}
	
	public void showUserSearch(){
		ArrayList<User> users = ListUserSearch(textField.getText());
		DefaultTableModel model = (DefaultTableModel)table2.getModel();
		Object[] row = new Object[7];
        
        for(int i = 0; i < users.size(); i++)
        {
            row[0] = users.get(i).getId();
            row[1] = users.get(i).getName();
            row[2] = users.get(i).getAge();
            row[3] = users.get(i).getDate();
            row[4] = users.get(i).getCovid19(); 
            row[5] = users.get(i).getPays();  
            row[6] = users.get(i).getDistance();  
            model.addRow(row);
        }
        
        table2.setModel(model);
       
	}
	
	public void cherchePays() {
		
        Statement st;
        ResultSet rs;
		
        try {
        	Connection con = ConnexionJM.connecterDB();
			st = con.createStatement();
			String searchQuery = "SELECT DISTINCT pays FROM `user`"; 
			rs = st.executeQuery(searchQuery);
			pays[0] = "";
			System.out.print("Searching country in data base : ");
			 while (rs.next()) {
				
				 System.out.print(rs.getString("pays")+" ");   
				 pays[i] = rs.getString("pays");
				 i++;
			 }
			System.out.println("\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}     
	
	public void chercheNomDuChamp() {
		int j = 0;
		Connection con = ConnexionJM.connecterDB();
		try {
			DatabaseMetaData dmd = con.getMetaData();
			ResultSet rs = dmd.getTables(con.getCatalog(),null,"%",null);
			
			
			ResultSet resultat = dmd.getColumns(con.getCatalog(),null,"user", "%"); 
			 System.out.println("###################################");  
			//affichage des informations 
			ResultSetMetaData rsmd = resultat.getMetaData();  
			
			/*
			while(resultat.next()){ 
				
				for(int k=1; k<rsmd.getColumnCount(); k++){  //afficher tout les informations du champs dans la BDD
					String col = rsmd.getColumnName(k+1); 
					Object val = resultat.getObject(k+1); 
					System.out.println(col+" = "+val); 
				} 
			}
			*/
			System.out.println("Insertion en cours...");
			while(resultat.next()){ 
			    for(int i=3; i<rsmd.getColumnCount(); i=i+21){  //on voulait que le nom du champs, le 1er commence a partir de la 3ème ligne et le (n+1)-ème commence 21 ligne apres le n, d'ou i=i+21
				//String col = rsmd.getColumnName(i+1); 
				Object val = resultat.getObject(i+1); 
				System.out.println("title["+j+"] = "+val); 
				String s = val.toString();
				title[j] = s;
				j++;
			    } 
			}
			 System.out.println("###################################"); 
			/*while(rs.next()){ 
				   System.out.println("###################################"); 
				   for(int i=0; i<rs.getMetaData().getColumnCount();i++){ 
				      String nomColonne = rs.getMetaData().getColumnName(i+1); 
				      Object valeurColonne = rs.getObject(i+1); 
				      System.out.println(nomColonne+" = "+valeurColonne); 
				   } 
			}*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void genererAnnee() {
		annee[0] = "";	
		System.out.print("Generating years : ");
		for(i=1;i<=119;i++) {
			System.out.print(j+" ");
			s=String.valueOf(j);
			annee[i] = s;	
			j++;
		}
}     
	public void clearTable() {
		DefaultTableModel model = (DefaultTableModel)table2.getModel();
		model.setRowCount(0);
	}
	
	public void initScreen() {
		affichePanel.setVisible(true);
		lblMsgErreur.setVisible(false);
		lblMsgErreur2.setVisible(false);
	}
	
	public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
}
