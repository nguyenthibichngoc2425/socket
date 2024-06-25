package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectionData;
import java.security.MessageDigest;
import model.NhanVien;

public class NhanVienDAO implements interfacesDAO<NhanVien> {

    public static String maHoa(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
	
	private Connection connection;
	
	public NhanVienDAO() {
		connection = ConnectionData.getConnection();
	}

	public NhanVienDAO(Connection connection) {
		this.connection = connection;
	}
	
	public static NhanVienDAO getInstance() {
		return new NhanVienDAO();
	}

	@Override
	public boolean insert(NhanVien t) {
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT INTO ds_nhanvien (`Mã nhân viên`, `Mật khẩu`, `Tên nhân viên`, `Vai trò`, `Địa chỉ`, `Điện thoại`, `Ngày sinh`, `Giới tính`, `Trạng thái`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, t.getMaNV());
		
			preparedStatement.setString(2, t.getTenNV());
			preparedStatement.setString(3, t.getVaiTro());
			preparedStatement.setString(4, t.getDiaChi());
			preparedStatement.setString(5, t.getDienThoai());
			preparedStatement.setString(6, t.getNgaySinh());
			preparedStatement.setString(7, t.getGioiTinh());
			preparedStatement.setString(8, t.getTrangThai());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(NhanVien t) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE ds_nhanvien SET `Mật khẩu` = ?, `Tên nhân viên` = ?, `Vai trò` = ?, `Địa chỉ` = ?, `Điện thoại` = ?, `Ngày sinh` = ?, `Giới tính` = ?, `Trạng thái` = ? WHERE `Mã nhân viên` = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(8, t.getMaNV());
			
			preparedStatement.setString(1, t.getTenNV());
			preparedStatement.setString(2, t.getVaiTro());
			preparedStatement.setString(3, t.getDiaChi());
			preparedStatement.setString(4, t.getDienThoai());
			preparedStatement.setString(5, t.getNgaySinh());
			preparedStatement.setString(6, t.getGioiTinh());
			preparedStatement.setString(7, t.getTrangThai());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(NhanVien t) {
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM ds_nhanvien WHERE `Mã nhân viên` = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, t.getMaNV());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<NhanVien> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<NhanVien> nhanviens = new ArrayList<NhanVien>();
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT *FROM ds_nhanvien";
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				String MaNV = resultSet.getString("Mã nhân viên");
				
				String TenNV = resultSet.getString("Tên nhân viên");
				String VaiTro = resultSet.getNString("Vai trò");
				String DiaChi = resultSet.getString("Địa chỉ");
				String DienThoai = resultSet.getString("Điện thoại");
				String NgaySinh = resultSet.getString("Ngày sinh");
				String GioiTinh = resultSet.getString("Giới tính");
				String TrangThai = resultSet.getString("Trạng thái");
				NhanVien nhanvien = new NhanVien(MaNV, TenNV, VaiTro, DiaChi, DienThoai, NgaySinh, GioiTinh, TrangThai);
				nhanviens.add(nhanvien);
			}
			statement.close();
			resultSet.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nhanviens;
	}

	@Override
	public NhanVien selectById(NhanVien t) {
		// TODO Auto-generated method stub
		NhanVien nhanvien = new NhanVien();
		
		try {
			String sql = "SELECT * FROM ds_nhanvien WHERE `Mã nhân viên` = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, t.getMaNV());
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				String MaNV = resultSet.getString("Mã nhân viên");
				
				String TenNV = resultSet.getString("Tên nhân viên");
				String VaiTro = resultSet.getNString("Vai trò");
				String DiaChi = resultSet.getString("Địa chỉ");
				String DienThoai = resultSet.getString("Điện thoại");
				String NgaySinh = resultSet.getString("Ngày sinh");
				String GioiTinh = resultSet.getString("Giới tính");
				String TrangThai = resultSet.getString("Trạng thái");
				nhanvien = new NhanVien(MaNV, TenNV, VaiTro, DiaChi, DienThoai, NgaySinh, GioiTinh, TrangThai);
			}
			preparedStatement.close();
			resultSet.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nhanvien;
	}

	@Override
	public ArrayList<NhanVien> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		ArrayList<NhanVien> nhanviens = new ArrayList<NhanVien>();
		try {
			String sql = "SELECT *FROM ds_nhanvien WHERE " + condition;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				String MaNV = resultSet.getString("Mã nhân viên");
				
				String TenNV = resultSet.getString("Tên nhân viên");
				String VaiTro = resultSet.getString("Vai trò");
				String DiaChi = resultSet.getString("Địa chỉ");
				String DienThoai = resultSet.getString("Điện thoại");
				String NgaySinh = resultSet.getString("Ngày sinh");
				String GioiTinh = resultSet.getString("Giới tính");
				String TrangThai = resultSet.getString("Trạng thái");
				NhanVien nhanvien = new NhanVien(MaNV, TenNV, VaiTro, DiaChi, DienThoai, NgaySinh, GioiTinh, TrangThai);
				nhanviens.add(nhanvien);
			}
			preparedStatement.close();
			resultSet.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nhanviens;
	}
//        public static String maHoa (String pass){
//         try {
//             MessageDigest md = MessageDigest.getInstance("SHA");
//             md.update(pass.getBytes());
//             byte[] rbt = md.digest();
//             StringBuilder sb = new StringBuilder();
//             
//             for(byte b : rbt){
//                 sb.append(String.format("%02x", b));
//             }
//             
//             return sb.toString();
//         } catch (Exception e) {
//         }
//         return null;
//     }
//        
}
