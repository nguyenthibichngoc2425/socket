package chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTabbedPane;

public class Manager extends JFrame implements Runnable {

    private JPanel contentPane;
    private JTextField txtServerPort;
    ServerSocket srvSocket = null;
    BufferedReader bf = null;
    Thread t;
    private JTabbedPane tabbedPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Manager frame = new Manager();
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
    public Manager() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 980, 500);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(176, 224, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Server info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(1, 2, 0, 0));

        JLabel lblNewLabel = new JLabel("Manager Port:   ");
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblNewLabel);

        txtServerPort = new JTextField();
        txtServerPort.setBackground(new Color(255, 192, 203));
        txtServerPort.setText("12340");
        panel.add(txtServerPort);
        txtServerPort.setColumns(10);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBackground(new Color(176, 224, 230));
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        int serverPort = Integer.parseInt(txtServerPort.getText());
        try {
            srvSocket = new ServerSocket(serverPort);

        } catch (Exception e) {
            e.printStackTrace();
        }

        t = new Thread(this);
        t.start();
    }

    public void run() {
        while (true) {
            try {
                Socket aStaffSocket = srvSocket.accept();
                if (aStaffSocket != null) {
                    bf = new BufferedReader(new InputStreamReader(aStaffSocket.getInputStream()));
                    String S = bf.readLine();
                    int pos = S.indexOf(":");
                    String staffName = S.substring(pos + 1);
                    chatP p = new chatP(aStaffSocket, " Manager", staffName);
                    tabbedPane.add(staffName, p);
                    p.updateUI();

                }
                Thread.sleep(100);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}