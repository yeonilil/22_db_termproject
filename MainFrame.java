package workshop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class MainFrame extends JFrame { // Main Gui
    JPanel basePanel = new JPanel(new BorderLayout());
    JPanel northPanel = new JPanel(new BorderLayout());
    JPanel centerPanel = new JPanel(new BorderLayout());
    JPanel westPanel = new JPanel();
    JPanel eastPanel = new JPanel();
    JPanel southPanel = new JPanel();
  /* Label */
    JLabel idL = new JLabel("ID");
    JLabel pwL = new JLabel("PWD");

    /* TextField */
    JTextField id = new JTextField();
    JPasswordField pw = new JPasswordField();

    /* Button */
    JButton loginBtn = new JButton("Sign In");
    JButton joinBtn = new JButton("Sign Up");
    JButton exitBtn = new JButton("Change PWD");
    
    Main o = null;
    
    MainFrame(Main _o){
        o = _o;
       
		
		ImagePanel panel = new ImagePanel(new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/img3.png").getImage());
		
		File f = new File("C:/Users/KSB/Desktop/workshop/src/Image/img3.png");
		System.out.println(f.exists()? "Exists":"doesnt exists");
	

        /* Label ũ�� */
        idL.setPreferredSize(new Dimension(50, 30));
        pwL.setPreferredSize(new Dimension(50, 30));

        /* TextField ũ�� */
        id.setPreferredSize(new Dimension(140, 30));
        pw.setPreferredSize(new Dimension(140, 30));

        /* Button ũ�� */
        joinBtn.setPreferredSize(new Dimension(135, 25));
        exitBtn.setPreferredSize(new Dimension(135, 25));

        /* Panel */
        setContentPane(basePanel);	//panel�� �⺻ �����̳ʷ� ����

        basePanel.add(northPanel, BorderLayout.NORTH);
        basePanel.add(centerPanel, BorderLayout.CENTER);
        basePanel.add(southPanel, BorderLayout.SOUTH);
        centerPanel.add(westPanel, BorderLayout.CENTER);
        centerPanel.add(eastPanel, BorderLayout.EAST);
        
        northPanel.setLayout(new FlowLayout());
        westPanel.setLayout(new FlowLayout());
        eastPanel.setLayout(new FlowLayout());
        southPanel.setLayout(new FlowLayout());

        westPanel.add(idL);
        westPanel.add(id);
        westPanel.add(pwL);
        westPanel.add(pw);
        westPanel.add(loginBtn);
        loginBtn.setPreferredSize(new Dimension(75, 55));
        

        southPanel.add(exitBtn);
        southPanel.add(joinBtn);

        northPanel.add(panel);
		
        /* Button Listener */
        ButtonListener bl = new ButtonListener();
        exitBtn.addActionListener(bl);
        joinBtn.addActionListener(bl);
        loginBtn.addActionListener(bl);
        
        setSize(850, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /* Button Listener */
    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
         
            /* TextField�� �Էµ� ���̵�� ��й�ȣ�� ������ �ʱ�ȭ */
            String uid = id.getText();
            String upass = "";
            for(int i=0; i<pw.getPassword().length; i++) {
                upass = upass + pw.getPassword()[i];
            }

            /* ���α׷� ���� */
            if(b.getText().equals("Change PWD")) {
            	o.cp.setVisible(true);
               
            }
            
            else if(b.getText().equals("Sign Up")) {
                o.su.setVisible(true);
                
            }
            /* �α��� */
            else if(b.getText().equals("Sign In")) {
                if(uid.equals("") || upass.equals("")) {
                	JOptionPane.showMessageDialog(null, "Please enter ID or PW");
                    
                }

                else if(uid != null && upass != null) {
                    try {
						if(o.db.logincheck(uid, upass)) {	
						    System.out.println("Success! ");
						    JOptionPane.showMessageDialog(null, "Sign In succeeded");    
						    o.f0.setVisible(true);
						      } 
						else {
						    System.out.println("Failed . . .");
						    JOptionPane.showMessageDialog(null, "Sign In Failed");
						}
					} catch (HeadlessException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            }
        }
    }
}
