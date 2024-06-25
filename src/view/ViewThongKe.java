package view;

import chat.clientC;
import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import controller.BanHangController;
import controller.HoaDonController;
import controller.NhanVienController;
import controller.SanPhamController;
import model.HoaDon;
import model.ThongKe;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Authenticator;
import java.net.Authenticator.RequestorType;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.URL;

public class ViewThongKe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDoanhThu;
	private JTextField txtSoHoaDon;
	private JTextField txtTongKhachHang;
	private JTextField txtBC;
	private JRadioButton rdbTatCa;
	private JButton btnThoat;
	private JButton btnGuiBC;
	private JComboBox cbbThang;
	private JComboBox cbbNam;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewThongKe frame = new ViewThongKe();
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
	public ViewThongKe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1320, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(255, 204, 204));
		contentPane_1.setBounds(0, 0, 1306, 723);
		contentPane.add(contentPane_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 153));
		panel.setLayout(null);
		panel.setBounds(0, 0, 226, 723);
		contentPane_1.add(panel);
		
		
		
		String imagePath = "D:\\DoAnCoSo1\\build\\classes\\view\\logo.jpg";
		JLabel image = new JLabel("");
		image.setBounds(10, 10, 206, 168);
		ImageIcon imageIcon = new ImageIcon(imagePath);
		//ImageIcon là 1 lớp đại diện cho 1 icon hay hình ảnh, cung cấp các phương thức
		Image image0 = imageIcon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), DO_NOTHING_ON_CLOSE);
		//Image là 1 lớp để điều chỉnh kích thước, getImage() là 1 phương thức của ImageIcon
		//getScaledInstance() dùng để điều chỉnh kích thước
		ImageIcon scaleIcon = new ImageIcon(image0);
		//Tạo 1 ImageIcon mới từ đối tượng image0 sau khi đã chỉnh sửa kích thước
		image.setIcon(scaleIcon);
		//Thiết lập scaleIcon là hình ảnh cho JLabel.
		panel.add(image);
		JLabel lblNewLabel = new JLabel("hehehe");
		lblNewLabel.setForeground(new Color(204, 255, 255));
		lblNewLabel.setBackground(new Color(153, 255, 255));
		lblNewLabel.setFont(new Font("Ravie", Font.BOLD | Font.ITALIC, 28));
		lblNewLabel.setBounds(36, 204, 180, 40);
		panel.add(lblNewLabel);
		
		JButton btnBH = new JButton("Bán hàng");
		btnBH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewBanHang banhang = new ViewBanHang();
				banhang.setLocationRelativeTo(null);
				BanHangController controller = new BanHangController(banhang, null, null);
				controller.showViewBanHang();
			}
		});
		btnBH.setFont(new Font("UTM Alexander", Font.PLAIN, 25));
		btnBH.setBackground(new Color(255, 204, 255));
		btnBH.setBounds(0, 269, 226, 65);
		panel.add(btnBH);
		
		JButton btnSP = new JButton("Sản phẩm");
		btnSP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewSanPham sanpham = new ViewSanPham();
				sanpham.setLocationRelativeTo(null);
				SanPhamController controller = new SanPhamController(sanpham);
				controller.showSanPhamView();
			}
		});
		btnSP.setFont(new Font("UTM Alexander", Font.PLAIN, 25));
		btnSP.setBackground(new Color(255, 204, 255));
		btnSP.setBounds(0, 332, 226, 65);
		panel.add(btnSP);
		
		JButton btnHD = new JButton("Hóa đơn");
		btnHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewHoaDon hoadon = new ViewHoaDon();
				hoadon.setLocationRelativeTo(null);
				HoaDonController controller = new HoaDonController(hoadon);
				controller.showViewHoaDon();
			}
		});
		btnHD.setFont(new Font("UTM Alexander", Font.PLAIN, 25));
		btnHD.setBackground(new Color(255, 204, 255));
		btnHD.setBounds(0, 395, 226, 65);
		panel.add(btnHD);
		
		JButton btnNV = new JButton("Nhân viên");
		btnNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewNhanVien nhanvien = new ViewNhanVien();
				nhanvien.setLocationRelativeTo(null);
				NhanVienController controller = new NhanVienController(nhanvien);
				controller.showViewNhanVien();
			}
		});
		btnNV.setFont(new Font("UTM Alexander", Font.PLAIN, 25));
		btnNV.setBackground(new Color(255, 204, 255));
		btnNV.setBounds(0, 458, 226, 65);
		panel.add(btnNV);
		
		JButton btnTK = new JButton("Thống kê");
		btnTK.setFont(new Font("UTM Alexander", Font.PLAIN, 25));
		btnTK.setBackground(new Color(255, 204, 255));
		btnTK.setBounds(0, 521, 226, 65);
		panel.add(btnTK);
		
		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("UTM Alexander", Font.PLAIN, 25));
		btnThoat.setBackground(new Color(255, 204, 255));
		btnThoat.setBounds(0, 647, 226, 65);
		panel.add(btnThoat);
                     JButton btnChat = new JButton("Chat");
    		btnChat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clientC c = new clientC();
				c.setLocationRelativeTo(null);
				c.setVisible(true);
			}
		});
		btnChat.setBackground(new Color(216, 191, 216));
		btnChat.setFont(new Font("UTM Alexander", Font.PLAIN, 25));
		btnChat.setBounds(0, 584, 226, 65);
		panel.add(btnChat);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setBorder(new LineBorder(new Color(255, 255, 255), 4));
		lblNewLabel_2_1_1.setBounds(236, 10, 295, 175);
		contentPane_1.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Doanh thu");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(295, 21, 169, 43);
		contentPane_1.add(lblNewLabel_1);
		
		txtDoanhThu = new JTextField();
		txtDoanhThu.setBounds(249, 91, 200, 50);
		contentPane_1.add(txtDoanhThu);
		txtDoanhThu.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("VNĐ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(459, 109, 54, 32);
		contentPane_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("");
		lblNewLabel_2_1_1_1.setBorder(new LineBorder(new Color(255, 255, 255), 4));
		lblNewLabel_2_1_1_1.setBounds(621, 10, 295, 175);
		contentPane_1.add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Số hóa đơn");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1_2.setBounds(682, 21, 169, 43);
		contentPane_1.add(lblNewLabel_1_2);
		
		txtSoHoaDon = new JTextField();
		txtSoHoaDon.setColumns(10);
		txtSoHoaDon.setBounds(671, 91, 200, 50);
		contentPane_1.add(txtSoHoaDon);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("");
		lblNewLabel_2_1_1_2.setBorder(new LineBorder(new Color(255, 255, 255), 4));
		lblNewLabel_2_1_1_2.setBounds(997, 10, 295, 175);
		contentPane_1.add(lblNewLabel_2_1_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Tổng khách hàng");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1_2_1.setBounds(1007, 21, 277, 43);
		contentPane_1.add(lblNewLabel_1_2_1);
		
		txtTongKhachHang = new JTextField();
		txtTongKhachHang.setColumns(10);
		txtTongKhachHang.setBounds(1042, 91, 200, 50);
		contentPane_1.add(txtTongKhachHang);
		
		rdbTatCa = new JRadioButton("Tất cả");
		rdbTatCa.setBackground(new Color(119, 136, 153));
		rdbTatCa.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbTatCa.setBounds(358, 248, 103, 21);
		contentPane_1.add(rdbTatCa);
		
		cbbThang = new JComboBox();
		cbbThang.setModel(new DefaultComboBoxModel(new String[] {"None", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cbbThang.setBounds(777, 245, 84, 21);
		contentPane_1.add(cbbThang);
		
		cbbNam = new JComboBox();
		cbbNam.setModel(new DefaultComboBoxModel(new String[] {"None", "2024", "2023", "2022", "2021", "2020"}));
		cbbNam.setBounds(1159, 248, 84, 21);
		contentPane_1.add(cbbNam);
		
		btnGuiBC = new JButton("Gửi báo cáo");
		btnGuiBC.setBackground(new Color(205, 92, 92));
		btnGuiBC.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnGuiBC.setBounds(1074, 492, 218, 66);
		contentPane_1.add(btnGuiBC);
		
		JLabel lblNewLabel_3 = new JLabel("Nội dung báo cáo:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(249, 347, 169, 32);
		contentPane_1.add(lblNewLabel_3);
		
		txtBC = new JTextField();
		txtBC.setBounds(427, 433, 589, 201);
		contentPane_1.add(txtBC);
		txtBC.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Theo tháng:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(660, 237, 97, 32);
		contentPane_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Theo năm:");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1_1.setBounds(1042, 237, 97, 32);
		contentPane_1.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_2_1_1_3 = new JLabel("");
		lblNewLabel_2_1_1_3.setBorder(new LineBorder(new Color(255, 255, 255), 4));
		lblNewLabel_2_1_1_3.setBounds(410, 394, 621, 256);
		contentPane_1.add(lblNewLabel_2_1_1_3);
		
		JLabel lblNewLabel_3_2 = new JLabel("Bản Báo Cáo Doanh Thu");
		lblNewLabel_3_2.setForeground(new Color(0, 0, 51));
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3_2.setBounds(456, 404, 205, 32);
		contentPane_1.add(lblNewLabel_3_2);
	}
	
	public void addThoatListener(ActionListener listener) {
		btnThoat.addActionListener(listener);
	}
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public void getTongDoanhThu(ArrayList<HoaDon> hoadons) {
		double tong = 0 ;
		for(HoaDon hoadon : hoadons)
		{
			tong += hoadon.getTongTien();
		}
		txtDoanhThu.setText(String.valueOf(tong));
	}
	
	public void getTongHoaDon(ArrayList<HoaDon> hoadons) {
		int tong = 0;
		for(HoaDon hoadon : hoadons)
		{
			tong ++;
		}
		txtSoHoaDon.setText(String.valueOf(tong));
	}
	
	public void getTongKhachHang(ArrayList<HoaDon> hoadons) {
		int tong = 0;
		for(HoaDon hoadon : hoadons)
		{
			tong ++;
		}
		txtTongKhachHang.setText(String.valueOf(tong));
	}
	    
	public ArrayList<HoaDon> getData(){
	    	ArrayList<HoaDon> hoadons = new ArrayList<HoaDon>();
	    	getTongDoanhThu(hoadons);
	    	getTongHoaDon(hoadons);
	    	getTongKhachHang(hoadons);
	    		    	
	    	return hoadons;
	}
	
	public void doSomeThing() {
		txtDoanhThu.setText("");
		txtSoHoaDon.setText("");
		txtTongKhachHang.setText("");
	}
	    
	public void addItemTongDoanhThuListener(ItemListener listener) {
		rdbTatCa.addItemListener(listener);
	}
	
	public String listThang() {
		String month = cbbThang.getSelectedItem().toString();
		return month;
	}
	
	public void addItemDoanhThuThang(ItemListener listener) {
		cbbThang.addItemListener(listener);
	}
	
	public String listNam() {
		String year = cbbNam.getSelectedItem().toString();
		return year;
	}
	
	public void addItemDoanhThuNam(ItemListener listener) {
		cbbNam.addItemListener(listener);
	}
	
	// from đóng vai trò là mail của cửa hàng, to là mail của chủ doanh nghệp, cửa hàng sẽ gửi mail thống kê tới chủ doanh nghiệp
	final static String from = "bichngoc.141205@gmail.com";
	
	final static String password = "timjbmuswrlpioek"; // pas ứng dụng
		
	final static String to = "trinhthithuynga11a1@gmail.com";
	
	public static void email(String to, String tieuDe, String noiDung) {
		
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		javax.mail.Authenticator auth = new javax.mail.Authenticator() {

			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new javax.mail.PasswordAuthentication(from, password);
			}
		};
		//Phiên làm việc
		Session session = Session.getInstance(props, auth);
		
		//Tạo 1 tin nhắn
		MimeMessage msg = new MimeMessage(session);
		try {
			//Kiểu nội dung
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			
			//Người gửi
			msg.setFrom(from);
			
			//Người nhận
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			
			//Tiêu đề email
			msg.setSubject(tieuDe);
			
			// Quy định ngày gửi
			msg.setSentDate(new Date());
			
			//Nội dung
			msg.setContent(noiDung, "text/HTML; charset=UTF-8");
			
			// Gửi email.
			Transport.send(msg);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public void addGuiBaoCaoListener(ActionListener listener) {
		btnGuiBC.addActionListener(listener);
	}
	
	public String noiDung(ArrayList<HoaDon> hoadons) {
		int tongDoanhThu = 0;
	    int tongHoaDon = 0;
	    int tongKhachHang = 0;
	    
	    for (HoaDon hoaDon : hoadons) {
	        tongDoanhThu += hoaDon.getTongTien();
	        tongHoaDon++;
	        tongKhachHang ++;
	    }
	    
		String noiDungEmail = "Báo cáo doanh thu tháng\n" + cbbThang.getSelectedItem() + ":" + "\n";
        noiDungEmail += "Tổng doanh thu: " + tongDoanhThu + "\n";
        noiDungEmail += "Số hóa đơn: " + tongHoaDon + "\n";
        noiDungEmail += "Số khách hàng: " + tongKhachHang + "\n";
        noiDungEmail += "Báo cáo: " + txtBC.getText()+ "\n";
        
        return noiDungEmail;
	}
	
}
