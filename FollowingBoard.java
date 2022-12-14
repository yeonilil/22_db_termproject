package workshop;

import java.awt.*;
import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.URL;
import java.net.MalformedURLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowingBoard extends JFrame implements ActionListener{
	//�ȷ��� ��� ���̰� 
	//�ȷ��� �ϴ� ����� �̸� ���� �� ���� ���尡 ���� 
	//�ش� user�� ���̵�� ��۴ޱ� ����
	
	private JTextArea followerList;
	private JPanel followingPanel;
	private JScrollPane scroll;
	private JTextField inputTag;
	private JLabel list_label;
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost/workshop";
	String dbname = "workshop";
	String id = "root";
	String pwd = "Spring030!";
	Connection con = null; 
	String user_id;
	String your_id;
	
	Main o = null;
	public FollowingBoard(Main _o){
		o = _o;
		//â ũ�� ����
		setSize(500,500);
	    //â ���� ����
		setTitle("Following List");
		//��ġ ������ ����
		setLayout(null);
		// �����ӹ� ������ܿ� X��ư�� ���� �������� ��� ����
	    this.addWindowListener(new WindowAdapter(){
	          public void windowClosing(WindowEvent e) { 
	               dispose();
	          }
	  });
			
	    
	    	//��ü panel ����
	    	followingPanel = new JPanel();
	    	followingPanel.setPreferredSize(new Dimension(510, 650));
	    	followingPanel.setLayout(null);//��ġ������ �������� - ������ġ ���
	    	followingPanel.setBounds(0, 0, 510, 650);
	    	followingPanel.setBackground(Color.WHITE);
	    	
	    	//�ȷο� ��� �̹��� ��
	    	 ImageIcon list_lb= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/FollowingList_label.png");
		        Image list_lb_png = list_lb.getImage();  //ImageIcon�� Image�� ��ȯ.
		        Image list_lb2_png = list_lb_png.getScaledInstance(150, 22, java.awt.Image.SCALE_SMOOTH);
		        ImageIcon list_LB = new ImageIcon(list_lb2_png); //Image�� ImageIcon ����
		      
		        list_label=new JLabel(list_LB);

			    followingPanel.add(list_label);
			    
	    	//�ȷο� ��� â ����
	    	   
	    	followerList=new JTextArea(11,30);
	    	followerList.setEditable(false);
		    followingPanel.add(followerList);
		    followerList.append("\n �� If you enter @USER, You can go to the user's board\n");
		    followerList.append("\n");
		    //�ȷο� ��Ͽ� DB ������ �ҷ�����
		    
		    //DB ����
			  try {
		            System.out.println("������");
		            Class.forName(driver);
		            con = DriverManager.getConnection(url, id, pwd);
		            System.out.println("�ȷ��� ����Ʈ ����̹� �ε� ����");
		        } catch (Exception e) {
		            System.out.println("�ȷ��� ����Ʈ ����̹� �ε� ���� ");
		            
		        }
		    String sql = "SELECT * FROM follow";
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				
				 pstmt = con.prepareStatement(sql); 
				 rs = pstmt.executeQuery();
				while (rs.next()) {
					  user_id=rs.getString("my_id");
					  your_id=rs.getString("your_id");
					  
					System.out.println("�ȷο� ����Ʈ ������ ����");
					System.out.println("�ҷ����� --> my_id: "+user_id);
					System.out.println("�ҷ����� --> following_id: "+your_id);
					
					followerList.append("   FOLLOWING: "+your_id+"\n");
				}
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}	
		
			
		    
		    
		    //�ȷο� ��� ��ũ�� ��� �߰�
		    scroll = new JScrollPane(followerList);
		    scroll.setViewportView(followerList);
		    followingPanel.add(scroll);
		      

	    	//�ȷο� ���� �Է��� �� �ִ� �Է� â
		    inputTag=new JTextField(30);
		    inputTag.addActionListener(this); 
		    followingPanel.add(inputTag);
		    
		    list_label.setBounds(20, 32, 200, 20);
		    scroll.setBounds(50, 60, 380, 300); // �޽��� �Է�â
		    inputTag.setBounds(50, 375, 380, 40);
	    
	    
	    add(followingPanel);
	    
	    
		
	}
	
	public void actionPerformed(ActionEvent e) {
		 
		if(e.getSource()==inputTag) {
			//@�ȷ��� ���̵� �Է½� �ش� ����� �̵�
			//@yeonilil �Է� �� �ش� ���� ����� �̵� -> �� â ����
			String following_ID_goboard =inputTag.getText();
			System.out.println(following_ID_goboard);
			String tag=following_ID_goboard.substring(0,1);
			System.out.println(tag);
			
			if(tag.equals("@")) {
				try {
					//board_id ���ڷ� �Ѱܾ���
					o.fbw.setVisible(true);
					}
				catch (Exception ea) {
					System.out.println(ea.getMessage());
				}	
				
			}
			String tag_ID="";
			
		}
	}
	
}
