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
	//팔로잉 목록 보이고 
	//팔로잉 하는 사람의 이름 누를 시 그의 보드가 보임 
	//해당 user의 아이디로 댓글달기 가능
	
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
		//창 크기 조절
		setSize(500,500);
	    //창 제목 설정
		setTitle("Following List");
		//배치 관리자 설정
		setLayout(null);
		// 프레임바 우측상단에 X버튼에 대한 강제종료 기능 지정
	    this.addWindowListener(new WindowAdapter(){
	          public void windowClosing(WindowEvent e) { 
	               dispose();
	          }
	  });
			
	    
	    	//전체 panel 생성
	    	followingPanel = new JPanel();
	    	followingPanel.setPreferredSize(new Dimension(510, 650));
	    	followingPanel.setLayout(null);//배치관리자 설정안함 - 절대위치 사용
	    	followingPanel.setBounds(0, 0, 510, 650);
	    	followingPanel.setBackground(Color.WHITE);
	    	
	    	//팔로우 목록 이미지 라벨
	    	 ImageIcon list_lb= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/FollowingList_label.png");
		        Image list_lb_png = list_lb.getImage();  //ImageIcon을 Image로 변환.
		        Image list_lb2_png = list_lb_png.getScaledInstance(150, 22, java.awt.Image.SCALE_SMOOTH);
		        ImageIcon list_LB = new ImageIcon(list_lb2_png); //Image로 ImageIcon 생성
		      
		        list_label=new JLabel(list_LB);

			    followingPanel.add(list_label);
			    
	    	//팔로우 목록 창 생성
	    	   
	    	followerList=new JTextArea(11,30);
	    	followerList.setEditable(false);
		    followingPanel.add(followerList);
		    followerList.append("\n ▶ If you enter @USER, You can go to the user's board\n");
		    followerList.append("\n");
		    //팔로우 목록에 DB 데이터 불러오기
		    
		    //DB 연결
			  try {
		            System.out.println("생성자");
		            Class.forName(driver);
		            con = DriverManager.getConnection(url, id, pwd);
		            System.out.println("팔로잉 리스트 드라이버 로딩 성공");
		        } catch (Exception e) {
		            System.out.println("팔로잉 리스트 드라이버 로딩 실패 ");
		            
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
					  
					System.out.println("팔로우 리스트 데이터 성공");
					System.out.println("불러오기 --> my_id: "+user_id);
					System.out.println("불러오기 --> following_id: "+your_id);
					
					followerList.append("   FOLLOWING: "+your_id+"\n");
				}
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}	
		
			
		    
		    
		    //팔로우 목록 스크롤 기능 추가
		    scroll = new JScrollPane(followerList);
		    scroll.setViewportView(followerList);
		    followingPanel.add(scroll);
		      

	    	//팔로우 보드 입력할 수 있는 입력 창
		    inputTag=new JTextField(30);
		    inputTag.addActionListener(this); 
		    followingPanel.add(inputTag);
		    
		    list_label.setBounds(20, 32, 200, 20);
		    scroll.setBounds(50, 60, 380, 300); // 메시지 입력창
		    inputTag.setBounds(50, 375, 380, 40);
	    
	    
	    add(followingPanel);
	    
	    
		
	}
	
	public void actionPerformed(ActionEvent e) {
		 
		if(e.getSource()==inputTag) {
			//@팔로잉 아이디 입력시 해당 보드로 이동
			//@yeonilil 입력 시 해당 유저 보드로 이동 -> 새 창 생성
			String following_ID_goboard =inputTag.getText();
			System.out.println(following_ID_goboard);
			String tag=following_ID_goboard.substring(0,1);
			System.out.println(tag);
			
			if(tag.equals("@")) {
				try {
					//board_id 인자로 넘겨야함
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
