package workshop;

import java.awt.*;
import javax.swing.*;

import java.sql.*;
import java.util.ArrayList;

import workshop.FollowingBoardWindow;

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

public class FollowingBoardWindow extends JFrame implements ActionListener {
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
		Connection confb = null;  
		 String user_feed_id=null;
		 String content=null;
		 
		 int post_comment_id=0;
		 String comment = null;
		 int board_id,post_feed_id=0;
		 String user_id=null;
		 int postCount=0; 
		 PreparedStatement pstmt33fb = null;
		 ResultSet rs33fb = null;
		 Profile pf = null;
		 int comment_id =0;
		   // Main o = null;
		 
		 //댓글 DB
		 PreparedStatement pstmt4fb = null;
		 ResultSet rs4fb = null;
		 
		 PreparedStatement pstmt5fb = null;
		 ResultSet rs5fb = null;
			
		 Main o = null;
	public FollowingBoardWindow(Main _o) throws Exception{
	o = _o;

		//DB 연결
		  try {
	            System.out.println("생성자");
	            Class.forName(driver);
	            confb = DriverManager.getConnection(url, id, pwd);
	            System.out.println("드라이버 로딩 성공");
	        } catch (Exception e) {
	            System.out.println("드라이버 로딩 실패 ");
	          
	        }
		
		  //ID 가져오기
		  
		  try {
		  Statement stmt=confb.createStatement();
		  String sql;
		  sql="select * from user";
		  ResultSet rs= stmt.executeQuery(sql);
		  
		  while(rs.next()) {
			 user_id=rs.getString("user_id");
			  System.out.println("ID: "+user_id);
		  }} catch(Exception e) {
			  System.out.println("ERROR: "+ e.getMessage());
		  }
		  
		  
			    
		//상단부 
		
		//창 크기 조절
	    setSize(600,850);
	    //창 제목 설정
		setTitle("Feed");
		//전체 배경 설정
		getContentPane().setBackground(Color.WHITE);
		//배치 관리자 설정
		setLayout(new FlowLayout());
		//setLayout(new BorderLayout());
		// 프레임바 우측상단에 X버튼에 대한 강제종료 기능 지정
		setDefaultCloseOperation(Feed02.EXIT_ON_CLOSE);
		
		// 버튼을 넣기위한 패널 생성
        JPanel buttonPanel = new JPanel();
        //배경색 흰색으로
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(600, 85));

        ImageIcon logo_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/logo_button.png");
        //이미지 사이즈 조절
        Image logo_image_png = logo_image.getImage();  //ImageIcon을 Image로 변환.
        Image logo_image2_png = logo_image_png.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(logo_image2_png); //Image로 ImageIcon 생성
 		
        ImageIcon search_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/search_button.png");
        Image search_image_png = search_image.getImage();  //ImageIcon을 Image로 변환.
        Image search_image2_png = search_image_png.getScaledInstance(350, 60, java.awt.Image.SCALE_SMOOTH);
        ImageIcon search = new ImageIcon(search_image2_png); //Image로 ImageIcon 생성
        
        ImageIcon mypage_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/mypage_button.png");
        Image mypage_image_png = mypage_image.getImage();  //ImageIcon을 Image로 변환.
        Image mypage_image2_png = mypage_image_png.getScaledInstance(73, 65, java.awt.Image.SCALE_SMOOTH);
        ImageIcon mypage = new ImageIcon(mypage_image2_png); //Image로 ImageIcon 생성
        
