package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

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
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextPane;

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
		
		String song = "Oh you heard\r\n" + 
				"What they say\r\n" + 
				"Oh, the more things change\r\n" + 
				"The more they stay the same\r\n" + 
				"Ain't that a shame? (I know it's a shame)\r\n" + 
				"I've been good\r\n" + 
				"For some time\r\n" + 
				"I'd be lying if I said that\r\n" + 
				"You ain't on my mind\r\n" + 
				"Been tryin' to give it some time\r\n" + 
				"Feeling like I'm runnin' away\r\n" + 
				"Never had the chance, chance to say\r\n" + 
				"I can say that loving you is easy\r\n" + 
				"I don't need to prove a single thing\r\n" + 
				"Somewhere along the way I guess you got under my skin\r\n" + 
				"I put all my cards out on the table\r\n" + 
				"You ain't ever gonna show your hand\r\n" + 
				"I would rather hold you close than try to understand";
		
		JLabel lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(WindowScreen.class.getResource("/imgs/cute_poop.jpg")));
		lblImg.setBounds(152, 45, 282, 198);
		displayPanel.add(lblImg);
		lblImg.setVisible(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(29, 11, 549, 266);
		displayPanel.add(scrollPane);
		scrollPane.setVisible(false);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setShowVerticalLines(false);
		table.setGridColor(new Color(105, 105, 105));
		table.setSelectionBackground(new Color(95, 158, 160));
		table.setRowHeight(25);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFocusable(false);
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(32, 136, 203));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.setRowHeight(24);
		
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
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 11, 593, 298);
		textPane.setText(song);
		displayPanel.add(textPane);
		textPane.setVisible(false);
		
		
		JButton btnNewButton = new JButton("Test text!! ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setVisible(true);
				lblImg.setVisible(false);
				scrollPane.setVisible(false);
			}
		});
		btnNewButton.setBounds(36, 103, 103, 23);
		searchPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Test image!!");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setVisible(false);
				lblImg.setVisible(true);
				scrollPane.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(250, 103, 103, 23);
		searchPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Test JTable!!");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setVisible(false);
				lblImg.setVisible(false);
				scrollPane.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(459, 103, 117, 23);
		searchPanel.add(btnNewButton_2);
		
	}
}
