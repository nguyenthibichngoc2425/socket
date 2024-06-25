package chat;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class clientC extends JFrame {

    private JPanel contentPane;
    private JTextField txtStaff;
    private JTextField txtServerIP;
    private JTextField txtServerPort;

    Socket mngSocket = null;
    String mngIP = "";
    int mngPort = 0;
    String staffName = "";
    BufferedReader bf = null;
    DataOutputStream os = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    clientC frame = new clientC();
                    frame.setVisible(true);
                   
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public clientC() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
        setBounds(200, 200, 1000, 400);
        
        // Set the application icon
        setIconImage(new ImageIcon("resources/icon.png").getImage());
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255)); // AliceBlue background
        contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(176, 196, 222), // LightSteelBlue border
                new Color(119, 136, 153)), // LightSlateGray title border
                "Staff and Server Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(70, 130, 180))); // SteelBlue title color
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblName = new JLabel("Name: ");
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblName.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblName, gbc);

        txtStaff = new JTextField();
        txtStaff.setBackground(new Color(255, 255, 255)); // White background for text fields
        txtStaff.setForeground(new Color(0, 0, 0)); // Black text color
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtStaff, gbc);
        txtStaff.setColumns(10);

        JLabel lblIP = new JLabel("IP: ");
        lblIP.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIP.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblIP, gbc);

        txtServerIP = new JTextField();
        txtServerIP.setBackground(new Color(255, 255, 255)); // White background for text fields
        txtServerIP.setForeground(new Color(0, 0, 0)); // Black text color
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtServerIP, gbc);
        txtServerIP.setColumns(10);

        JLabel lblPort = new JLabel("Port: ");
        lblPort.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPort.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblPort, gbc);

        txtServerPort = new JTextField();
        txtServerPort.setBackground(new Color(255, 255, 255)); // White background for text fields
        txtServerPort.setForeground(new Color(0, 0, 0)); // Black text color
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtServerPort, gbc);
        txtServerPort.setColumns(10);

        JButton btnConnect = new JButton("Connect");
        btnConnect.setForeground(new Color(255, 255, 255)); // White text color
        btnConnect.setBackground(new Color(70, 130, 180)); // SteelBlue background
        btnConnect.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(btnConnect, gbc);

        JFrame thisFrame = this;
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mngIP = txtServerIP.getText();
                mngPort = Integer.parseInt(txtServerPort.getText());
                staffName = txtStaff.getText();

                try {
                    mngSocket = new Socket(mngIP, mngPort);
                    if (mngSocket != null) {
                        chatP p = new chatP(mngSocket, staffName, " Manager");
                        thisFrame.getContentPane().add(p, BorderLayout.SOUTH);
                        p.getTxtMessage().append("Manager is running");
                        p.updateUI();
                        bf = new BufferedReader(new InputStreamReader(mngSocket.getInputStream()));
                        os = new DataOutputStream(mngSocket.getOutputStream());
                        os.writeBytes("Staff:" + staffName);
                        os.write('\r');
                        os.write('\n');
                    }
                } catch (Exception ex) {
                    // Handle exception
                }
            }
        });
    }
}

