package view.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Model_Group;
import model.Model_Message;
import model.Model_Message_Group;
import model.Model_User;
import net.miginfocom.swing.MigLayout;
import service.Service;
import view.design.JIMSendTextPane;

public class Chat_Bottom extends JPanel{

    private Model_User user;
    private Model_Group group;
    
	public Chat_Bottom() {
        setBackground(new Color(255, 255, 255));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        
        setLayout(new MigLayout("fillx, filly", "2[fill]0[]0[]2", "10[fill]2"));
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        JIMSendTextPane txt = new JIMSendTextPane();
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                refresh();
            }
        });
        txt.setHintText("Write message here...");
        scroll.setViewportView(txt);
        add(scroll, "w 100%");
        
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("filly", "0[]0", "0[bottom]0"));
        panel.setPreferredSize(new Dimension(30, 28));
        panel.setBackground(Color.white);
        JButton cmd = new JButton();
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setIcon(new ImageIcon(getClass().getResource("/images/icon/send.png")));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String text = txt.getText().trim();
                if (!text.equals("")) {
                    Date currentTime = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM");
                    String formattedTime = dateFormat.format(currentTime);
                	
//                    Model_Message message = new Model_Message(Service.getInstance().getUser().getUser_Id(), user.getUser_Id(), text, formattedTime);
                    if(!Service.getInstance().getMain().getHome().getChat().getChatTitle().getLbStatus().getText().isEmpty()) {                    	
                        Model_Message message = new Model_Message(Service.getInstance().getUser().getUser_Id(),  user.getUser_Id(), text, formattedTime);
                    	send(message);
                    	Service.getInstance().sendBottomChat(message);
                    }
                    else {
                        Model_Message_Group message = new Model_Message_Group(group.getGroupId(), Service.getInstance().getUser().getFullName(), text);
                    	sendGroup(message);
                    	Service.getInstance().sendBottomChat(message);
                    }
//                    PublicEvent.getInstance().getEventChat().sendMessage(message);
              
                    txt.setText("");
                    txt.grabFocus();
                    refresh();
                } else {
                    txt.grabFocus();
                }
            }
        });
        panel.add(cmd);
        add(panel);
	}
	
	private void send(Model_Message data) {
		Service.getInstance().sendMessage(data.toJsonObject("sendMessage"));
    }
	
	private void sendGroup(Model_Message_Group data) {
		Service.getInstance().sendMessageGroup(data.toJsonObject("sendMessageGroup"));
    }
	
	public void refresh() {
		revalidate();
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
