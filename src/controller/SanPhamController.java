package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.SanPhamDAO;
import model.SanPham;
import view.ViewSanPham;

public class SanPhamController {
	
	private ViewSanPham viewsanPham;
	private SanPhamDAO sanphamDAO;
	
	public SanPhamController(ViewSanPham viewsanPham) {
		this.viewsanPham = viewsanPham;
		sanphamDAO = new SanPhamDAO();// khởi tạo đối tượng spDAO để thực hiện các pthuc CRUD
		
		viewsanPham.addThoatListener(new ActionListener() {// dùng để thoát ứng dụng
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		viewsanPham.addAddSanPhamListener(new ActionListener() {// thêm 1 actionListener vào sự kiện của btn themSP
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SanPham sanpham = viewsanPham.getSanPhamData();// lấy info sp từ giao diện bằng cách gọi phương thức 
				if(sanpham!=null)// kiểm tra xem sp của null hay kh. nếu khác null thì đã láy được tt từ gdien
				{
					boolean success = sanphamDAO.insert(sanpham);// gọi insert của spDAO ddeer thêm sp vào dtb
					if(success)
					{
						viewsanPham.showListSanPham(sanphamDAO.selectAll());// update lại ds sp trên gd
						viewsanPham.showMessage("Thêm thành công");
					}
					else {
						viewsanPham.showMessage("Thêm thất bại");
					}
				}
			}
		});
		
		viewsanPham.addTableSelectionListener(new ListSelectionListener() {// thêm 1 listSelect vào sk change of table. khi user chọn 1 dòng trong table thfi valueChanged của listSe sẽ được kích hoạt
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				viewsanPham.fillInputForm();// điền thông tin từ dòng được chọn vào các trường nhập liệu (input form) trên giao diện viewsanPham.
			}
		});
		
		viewsanPham.editAddSanPhamListener(new ActionListener() {//thêm sk khi nhấn btn sửa
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SanPham sanpham = viewsanPham.getSanPhamData();// lấy ttin sp từ view
				if(sanpham!=null)
				{
					boolean success = sanphamDAO.update(sanpham);// gọi update để cập nhật ttin
					if(success)
					{
						viewsanPham.showListSanPham(sanphamDAO.selectAll());// hiện lại ds sp
						viewsanPham.showMessage("Sửa thành công");
					}
					else
					{
						viewsanPham.showMessage("Sửa thất bại");
					}
				}
			}
		});
		
		viewsanPham.deleteAddSanPhamListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SanPham sanpham = viewsanPham.getSanPhamData();
				if(sanpham!=null)
				{
					boolean success = sanphamDAO.delete(sanpham);
					if(success)
					{
						viewsanPham.showListSanPham(sanphamDAO.selectAll());
						viewsanPham.showMessage("Đã xóa thành công");
					}
					else
					{
						viewsanPham.showMessage("Xóa thất bại");
					}
				}
			}
		});
		
		viewsanPham.searchAddSanPhamListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sanpham = viewsanPham.findSanPham();// lấy sp cần search từ gd bằng cách gọi phuongư thức findSP vủa view
				if(sanpham!=null)
				{
					ArrayList<SanPham> success = sanphamDAO.selectByName(sanpham);//gọi pt selectName của spDAO để lấy ds stuwf csdl
					if(success!=null)
					{
						viewsanPham.showListSanPham(success);
					}
					else 
					{
						viewsanPham.showMessage("Không tìm thấy sản phẩm");
					}
				}
			}
		});
		
		viewsanPham.addRefreshTableSanPham(new ActionListener() { //dùng để làm mới table sp
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean success = viewsanPham.setSanPhamAll();
				if(success)
				{
					viewsanPham.showListSanPham(sanphamDAO.selectAll());
				}
			}
		});
		
	}
	
	//Giao diện sẽ không hiển thị ở view mà ở đây, nên ta tạo hàm ni
	public void showSanPhamView() {
		//Trước khi hiển thị giao diện thì cần lấy dữ liệu từ database vào bảng đã
		List<SanPham> sanPhams = sanphamDAO.selectAll();
		// Sau khi lấy xong thì cập nhật lại dữ liệu rồi hiển thị lên bằng showListSanPham đc tạo bên view
		viewsanPham.showListSanPham(sanPhams);
		// rồi mới hiển thị giao diện
		viewsanPham.setVisible(true);
	}

}
