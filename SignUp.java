package workshop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUp extends JFrame{
    /* Panel */
    JPanel panel = new JPanel();

    /* Label */
    JLabel idL = new JLabel("ID");
    JLabel pwL = new JLabel("Password");

    /* TextField */
    JTextField id = new JTextField();
    JPasswordField pw = new JPasswordField();

    /* Button */
    JButton joinBtn = new JButton("Sign Up");
    JButton cancelBtn = new JButton("Cancellation");

    Main o = null;

    SignUp(Main _o) {
        o = _o;

        setTitle("Sign Up");

        /* Label 크기 작업 */
        idL.setPreferredSize(new Dimension(50, 30));
        pwL.setPreferredSize(new Dimension(50, 30));

        /* TextField 크기 작업 */
        id.setPreferredSize(new Dimension(140, 30));
        pw.setPreferredSize(new Dimension(140, 30));

        /* Button 크기 작업 */
        joinBtn.setPreferredSize(new Dimension(95, 25));
        cancelBtn.setPreferredSize(new Dimension(95, 25));

        /* Panel 추가 작업 */
        setContentPane(panel);

        panel.add(idL);
        panel.add(id);

        panel.add(pwL);
        panel.add(pw);

        panel.add(cancelBtn);
        panel.add(joinBtn);

        /* Button 이벤트 리스너 추가 */
        ButtonListener bl = new ButtonListener();

        cancelBtn.addActionListener(bl);
        joinBtn.addActionListener(bl);

        setSize(250, 150);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /* Button 이벤트 리스너 */
    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            
            /* TextField에 입력된 회원 정보들을 변수에 초기화 */
            String uid = id.getText();
            String upass = "";
            for(int i=0; i<pw.getPassword().length; i++) {
                upass = upass + pw.getPassword()[i];
            }

            /* 가입 안해 */
            if(b.getText().equals("Cancellation")) {
                dispose();
            }
            
            /* 가입해 */
            else {
                if(uid.equals("") || upass.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the information", "Sign up Failed", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Sign up Failed > Member information not entered");
                }

                else {
                    	System.out.println(o.db.joinCheck(uid, upass));
                        System.out.println("Sign up Succeeded");
                        JOptionPane.showMessageDialog(null, "회원가입에 성공하였습니다");
                        dispose();
                    
                }
            }
        }
    }
}
