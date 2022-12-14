package workshop;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.io.RandomAccessFile;

public class Database {
    Connection con = null;
    Statement stmt = null;
    PreparedStatement pstmt;
	ResultSet rs = null;

	public Database() {    // 데이터베이스에 연결

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
    }

	 private ArrayList<ToTable> datas = new ArrayList<ToTable>();
	    public ArrayList<ToTable> searchEname(String strEname ) throws ClassNotFoundException, SQLException {

	        String sql =
	                "select *from user where user_id = ?";
	        PreparedStatement pstmt = this.con.prepareStatement(sql);
	        pstmt.setString(1, strEname);
	        rs = pstmt.executeQuery();
	        while( rs.next() ) {
	            ToTable data = new ToTable();
	            data.setId( rs.getString(1) );
	            data.setPwd( rs.getString(2) );

	            datas.add( data );
	        }

	        if( rs != null ) rs.close();
	        if( pstmt != null ) pstmt.close();
	        if( con != null ) con.close();

	        return datas;
	    }

	    void followCheck(String my_id, String your_id) {
	        try {
	            stmt = con.createStatement();
	            String sql = "select *from user where user_id = ('"+your_id+"') ";
	            rs = stmt.executeQuery(sql);

	            int index =0;
	            String id="";

	            if( rs.next() ) {
	                id = rs.getString(1);
	                index++;
	            }

	            String sql2 =
	                    "insert into follow values('" + my_id + "', '" + your_id + "')";
	            stmt.executeUpdate(sql2);

	        } catch (Exception e) {
	            System.out.println("Follow Failed > " + e.toString());
	        }
	    }
	
	 static String real_my_id = "";
	 
	 boolean logincheck(String ID, String PWD) throws IOException {
		 String id = ID, pw = PWD; /*, pn = user_phone, un = user_name, introduce = user_introduce; */
		 boolean flag = false;
		

	        try (RandomAccessFile raf = new RandomAccessFile("my_id.txt", "rw")) {
	            raf.setLength(0);
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	        
			BufferedWriter str = new BufferedWriter(new FileWriter("my_id.txt", true));
					
		
	        try {
	           stmt = con.createStatement();
	           String qu = "select *from user where user_id = '" + id + "' and password = '" + pw + "'";
	           rs =  stmt.executeQuery(qu);
	   
	           
	           if (rs.next()) {
	            	real_my_id = rs.getString(1); 
	            
	            	str.write(real_my_id);
	        		str.close();
	        		
	        	    System.out.println("ID = " + rs.getString(1) + ", password = " + rs.getString(2));
	            	return true;
	            }  
	            
	            
	        } catch (Exception e) {
	            
	            System.out.println("Sign up Failed > " + e.getMessage());
	        }
	        str.close();
	        return flag;
	    }
	 
	 boolean joinCheck(String user_id, String user_pwd /* , String user_phone, String user_name, String user_introduce */) {
	        boolean flag = false;

	        String id = user_id, pw = user_pwd; /*, pn = user_phone, un = user_name, introduce = user_introduce; */

	        try {
	        	stmt = con.createStatement();
	        	String qu = "insert into user (user_id, password) values('" + id + "', '" + pw + "')";
		        stmt.executeUpdate(qu);

	            flag = true;
	            System.out.println("Sign up Succeeded");
	        } catch (Exception e) {
	            flag = false;
	            System.out.println("Sign up Failed > " + e.toString());
	        }

	        return flag;
	    }
	
	 boolean changeCheck(String user_id, String newPw /* , String user_phone, String user_name, String user_introduce */) {
	        boolean flag = false;

	        String id = user_id, pw = newPw; /*, pn = user_phone, un = user_name, introduce = user_introduce; */

	        try {
	        	stmt = con.createStatement();
	        	String qu = "update user set password = '" + newPw + "' where user_id = '" + id + "'";
		        stmt.executeUpdate(qu);
		        
	            flag = true;
	            System.out.println("Change success");
	        } catch (Exception e) {
	            flag = false;
	            System.out.println("Change Failed > " + e.toString());
	        }

	        return flag;
	    }
	String message(String id) {
		String msg = "";
		  try {
	        	stmt = con.createStatement();
	        	String qu = "select msg from user where user_id = '" + id + "'";
	        	rs =  stmt.executeQuery(qu);
		            
		        if (rs.next()) {
		           System.out.println("MSG = " + rs.getString(1));
		           msg = rs.getString(1);
		        }
	            
	        } catch (Exception e) {
	        	msg = "";
	            System.out.println("Message print Failed > " + e.toString());
	        }
	return msg;
	 }
	String modify(String id, String newMsg) {
		String msg = "";
		  try {
	        	stmt = con.createStatement();
		         
	        	String qu = "update user set msg = '" + newMsg + "' where user_id = '" + id + "'";
	        	 stmt.executeUpdate(qu);
	        
	        	String qu2 = "select msg from user where user_id = '" + id + "'";
	        	rs =  stmt.executeQuery(qu2);
	        	
		        if (rs.next()) {
		           System.out.println("MSG = " + rs.getString(1));
		           msg = rs.getString(1);
		        }
	            
	        } catch (Exception e) {
	        	msg = "";
	            System.out.println("Message print Failed > " + e.toString());
	        }
	return msg;
	 }
	
	
	 boolean FollowCancle(String my_id, String your_id) {
	        boolean flag = false;

	        try {
	        	stmt = con.createStatement();
	        	String qu = "delete from follow_table where my_id = '" + my_id + "'and your_id = '"   + your_id + "'"; 
		        stmt.executeUpdate(qu);
		        
	            flag = true;
	            System.out.println("Delete success");
	        } catch (Exception e) {
	            flag = false;
	            System.out.println("Delete Failed > " + e.toString());
	        }

	        return flag;
	    }
		int FollowingNum(String my_id) {
	      
	        int num = 0;
	        
	        try {
	        	stmt = con.createStatement();
	        	String qu = "select count(distinct your_id) from follow where my_id = '" + my_id + "'";
	        	rs =  stmt.executeQuery(qu);
		            
		        if (rs.next()) {
		           System.out.println("Count Success");
		          num = rs.getInt(1);
		        }
	        } catch (Exception e) {
	            
	            System.out.println("Count Failed > " + e.toString());
	        }

	        return num;
	    }
		
		int FollowerNum(String my_id) {
		      
	        int num = 0;
	        
	        try {
	        	stmt = con.createStatement();
	        	String qu = "select count(distinct your_id) from follow where your_id = '" + my_id + "'";
	        	rs =  stmt.executeQuery(qu);
		            
		        if (rs.next()) {
		           System.out.println("Count Success");
		          num = rs.getInt(1);
		        }
	        } catch (Exception e) {
	            
	            System.out.println("Count Failed > " + e.toString());
	        }

	        return num;
	    }
	
}