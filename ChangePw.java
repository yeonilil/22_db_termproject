package workshop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ChangePw extends JFrame{
    /* Panel */
    JPanel panel = new JPanel();

    /* Label */
    JLabel idL = new JLabel("ID");
    JLabel pwL = new JLabel("Password");

    /* TextField */
    JTextField id = new JTextField();
    JPasswordField pw = new JPasswordField();

    /* Button */
    JButton joinBtn = new JButton("Change");
    JButton cancelBtn = new JButton("Cancel");

    Main o = null;

    ChangePw(Main _o) {
        o = _o;

        setTitle("Confirmation of personal information");

        /* Label ũ�� �۾� */
        idL.setPreferredSize(new Dimension(150, 30));
        pwL.setPreferredSize(new Dimension(150, 30));

        /* TextField ũ�� �۾� */
        id.setPreferredSize(new Dimension(140, 30));
        pw.setPreferredSize(new Dimension(140, 30));

        /* Button ũ�� �۾� */
        joinBtn.setPreferredSize(new Dimension(100, 25));
        cancelBtn.setPreferredSize(new Dimension(100, 25));

        /* Panel �߰� �۾� */
        setContentPane(panel);

        panel.add(idL);
        panel.add(id);

        panel.add(pwL);
        panel.add(pw);

        panel.add(cancelBtn);
        panel.add(joinBtn);

        /* Button �̺�Ʈ ������ �߰� */
        ButtonListener bl = new ButtonListener();

        cancelBtn.addActionListener(bl);
        joinBtn.addActionListener(bl);

        setSize(330, 150); // â ������ ����
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /* Button �̺�Ʈ ������ */
    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();

            /* TextField�� �Էµ� ȸ�� �������� ������ �ʱ�ȭ */
            String uid = id.getText();
            String upass = "";
            for(int i=0; i<pw.getPassword().length; i++) {
                upass = upass + pw.getPassword()[i];
            }

            if(b.getText().equals("Cancel")) {
                dispose();
            }

            else {
                if(uid.equals("") || upass.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the information", "Sign up Failed", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Sign In Failed > Member information not entered");
                } else
					try {
						if (o.db.logincheck(uid, upass)) {
						    o.cu.setVisible(true);
						    System.out.println("Self-confirmation successful");

						    dispose();
						}

						else {
						    System.out.println("Sign In Failed.");
						    JOptionPane.showMessageDialog(null, "INCORRECT ID or PASSWORD");
						}
					} catch (HeadlessException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            }
        }
    }
}