package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionData {
	
	public static  Connection getConnection() {
		Connection connection = null;
		try {
//			String username = "root";
//			String password = "";
//			String DatabaseName = "quanly_banhang";
//			String url = "jdbc:mysql://localhost/" + DatabaseName;
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			connection = DriverManager.getConnection(url, username, password);
			Class.forName("com.mysql.cj.jdbc.Driver");
			  
            String url = "jdbc:mysql://localhost:3306/banhang";
            String user = "root"; // thay đổi theo tài khoản của bạn
            String password = ""; // thay đổi theo mật khẩu của bạn
            // Tạo kết nối
            connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return connection;
	}
	public static void main(String[] args) {
		ConnectionData.getConnection();
	}

}
