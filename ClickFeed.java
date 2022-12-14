package workshop;

import java.awt.EventQueue;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.List;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
	
public class ClickFeed {

	private JFrame frm;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClickFeed window = new ClickFeed();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClickFeed() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm = new JFrame("My Feed");
		
		frm.setSize(500, 500);
		 
		//TODO �θ� �������� ȭ�� ����� ��ġ
		frm.setLocationRelativeTo(null);
 
		//TODO �θ� �������� �ݾ��� �� �޸𸮿��� ���ŵǵ��� ����
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//TODO �θ� ������ â ũ�� ���� �ǽ�
		frm.setResizable(false);
 
		//TODO �θ� ���̾ƿ� ����
		frm.getContentPane().setLayout(null);	
		
		//TODO �θ� �������� ���̵��� ����
		frm.setVisible(true);
		
		JLabel jl = new JLabel("My Feed");
		jl.setHorizontalAlignment(jl.CENTER);
		jl.setSize(500,30);
		frm.getContentPane().add(jl, BorderLayout.NORTH);
		
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
			
		try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // jdbc driver load

            String url =  "jdbc:mysql://localhost/workshop";
            String user = "root";
            String passwd = "Spring030!";       // root ���� ��й�ȣ

            con = DriverManager.getConnection(url, user, passwd);
            
            System.out.println(con);
            System.out.println("MySQL Server Link Successful");

        } catch (Exception e) {
            e.getMessage();
        }
        PreparedStatement ps = null;        

        String text= "";
        String path = "";
        
    	try{
	    	Class.forName("com.mysql.cj.jdbc.Driver");//�̹���
	        stmt = con.createStatement();
	    	String qu = "select * from image";
	    	rs =  stmt.executeQuery(qu);
	        path = rs.getString(2); 
        }
        
        catch (Exception e){
            System.out.println(e);
        }

    	 JLabel lblImage = new JLabel();
		lblImage.setBounds(0, 30, 500, 250);
        //lblImage.setIcon(new ImageIcon("C:\\Users\\sally\\Pictures\\����캼.jpg"));
		lblImage.setIcon(new ImageIcon(path)); // index ó�� 0, IMAGES[0] �� ��� ����
		frm.getContentPane().add(lblImage);
		
		try{
	    	Class.forName("com.mysql.cj.jdbc.Driver");//�̹���
	        stmt = con.createStatement();
			String sql = "select * from comment";//��
	    	rs =  stmt.executeQuery(sql);
	    	text = rs.getString(1);
	    	System.out.println(text);
			//txtpnArticle.setText(text);
			if( rs != null ) rs.close();
	        if( stmt != null ) stmt.close();
	        if( con != null ) con.close();
	    	
	        }
        catch (Exception e){
            System.out.println(e);
        }
		
		JTextArea txtpnArticle = new JTextArea();
		txtpnArticle.setBounds(0, 290, 500, 250);
		txtpnArticle.setText(text);
		frm.getContentPane().add(txtpnArticle);
		txtpnArticle.setEditable(false); //���� �Ұ���
	
	}

}
