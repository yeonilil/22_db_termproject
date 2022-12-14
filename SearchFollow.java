package workshop;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.SQLException;

public class SearchFollow extends JFrame {

    private JPanel contentPane;
    private JTextArea searchText;
    private JTable table;
    private JScrollPane scrollPane;

    Main o = null;

    SearchFollow(Main _o) {
        o = _o;
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 661, 507);

        /* Pane */
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Please enter ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(6, 10, 633, 65);
        contentPane.add(panel);
        panel.setLayout(null);

        /* Label */
        JLabel lblNewLabel = new JLabel("ID: ");
        lblNewLabel.setBounds(15, 27, 57, 15);
        panel.add(lblNewLabel);

        searchText = new JTextArea();
        searchText.setBounds(63, 23, 455, 24);
        panel.add(searchText);

        /* Button Listener */
        JButton searchBtn = new JButton("Search");
        JButton followBtn = new JButton("Follow");

        searchBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String inputId = searchText.getText();
                if (table != null) {
                    if (inputId.length() < 2) {
                        JOptionPane.showMessageDialog(SearchFollow.this, "Please enter at least 2 characters", "Search Failed", JOptionPane.WARNING_MESSAGE);
                    } else {
                        try {
                            table.setModel(new ShowTable(inputId));
                        } catch (ClassNotFoundException | SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
        followBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               String your_id = searchText.getText();
               String my_id = o.db.real_my_id;
               
               if (table != null) {
                   o.db.followCheck(my_id , your_id);
                   System.out.println(my_id);
               }
            }
        });

        searchBtn.setBounds(530, 11, 97, 23);
        followBtn.setBounds(530, 36, 97, 23);

        panel.add(searchBtn);
        panel.add(followBtn);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 78, 621, 380);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);


        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

