package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import moteur.ConnexionJM;
import moteur.FindFunction;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class WinScreenTest1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	StringBuilder builder = new StringBuilder();

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 875, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(10, 11, 839, 191);
		contentPane.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(179, 11, 471, 50);
		buttonPanel.add(textField);
		textField.setColumns(10);
		
		
		
		JPanel affichePanel = new JPanel();
		affichePanel.setBounds(10, 202, 839, 261);
		contentPane.add(affichePanel);
		affichePanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 22, 705, 211);
		affichePanel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setColumnHeaderView(textArea);
		textArea.setEditable(false);
		
		JLabel lblMsgErreur = new JLabel("Information introuvable ! Veuillez recommencer ! ");
		lblMsgErreur.setBounds(179, 72, 444, 14);
		buttonPanel.add(lblMsgErreur);
		lblMsgErreur.setVisible(false);
		
		JButton btnShowAllData = new JButton("Show all data ! ");
		btnShowAllData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection c = ConnexionJM.connecterDB();
					Statement st;
					ResultSet rst;
					
					
					st=c.createStatement();
					rst=st.executeQuery("SELECT * FROM user");
					
					FileWriter fw=new FileWriter("src/doc/text"); 
					String head = "id nom age" + '\n';
					fw.write(head);
					
						while(rst.next()) {
							System.out.print(rst.getInt("id")+"\t");
							System.out.print(rst.getString("nom")+"\t");
							System.out.print(rst.getInt("age")+"\n");
							//textArea.setText(rst.getInt("id")+"\t"+rst.getString("nom")+"\t"+rst.getInt("age")+"\t");
							
							// Accept a string  
							
							String id = String.valueOf(rst.getInt("id"))+" ";
							String age = String.valueOf(rst.getInt("age"))+" ";
					        String str = id + rst.getString("nom")+ " " + age + '\n'; 
					  
					        // attach a file to FileWriter  
					        // read character wise from string and write  
					        // into FileWriter  
					       
					        for (int i = 0; i < str.length(); i++) { 
					            fw.write(str.charAt(i));
					        }	
							
						}
						 fw.close();
						 
						//afficher dans textArea
						 try {
			                	FileReader reader = null;
			                	reader = new FileReader( "src/doc/text" );
			                	BufferedReader br = new BufferedReader(reader);
								textArea.read(br, null);
								br.close();
			                    textArea.requestFocus();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						
					}catch(Exception e1) {
						e1.printStackTrace();
					}
				
				
			}
		});
		
		btnShowAllData.setBounds(289, 135, 261, 23);
		buttonPanel.add(btnShowAllData);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rst = null;
				FindFunction f = new FindFunction();
				
				rst = f.find(textField.getText());
				textArea.setText("id\t"+"nom\t"+"age\t");
				try {
					if(rst.next()) {
						System.out.print(rst.getInt("id")+"\t");
						System.out.print(rst.getString("nom")+"\t");
						System.out.print(rst.getInt("age")+"\n");
						
						textArea.setText(rst.getInt("id")+"\t"+rst.getString("nom")+"\t"+rst.getInt("age")+"\t");
						System.out.println("ok");
					}else {
						
						System.out.println("pas ok");
						lblMsgErreur.setVisible(true);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnOk.setBounds(660, 11, 118, 50);
		buttonPanel.add(btnOk);
	}
	
	
}
