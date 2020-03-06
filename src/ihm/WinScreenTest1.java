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
import moteur.User;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTable;







public class WinScreenTest1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTable table2 = new JTable();
	JLabel lblMsgErreur = new JLabel();
	JLabel lblMsgErreur2 = new JLabel();
	
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
	 */
	public WinScreenTest1() {
		show_user();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(156, 11, 634, 167);
		contentPane.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(26, 11, 471, 50);
		buttonPanel.add(textField);
		textField.setColumns(10);
		
		
		
		JPanel affichePanel = new JPanel();
		affichePanel.setBounds(156, 241, 634, 238);
		contentPane.add(affichePanel);
		affichePanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 636, 238);
		affichePanel.add(scrollPane);
		
		table2 = new JTable();
		table2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nom", "Age"
			}
		));
		scrollPane.setViewportView(table2);
		
		
		
		lblMsgErreur = new JLabel("Information introuvable ! Veuillez recommencer ! ");
		lblMsgErreur.setBounds(180, 61, 322, 14);
		buttonPanel.add(lblMsgErreur);
		lblMsgErreur.setVisible(false);
		
		lblMsgErreur2 = new JLabel("Veuillez saisir les informations dans la barre de recherche");
		lblMsgErreur2.setBounds(115, 61, 382, 14);
		buttonPanel.add(lblMsgErreur2);
		lblMsgErreur2.setVisible(false);
		 
		JButton btnShowAllData = new JButton("Show all data ! ");
		btnShowAllData.setBackground(Color.WHITE);
		btnShowAllData.setIcon(new ImageIcon(WinScreenTest1.class.getResource("/imgs/logo/icons8-bookmark-64.png")));
		btnShowAllData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				lblMsgErreur.setVisible(false);
				lblMsgErreur2.setVisible(false);
				clearTable(); 
				show_user();
					
			}
			
		});
		
		btnShowAllData.setBounds(26, 84, 199, 72);
		buttonPanel.add(btnShowAllData);
		
		JButton btnOk = new JButton("");
		btnOk.setBackground(Color.WHITE);
		btnOk.setIcon(new ImageIcon(WinScreenTest1.class.getResource("/imgs/logo/icons8-search-64.png")));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblMsgErreur.setVisible(false);
				lblMsgErreur2.setVisible(false);
				clearTable(); 
				find_user();
				
			}
			
		});
		btnOk.setBounds(507, 11, 97, 75);
		buttonPanel.add(btnOk);
		
		
		
		JLabel lblNewLabel = new JLabel();
		//String path="/imgs/stars3.jpg";
		lblNewLabel.setIcon(new ImageIcon(WinScreenTest1.class.getResource("/imgs/stars3.jpg")));
		lblNewLabel.setBounds(0, 0, 913, 547);
		contentPane.add(lblNewLabel);
		
		/*
		ImageIcon myImage = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imgs/stars2.jpg")));
		Image newImage = myImage.getImage(); 
		Image newImage2 = newImage.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon finalImage = new ImageIcon(newImage);
		*/
		
		/*
		ImageIcon icon = new ImageIcon("/imgs/stars3.jpg");
		Image img = icon.getImage();
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
		ImageIcon finalImage = new ImageIcon(bi); 
		*/
		
		//ImageIcon finalImage = new ImageIcon(new ImageIcon("/imgs/stars3.jpg").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		/*
		ImageIcon icon = new ImageIcon("/imgs/stars1.jpg");
		Image image = icon.getImage();
		ImageIcon finalImage = new ImageIcon(fitimage(image, lblNewLabel.getWidth(), lblNewLabel.getHeight()));
		lblNewLabel.setIcon(finalImage);
		*/
		
		
	}
	private Image fitimage(Image img , int w , int h){
	    BufferedImage resizedimage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedimage.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(img, 0, 0,w,h,null);
	    g2.dispose();
	    return resizedimage;
	    
	    
	}
	
	public ArrayList<User> ListUsers(){
		ArrayList<User> usersList = new ArrayList<User>();
		Statement st;
        ResultSet rs;
        PreparedStatement ps = null;
        
        try{
        	Connection con = ConnexionJM.connecterDB();
            st = con.createStatement();
            String searchQuery = "SELECT * FROM user";
            rs = st.executeQuery(searchQuery);
            
            User user;
            
            while(rs.next())
            {
                user = new User( rs.getInt("id"),rs.getString("nom"),rs.getInt("age"));
                usersList.add(user);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return usersList;
	}
	
	public void show_user(){
		ArrayList<User> users = ListUsers();
		DefaultTableModel model = (DefaultTableModel)table2.getModel();
		Object[] row = new Object[3];
        
        for(int i = 0; i < users.size(); i++)
        {
            row[0] = users.get(i).getId();
            row[1] = users.get(i).getName();
            row[2] = users.get(i).getAge();
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
        	   System.out.println("No text in textField");
        	   lblMsgErreur2.setVisible(true);
           }else {
	            if (!rs.isBeforeFirst()) {    
	                System.out.println("No data found");
	                lblMsgErreur.setVisible(true);
	                lblMsgErreur2.setVisible(false);
	            }else { 
             
		            while(rs.next()) {
		                user = new User(
		                                 rs.getInt("id"),
		                                 rs.getString("nom"),
		                                 rs.getInt("age")
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
		Object[] row = new Object[3];
        
        for(int i = 0; i < users.size(); i++)
        {
            row[0] = users.get(i).getId();
            row[1] = users.get(i).getName();
            row[2] = users.get(i).getAge();
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
