package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.Color;

public class WindowScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowScreen frame = new WindowScreen();
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
	public WindowScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel optionPanel = new JPanel();
		optionPanel.setBounds(10, 11, 211, 488);
		contentPane.add(optionPanel);
		optionPanel.setLayout(null);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(231, 11, 613, 154);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);
		
		JPanel displayPanel = new JPanel();
		displayPanel.setBounds(231, 179, 613, 320);
		contentPane.add(displayPanel);
		displayPanel.setLayout(null);
		
		JLabel lblTxt = new JLabel("Test");
		lblTxt.setBounds(291, 119, 143, 53);
		displayPanel.add(lblTxt);
		lblTxt.setVisible(false);
		
		JLabel lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(WindowScreen.class.getResource("/imgs/cute_poop.jpg")));
		lblImg.setBounds(152, 45, 282, 198);
		displayPanel.add(lblImg);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(30, 24, 549, 285);
		displayPanel.add(scrollPane);
		scrollPane.setVisible(false);
		
		table = new JTable();
		table.setGridColor(new Color(105, 105, 105));
		table.setSelectionBackground(new Color(95, 158, 160));
		table.setRowHeight(25);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFocusable(false);
	
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "Roat", null, null},
				{"2", "Faiza", null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Stage", "Name", "Winner", "Country"
			}
		));
		scrollPane.setViewportView(table);
		lblImg.setVisible(false);
		
		
		JButton btnNewButton = new JButton("Test text!! ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTxt.setVisible(true);
				lblImg.setVisible(false);
				scrollPane.setVisible(false);
			}
		});
		btnNewButton.setBounds(36, 103, 103, 23);
		searchPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Test image!!");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTxt.setVisible(false);
				lblImg.setVisible(true);
				scrollPane.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(250, 103, 103, 23);
		searchPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Test JTable!!");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTxt.setVisible(false);
				lblImg.setVisible(false);
				scrollPane.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(459, 103, 117, 23);
		searchPanel.add(btnNewButton_2);
		
	}
}
