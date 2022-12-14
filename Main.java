package workshop;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
	 Database db = null;
	 MainFrame mf = null;
	 SignUp su = null;
	 ChangePw cp = null;
	 ChangeUserIf cu = null;
	 Profile pf = null;
	 ChangeMsg cm = null;
	 SearchFollow sf = null;
	 Feed02 f0 = null;
	 Uploadwindow uw = null;
	 FollowingBoard fb = null;
	 FollowingBoardWindow fbw = null;

	 public static void main(String[] args) throws Exception {
    	Main opt = new Main();
    	opt.db = new Database();
    	opt.pf = new Profile(opt);
        opt.mf = new MainFrame(opt);
        opt.su = new SignUp(opt);
        opt.cp = new ChangePw(opt);
    	opt.cu = new ChangeUserIf(opt);
    	opt.cm = new ChangeMsg(opt);
    	opt.sf = new SearchFollow(opt);
    	opt.f0 = new Feed02(opt);	
    	opt.uw = new Uploadwindow(opt);
    	opt.fb = new FollowingBoard(opt);
    	opt.fbw = new FollowingBoardWindow(opt);
    	

	 }
}