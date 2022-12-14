package workshop;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Profile extends JFrame { // Main Gui
    JPanel basePanel = new JPanel(new BorderLayout());
    
    JPanel centerPanel = new JPanel(new BorderLayout());
    JPanel westPanel = new JPanel();
    JPanel eastPanel = new JPanel();
    JPanel southPanel = new JPanel();

    private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtArticleCount;
	private JTextField txtShortMessage;
	private JTextField txtId;
	
	Profile(Main _o) throws IOException {
		Main o = _o;
		
		File file = new File("my_id.txt");
			
		BufferedReader br = new BufferedReader(new FileReader(file)); //¼±¾ð
		String my_id = br.readLine(); 
			ImageIcon mypage_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/kk2.png");
	        Image mypage_image_png = mypage_image.getImage();  //ImageIconÀ» Image·Î º¯È¯.
	        Image mypage_image2_png = mypage_image_png.getScaledInstance(73, 65, java.awt.Image.SCALE_SMOOTH);
	        ImageIcon mypage = new ImageIcon(mypage_image2_png); //Image·Î ImageIcon »ý¼º
	        
	        ImageIcon change_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/ch.png");
	        Image change_image_png = change_image.getImage();  //ImageIconÀ» Image·Î º¯È¯.
	        Image change_image2_png = change_image_png.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
	        ImageIcon change = new ImageIcon(change_image2_png); //Image·Î ImageIcon »ý¼º
	        
		        
			String msg = o.db.message(my_id);
			String followingNum = Integer.toString(o.db.FollowingNum(my_id));
			String followerNum = Integer.toString(o.db.FollowerNum(my_id));

	        /* Panel Å©±â */
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setBackground(Color.WHITE);
			
			setContentPane(contentPane);
	    
			
		
			JButton btnNewButton = new JButton("follower");
			btnNewButton.setBackground(SystemColor.menu);
			btnNewButton.setFont(new Font("±¼¸²", Font.BOLD | Font.ITALIC, 11));
			btnNewButton.setBounds(106, 119, 91, 23);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Follower();					
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("following");
		btnNewButton_1.setBackground(SystemColor.menu);
		btnNewButton_1.setFont(new Font("±¼¸²", Font.BOLD | Font.ITALIC, 11));
		btnNewButton_1.setBounds(209, 119, 91, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Following();
			}
		});
		contentPane.add(btnNewButton_1);
		
		JButton btn1 = new JButton("peed01");	
		contentPane.add(btn1);
		btn1.setBounds(29, 136, 119, 107);
		btn1.setBackground(Color.white);
		btn1.addActionListener(new ActionListener() {	//btn1À» ´­·¶À» ¶§ È¿°úÃß°¡
			public void actionPerformed(ActionEvent e) {	
				ClickFeed cf = new ClickFeed();
			}
		});
		JButton btn2 = new JButton("peed02");	
		contentPane.add(btn2);
		btn1.setBounds(160, 136, 119, 107);
		btn1.setBackground(Color.white);
		btn1.addActionListener(new ActionListener() {	//btn1À» ´­·¶À» ¶§ È¿°úÃß°¡
			public void actionPerformed(ActionEvent e) {	
				ClickFeed cf = new ClickFeed();
			}
		});
		
		JButton btn3 = new JButton("peed03");	
		contentPane.add(btn3);
		btn1.setBounds(291, 136, 119, 107);
		btn1.setBackground(Color.white);
		btn1.addActionListener(new ActionListener() {	//btn1À» ´­·¶À» ¶§ È¿°úÃß°¡
			public void actionPerformed(ActionEvent e) {	
				ClickFeed cf = new ClickFeed();
			}
		});
		JTextPane textField = new JTextPane();
		textField.setFont(new Font("±¼¸²", Font.ITALIC, 11));
		textField.setBackground(SystemColor.menu);
		textField.setBounds(227, 21, 59, 39);
		textField.setText(" follower\n" + "     " + followerNum);
		contentPane.add(textField);

		JTextPane textField1 = new JTextPane();
		textField1.setFont(new Font("±¼¸²", Font.ITALIC, 11));
		textField1.setBackground(SystemColor.menu);
		textField1.setBounds(291, 21, 59, 39);
		textField1.setText("following\n" + "     " + followingNum);
		contentPane.add(textField1);

		
		JTextPane txtArticleCount = new JTextPane();
		txtArticleCount.setFont(new Font("±¼¸²", Font.ITALIC, 11));
		txtArticleCount.setBackground(SystemColor.menu);
		txtArticleCount.setBounds(160,21, 59, 39);
		txtArticleCount.setText("  Article\r\n      0");
		contentPane.add(txtArticleCount);


		JTextPane txtShortMessage = new JTextPane();
		txtShortMessage.revalidate();
		txtShortMessage.repaint();
		txtShortMessage.setFont(new Font("±¼¸²", Font.ITALIC, 11));
		txtShortMessage.setBackground(SystemColor.menu);
		txtShortMessage.setBounds(29, 70, 331, 39);
		txtShortMessage.setText(msg);
		contentPane.add(txtShortMessage);
		SwingUtilities.updateComponentTreeUI(contentPane);
		contentPane.revalidate();
		
		

		JTextPane txtId = new JTextPane();
		txtId.setBounds(96, 21, 59, 21);
		txtId.setText("@" + my_id);
		contentPane.add(txtId);
		

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(370, 70, 40, 39);
		btnNewButton_3.setIcon(new ImageIcon(change_image2_png));
		btnNewButton_3.setBackground(Color.WHITE);
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		            o.cm.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_3);
		
		 btnNewButton_3.setBorderPainted(false);
	     btnNewButton_3.setContentAreaFilled(false);
	     btnNewButton_3.setFocusPainted(false);
		
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(mypage_image2_png));
		btnNewButton_2.setBounds(23, 10, 91, 50);
		btnNewButton_2.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	          
			}
		});
		
		 btnNewButton_2.setBorderPainted(false);
	     btnNewButton_2.setContentAreaFilled(false);
	     btnNewButton_2.setFocusPainted(false);
	     
	     
	}
	
}
