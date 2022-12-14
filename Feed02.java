package workshop;

import java.awt.*;
import javax.swing.*;

import java.sql.*;
import java.util.ArrayList;

import workshop.Feed02;

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
import java.util.Scanner;

public class Feed02 extends JFrame implements ActionListener {
   private JTextArea display;
   private JTextField input;

    private JPanel panel;
    private JTextField clientId, inputText;
    private JButton button;
    private JButton logo_button;
    private JButton search_button;
    private JButton mypage_button;
    private JButton reload_button;
    private JButton send_button,GO_followerboard;
    private JLabel label;
    private JDialog dialog;
    private Dimension frameSize, screenSize;
    private JTextArea chatting, clientList;
    private JScrollPane scrollPane;
    private JButton upload_window_bt;
    private JButton next_button;
   
    String driver = "com.mysql.cj.jdbc.Driver";
      String url = "jdbc:mysql://localhost/workshop";
      String dbname = "workshop";
      String id = "root";
      String pwd = "Spring030!";
      Connection con = null;  
       String user_feed_id=null;
       String content=null;
       
       int post_comment_id=0;
       String comment = null;
       int board_id,post_feed_id=0;
       String user_id=null;
       int postCount=0; 
       PreparedStatement pstmt33 = null;
       ResultSet rs33 = null;
       Profile pf = null;
       int comment_id =0;
         // Main o = null;
       
       //��� DB
       PreparedStatement pstmt4 = null;
       ResultSet rs4 = null;
       
       PreparedStatement pstmt5 = null;
       ResultSet rs5 = null;
         
       Main o = null;
   public Feed02(Main _o) throws Exception{
      o = _o;

      //DB ����
        try {
               System.out.println("������");
               Class.forName(driver);
               con = DriverManager.getConnection(url, id, pwd);
               System.out.println("����̹� �ε� ����");
           } catch (Exception e) {
               System.out.println("����̹� �ε� ���� ");
               try {
                   con.close();
               } catch (SQLException e1) {    }
           }
      
        //ID ��������
        
        try {
        Statement stmt=con.createStatement();
        String sql;
        sql="select * from user";
        ResultSet rs= stmt.executeQuery(sql);
        
        while(rs.next()) {
          user_id=rs.getString("user_id");
           System.out.println("ID: "+user_id);
        }} catch(Exception e) {
           System.out.println("ERROR: "+ e.getMessage());
        }
        
        
             
      //��ܺ� 
      
      //â ũ�� ����
       setSize(600,850);
       //â ���� ����
      setTitle("Feed");
      //��ü ��� ����
      getContentPane().setBackground(Color.WHITE);
      //��ġ ������ ����
      setLayout(new FlowLayout());
      //setLayout(new BorderLayout());
      // �����ӹ� ������ܿ� X��ư�� ���� �������� ��� ����
      setDefaultCloseOperation(Feed02.EXIT_ON_CLOSE);
      
      // ��ư�� �ֱ����� �г� ����
        JPanel buttonPanel = new JPanel();
        //���� �������
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(600, 85));

        ImageIcon logo_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/logo_button.png");
        //�̹��� ������ ����
        Image logo_image_png = logo_image.getImage();  //ImageIcon�� Image�� ��ȯ.
        Image logo_image2_png = logo_image_png.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(logo_image2_png); //Image�� ImageIcon ����
       
        ImageIcon search_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/search_button.png");
        Image search_image_png = search_image.getImage();  //ImageIcon�� Image�� ��ȯ.
        Image search_image2_png = search_image_png.getScaledInstance(350, 60, java.awt.Image.SCALE_SMOOTH);
        ImageIcon search = new ImageIcon(search_image2_png); //Image�� ImageIcon ����
        
        ImageIcon mypage_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/mypage_button.png");
        Image mypage_image_png = mypage_image.getImage();  //ImageIcon�� Image�� ��ȯ.
        Image mypage_image2_png = mypage_image_png.getScaledInstance(73, 65, java.awt.Image.SCALE_SMOOTH);
        ImageIcon mypage = new ImageIcon(mypage_image2_png); //Image�� ImageIcon ����
        
        logo_button=new JButton(logo);
        //��ư �׵θ� ���ֱ�
        logo_button.setBorderPainted(false);
        logo_button.setContentAreaFilled(false);
        logo_button.setFocusPainted(false);
        
     
         
