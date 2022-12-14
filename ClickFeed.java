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
		 
		//TODO 부모 프레임을 화면 가운데에 배치
		frm.setLocationRelativeTo(null);
 
		//TODO 부모 프레임을 닫았을 때 메모리에서 제거되도록 설정
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//TODO 부모 프레임 창 크기 고정 실시
		frm.setResizable(false);
 
		//TODO 부모 레이아웃 설정
		frm.getContentPane().setLayout(null);	
		
		//TODO 부모 프레임이 보이도록 설정
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
            String passwd = "Spring030!";       // root 계정 비밀번호

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
	    	Class.forName("com.mysql.cj.jdbc.Driver");//이미지
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
        //lblImage.setIcon(new ImageIcon("C:\\Users\\sally\\Pictures\\스노우볼.jpg"));
		lblImage.setIcon(new ImageIcon(path)); // index 처음 0, IMAGES[0] 랑 결과 같음
		frm.getContentPane().add(lblImage);
		
		try{
	    	Class.forName("com.mysql.cj.jdbc.Driver");//이미지
	        stmt = con.createStatement();
			String sql = "select * from comment";//글
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
		txtpnArticle.setEditable(false); //수정 불가능
	
	}

}
