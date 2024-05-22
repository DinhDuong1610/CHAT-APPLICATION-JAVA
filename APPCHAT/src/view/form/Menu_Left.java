package view.form;

import java.awt.CardLayout;
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

import model.Model_Group;
import model.Model_User;
import net.miginfocom.swing.MigLayout;
import service.Service;
import view.Main;
import view.component.Item_People;
import view.component.Item_group;

public class Menu_Left extends JPanel{
	private JLayeredPane panel_menu_list;
	private List<Model_User> userAccount;
	private List<Model_Group> groupList;
	private JPanel panel_chat;
	private CardLayout cardLayout;
	private JLayeredPane panel_group_chat;

	public Menu_Left() {
		userAccount = new ArrayList<>();
		groupList =  new ArrayList<>();
		setSize(300, 803);
		setLayout(new MigLayout("fillx, filly", "0[300]0", "0[50]0[100%,fill]0"));
		JPanel panel_menu = new JPanel();
		add(panel_menu, "width 300:300:300, height 50:50:50, wrap");
		panel_menu.setLayout(new MigLayout("fillx, filly", "0[50!]0[125!]0[125!]0", "0[50]0"));
		
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
	
		cardLayout = new CardLayout();
		panel_chat = new JPanel(cardLayout);
		add(panel_chat);
				
		panel_menu_list = new JLayeredPane();
		panel_menu_list.setLayout(new MigLayout("fillx", "2[300]2", "3[]3"));

		JScrollPane jScrollPane = new JScrollPane(panel_menu_list);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_chat.add(jScrollPane, "panel_menu_list");
		
		panel_group_chat = new JLayeredPane();
		panel_group_chat.setLayout(new MigLayout("fillx", "2[300]2", "3[]3"));

		JScrollPane jScrollPane2 = new JScrollPane(panel_group_chat);
		jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_chat.add(jScrollPane2, "panel_group_chat");
		
		cardLayout.show(panel_chat, "panel_menu_list");
		
		setBackground(Color.blue);
		panel_menu_list.removeAll();
	}
	
	public void newUser(Model_User d) {	
	  userAccount.add(d);
	  panel_menu_list.add(new Item_People(d), "width 285:285:285, height 50:50:50, wrap");
	  panel_menu_list.repaint();
	  panel_menu_list.revalidate();
	}
	
	public void addGroup(Model_Group group) {	
		  groupList.add(group);
		  panel_group_chat.add(new Item_group(group), "width 285:285:285, height 50:50:50, wrap");
		  panel_group_chat.repaint();
		  panel_group_chat.revalidate();
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
		cardLayout.show(panel_chat, "panel_menu_list");
		Service.getInstance().getMain().getHome().getChat().getChatTitle().getBt_add().setVisible(false);
		Service.getInstance().getMain().getHome().getChat().getChatTitle().getBt_member().setVisible(false);
	}
	
	public void showGroup() {
		cardLayout.show(panel_chat, "panel_group_chat");
		Service.getInstance().getMain().getHome().getChat().setVisible(true);
		Service.getInstance().getMain().getHome().getChat().getChatTitle().getLbName().setText("");
		Service.getInstance().getMain().getHome().getChat().getChatTitle().getLbStatus().setText("");
		Service.getInstance().getMain().getHome().getChat().getChatTitle().getBt_add().setVisible(true);
		Service.getInstance().getMain().getHome().getChat().getChatTitle().getBt_member().setVisible(true);
		Service.getInstance().getMain().getHome().getChat().getChatBody().clearChat();
		
		panel_group_chat.removeAll();
		panel_group_chat.repaint();
		panel_group_chat.revalidate();
		Service.getInstance().listGroup();
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
