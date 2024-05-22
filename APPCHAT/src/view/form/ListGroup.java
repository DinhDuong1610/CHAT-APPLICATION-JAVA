package view.form;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.Model_Group;
import model.Model_User;
import net.miginfocom.swing.MigLayout;
import service.Service;
import view.component.Item_People;

public class ListGroup extends JPanel{
	private JLayeredPane panel_menu_list;
	private List<Model_Group> groupList;
	private Model_Group group;

	public ListGroup(Model_Group group) {
		this.group = group;
		setSize(300, 803);
		setLayout(new MigLayout("fillx, filly", "0[290]0", "0[50]0[100%,fill]0"));
		JPanel panel_menu = new JPanel();
		add(panel_menu, "width 300:300:300, wrap");
		panel_menu.setLayout(new GridLayout(1, 1, 0, 0));
		
		JButton bt_chat2P = new JButton("");
		bt_chat2P.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = JOptionPane.showInputDialog("INPUT USERNAME");
				Service.getInstance().addMember(userName, group);
			}
		});

		panel_menu.add(bt_chat2P);
		
		bt_chat2P.setIcon(new ImageIcon(ListGroup.class.getResource("/images/icon/icon_add_user.png")));
		panel_menu_list = new JLayeredPane();
		panel_menu_list.setLayout(new MigLayout("fillx", "2[300]2", "3[]3"));
		
		groupList = new ArrayList<>();
		
		JScrollPane jScrollPane = new JScrollPane(panel_menu_list);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(jScrollPane);

		panel_menu_list.setBackground(new Color(240, 240, 240));
		panel_menu_list.removeAll();
		
	}
	
	public void addMember(Model_User user) {
		panel_menu_list.add(new Item_People(user) , "width 295:295:295, height 50:50:50, wrap");
		panel_menu_list.repaint();
		panel_menu_list.revalidate();
	}
	
	

}
