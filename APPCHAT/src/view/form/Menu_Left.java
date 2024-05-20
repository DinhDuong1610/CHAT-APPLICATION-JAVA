package view.form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import model.Model_User;
import net.miginfocom.swing.MigLayout;
import service.Service;
import view.Main;
import view.component.Item_People;

public class Menu_Left extends JPanel{
	private JLayeredPane panel_menu_list;
	private List<Model_User> userAccount;

	public Menu_Left() {
		setSize(300, 803);
		setLayout(new MigLayout("fillx, filly", "0[300]0", "0[50]0[100%,fill]0"));
		JPanel panel_menu = new JPanel();
		add(panel_menu, "width 300:300:300, height 50:50:50, wrap");
		panel_menu.setLayout(new MigLayout("fillx, filly", "0[50!]0[125!]0[125!]0", "0[50]0"));
		
//		JLabel label = new JLabel("MESSAGE");
//		label.setForeground(new Color(255, 255, 255));
//		label.setFont(new Font("Gill Sans Ultra Bold Condensed", Font.BOLD, 26));
//		label.setBackground(new Color(0, 191, 255));
//		label.setOpaque(true);
//		label.setHorizontalAlignment(SwingConstants.CENTER);
//		add(label, "width 300:300:300, height 50:50:50, wrap");
		
		JButton bt_setting = new JButton("");
		bt_setting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showProfile();
			}
		});
		panel_menu.add(bt_setting, "width 52:52:52");
		
		JButton bt_chat2P = new JButton("");
		bt_chat2P.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPeople();
			}
		});
		panel_menu.add(bt_chat2P, "width 125:125:125");
		
		JButton bt_chatGroup = new JButton("");
		bt_chatGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showGroup();
			}
		});
		panel_menu.add(bt_chatGroup, "width 125:125:125");
		
		bt_chat2P.setIcon(new ImageIcon((new ImageIcon((Main.class.getResource("/images/icon/chat2p.png"))).getImage())));
		bt_chatGroup.setIcon(new ImageIcon((new ImageIcon((Main.class.getResource("/images/icon/chatgroup.png"))).getImage())));
		bt_setting.setIcon(new ImageIcon((new ImageIcon((Main.class.getResource("/images/icon/setting.png"))).getImage())));
	
		panel_menu_list = new JLayeredPane();
		panel_menu_list.setLayout(new MigLayout("fillx", "2[300]2", "3[]3"));
		
		userAccount = new ArrayList<>();
		
//        PublicEvent.getInstance().addEventMenuLeft(new EventMenuLeft() {
//            @Override
//            public void newUser(Model_User_Account d) {	
//                    userAccount.add(d);
//                    panel_menu_list.add(new Item_People(d), "width 285:285:285, height 50:50:50, wrap");
//                    panel_menu_list.repaint();
//                    panel_menu_list.revalidate();
//            }
//        });
		
		JScrollPane jScrollPane = new JScrollPane(panel_menu_list);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(jScrollPane);
		
		showPeople();
		
		setBackground(Color.blue);
		panel_menu_list.removeAll();
		
		
//		// KHỞI TẠO THỬ
//		newUser(new Model_User(1, "DinhDuong1", "Đính Dương", "123", "123", "123", "123", true));
//		newUser(new Model_User(2, "DinhDuong2", "Võ Minh Hùng", "123", "123", "123", "123", true));
//		newUser(new Model_User(3, "DinhDuong3", "Nguyễn Văn Hội", "123", "123", "123", "123", false));
//		newUser(new Model_User(4, "DinhDuong4", "Trương Diệu Vy", "123", "123", "123", "123", true));
//		newUser(new Model_User(5, "DinhDuong5", "Phạm Thị Hạnh", "123", "123", "123", "123", true));
//		newUser(new Model_User(11, "DinhDuong11", "Nhật Hưng", "123", "123", "123", "123", false));
//		newUser(new Model_User(6, "DinhDuong6", "Trần Công Hoàng", "123", "123", "123", "123", false));
//		newUser(new Model_User(7, "DinhDuong7", "My Lee", "123", "123", "123", "123", true));
//		newUser(new Model_User(8, "DinhDuong8", "Thảo Giang", "123", "123", "123", "123", true));
//		newUser(new Model_User(9, "DinhDuong9", "Phan Văn Đạt", "123", "123", "123", "123", true));
//		newUser(new Model_User(10, "DinhDuong10", "Hoàng Văn Thắng", "123", "123", "123", "123", false));
//		newUser(new Model_User(12, "DinhDuong12", "Lê Hữu Anh Tú", "123", "123", "123", "123", false));
//		newUser(new Model_User(13, "DinhDuong13", "Cao Hoàng Phước Bảo", "123", "123", "123", "123", true));
//		newUser(new Model_User(14, "DinhDuong14", "Lê Trung Việt", "123", "123", "123", "123", false));
//		newUser(new Model_User(15, "DinhDuong15", "Hồ Sỹ Bảo Nhân", "123", "123", "123", "123", true));
//		newUser(new Model_User(16, "DinhDuong16", "Dung Hoàng", "123", "123", "123", "123", false));
//		newUser(new Model_User(17, "DinhDuong17", "Trần Ngọc Anh Dũng", "123", "123", "123", "123", true));
//		// KHỞI TẠO THỬ
	}
	
	public void newUser(Model_User d) {	
	  userAccount.add(d);
	  panel_menu_list.add(new Item_People(d), "width 285:285:285, height 50:50:50, wrap");
	  panel_menu_list.repaint();
	  panel_menu_list.revalidate();
	}
	
	public void loadActive(int userId, boolean isActive) {
		Component[] components = panel_menu_list.getComponents();
		for (Component component : components) {
		    if (component instanceof Item_People) {
		        Item_People item = (Item_People) component;
		        if(item.getUser().getUser_Id() == userId) {
		        	item.active(isActive);
		        }
		    }
		}
	}
	
	public void showPeople() {
//		panel_menu_list.removeAll();		
//        for (Model_User_Account d : userAccount) {
//        	panel_menu_list.add(new Item_People(d), "width 296:296:296, height 50:50:50, wrap");
//        }
//		panel_menu_list.repaint();
//		panel_menu_list.revalidate();
	}
	
	public void showGroup() {
//		panel_menu_list.removeAll();
//		for(int i = 1; i <= 10; i++) {
//			panel_menu_list.add(new Item_People("Group " + i), "width 296:296:296, height 50:50:50, wrap");
//		}
//		panel_menu_list.repaint();
//		panel_menu_list.revalidate();
	}
	
	public void showProfile() {
    	JDialog dialog = new JDialog();
    	Profile profile = new Profile(dialog, Service.getInstance().getUser());
		dialog.getContentPane().setLayout(new GridLayout(1,1));
		dialog.setSize(1400, 600);
		dialog.setLocationRelativeTo(null);
    	dialog.getContentPane().add(profile);
    	dialog.setVisible(true);
	}

	public JLayeredPane getPanel_menu_list() {
		return panel_menu_list;
	}

	public void setPanel_menu_list(JLayeredPane panel_menu_list) {
		this.panel_menu_list = panel_menu_list;
	}
	
	
}
