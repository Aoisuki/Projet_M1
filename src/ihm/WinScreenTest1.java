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

	private JPanel contentPane;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTable table2 = new JTable();
	private JLabel lblMsgErreur = new JLabel();
	private JLabel lblMsgErreur2 = new JLabel();
	private JPanel affichePanel = new JPanel();
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
		show_user();
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
		
		JButton btnNom = new JButton("Nom");
		btnNom.setBackground(Color.WHITE);
		btnNom.setBounds(10, 81, 98, 34);
		boutonPanel.add(btnNom);
		
		JButton btnPays = new JButton("Pays");
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
			new String[] {
				"Id", "Nom", "Age", "Date", "Pays", "Covid-19", "Distance"
			}
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
				
				affichePanel.setVisible(true);
				lblMsgErreur.setVisible(false);
				lblMsgErreur2.setVisible(false);
				clearTable(); 
				show_user();
					
			}
			
		});
		
		btnShowAllData.setBounds(26, 88, 199, 72);
		searchPanel.add(btnShowAllData);
		
		JButton btnOk = new JButton("");
		btnOk.setBackground(Color.WHITE);
		btnOk.setIcon(new ImageIcon(WinScreenTest1.class.getResource("/imgs/logo/icons8-search-64.png")));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				affichePanel.setVisible(true);
				lblMsgErreur.setVisible(false);
				lblMsgErreur2.setVisible(false);
				clearTable(); 
				find_user();
				
			}
			
		});
		btnOk.setBounds(507, 11, 97, 75);
		searchPanel.add(btnOk);
		String[] date = {"", "2011", "2012", "2013", "2014", "2015", "2016", "2017"}; 
		JComboBox cboxDateA = new JComboBox(date);
		cboxDateA.setBounds(349, 110, 85, 20);
		searchPanel.add(cboxDateA);
		
		JComboBox cboxDate2 = new JComboBox(date);
		cboxDate2.setBounds(504, 110, 85, 20);
		searchPanel.add(cboxDate2);
		
		JLabel lblSelectDate = new JLabel("Date between : ");
		lblSelectDate.setBounds(246, 113, 93, 14);
		lblSelectDate.setForeground(Color.white);
		searchPanel.add(lblSelectDate);
		
		JLabel lblAnd = new JLabel("and");
		lblAnd.setForeground(Color.WHITE);
		lblAnd.setBounds(456, 113, 46, 14);
		searchPanel.add(lblAnd);
		
		JLabel lblPays = new JLabel("Pays :");
		lblPays.setForeground(Color.WHITE);
		lblPays.setBounds(246, 143, 69, 14);
		searchPanel.add(lblPays);
		
		JComboBox cboxPays = new JComboBox();
		cboxPays.setBounds(349, 140, 85, 20);
		searchPanel.add(cboxPays);
		
		
		
		JLabel lblBgImg = new JLabel();
		//String path="/imgs/stars3.jpg";
		lblBgImg.setIcon(new ImageIcon(WinScreenTest1.class.getResource("/imgs/stars3-conv.jpg")));
		lblBgImg.setBounds(0, 0, 1019, 633);
		contentPane.add(lblBgImg);
		
	
		affichePanel.setVisible(false);
	
			
			
			
			


		
	}
	private Image fitimage(Image img , int w , int h){
	    BufferedImage resizedimage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedimage.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(img, 0, 0,w,h,null);
	    g2.dispose();
	    return resizedimage;
	    
	    
	}
	//une méthode pour stocker les Users, on utilise ici une ArrayList, chaque instance "User" créé sera insérer dans la liste "usersList"
	public ArrayList<User> ListUsers(){ 
		ArrayList<User> usersList = new ArrayList<User>();
		Statement st;
        ResultSet rs;
        PreparedStatement ps = null;
        
        try{
        	Connection con = ConnexionJM.connecterDB(); //connexion au bdd
            st = con.createStatement(); //on creer des statements pour extraire les données
            String searchQuery = "SELECT * FROM user"; //Ici notre requêtes
            rs = st.executeQuery(searchQuery); //exécuter
            
            User user;
            
            while(rs.next()) //inserer les données récupérés depuis la bdd dans User
            {
                user = new User( rs.getInt("id"),
                				 rs.getString("nom"),
                				 rs.getInt("age"),
                				 rs.getDate("date"),
                				 rs.getString("pays"),
                				 rs.getBoolean("covid-19"),
                				 rs.getInt("distance"));
                usersList.add(user); //ensuite ajouter user dans la liste
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return usersList;
	}
	
	public void show_user(){ //afficher les données récupérés dans JTable
		ArrayList<User> users = ListUsers();
		DefaultTableModel model = (DefaultTableModel)table2.getModel();
		Object[] row = new Object[7];
        
        for(int i = 0; i < users.size(); i++)
        {
            row[0] = users.get(i).getId();
            row[1] = users.get(i).getName();
            row[2] = users.get(i).getAge();
            row[3] = users.get(i).getDate();    
            row[4] = users.get(i).getPays();  
            row[5] = users.get(i).getCovid19();
            row[6] = users.get(i).getDistance();  
            model.addRow(row);
        }
       // table2.setModel(model);
       
	}
	public ArrayList<User> ListUsers2Find(String ValToSearch)
    {
        ArrayList<User> usersList = new ArrayList<User>();
        
        Statement st;
        ResultSet rs;
        
        try{
        	Connection con = ConnexionJM.connecterDB();
            st = con.createStatement();
            String searchQuery = "SELECT * FROM `user` WHERE CONCAT(`id`, `nom`, `age`) LIKE '%"+ValToSearch+"%'";
            rs = st.executeQuery(searchQuery);
            
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
		                                 rs.getString("pays"),
		                                 rs.getBoolean("covid-19"),
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
	public void find_user(){
		ArrayList<User> users = ListUsers2Find(textField.getText());
		DefaultTableModel model = (DefaultTableModel)table2.getModel();
		Object[] row = new Object[7];
        
        for(int i = 0; i < users.size(); i++)
        {
            row[0] = users.get(i).getId();
            row[1] = users.get(i).getName();
            row[2] = users.get(i).getAge();
            row[3] = users.get(i).getDate();    
            row[4] = users.get(i).getPays();  
            row[5] = users.get(i).getCovid19(); 
            row[6] = users.get(i).getDistance();  
            model.addRow(row);
        }
        
        table2.setModel(model);
       
	}
	
	public void clearTable() {
		DefaultTableModel model = (DefaultTableModel)table2.getModel();
		model.setRowCount(0);
	}
	
	public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
}