        logo_button=new JButton(logo);
        //버튼 테두리 없애기
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
				  pf.setVisible(true);
			}
		});
        
     
	     
        logo_button.setPreferredSize(new Dimension(55, 70));
        search_button.setPreferredSize(new Dimension(350, 80));
        mypage_button.setPreferredSize(new Dimension(60,80));
        

        
        add(buttonPanel);
        buttonPanel.add(logo_button);
        buttonPanel.add(search_button);
        buttonPanel.add(mypage_button);
        
      
	      //하단부
	      
	      JPanel bottomPanel = new JPanel();
	      bottomPanel.setPreferredSize(new Dimension(510, 650));
	      bottomPanel.setLayout(null);//배치관리자 설정안함 - 절대위치 사용
	      bottomPanel.setBounds(0, 100, 600, 400);
	      bottomPanel.setBackground(Color.WHITE);
	      //board
	      
	      ImageIcon board_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/FollowingBoard_label.png");
	        Image board_image_png = board_image.getImage();  //ImageIcon을 Image로 변환.
	        Image board_image2_png = board_image_png.getScaledInstance(140, 22, java.awt.Image.SCALE_SMOOTH);
	        ImageIcon boarddd = new ImageIcon(board_image2_png); //Image로 ImageIcon 생성
	        
	        JLabel boardlabel = new JLabel(boarddd);
	        
	      
	     //User board 보드!!
	      
	      display=new JTextArea(11,30);
	      display.setEditable(false);
	      
	      JScrollPane scroll = new JScrollPane(display);
	      scroll.setViewportView(display);
	      bottomPanel.add(scroll);
	      
	      input=new JTextField(30);
	      input.addActionListener(this);
	      bottomPanel.add(input);
	      
	  
	      
	      //보드 옆 NEXT 버튼
	      ImageIcon next_bt= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/next_button.png");
	        Image next_button_png = next_bt.getImage();  //ImageIcon을 Image로 변환.
	        Image next_button2_png = next_button_png.getScaledInstance(70, 120, java.awt.Image.SCALE_SMOOTH);
	        ImageIcon next = new ImageIcon(next_button2_png); //Image로 ImageIcon 생성
	      
	        next_button=new JButton(next);
	        //버튼 테두리 없애기
	        next_button.setBorderPainted(false);
	        next_button.setContentAreaFilled(false);
	        next_button.setFocusPainted(false);
	        
	       //postCount 미리 받아두기
	        String sql22 = "SELECT * FROM post";
			PreparedStatement pstmt22 = null;
			ResultSet rs22 = null;
			
			try {
				
				 pstmt22 = confb.prepareStatement(sql22);
				 rs22 = pstmt22.executeQuery();
				while (rs22.next()) {
					
					 post_feed_id=rs22.getInt("post_id");
					 if(post_feed_id<2000) {
						 continue;
					 }
					 if(post_feed_id>postCount) {
						 postCount=post_feed_id;
						 System.out.println("postCount 성공");
					 }
					 else {
						 continue;
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
	        
	        //보드에 피드 내용 업로드
	        //보드 내용 띄우기
	        String sql = "SELECT *" + "FROM post WHERE post_id=";
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				
				 pstmt = confb.prepareStatement(sql + postCount); 
				 rs = pstmt.executeQuery();
				while (rs.next()) {
					  content=rs.getString("content");
					  user_feed_id=rs.getString("user_id");
					  board_id=rs.getInt("board_id");
					 post_feed_id=rs.getInt("post_id");
					 postCount=post_feed_id;
					 
					 System.out.println("피드 데이터 성공");
					System.out.println("불러오기 --> user_feed_id: "+user_feed_id);
					System.out.println("불러오기 --> content: "+content);
					System.out.println("불러오기 --> board_id: "+board_id);
					System.out.println("불러오기 --> post_feed_id: "+post_feed_id);
					if(board_id==2) { //알고리즘 구현....
						display.append("\n    --------------------- POST "+postCount+" -----------------------\n\n");
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
			
	
			
			
			//버튼 눌려지면 다음으로 최근 게시물 뜨도록
		
			 String sql33 = "SELECT * FROM post WHERE post_id=";
				
			next_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(postCount==2001) {
							
						}
						else {
							postCount=postCount-1;
							pstmt33fb = confb.prepareStatement(sql33 + postCount); 
							 rs33fb = pstmt33fb.executeQuery();
							  while (rs33fb.next()) {
								  content=rs33fb.getString("content");
								  user_feed_id=rs33fb.getString("user_id");
								 
								System.out.println("이전 내용 --> user_feed_id: "+user_feed_id);
								System.out.println("이전 내용 --> content: "+content);
								display.setText("");
								display.append("\n    ------------------------ POST"+postCount+" ------------------------\n\n");
								display.append("\n\n    ["+user_feed_id+"]  "+content+"\n\n\n");
								display.append("\n    ---------------------- COMMENT ----------------------\n\n");
								
								
									comment(); //댓글 띄우는 함수
								
						}
						}
					} 
					catch (Exception ee) {
						System.out.println("버튼 데이터 오류"+ee.getMessage());
					}	
					finally {
						try {
							pstmt33fb.close();
						} catch (Exception ee) {
							System.out.println("버튼 데이터 오류"+ee.getMessage());
						}
					}
					
					
				}
				});
			
		
				
	   
	      //댓글 보내기 버튼
	      ImageIcon send_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/send.png");
	        Image send_image_png = send_image.getImage();  //ImageIcon을 Image로 변환.
	        Image send_image2_png = send_image_png.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
	        ImageIcon send = new ImageIcon(send_image2_png); //Image로 ImageIcon 생성
	      
	        send_button=new JButton(send);
	        //버튼 테두리 없애기
	        send_button.setBorderPainted(false);
	        send_button.setContentAreaFilled(false);
	        send_button.setFocusPainted(false);
	        
	 
	     
	      //위치 조정
	      // - setBounds(x 좌표, y 좌표, 폭, 높이)
	      boardlabel.setBounds(8, 0, 200, 20); //USER BOARD
	      scroll.setBounds(40, 25, 380, 300); // 메시지 입력창
	      input.setBounds(40, 340, 380, 40); //입력창
	      next_button.setBounds(400, 70, 100, 200);
	      send_button.setBounds(400, 340, 100, 40); //SEND 버튼
	     
	      add(bottomPanel);
	      
	      bottomPanel.add(boardlabel);
	      bottomPanel.add(send_button);
	      bottomPanel.add(next_button);
	     
	    

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if(e.getSource()==input) {
				
				//DB에 댓글 정보 넣기
				String sql5 = "select * from comment";
				
				//comment ID 미리 받아두기
				try {
					pstmt5fb = confb.prepareStatement(sql5);
					rs5fb = pstmt5fb.executeQuery();
					while (rs5fb.next()) {
						
						comment_id=rs5fb.getInt("comment_id");
						comment_id=comment_id+1; //primarykey니까 원래 값에 하나씩 늘려 가며 저장
					}
				}
				catch (Exception e5) {
					System.out.println(e5.getMessage());
				}	
				finally {
					try {
						pstmt4fb.close();
					} catch (Exception e5) {
						System.out.println(e5.getMessage());
					}
				}
				//DB에 입력받은 댓글 정보 넣기
				String inputcomment=input.getText(); //입력 정보 String으로 받기
				 String sql4 = "insert into comment values (?,?,?,?,?)";
				try {
					
					pstmt4fb = confb.prepareStatement(sql4);
					
					
						pstmt4fb.setInt(1,comment_id);
						pstmt4fb.setString(2,inputcomment);
						pstmt4fb.setString(3,user_feed_id); //following board로 가면 follow_id 로 변경해야O
						pstmt4fb.setInt(4,postCount);
						pstmt4fb.setString(5,user_id);
						
						int result=pstmt4fb.executeUpdate();
						if(result==1) {
							System.out.println("댓글 DB 저장 성공");
							display.append("  "+comment_id+"    "+ user_id);//글씨 굵게 효과
							display.append(": "+inputcomment+"\n");
							input.selectAll();
							
						}
					} 
				
				catch (Exception e4) {
					System.out.println(e4.getMessage());
				}	
				finally {
					try {
						pstmt4fb.close();
					} catch (Exception e4) {
						System.out.println(e4.getMessage());
					}
				}
	}
		
	}
	 int comment () {
		//보드 post 아래 댓글 띄우기 
		
		 String sql6 = "SELECT * FROM comment";
			PreparedStatement pstmt6 = null;
			ResultSet rs6 = null;
			try {
				
				
				pstmt6 = confb.prepareStatement(sql6); 
				rs6 = pstmt6.executeQuery();
				
					 while (rs6.next()) {
					  comment=rs6.getString("content");
					  user_feed_id=rs6.getString("user_id");
					  post_comment_id=rs6.getInt("post_id");
					  System.out.println("댓글 불러오기 --> post_comment_id: "+post_comment_id);
					  System.out.println("댓글 불러오기 --> comment: "+comment);
					
					  System.out.println("댓글 데이터 성공");
					  if(postCount==post_comment_id)
					  display.append("    ["+user_feed_id+"]  "+comment+"\n");
						 
						
					
				}
				 
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}	
		
			return post_comment_id;
		
	}
	


}
