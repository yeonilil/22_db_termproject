package workshop;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class ChangeMsg extends JFrame {
	
	JPanel basePanel = new JPanel(new BorderLayout());
    JPanel centerPanel = new JPanel(new BorderLayout());
    JPanel westPanel = new JPanel();
    JPanel eastPanel = new JPanel();
    JPanel southPanel = new JPanel();

    private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtArticleCount;
	private JTextField txtShortMessage;
	private JTextField txtId;

	public ChangeMsg(Main _o) throws IOException {
		Main o = _o;
		File file = new File("my_id.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(file)); //선언
		String my_id = br.readLine(); //String
			
		String msg = o.db.message(my_id);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		
		setContentPane(contentPane);
    	contentPane.setLayout(null);
		
    	 ImageIcon change_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/ch.png");
         Image change_image_png = change_image.getImage();  //ImageIcon을 Image로 변환.
         Image change_image2_png = change_image_png.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
         ImageIcon change = new ImageIcon(change_image2_png); //Image로 ImageIcon 생성
         

 		ImageIcon mypage_image= new ImageIcon("C:/Users/KSB/Desktop/workshop/src/Image/kk2.png");
         Image mypage_image_png = mypage_image.getImage();  //ImageIcon을 Image로 변환.
         Image mypage_image2_png = mypage_image_png.getScaledInstance(73, 65, java.awt.Image.SCALE_SMOOTH);
         ImageIcon mypage = new ImageIcon(mypage_image2_png); //Image로 ImageIcon 생성
         
         
		JTextArea txtrWriteDownA = new JTextArea();
		txtrWriteDownA.setBackground(SystemColor.menu);
		txtrWriteDownA.setBounds(101, 143, 212, 89);
		txtrWriteDownA.setText("Write down a message you want to modify");
		contentPane.add(txtrWriteDownA);
		
		JTextArea txtrMessageBeforeModification = new JTextArea();
		txtrMessageBeforeModification.setBackground(SystemColor.menu);
		txtrMessageBeforeModification.setBounds(101, 36, 212, 89);
		txtrMessageBeforeModification.setText(msg);
		contentPane.add(txtrMessageBeforeModification);
		

		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(325, 102, 91, 89);
		btnNewButton.setIcon(new ImageIcon(change_image2_png));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
				 
				 String newMsg = txtrWriteDownA.getText();
				
				 String msg = o.db.modify(my_id, newMsg);
				System.out.println(newMsg);
				 
			}
		});
		contentPane.add(btnNewButton);
		
		 btnNewButton.setBorderPainted(false);
	     btnNewButton.setContentAreaFilled(false);
	     btnNewButton.setFocusPainted(false);
	     
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(mypage_image2_png));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b1 = (JButton)e.getSource();
				o.pf.dispose();
				o.pf.setVisible(true);

			}
		});
		btnNewButton_1.setBounds(345, 10, 91, 53);
		btnNewButton_1.setBackground(Color.WHITE);

		contentPane.add(btnNewButton_1);
		
		 btnNewButton_1.setBorderPainted(false);
	     btnNewButton_1.setContentAreaFilled(false);
	     btnNewButton_1.setFocusPainted(false);
		
	}
}
