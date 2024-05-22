package view.component;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import model.Model_Group;
import model.Model_Message;
import model.Model_Message_Group;
import model.Model_User;
import net.miginfocom.swing.MigLayout;
import service.Service;

public class Chat_Body extends javax.swing.JPanel {
    private JPanel body;
    private JScrollPane sp;
    private Model_User user;
    private Model_Group group;
	
    public Chat_Body() {
    	sp = new JScrollPane();
        body = new JPanel();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

//        body.setBackground(new Color(236, 247, 252));
        body.setBackground(new Color(230, 230, 230));

        GroupLayout bodyLayout = new GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        ); 
        
        body.setLayout(new MigLayout("fillx", "", "5[]5"));
        
        updateScroll();
    }
    
    public void loadHistory(String history) {
    	System.out.println("loadHistory : \n" + history);
        String[] lines = history.split("\n");
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length == 4) {
                int fromUserID = Integer.parseInt(parts[0]);
                String text = parts[1];
                int toUserID = Integer.parseInt(parts[2]);
                String time = parts[3];
                
                if(fromUserID == Service.getInstance().getUser().getUser_Id()) {
                	Model_Message data = new Model_Message(fromUserID, toUserID, text, time);
                	addItemRight(data);
                }
                else {
                	Model_Message data = new Model_Message(fromUserID, toUserID, text, time);
                	addItemLeft(data);
                }
            } else {
                System.out.println("Dòng không hợp lệ: " + line);
            }
        }
    }
    
    public void addItemLeft(Model_Message data) {
    	Chat_Left item = new Chat_Left(user);
        item.setText(data.getText());
        item.setTime(data.getTime());
        body.add(item, "wrap, w 100::80%");
        repaint();
        revalidate();
        updateScroll();  
        updateScroll(); 
    }
    
    public void addItemLeft(Model_Message_Group data) {
        Chat_Left_Group item = new Chat_Left_Group();
        item.setUserProfile(data.getName());
        item.setText(data.getMessage());
        item.setTime();
        body.add(item, "wrap, w 100::80%");
        repaint();
        revalidate();
        updateScroll();  
    }
    
    
    public void addItemRight(Model_Message data) {
        Chat_Right item = new Chat_Right();
        item.setText(data.getText());
        body.add(item, "wrap, al right, w 100::80%");
        repaint();
        revalidate();
        item.setTime(data.getTime());
        updateScroll();
        updateScroll();
    }
    
    public void addItemRight(Model_Message_Group data) {
        Chat_Right item = new Chat_Right();
        item.setText(data.getMessage());
        body.add(item, "wrap, al right, w 100::80%");
        repaint();
        revalidate();
        updateScroll();
        updateScroll();
    }
    
    public void clearChat() {
        body.removeAll();
        repaint();
        revalidate();
    }
    
    public void updateScroll() {
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = sp.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        });
    }
    
    public Model_User getUser() {
        return user;
    }

    public void setUser(Model_User user) {
        this.user = user;
    }

	public Model_Group getGroup() {
		return group;
	}

	public void setGroup(Model_Group group) {
		this.group = group;
	}
    
    
}
