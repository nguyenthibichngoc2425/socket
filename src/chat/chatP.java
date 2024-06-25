package chat;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class chatP extends JPanel {
	private Socket socket = null;
	private BufferedReader bf = null;
	private DataOutputStream os = null;
	private Output t = null;
	private String sender;
	private String receiver;
	private JTextArea txtMessage;
	private List<String> emojis; // Danh sách các emoji

	public chatP(Socket s, String sender, String receiver) {
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(new BorderLayout());
		add(panel, BorderLayout.SOUTH);
		JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		panel.add(scrollPane, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(buttonPanel, BorderLayout.SOUTH);
		JButton btnSend = new JButton("Send");
		btnSend.setBackground(new Color(34, 139, 34));
		btnSend.setForeground(Color.WHITE);
		btnSend.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textArea.getText().trim().length() == 0)
					return;
				try {
					String message = textArea.getText();
					message = message.replaceAll("[\\r\\n]+", "\n");
					os.write(message.getBytes(StandardCharsets.UTF_8));
					os.write('\r');
					os.write('\n');
					txtMessage.append("\n " + sender + ": " + message);
					textArea.setText("");
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		buttonPanel.add(btnSend);
		JButton btnAttach = new JButton("Attach");
		btnAttach.setForeground(new Color(230, 230, 250));
		btnAttach.setBackground(new Color(34, 139, 34));
		btnAttach.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnAttach.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int option = chooser.showOpenDialog(chatP.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					try {
						sendFile(file);
					} catch (IOException ex) {
						txtMessage.append("\n " + sender + ": (file) " + file.getName() + " - Error sending file.");
						ex.printStackTrace();
					}
				}
			}
		});
		buttonPanel.add(btnAttach);
		JPanel emojiPanel = new JPanel(new GridLayout(2, 4, 5, 5)); // Sửa GridLayout thành 2x4
		emojis = new ArrayList<>();
		emojis.add("😊");
		emojis.add("😂");
		emojis.add("😍");
		emojis.add("👍");
		emojis.add("❤️");
		emojis.add("👎");
		emojis.add("😡");
		emojis.add("😢");
		for (String emoji : emojis) {
			JButton emojiButton = new JButton(emoji);
			emojiButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Chèn emoji vào vị trí hiện tại của con trỏ
					textArea.insert(emoji, textArea.getCaretPosition());
				}
			});
			emojiPanel.add(emojiButton);
		}
		panel.add(emojiPanel, BorderLayout.WEST);
		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, BorderLayout.CENTER);
		this.txtMessage = new JTextArea();
		txtMessage.setTabSize(5);
		scrollPane_1.setViewportView(txtMessage);
		socket = s;
		this.sender = sender;
		this.receiver = receiver;
		try {
			bf = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			os = new DataOutputStream(socket.getOutputStream());
			t = new Output(s, txtMessage, sender, receiver);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendFile(File file) throws IOException {
		if (!file.exists() || !file.isFile()) {
			System.err.println("File doesn't exist or is not a regular file.");
			return;
		}

		// Gửi thông tin về file (tên và kích thước)
		os.write(("/file " + file.getName() + "\r\n").getBytes(StandardCharsets.UTF_8));
		os.write((file.length() + "\r\n").getBytes(StandardCharsets.UTF_8));

		try (FileInputStream fis = new FileInputStream(file)) {
			byte[] buffer = new byte[8192];
			int count;
			while ((count = fis.read(buffer)) > 0) {
				os.write(buffer, 0, count);
				os.flush();
			}
		}

		txtMessage.append("\n " + sender + ": (file) " + file.getName() + " (" + file.length() + " bytes)");
	}

	public JTextArea getTxtMessage() {
		return this.txtMessage;
	}

	public Socket getSocket() {
		return this.socket;
	}
}
