package workshop;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.List;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
   
public class Follower {

   private JFrame frm;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Follower window = new Follower();
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public Follower() {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      frm = new JFrame("팔로워");
      
      frm.setSize(250, 500);
       
      //TODO 부모 프레임을 화면 가운데에 배치
      frm.setLocationRelativeTo(null);
 
      //TODO 부모 프레임을 닫았을 때 메모리에서 제거되도록 설정
      frm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      
      //TODO 부모 프레임 창 크기 고정 실시
      frm.setResizable(false);
 
      //TODO 부모 레이아웃 설정
      frm.getContentPane().setLayout(null);   
      
      //TODO 부모 프레임이 보이도록 설정
      frm.setVisible(true);

      
      JLabel jl = new JLabel("follower");
      jl.setHorizontalAlignment(jl.CENTER);
      jl.setSize(250,30);
      frm.getContentPane().add(jl, BorderLayout.NORTH);
      
      Scrollbar scrollbar = new Scrollbar();
      frm.getContentPane().add(scrollbar, BorderLayout.EAST);
      
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
        final DefaultListModel<String> lm = new DefaultListModel<>();        
        String follower = "";
        
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
           String qu = "select * from follow";
           rs =  stmt.executeQuery(qu);
           int i = 1;
           while(rs.next()) {
              follower = rs.getString(1);
              lm.addElement(i +". follower:    "+follower);
              i++;
              }
           if( rs != null ) rs.close();
           if( stmt != null ) stmt.close();
           if( con != null ) con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
         final JList<String> ls = new JList<>(lm);
         ls.setBounds(0,30,250,500);
      
         frm.add(ls);
         frm.setLayout(null);
         frm.setVisible(true);
         frm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      
   }

}