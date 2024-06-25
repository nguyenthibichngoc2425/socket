package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import dao.HoaDonDAO;
import model.HoaDon;
import view.ViewHoaDon;

public class HoaDonController {
	
	private ViewHoaDon viewHoaDon;
	private HoaDonDAO hoadonDAO;
	
	public HoaDonController() {
		viewHoaDon = new ViewHoaDon();
	}
	
	public HoaDonController(ViewHoaDon viewHoaDon) {
		this.viewHoaDon = viewHoaDon;
		hoadonDAO = new HoaDonDAO();
		
		viewHoaDon.addThoatListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		viewHoaDon.addTimKiemHoaDonListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String tenKH = viewHoaDon.findTenKhachHang();
				
				if(tenKH!="")
				{
					ArrayList<HoaDon> hoadons = hoadonDAO.selectByTenKhachHang(tenKH);
					if(hoadons!=null)
					{
						viewHoaDon.showListHoaDon(hoadons);
					}
					else
					{
						viewHoaDon.showMessage("Không tìm thấy hóa đơn!");
					}
				}
			}
		});
		
		viewHoaDon.addItemTimKiemThangListener(new ItemListener() {
			// khi người dùng chọn 1 mục từ đanh sácb tháng pth itemStatechange sẽ được gọi
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
					String month = viewHoaDon.listThang();// lấy giá trị tháng từ ds thông qua listThang() . nó sẽ dùng để tìm kiếm hoá đơn 
					if(month!="None")// ktra xem thsang có khác null không, nếu khác thì sẽ tiếp tục xử lý
					{
						ArrayList<HoaDon> hoadons = hoadonDAO.selectMonth(month);//gọi selectMonth để lấy ds hóa đơn .kq trả về là 1 ds arraylist chứ các dt HoaDon
						if(hoadons!=null)
						{
							viewHoaDon.showListHoaDon(hoadons);// hiển thị ds hóa đơn trong tháng được chọntreen giao diện dung pth showLítHoaDon(hoadons
						}
						else// bằng null thì h có hóa đon nào được chọn
						{
							viewHoaDon.showMessage("Không tồn tại!");
						}
					}
					else
					{
						viewHoaDon.showListHoaDon(hoadonDAO.selectAll());//hiện ds hóa dơn 
					}
			}
		});
		
		viewHoaDon.addItemTimKiemNamListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String year = viewHoaDon.listNam();
				if(year!= "None")
				{
					ArrayList<HoaDon> hoadons = hoadonDAO.selectYear(year);
					if(hoadons!=null)
					{
						viewHoaDon.showListHoaDon(hoadons);
					}
					else
					{
						viewHoaDon.showMessage("Không tồn tại");
					}
				}
				else
				{
					viewHoaDon.showListHoaDon(hoadonDAO.selectAll());
				}
			}
		});
		
	}
	
	
	public void showViewHoaDon() {
		List<HoaDon> hoaDon = hoadonDAO.selectAll();
		viewHoaDon.showListHoaDon(hoaDon);
		viewHoaDon.setVisible(true);
	}

}
