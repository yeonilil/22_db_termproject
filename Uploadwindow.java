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
	 //DB ����
	   o = _o;
	   
		  try {
	            Class.forName(driver);
	            con = DriverManager.getConnection(url, id, pwd);
	            System.out.println("upload>>> ����̹� �ε� ����");
	        } catch (Exception edb) {
	            System.out.println("����̹� �ε� ���� ");
	             }
		  
  
      //â ũ�� ����
       setSize(500,500);
       //â ���� ����
      setTitle("Feed");
      //��ü ��� ����
      setBackground(Color.WHITE);
      //��ġ ������ ����
      setLayout(new FlowLayout());
      // �����ӹ� ������ܿ� X��ư�� ���� �������� ��� ����
      //setDefaultCloseOperation(Uploadwindow.EXIT_ON_CLOSE);
      this.addWindowListener(new WindowAdapter(){
          public void windowClosing(WindowEvent e) { 
               dispose();
          }
  });
      
      
      //panel ����
       JPanel uploadPanel = new JPanel();
           //���� �������
       uploadPanel.setBackground(Color.WHITE);
       uploadPanel.setPreferredSize(new Dimension(500, 500));
       uploadPanel.setLayout(null);
       
       //USER ID �Է�â or �±��ϰ��� �ϴ� ��� ID�Է�
       JLabel label = new JLabel();
        label.setText("��  ID");
        
        //ID �޽���â
        iddisplay=new JTextArea(11,30);
        iddisplay.setEditable(false);
         
         JScrollPane scroll = new JScrollPane(iddisplay);
         scroll.setViewportView(iddisplay);
         uploadPanel.add(scroll);
         
         idinput=new JTextField(30);
         idinput.addActionListener(this);
   
        // idinput.addActionListener(this);
         uploadPanel.add(idinput);
        
         
        //���ε��ϰ��� �ϴ� �޽��� ���� �Է�
        JLabel label2 = new JLabel();
        label2.setText("��  message ");
        
        //�Խù� �޽���â
        messagedisplay=new JTextArea(11,30);
        messagedisplay.setEditable(false);
         
         JScrollPane scroll2 = new JScrollPane(messagedisplay);
         scroll2.setViewportView(messagedisplay);
         uploadPanel.add(scroll2);
         
         messageinput=new JTextField(30);
         messageinput.addActionListener(this);

        // messageinput.addActionListener(this);
         uploadPanel.add(messageinput);
        
        
        //�Խñ� ���ε� ��ư
        ImageIcon upload_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/Upload_button.png");
        Image upload_image_png = upload_image.getImage();  //ImageIcon�� Image�� ��ȯ.
        Image upload_image2_png = upload_image_png.getScaledInstance(220, 200, java.awt.Image.SCALE_SMOOTH);
        ImageIcon ub = new ImageIcon(upload_image2_png); //Image�� ImageIcon ����
        
        upload_bt=new JButton(ub);
        //��ư �׵θ� ���ֱ�
        upload_bt.setBorderPainted(false);
        upload_bt.setContentAreaFilled(false);
        upload_bt.setFocusPainted(false);
        upload_bt.addActionListener(this);
         
        
        
        //��ġ ����
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
		
		// id,message ������ DB ����
		// post_id setting -> DB ��ü select �ʿ�
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
		  }//postID�� �� �ִ밪�� +1 ���� ���� ������ �������� 
			  catch(Exception ee1) {
				  System.out.println("ERROR: "+ ee1.getMessage());
			  }
			 

		  //post DB �� ������ ����
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
			      System.out.println("������ �Է� ����");
			      con.close();
				  System.out.println("---> board ID: "+count);
				  System.out.println("---> insert ID: "+idtext);
				  System.out.println("---> MESSAGE: "+messagetext);
				  System.out.println("---> post_id: "+post_ID);
				  
				  }
				  else {
					  System.out.println("������ �Է� ����");
			  }
			 
		   }catch(Exception ee) {
			  System.out.println("ERROR: "+ ee.getMessage());
		  }
	}

}
}