        search_button=new JButton(search);
        search_button.setBorderPainted(false);
        search_button.setContentAreaFilled(false);
        search_button.setFocusPainted(false);
        
        search_button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
           o.sf.setVisible(true);
         }
      });
        
        mypage_button=new JButton(mypage);
        mypage_button.setBorderPainted(false);
        mypage_button.setContentAreaFilled(false);
        mypage_button.setFocusPainted(false);

        mypage_button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
              o.pf.setVisible(true);
         }
      });
        
     
        
        logo_button.setPreferredSize(new Dimension(55, 70));
        search_button.setPreferredSize(new Dimension(350, 80));
        mypage_button.setPreferredSize(new Dimension(60,80));
        

        
        add(buttonPanel);
        buttonPanel.add(logo_button);
        buttonPanel.add(search_button);
        buttonPanel.add(mypage_button);
        
      
         //�ϴܺ�
         
         JPanel bottomPanel = new JPanel();
         bottomPanel.setPreferredSize(new Dimension(510, 650));
         bottomPanel.setLayout(null);//��ġ������ �������� - ������ġ ���
         bottomPanel.setBounds(0, 100, 600, 400);
         bottomPanel.setBackground(Color.WHITE);
         //board
         
         ImageIcon board_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/USER_BOARD.png");
           Image board_image_png = board_image.getImage();  //ImageIcon�� Image�� ��ȯ.
           Image board_image2_png = board_image_png.getScaledInstance(100, 25, java.awt.Image.SCALE_SMOOTH);
           ImageIcon boarddd = new ImageIcon(board_image2_png); //Image�� ImageIcon ����
           
           JLabel boardlabel = new JLabel(boarddd);
           
         
        //User board ����!!
         
         display=new JTextArea(11,30);
         display.setEditable(false);
         
         JScrollPane scroll = new JScrollPane(display);
         scroll.setViewportView(display);
         bottomPanel.add(scroll);
         
         input=new JTextField(30);
         input.addActionListener(this);
         bottomPanel.add(input);
         
     
         
         //���� �� NEXT ��ư
         ImageIcon next_bt= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/next_button.png");
           Image next_button_png = next_bt.getImage();  //ImageIcon�� Image�� ��ȯ.
           Image next_button2_png = next_button_png.getScaledInstance(70, 120, java.awt.Image.SCALE_SMOOTH);
           ImageIcon next = new ImageIcon(next_button2_png); //Image�� ImageIcon ����
         
           next_button=new JButton(next);
           //��ư �׵θ� ���ֱ�
           next_button.setBorderPainted(false);
           next_button.setContentAreaFilled(false);
           next_button.setFocusPainted(false);
           
          //postCount �̸� �޾Ƶα�
           String sql22 = "SELECT * FROM post";
         PreparedStatement pstmt22 = null;
         ResultSet rs22 = null;
         
         try {
            
             pstmt22 = con.prepareStatement(sql22);
             rs22 = pstmt22.executeQuery();
            while (rs22.next()) {
                post_feed_id=rs22.getInt("post_id");
                if(post_feed_id<2000) {
                   if(post_feed_id>postCount) {
                      postCount=post_feed_id;
                      System.out.println("postCount ����");
                   }
                   else {
                      continue;
                   }
                   
                }
               
            }
         } 
         catch (Exception e) {
            System.out.println(e.getMessage());
         }   
         finally {
            try {
               pstmt22.close();
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
         }
           
           //���忡 �ǵ� ���� ���ε�
           //���� ���� ����
           String sql = "SELECT *" + "FROM post WHERE post_id=";
         PreparedStatement pstmt = null;
         ResultSet rs = null;

         try {
            
             pstmt = con.prepareStatement(sql + postCount); 
             rs = pstmt.executeQuery();
            while (rs.next()) {
                 content=rs.getString("content");
                 user_feed_id=rs.getString("user_id");
                 board_id=rs.getInt("board_id");
                post_feed_id=rs.getInt("post_id");
                postCount=post_feed_id;
                
                System.out.println("�ǵ� ������ ����");
               System.out.println("�ҷ����� --> user_feed_id: "+user_feed_id);
               System.out.println("�ҷ����� --> content: "+content);
               System.out.println("�ҷ����� --> board_id: "+board_id);
               System.out.println("�ҷ����� --> post_feed_id: "+post_feed_id);
               if(board_id==1) {
                  display.append("\n    ------------------------ POST"+postCount+" -------------------------\n\n");
                  display.append("\n\n    ["+user_feed_id+"]  "+content+"\n\n\n");
                  display.append("\n    ---------------------- COMMENT ----------------------\n\n");
                  
                  comment();
                  
               }
            }
         } 
         catch (Exception e) {
            System.out.println(e.getMessage());
         }   
         finally {
            try {
               pstmt.close();
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
         }
         
   
         
         
         //��ư �������� �������� �ֱ� �Խù� �ߵ���
      
          String sql33 = "SELECT * FROM post WHERE post_id=";
            
         next_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               try {
                  if(postCount==1) {
                     
                  }
                  else {
                     postCount=postCount-1;
                     pstmt33 = con.prepareStatement(sql33 + postCount); 
                      rs33 = pstmt33.executeQuery();
                       while (rs33.next()) {
                          content=rs33.getString("content");
                          user_feed_id=rs33.getString("user_id");
                         
                        System.out.println("���� ���� --> user_feed_id: "+user_feed_id);
                        System.out.println("���� ���� --> content: "+content);
                        display.setText("");
                        display.append("\n    ------------------------ POST"+postCount+" ------------------------\n\n");
                        display.append("\n\n    ["+user_feed_id+"]  "+content+"\n\n\n");
                        display.append("\n    ---------------------- COMMENT ----------------------\n\n");
                        
                        
                           comment(); //��� ���� �Լ�
                        
                  }
                  }
               } 
               catch (Exception ee) {
                  System.out.println("��ư ������ ����"+ee.getMessage());
               }   
               finally {
                  try {
                     pstmt33.close();
                  } catch (Exception ee) {
                     System.out.println("��ư ������ ����"+ee.getMessage());
                  }
               }
               
               
            }
            });
         
      
            
      
         //��� ������ ��ư
         ImageIcon send_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/send.png");
           Image send_image_png = send_image.getImage();  //ImageIcon�� Image�� ��ȯ.
           Image send_image2_png = send_image_png.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
           ImageIcon send = new ImageIcon(send_image2_png); //Image�� ImageIcon ����
         
           send_button=new JButton(send);
           //��ư �׵θ� ���ֱ�
           send_button.setBorderPainted(false);
           send_button.setContentAreaFilled(false);
           send_button.setFocusPainted(false);
           
           
           //���ε� ��ư
           upload_window_bt=new JButton("POST");
           //��ư �׵θ� ���ֱ�
          //upload_window_bt.setBorderPainted(false);
           upload_window_bt.setContentAreaFilled(false);
           upload_window_bt.setFocusPainted(false);
           
           //�ȷ��� ����� �̵� ��ư -> ��ư ���� �� �ȷο� ����� ��
           GO_followerboard=new JButton("FOLLOWING BOARD");
           //��ư �׵θ� ���ֱ�
          //upload_window_bt.setBorderPainted(false);
           GO_followerboard.setContentAreaFilled(false);
           GO_followerboard.setFocusPainted(false);
           
           ImageIcon post_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/post_button.png");
           Image post_image_png = post_image.getImage();  //ImageIcon�� Image�� ��ȯ.
           Image post_image2_png = post_image_png.getScaledInstance(220, 200, java.awt.Image.SCALE_SMOOTH);
           ImageIcon post = new ImageIcon(post_image2_png); //Image�� ImageIcon ����
           
           ImageIcon followingboard_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/FollowingBoard_button.png");
           Image followingboard_image_png = followingboard_image.getImage();  //ImageIcon�� Image�� ��ȯ.
           Image followingboard2_image_png = followingboard_image_png.getScaledInstance(220, 200, java.awt.Image.SCALE_SMOOTH);
           ImageIcon fb = new ImageIcon(followingboard2_image_png); //Image�� ImageIcon ����
           
           upload_window_bt=new JButton(post);
           //��ư �׵θ� ���ֱ�
           upload_window_bt.setBorderPainted(false);
           upload_window_bt.setContentAreaFilled(false);
           upload_window_bt.setFocusPainted(false);
            
           GO_followerboard=new JButton(fb);
           GO_followerboard.setBorderPainted(false);
           GO_followerboard.setContentAreaFilled(false);
           GO_followerboard.setFocusPainted(false);
           
           upload_window_bt.setPreferredSize(new Dimension(180, 30));
           GO_followerboard.setPreferredSize(new Dimension(180, 30));
           
           
           upload_window_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               o.uw.setVisible(true);
            }
         });
           
           GO_followerboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              o.fb.setVisible(true);
            }
         });
       
         //��ġ ����
         // - setBounds(x ��ǥ, y ��ǥ, ��, ����)
         boardlabel.setBounds(20, 0, 131, 15); //USER BOARD
         scroll.setBounds(40, 20, 380, 300); // �޽��� �Է�â
         input.setBounds(40, 340, 380, 40); //�Է�â
         next_button.setBounds(400, 100, 100, 130);
         send_button.setBounds(400, 340, 100, 40); //SEND ��ư
         upload_window_bt.setBounds(40, 400, 200, 40); //upload window
         GO_followerboard.setBounds(260, 400, 200, 40);
         add(bottomPanel);
         
         bottomPanel.add(boardlabel);
         bottomPanel.add(send_button);
         bottomPanel.add(next_button);
         bottomPanel.add(upload_window_bt);
        bottomPanel.add(GO_followerboard);
        
        

   }
   @Override
   public void actionPerformed(ActionEvent e) {
       
      if(e.getSource()==input) {
            
            //DB�� ��� ���� �ֱ�
            String sql5 = "select * from comment";
            
            //comment ID �̸� �޾Ƶα�
            try {
               pstmt5 = con.prepareStatement(sql5);
               rs5 = pstmt5.executeQuery();
               while (rs5.next()) {
                  
                  comment_id=rs5.getInt("comment_id");
                  comment_id=comment_id+1; //primarykey�ϱ� ���� ���� �ϳ��� �÷� ���� ����
               }
            }
            catch (Exception e5) {
               System.out.println(e5.getMessage());
            }   
            finally {
               try {
                  pstmt4.close();
               } catch (Exception e5) {
                  System.out.println(e5.getMessage());
               }
            }
            //DB�� �Է¹��� ��� ���� �ֱ�
            String inputcomment=input.getText(); //�Է� ���� String���� �ޱ�
             String sql4 = "insert into comment values (?,?,?,?,?)";
            try {
               
               pstmt4 = con.prepareStatement(sql4);
               
               
                  pstmt4.setInt(1,comment_id);
                  pstmt4.setString(2,inputcomment);
                  pstmt4.setString(3,user_id); //following board�� ���� follow_id �� �����ؾ�O
                  pstmt4.setInt(4,postCount);
                  pstmt4.setString(5,user_id);
                  
                  int result=pstmt4.executeUpdate();
                  if(result==1) {
                     System.out.println("��� DB ���� ����");
                  //   display.append("  "+comment_id+"    "+ user_id);//�۾� ���� ȿ��
                     //display.append(": "+inputcomment+"\n");
                     
                       display.append("    ["+user_feed_id+"]  "+comment+"\n");
                     input.selectAll();
                     
                  }
               } 
            
            catch (Exception e4) {
               System.out.println(e4.getMessage());
            }   
            finally {
               try {
                  pstmt4.close();
               } catch (Exception e4) {
                  System.out.println(e4.getMessage());
               }
            }
   }
      
   }
    int comment () {
      //���� post �Ʒ� ��� ���� 
      
       String sql6 = "SELECT * FROM comment";
         PreparedStatement pstmt6 = null;
         ResultSet rs6 = null;
         try {
            
            
            pstmt6 = con.prepareStatement(sql6); 
            rs6 = pstmt6.executeQuery();
            
                while (rs6.next()) {
                 comment=rs6.getString("content");
                 user_feed_id=rs6.getString("user_id");
                 post_comment_id=rs6.getInt("post_id");
                 System.out.println("��� �ҷ����� --> post_comment_id: "+post_comment_id);
                 System.out.println("��� �ҷ����� --> comment: "+comment);
               
                 System.out.println("��� ������ ����");
                 if(postCount==post_comment_id)
                 display.append("    ["+user_feed_id+"]  "+comment+"\n");
                   
                  
               
            }
             
         } 
         catch (Exception e) {
            System.out.println(e.getMessage());
         }   
         finally {
            try {
               pstmt6.close();
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
         }
         return post_comment_id;
      
   }

}