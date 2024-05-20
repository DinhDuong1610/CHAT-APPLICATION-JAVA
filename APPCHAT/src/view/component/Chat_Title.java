package view.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.Model_User;

public class Chat_Title extends JPanel{
	private JLayeredPane layer;
	private JLabel lbName;
	private JLabel lbStatus;
	
	private Model_User user;
	
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
		
		GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(layer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(406, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(layer, GroupLayout.PREFERRED_SIZE, 38, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
		
	}
	
    public void setUserName(Model_User user) {
        this.user = user;
        lbName.setText(user.getFullName());
        if (user.isStatus()) {
        	setStatusText(true);
        } else {
        	setStatusText(false);
        }
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
