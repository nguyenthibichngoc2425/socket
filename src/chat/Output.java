package chat;


import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Output extends Thread {
    private Socket socket;
    private JTextArea txt;
    private BufferedReader bf;
    private String receiver;
    private char[] buffer;

    public Output(Socket socket, JTextArea txt, String sender, String receiver) {
        super();
        this.socket = socket;
        this.txt = txt;
        this.receiver = receiver;
        this.buffer = new char[4096]; // Initialize buffer array
        try {
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            showError("Error occurred while initializing BufferedReader: " + e.getMessage());
        }
    }

    public void run() {
        try {
            String msg;
            while ((msg = bf.readLine()) != null) {
                if (msg.startsWith("/file")) {
                    handleFileMessage(msg);
                } else {
                	String messageWithEmoji = decodeEmoji(msg);
                    txt.append("\n" + receiver + ": " + messageWithEmoji);
                }
            }
        } catch (IOException e) {
            showError("Error occurred while reading message: " + e.getMessage());
        } finally {
            closeResources();
        }
    }

    private void handleFileMessage(String msg) throws IOException {
        String fileName = msg.substring(6);
        int fileSize = Integer.parseInt(bf.readLine());
        String fileMsg = "Download " + fileName + " (" + fileSize + " bytes)?";
        int option = JOptionPane.showConfirmDialog(null, fileMsg, "Download file",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            saveFile(fileName, fileSize);
        } else {
            skipFileData(fileSize);
        }
    }

    private void saveFile(String fileName, int fileSize) throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String savePath = chooser.getSelectedFile().getAbsolutePath() + File.separator + fileName;
            try (DataOutputStream fileOut = new DataOutputStream(new FileOutputStream(savePath))) {
                byte[] buffer = new byte[4096];
                int read;
                while ((read = socket.getInputStream().read(buffer, 0, buffer.length)) != -1) {
                    fileOut.write(buffer, 0, read);
                    fileSize -= read;
                    if (fileSize <= 0) break; // File has been fully written or error occurred
                }
                // Check if the file has been fully written
                if (fileSize > 0) {
                    // Handle error: File was not fully written
                    showError("Error: File was not fully written.");
                    // Delete incomplete file
                    new File(savePath).delete();
                } else {
                    txt.append("\n" + fileName + " was saved in " + savePath);
                }
            } catch (IOException e) {
                // Handle IO error
                showError("Error occurred while saving file: " + e.getMessage());
                // Delete incomplete file
                new File(savePath).delete();
            }
        }
    }

    private void skipFileData(int fileSize) throws IOException {
        while (fileSize > 0) {
            int bytesRead = bf.read(buffer, 0, Math.min(buffer.length, fileSize));
            if (bytesRead == -1) {
                break;
            }
            fileSize -= bytesRead;
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void closeResources() {
        try {
            if (bf != null) bf.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            showError("Error occurred while closing resources: " + e.getMessage());
        }
    }

    // Phương thức chuyển đổi emoji thành chuỗi UTF-8
    private String decodeEmoji(String message) {
        // Dấu hiệu cho emoji
        Pattern emojiPattern = Pattern.compile(":\\w+:");
        Matcher matcher = emojiPattern.matcher(message);

        // Duyệt qua các emoji và thay thế chúng bằng ký tự UTF-8 tương ứng
        while (matcher.find()) {
            String emoji = matcher.group();
            // Thay thế emoji bằng ký tự UTF-8 tương ứng
            // Ví dụ: ":smile:" -> 😊
            // Bạn cần thêm các trường hợp cho các emoji khác
            switch (emoji) {
                case ":smile:":
                    message = message.replace(emoji, "😊");
                    break;
                case ":laugh:":
                    message = message.replace(emoji, "😂");
                    break;
                case ":heart:":
                    message = message.replace(emoji, "❤️");
                    break;
                case ":thumbsup:":
                    message = message.replace(emoji, "👍");
                    break;
                case ":thumbsdown:":
                    message = message.replace(emoji, "👎");
                    break;
                case ":angry:":
                    message = message.replace(emoji, "😡");
                    break;
                case ":cry:":
                    message = message.replace(emoji, "😢");
                    break;
                // Thêm các trường hợp khác tại đây
            }
        }
        return message;
    }
}