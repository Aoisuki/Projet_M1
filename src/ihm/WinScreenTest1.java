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
import moteur.User2;

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
	private JTable table3 = new JTable();
	private JTable table4 = new JTable();
	private JLabel lblMsgErreur = new JLabel();
	private JLabel lblMsgErreur2 = new JLabel();
	private JPanel affichePanel = new JPanel();
	private JComboBox cboxDateA = new JComboBox();
	private JComboBox cboxDateB = new JComboBox();
	private JComboBox cboxPays = new JComboBox();
	private String pays[] = new String[42];
	private String annee[] = new String[120];
	private String title[] = new String[9]; //changer la taille max pour plus de valeur
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
		setBounds(100, 100, 1023, 662);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel boutonPanel = new JPanel();
		boutonPanel.setBounds(24, 45, 232, 555);
		contentPane.add(boutonPanel);
		boutonPanel.setLayout(null);
		boutonPanel.setBackground(new Color(0,0,0,0));
		
		JLabel lblTrierPar = new JLabel("Sort by :");
		lblTrierPar.setForeground(Color.WHITE);
		lblTrierPar.setBounds(83, 60, 98, 14);
		boutonPanel.add(lblTrierPar);
		
		JButton btnTrierDateDESC = new JButton("Date (DESC)");
		btnTrierDateDESC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initScreen(); //enlever les messages d'erreur 
				clearTable(); //initialisé le tableau 
				showTrierData(1);
			}
		});
		btnTrierDateDESC.setBackground(Color.WHITE);
		btnTrierDateDESC.setBounds(10, 109, 192, 34);
		boutonPanel.add(btnTrierDateDESC);
		
		JButton btnTrierDateASC = new JButton("Date (ASC)");
		btnTrierDateASC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initScreen(); //enlever les messages d'erreur 
				clearTable(); //initialisé le tableau 
				showTrierData(2);
			}
		});
		btnTrierDateASC.setBackground(Color.WHITE);
		btnTrierDateASC.setBounds(10, 154, 192, 34);
		boutonPanel.add(btnTrierDateASC);
		
		JButton btnTrierNom = new JButton("Name");
		btnTrierNom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initScreen(); //enlever les messages d'erreur 
				clearTable(); //initialisé le tableau 
				showTrierData(3);
			}
		});
		btnTrierNom.setBackground(Color.WHITE);
		btnTrierNom.setBounds(10, 199, 192, 34);
		boutonPanel.add(btnTrierNom);
		
		JButton btnTrierPays = new JButton("Country");
		btnTrierPays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initScreen(); //enlever les messages d'erreur 
				clearTable(); //initialisé le tableau 
				showTrierData(4);
			}
		});
		btnTrierPays.setBackground(Color.WHITE);
		btnTrierPays.setBounds(10, 244, 192, 34);
		boutonPanel.add(btnTrierPays);
		
		JButton btnTrierType = new JButton("Type");
		btnTrierType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initScreen(); //enlever les messages d'erreur 
				clearTable(); //initialisé le tableau 
				showTrierData(5);
			}
		});
		btnTrierType.setBackground(Color.WHITE);
		btnTrierType.setBounds(10, 334, 192, 34);
		boutonPanel.add(btnTrierType);
		
		JButton btnTrierOrigin = new JButton("Origin");
		btnTrierOrigin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initScreen(); //enlever les messages d'erreur 
				clearTable(); //initialisé le tableau 
				showTrierData(6);
			}
		});
		btnTrierOrigin.setBackground(Color.WHITE);
		btnTrierOrigin.setBounds(10, 289, 192, 34);
		boutonPanel.add(btnTrierOrigin);
		
		JButton btnTrierDesti = new JButton("Destination");
		btnTrierDesti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initScreen(); //enlever les messages d'erreur 
				clearTable(); //initialisé le tableau 
				showTrierData(7);
			}
		});
		btnTrierDesti.setBackground(Color.WHITE);
		btnTrierDesti.setBounds(10, 379, 192, 34);
		boutonPanel.add(btnTrierDesti);
		
		JButton btnTrierDistanceDESC = new JButton("Distance (D)");
		btnTrierDistanceDESC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initScreen(); //enlever les messages d'erreur 
				clearTable(); //initialisé le tableau 
				showTrierData(8);
			}
		});
		btnTrierDistanceDESC.setBackground(Color.WHITE);
		btnTrierDistanceDESC.setBounds(10, 424, 192, 34);
		boutonPanel.add(btnTrierDistanceDESC);
		
		JButton btnDistanceASC = new JButton("Distance (A)");
		btnDistanceASC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initScreen(); //enlever les messages d'erreur 
				clearTable(); //initialisé le tableau 
				showTrierData(9);
			}
		});
		btnDistanceASC.setBackground(Color.WHITE);
		btnDistanceASC.setBounds(10, 477, 192, 34);
		boutonPanel.add(btnDistanceASC);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(285, 45, 666, 239);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);
		searchPanel.setBackground(new Color(0,0,0,0)); //Background panel tranparent !! <3 contraire : il faut mettre Color(0,0,0,100)
		
		textField = new JTextField();
		textField.setBounds(26, 11, 471, 50);
		searchPanel.add(textField);
		textField.setColumns(10);
		
		
		affichePanel.setBounds(266, 303, 729, 239);
		contentPane.add(affichePanel);
		affichePanel.setLayout(null);
		affichePanel.setBackground(new Color(0,0,0,0));
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 728, 238);
		//scrollPane.setBackground(new Color(0,0,0,0));
		affichePanel.add(scrollPane);
		
		
		table2 = new JTable();
		table2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			title
		));
		//scrollPane.setViewportView(table2);
		
		table3 = new JTable();
		table3.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Top", "Name", "Country", "Time(s)"
				}
		));
		//scrollPane.setViewportView(table3);
		
		table4 = new JTable();
		table4.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Top", "Country", "Time(s)"
				}
		));
		
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
		
		btnShowAllData.setBounds(392, 153, 212, 72);
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
		cboxDateA.setBounds(129, 179, 85, 20);
		searchPanel.add(cboxDateA);
		
		cboxDateB = new JComboBox(annee);
		cboxDateB.setBounds(272, 179, 85, 20);
		searchPanel.add(cboxDateB);
		
		JLabel lblSelectDate = new JLabel("Date between : ");
		lblSelectDate.setBounds(26, 182, 93, 14);
		lblSelectDate.setForeground(Color.white);
		searchPanel.add(lblSelectDate);
		
		JLabel lblAnd = new JLabel("and");
		lblAnd.setForeground(Color.WHITE);
		lblAnd.setBounds(236, 182, 46, 14);
		searchPanel.add(lblAnd);
		
		JLabel lblPays = new JLabel("Country :");
		lblPays.setForeground(Color.WHITE);
		lblPays.setBounds(26, 214, 69, 14);
		searchPanel.add(lblPays);
		
		cboxPays = new JComboBox(pays);
		cboxPays.setBounds(129, 211, 85, 20);
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
		            String searchQuery = "SELECT * FROM stages"; //Ici notre requêtes
		            rs = st.executeQuery(searchQuery); //exécuter
		            
		            
		            usersList = addToUsersList(rs, usersList);
		            
		        }catch(Exception ex){
		            System.out.println(ex.getMessage());
		        }
		        
		        return usersList;
        	}else { //on cherche dans la data avec la date = null et pays = non null (sinon)
        		try {
        			System.out.println("Bouton Show Data : Les Dates ne sont pas séléctioné(s) dans les comboboxs, pays = "+this.cboxPays.getModel().getSelectedItem()+" !");
        			
	        		Connection con = ConnexionJM.connecterDB(); //connexion au bdd
	  	            st = con.createStatement(); //on creer des statements pour extraire les données
	  	            String searchQuery = "SELECT * FROM stages WHERE winner_country ='"+this.cboxPays.getModel().getSelectedItem()+"'"; //Ici notre requêtes
	  	            rs = st.executeQuery(searchQuery); //exécuter
	  	            
	  	           
	  	            usersList = addToUsersList(rs, usersList);
	  	            
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
	 	            String searchQuery = "SELECT * FROM stages WHERE date BETWEEN '"+cboxDateA.getModel().getSelectedItem()+"-01-01' AND '"+cboxDateB.getModel().getSelectedItem()+"-01-01'"; //Ici notre requêtes
	 	            rs = st.executeQuery(searchQuery); //exécuter
	 	           
	 	            usersList = addToUsersList(rs, usersList);
	 	            
	 	        }catch(Exception ex){
	 	            System.out.println(ex.getMessage());
	 	        }
	 	        
	 	        return usersList;
        	}else { //on cherche dans la data avec la date = non null et pays = non null (sinon)
	        	 try{
	        		 System.out.println("Bouton Show Data : Les Dates sont séléctioné(s) : "+cboxDateA.getModel().getSelectedItem()+" et "+cboxDateB.getModel().getSelectedItem()+" dans les comboboxs, pays ='"+this.cboxPays.getModel().getSelectedItem()+"' !");
	        		 
	  	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
	  	            st = con.createStatement(); //on creer des statements pour extraire les données
	  	            String searchQuery = "SELECT * FROM stages WHERE date BETWEEN '"+cboxDateA.getModel().getSelectedItem()+"-01-01' AND '"+cboxDateB.getModel().getSelectedItem()+"-01-01' AND pays = '"+this.cboxPays.getModel().getSelectedItem()+"'"; //Ici notre requêtes
	  	            rs = st.executeQuery(searchQuery); //exécuter
	  	            
	  	           
	  	          usersList = addToUsersList(rs, usersList);
	  	          
	  	        }catch(Exception ex){
	  	            System.out.println(ex.getMessage());
	  	        }
	  	        
	  	        return usersList;
        	}
        }
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------//
	
	public ArrayList<User> ListUsersTrier(int a){ // if a = 1 (Date) if a = 2 (name) if a = 3 (country)
		ArrayList<User> usersList = new ArrayList<User>();
		Statement st;
        ResultSet rs;
        PreparedStatement ps = null;
        
        if(a == 1) { // Date DESC
	        try{
	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
	            st = con.createStatement(); //on creer des statements pour extraire les données
	            String searchQuery = "SELECT * FROM stages ORDER BY date DESC"; //Ici notre requêtes pour trier
	            rs = st.executeQuery(searchQuery); //exécuter
	            
	            
	            usersList = addToUsersList(rs, usersList);
	            
	        }catch(Exception ex){
	            System.out.println(ex.getMessage());
	        }
	        
        }else if(a == 2){ // Date ASC
        	  try{
  	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
  	            st = con.createStatement(); //on creer des statements pour extraire les données
  	            String searchQuery = "SELECT * FROM stages ORDER BY date ASC"; //Ici notre requêtes pour trier
  	            rs = st.executeQuery(searchQuery); //exécuter
  	            
  	            
  	            usersList = addToUsersList(rs, usersList);
  	            
  	        }catch(Exception ex){
  	            System.out.println(ex.getMessage());
  	        }
        }else if(a == 3){ // Nom ou winner
        	 try{
   	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
   	            st = con.createStatement(); //on creer des statements pour extraire les données
   	            String searchQuery = "SELECT * FROM stages ORDER BY winner ASC"; //Ici notre requêtes pour trier
   	            rs = st.executeQuery(searchQuery); //exécuter
   	            
   	            
   	            usersList = addToUsersList(rs, usersList);
   	            
   	        }catch(Exception ex){
   	            System.out.println(ex.getMessage());
   	        }
        }else if(a == 4){ // Pays ou winner_country
	       	 try{
	  	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
	  	            st = con.createStatement(); //on creer des statements pour extraire les données
	  	            String searchQuery = "SELECT * FROM stages ORDER BY winner_country ASC"; //Ici notre requêtes pour trier
	  	            rs = st.executeQuery(searchQuery); //exécuter
	  	           
	  	            
	  	            usersList = addToUsersList(rs, usersList);
	  	            
	  	        }catch(Exception ex){
	  	            System.out.println(ex.getMessage());
	  	        }
        }else if(a == 5){ // type
		       	 try{
		  	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
		  	            st = con.createStatement(); //on creer des statements pour extraire les données
		  	            String searchQuery = "SELECT * FROM stages ORDER BY type ASC"; //Ici notre requêtes pour trier
		  	            rs = st.executeQuery(searchQuery); //exécuter
		  	           
		  	            
		  	            usersList = addToUsersList(rs, usersList);
		  	            
		  	        }catch(Exception ex){
		  	            System.out.println(ex.getMessage());
		  	        }
        }else if(a == 6){ // origine
	       	 try{
	  	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
	  	            st = con.createStatement(); //on creer des statements pour extraire les données
	  	            String searchQuery = "SELECT * FROM stages ORDER BY origin ASC"; //Ici notre requêtes pour trier
	  	            rs = st.executeQuery(searchQuery); //exécuter
	  	            
	  	            
	  	            usersList = addToUsersList(rs, usersList);
	  	            
	  	        }catch(Exception ex){
	  	            System.out.println(ex.getMessage());
	  	        }
        }else if(a == 7){ // destination
	       	 try{
	  	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
	  	            st = con.createStatement(); //on creer des statements pour extraire les données
	  	            String searchQuery = "SELECT * FROM stages ORDER BY destination ASC"; //Ici notre requêtes pour trier
	  	            rs = st.executeQuery(searchQuery); //exécuter
	  	            
	  	            
	  	            usersList = addToUsersList(rs, usersList);
	  	            
	  	        }catch(Exception ex){
	  	            System.out.println(ex.getMessage());
	  	        }
        }else if(a == 8){ // distance DESC
	       	 try{
	  	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
	  	            st = con.createStatement(); //on creer des statements pour extraire les données
	  	            String searchQuery = "SELECT * FROM stages ORDER BY distance DESC"; //Ici notre requêtes pour trier
	  	            rs = st.executeQuery(searchQuery); //exécuter
	  	            
	  	            
	  	            usersList = addToUsersList(rs, usersList);
	  	            
	  	        }catch(Exception ex){
	  	            System.out.println(ex.getMessage());
	  	        }
        }else if(a == 9){ // distance ASC
		       	 try{
		  	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
		  	            st = con.createStatement(); //on creer des statements pour extraire les données
		  	            String searchQuery = "SELECT * FROM stages ORDER BY distance ASC"; //Ici notre requêtes pour trier
		  	            rs = st.executeQuery(searchQuery); //exécuter
		  	           
		  	            
		  	            usersList = addToUsersList(rs, usersList);
		  	            
		  	        }catch(Exception ex){
		  	            System.out.println(ex.getMessage());
		  	        }
        }else{
        	
        } 
        return usersList;
	}
	

	public ArrayList<User2> ListTopRank(int a){ // if a = 1 (Player) if a = 2 (Countries)
		ArrayList<User2> usersList = new ArrayList<User2>();
		Statement st;
        ResultSet rs;
        PreparedStatement ps = null;
        
        if(a == 1){ // best player
	       	 try{
	  	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
	  	            st = con.createStatement(); //on creer des statements pour extraire les données
	  	            String searchQuery = "SELECT * FROM ( SELECT *,count(winner) as mycount FROM stages GROUP BY winner )s GROUP BY mycount DESC LIMIT 5 "; //Ici notre requêtes pour trier
	  	            rs = st.executeQuery(searchQuery); //exécuter
	  	  
	  	            
	  	            usersList = addToUsers2List(rs, usersList);
	  	            
	  	        }catch(Exception ex){
	  	            System.out.println(ex.getMessage());
	  	        }
       }else if(a == 2){ // best country top 5
		       	 try{
		  	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
		  	            st = con.createStatement(); //on creer des statements pour extraire les données
		  	            String searchQuery = "SELECT * FROM ( SELECT *,count(winner_country) as mycount FROM stages GROUP BY winner_country )s GROUP BY mycount DESC LIMIT 5 "; //Ici notre requêtes pour trier 
		  	            rs = st.executeQuery(searchQuery); //exécuter
		  	         
		  	            
		  	            usersList = addToUsers2List(rs, usersList);
		  	            
		  	        }catch(Exception ex){
		  	            System.out.println(ex.getMessage());
		  	        }
       }else{
       	
       } 
        return usersList;   
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------//
	
	public ArrayList<User> addToUsersList (ResultSet rs, ArrayList<User> usersList){
		  User user;
            
           
                try { 
                	System.out.println("Adding values to instance User...");
	                while(rs.next()) //inserer les données récupérés depuis la bdd dans User
	                {
						user = new User( rs.getInt("id"),
										 rs.getString("stage"),
										 rs.getDate("date"),
										 rs.getFloat("distance"),
										 rs.getString("origin"),
										 rs.getString("destination"),
										 rs.getString("type"),
										 rs.getString("winner"),
										 rs.getString("winner_country")); 
						
						usersList.add(user); //ensuite ajouter user dans la liste
	                }
	     
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              	return usersList;
            }
	
	public ArrayList<User2> addToUsers2List (ResultSet rs, ArrayList<User2> users2List){
		  User2 user;
          
         
              try { 
              	System.out.println("Adding values to instance User...");
	                while(rs.next()) //inserer les données récupérés depuis la bdd dans User
	                {
						user = new User2( rs.getInt("id"),
										 rs.getString("stage"),
										 rs.getDate("date"),
										 rs.getFloat("distance"),
										 rs.getString("origin"),
										 rs.getString("destination"),
										 rs.getString("type"),
										 rs.getString("winner"),
										 rs.getString("winner_country"),
										 rs.getInt("mycount")
										 ); 
						
						users2List.add(user); //ensuite ajouter user dans la liste
	                }
	     
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	return users2List;
          }
	
	public void showUserData(){ //afficher les données récupérés dans JTable
		ArrayList<User> users = ListUsers();
		DefaultTableModel model = (DefaultTableModel)table2.getModel();
		Object[] row = new Object[9];
        
        for(int i = 0; i < users.size(); i++) 
        {
            row[0] = users.get(i).getId();
            row[1] = users.get(i).getStage();
            row[2] = users.get(i).getDate();
            row[3] = users.get(i).getDistance();  
            row[4] = users.get(i).getOrigin();
            row[5] = users.get(i).getDestination();  
            row[6] = users.get(i).getType();  
            row[7] = users.get(i).getWinner();  
            row[8] = users.get(i).getWinnerCountry();  
            model.addRow(row);
        }
       // table2.setModel(model);
        scrollPane.setViewportView(table2);
       
	}
	
	public void showTrierData(int a){ //afficher les données récupérés dans JTable
		int numInstruction = a;
		ArrayList<User> users = ListUsersTrier(numInstruction);
		DefaultTableModel model = (DefaultTableModel)table2.getModel();
		Object[] row = new Object[9];
        
        for(int i = 0; i < users.size(); i++) 
        {
        	 row[0] = users.get(i).getId();
             row[1] = users.get(i).getStage();
             row[2] = users.get(i).getDate();
             row[3] = users.get(i).getDistance();  
             row[4] = users.get(i).getOrigin();
             row[5] = users.get(i).getDestination();  
             row[6] = users.get(i).getType();  
             row[7] = users.get(i).getWinner();  
             row[8] = users.get(i).getWinnerCountry(); 
            model.addRow(row);
        }
        table2.setModel(model);
        scrollPane.setViewportView(table2);
       
	}
	
	public void showBestStat(int a){
		int numInstruction = a;
			if(numInstruction == 10) {
			//ArrayList<User> users = ListUsersTrier(10);
			ArrayList<User2> users = ListTopRank(1);
			DefaultTableModel model = (DefaultTableModel)table3.getModel();
			Object[] row = new Object[4];
			  for(int i = 0; i < users.size(); i++) 
		        {
				     row[0] = i+1;
		        	 row[1] = users.get(i).getWinner();
		             row[2] = users.get(i).getWinnerCountry();
		             row[3] = users.get(i).getCmp();
		             model.addRow(row);
		        }
			 table3.setModel(model);
			 scrollPane.setViewportView(table3);
			 
		}else if(numInstruction == 11){
			//ArrayList<User> users = ListUsersTrier(11);
			ArrayList<User2> users = ListTopRank(2);
			DefaultTableModel model = (DefaultTableModel)table4.getModel();
			Object[] row = new Object[3];
			  for(int i = 0; i < users.size(); i++) 
		        {
				  	 row[0] = i+1;
		        	 row[1] = users.get(i).getWinnerCountry();
		        	 row[2] = users.get(i).getCmp();
		             model.addRow(row);
		        }
			 table4.setModel(model);
			 scrollPane.setViewportView(table4);
		}else {
			System.out.println("Erreur dans le code ! Fait attention au numéro d'instruction");
		}
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
		            String searchQuery = "SELECT * FROM stages WHERE CONCAT(`origin`, `destination`, `type`, `winner`) LIKE '%"+ValToSearch+"%'"; //Ici notre requêtes
		            rs = st.executeQuery(searchQuery); //exécuter
		           
		            usersList = addToUsersListWithSearchBar(rs,usersList,ValToSearch);
		            
		        }catch(Exception ex){
		            System.out.println(ex.getMessage());
		        }
		        
		        return usersList;
        	}else {
        		try {
        			System.out.println("Bouton Search : Les Dates ne sont pas séléctioné(s) dans les comboboxs, pays = "+this.cboxPays.getModel().getSelectedItem()+" !");
        			
	        		Connection con = ConnexionJM.connecterDB(); //connexion au bdd
	  	            st = con.createStatement(); //on creer des statements pour extraire les données
	  	            String searchQuery = "SELECT * FROM stages WHERE CONCAT(`origin`, `destination`, `type`, `winner`) LIKE '%"+ValToSearch+"%' AND pays ='"+this.cboxPays.getModel().getSelectedItem()+"'"; //Ici notre requêtes
	  	            rs = st.executeQuery(searchQuery); //exécuter
	  	            
	  	           
	  	          usersList = addToUsersListWithSearchBar(rs,usersList,ValToSearch);
	  	          
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
	 	            String searchQuery = "SELECT * FROM stages WHERE CONCAT(`origin`, `destination`, `type`, `winner`) LIKE '%"+ValToSearch+"%' AND date BETWEEN '"+cboxDateA.getModel().getSelectedItem()+"-01-01' AND '"+cboxDateB.getModel().getSelectedItem()+"-12-31'"; //Ici notre requêtes
	 	            rs = st.executeQuery(searchQuery); //exécuter
	 	            
	 	           
	 	            usersList = addToUsersListWithSearchBar(rs,usersList,ValToSearch);
	 	            
	        }catch(Exception ex){
	            System.out.println(ex.getMessage());
	        }
	        
	        return usersList;
        	}else {
	        	 try{
	        		 System.out.println("Bouton Show Data : Les Dates sont séléctioné(s) : "+cboxDateA.getModel().getSelectedItem()+" et "+cboxDateB.getModel().getSelectedItem()+" dans les comboboxs, pays ='"+this.cboxPays.getModel().getSelectedItem()+"' !");
	        		 
	  	        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
	  	            st = con.createStatement(); //on creer des statements pour extraire les données
	  	            String searchQuery = "SELECT * FROM stages WHERE CONCAT(`origin`, `destination`, `type`, `winner`) LIKE '%"+ValToSearch+"%' AND  date BETWEEN '"+cboxDateA.getModel().getSelectedItem()+"-01-01' AND '"+cboxDateB.getModel().getSelectedItem()+"-01-01' AND pays = '"+this.cboxPays.getModel().getSelectedItem()+"'"; //Ici notre requêtes
	  	            rs = st.executeQuery(searchQuery); //exécuter
	  	           
	  	            usersList = addToUsersListWithSearchBar(rs,usersList,ValToSearch);
	  	            
	        }catch(Exception ex){
	            System.out.println(ex.getMessage());
	        }
	        
	        return usersList;
        	}
        }
       
}  	/*******************************************************************************************************************************/
	public ArrayList<User> addToUsersListWithSearchBar(ResultSet rs, ArrayList<User> usersList, String ValToSearch){
		User user;
	       try {
	    	  System.out.println("Studying the values and conditions...");
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
										 rs.getString("stage"),
										 rs.getDate("date"),
										 rs.getFloat("distance"),
										 rs.getString("origin"),
										 rs.getString("destination"),
										 rs.getString("type"),
										 rs.getString("winner"),
										 rs.getString("winner_country")
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
		Object[] row = new Object[9];
        
        for(int i = 0; i < users.size(); i++)
        {
        	 row[0] = users.get(i).getId();
             row[1] = users.get(i).getStage();
             row[2] = users.get(i).getDate();
             row[3] = users.get(i).getDistance();  
             row[4] = users.get(i).getOrigin();
             row[5] = users.get(i).getDestination();  
             row[6] = users.get(i).getType();  
             row[7] = users.get(i).getWinner();  
             row[8] = users.get(i).getWinnerCountry(); 
            model.addRow(row);
        }
        
        table2.setModel(model);
        scrollPane.setViewportView(table2);
       
	}
	
	public void cherchePays() {
		
        Statement st;
        ResultSet rs;
		
        try {
        	Connection con = ConnexionJM.connecterDB();
			st = con.createStatement();
			String searchQuery = "SELECT DISTINCT winner_country FROM `stages`"; 
			rs = st.executeQuery(searchQuery);
			pays[0] = "";
			System.out.print("Searching country in data base : ");
			 while (rs.next()) {
				
				 System.out.print(rs.getString("winner_country")+" ");   
				 pays[i] = rs.getString("winner_country");
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
			
			
			ResultSet resultat = dmd.getColumns(con.getCatalog(),null,"stages", "%"); 
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
	
	public void clearTable2() {
		DefaultTableModel model = (DefaultTableModel)table3.getModel();
		model.setRowCount(0);
	
	}
	
	public void clearTable3() {
		DefaultTableModel model = (DefaultTableModel)table4.getModel();
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
