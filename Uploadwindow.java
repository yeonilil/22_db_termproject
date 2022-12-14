package workshop;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import workshop.Uploadwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

 

public class Uploadwindow extends JFrame implements ActionListener {

    private JButton upload_bt;
    private JLabel label;
    private JTextArea iddisplay;
    private JLabel label2;
    private JTextArea messagedisplay;
    
    private JTextField idinput;
    private JTextField messageinput;
    
    String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost/workshop";
	String dbname = "workshop";
	String id = "root";
	String pwd = "Spring030!";
	Connection con = null; 
    

    Main o = null;
   public Uploadwindow(Main _o){
	 //DB 연결
	   o = _o;
	   
		  try {
	            Class.forName(driver);
	            con = DriverManager.getConnection(url, id, pwd);
	            System.out.println("upload>>> 드라이버 로딩 성공");
	        } catch (Exception edb) {
	            System.out.println("드라이버 로딩 실패 ");
	             }
		  
  
      //창 크기 조절
       setSize(500,500);
       //창 제목 설정
      setTitle("Feed");
      //전체 배경 설정
      setBackground(Color.WHITE);
      //배치 관리자 설정
      setLayout(new FlowLayout());
      // 프레임바 우측상단에 X버튼에 대한 강제종료 기능 지정
      //setDefaultCloseOperation(Uploadwindow.EXIT_ON_CLOSE);
      this.addWindowListener(new WindowAdapter(){
          public void windowClosing(WindowEvent e) { 
               dispose();
          }
  });
      
      
      //panel 생성
       JPanel uploadPanel = new JPanel();
           //배경색 흰색으로
       uploadPanel.setBackground(Color.WHITE);
       uploadPanel.setPreferredSize(new Dimension(500, 500));
       uploadPanel.setLayout(null);
       
       //USER ID 입력창 or 태그하고자 하는 상대 ID입력
       JLabel label = new JLabel();
        label.setText("▶  ID");
        
        //ID 메시지창
        iddisplay=new JTextArea(11,30);
        iddisplay.setEditable(false);
         
         JScrollPane scroll = new JScrollPane(iddisplay);
         scroll.setViewportView(iddisplay);
         uploadPanel.add(scroll);
         
         idinput=new JTextField(30);
         idinput.addActionListener(this);
   
        // idinput.addActionListener(this);
         uploadPanel.add(idinput);
        
         
        //업로드하고자 하는 메시지 내용 입력
        JLabel label2 = new JLabel();
        label2.setText("▶  message ");
        
        //게시물 메시지창
        messagedisplay=new JTextArea(11,30);
        messagedisplay.setEditable(false);
         
         JScrollPane scroll2 = new JScrollPane(messagedisplay);
         scroll2.setViewportView(messagedisplay);
         uploadPanel.add(scroll2);
         
         messageinput=new JTextField(30);
         messageinput.addActionListener(this);

        // messageinput.addActionListener(this);
         uploadPanel.add(messageinput);
        
        
        //게시글 업로드 버튼
        ImageIcon upload_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/Upload_button.png");
        Image upload_image_png = upload_image.getImage();  //ImageIcon을 Image로 변환.
        Image upload_image2_png = upload_image_png.getScaledInstance(220, 200, java.awt.Image.SCALE_SMOOTH);
        ImageIcon ub = new ImageIcon(upload_image2_png); //Image로 ImageIcon 생성
        
        upload_bt=new JButton(ub);
        //버튼 테두리 없애기
        upload_bt.setBorderPainted(false);
        upload_bt.setContentAreaFilled(false);
        upload_bt.setFocusPainted(false);
        upload_bt.addActionListener(this);
         
        
        
        //위치 조정
        label.setBounds(70, 20, 340, 30);
        idinput.setBounds(70, 60, 340, 20);
        label2.setBounds(70, 80, 340, 30);
        messageinput.setBounds(70, 120, 340, 60);
        upload_bt.setBounds(70, 200, 340, 30);
        iddisplay.setBounds(30, 20, 340, 30);
        
        uploadPanel.add(label);
        uploadPanel.add(label2);
        uploadPanel.add(upload_bt);
    
        add(uploadPanel);
        
   }
   


@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub)
	
	if(upload_bt.equals(e.getSource())) {
		String idtext = idinput.getText();
		String messagetext = messageinput.getText();
		System.out.println("\nID: "+idtext);
		System.out.println("MESSAGE: "+messagetext);
		
		// id,message 데이터 DB 저장
		// post_id setting -> DB 전체 select 필요
		  String sql1="select * from post";
		  PreparedStatement pstmt=null;
		  int count=1;
		  int post_ID=0;
		  int temp=0;
	
		  
		  try {
			  pstmt=con.prepareStatement(sql1);
			  ResultSet rs1= pstmt.executeQuery(sql1);
			  while (rs1.next()) {
						 post_ID=rs1.getInt("post_id");
						 if(post_ID<2000) {
							 if(post_ID>temp) 
								 temp=post_ID;
						 }
						 else {
							 continue;
						 }
						 
					 }
					 post_ID=temp+1;
		  }//postID들 중 최대값에 +1 더해 각자 고유값 가지도록 
			  catch(Exception ee1) {
				  System.out.println("ERROR: "+ ee1.getMessage());
			  }
			 

		  //post DB 에 데이터 저장
		  String sql2="insert into post values (?,?,?,?)";
		  PreparedStatement pstmt2=null;
	
		  try {
			 pstmt2=con.prepareStatement(sql2);
			 
				  pstmt2.setString(1,messagetext);
				  pstmt2.setString(2,idtext);
				  pstmt2.setInt(3,count);
				  pstmt2.setInt(4,post_ID);
				  int result=pstmt2.executeUpdate();
				  if(result==1) {
			      System.out.println("데이터 입력 성공");
			      con.close();
				  System.out.println("---> board ID: "+count);
				  System.out.println("---> insert ID: "+idtext);
				  System.out.println("---> MESSAGE: "+messagetext);
				  System.out.println("---> post_id: "+post_ID);
				  
				  }
				  else {
					  System.out.println("데이터 입력 실패");
			  }
			 
		   }catch(Exception ee) {
			  System.out.println("ERROR: "+ ee.getMessage());
		  }
	}

}
}



