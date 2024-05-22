package view.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Model_Group;
import model.Model_User;
import service.Service;

import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Chat_Title extends JPanel{
	private JLayeredPane layer;
	private JLabel lbName;
	private JLabel lbStatus;
	
	private Model_User user;
	private Model_Group group;
	private JButton bt_member;
	private JButton bt_add;
	
	public Chat_Title() {
		layer = new JLayeredPane();
		lbName = new JLabel();
		lbStatus = new JLabel();
		
		setBackground(new Color(255, 255, 255));
		layer.setLayout(new GridLayout(0, 1));
		
		lbName.setFont(new Font("sansserif", Font.BOLD, 18));
		lbName.setForeground(new Color(66, 66, 66));
		lbName.setText("Đính Dương");
		layer.add(lbName);
		
		lbStatus.setForeground(new Color(71, 212, 90));
		lbStatus.setFont(new Font("sansserif", Font.BOLD, 16));
		setStatusText(true);
		layer.add(lbStatus);
		
		bt_member = new JButton("Member");
		bt_member.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Service.getInstance().getMain().getHome().getChat().memberGroup();
			}
		});
		bt_member.setFont(new Font("Tahoma", Font.BOLD, 15));
		bt_member.setVisible(false);
		
		bt_add = new JButton("New group");
		bt_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGroup();
			}
		});
		bt_add.setFont(new Font("Tahoma", Font.BOLD, 15));
		bt_add.setVisible(false);
		
		GroupLayout layout = new GroupLayout(this);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(layer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 490, Short.MAX_VALUE)
					.addComponent(bt_member, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(bt_add)
					.addGap(63))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(layer, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
							.addGap(10)
							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(bt_add, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
								.addComponent(bt_member, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addGap(10)))
					.addContainerGap())
		);
        this.setLayout(layout);
		
	}
	
	public void newGroup() {
		String nameGroup = JOptionPane.showInputDialog(Service.getInstance().getMain().getHome().getMenu_Left(), "INPUT NAME GROUP");
		Model_Group group = new Model_Group(0, nameGroup);
		Service.getInstance().addGroup(group.toJsonObject("addGroup"));
	}
	
    public void setUserName(Model_User user) {
        this.user = user;
        lbName.setText(user.getFullName());
        lbStatus.setVisible(true);
        if (user.isStatus()) {
        	setStatusText(true);
        } else {
        	setStatusText(false);
        }
    }
    
    public void setGroupName(Model_Group group) {
        this.group = group;
        lbName.setText(group.getGroupName());
        lbStatus.setVisible(false);
    }

    public void updateUser(Model_User user) {
        if (this.user == user) {
            lbName.setText(user.getFullName());
            if (user.isStatus()) {
            	setStatusText(true);
            } else {
            	setStatusText(false);
            }
        }
    }
	
	public void setUserName(String userName) {
		lbName.setText(userName);
	}
	
	public void setStatusText(boolean active) {
		if(active) {
			lbStatus.setText("Online");
			lbStatus.setForeground(Color.GREEN);
		}
		else {
			lbStatus.setText("Offline");
			lbStatus.setForeground(new Color(151, 41, 32));
		}
	}
	
		
	
    public JButton getBt_member() {
		return bt_member;
	}

	public JButton getBt_add() {
		return bt_add;
	}

	public Model_User getUser() {
        return user;
    }

	public JLayeredPane getLayer() {
		return layer;
	}

	public void setLayer(JLayeredPane layer) {
		this.layer = layer;
	}

	public JLabel getLbName() {
		return lbName;
	}

	public void setLbName(JLabel lbName) {
		this.lbName = lbName;
	}

	public JLabel getLbStatus() {
		return lbStatus;
	}

	public void setLbStatus(JLabel lbStatus) {
		this.lbStatus = lbStatus;
	}

	public void setUser(Model_User user) {
		this.user = user;
	}
    
    
}
