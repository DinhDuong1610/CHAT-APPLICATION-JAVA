package view.form;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;

import model.Model_Group;
import model.Model_User;
import net.miginfocom.swing.MigLayout;
import service.Service;
import view.component.Chat_Body;
import view.component.Chat_Bottom;
import view.component.Chat_Title;

public class Chat extends JPanel{
	private Chat_Body chatBody;
	private Chat_Bottom chatBottom;
	private Chat_Title chatTitle;
	private Model_Group group;
	private ListGroup member;
	
	public Chat() {
		chatBody = new Chat_Body();
		chatBottom = new Chat_Bottom();
		chatTitle = new Chat_Title();
		
        setBackground(new Color(255, 255, 255));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 727, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
        );
		
        setLayout(new MigLayout("fillx", "0[fill]0", "0[]0[100%, fill]0[shrink 0]0"));
        
        add(chatTitle, "wrap, height 50:50:50");
        add(chatBody, "wrap");
        add(chatBottom, "h :: 50%");
	}
	
    public void setUser(Model_User user) {
        chatTitle.setUserName(user);
        chatBottom.setUser(user);
        chatBody.setUser(user);
        chatBody.clearChat();
        Service.getInstance().historyMessage(user.getUser_Id());
    }
    
    public void setGroup(Model_Group group) {
    	this.group = group;
        chatTitle.setGroupName(group);
        chatBottom.setGroup(group);
        chatBody.setGroup(group);
        chatBody.clearChat();
//        Service.getInstance().historyGroup(group);
    }
    
	public void memberGroup() {
		JDialog dialog = new JDialog();
		member = new ListGroup(group);
		Service.getInstance().listMember(group.getGroupId());
		dialog.setSize(310, 400);
		dialog.setLocationRelativeTo(null);
		dialog.add(member);
		dialog.setVisible(true);
	}

    public void updateUser(Model_User user) {
        chatTitle.updateUser(user);
    }

	public Chat_Body getChatBody() {
		return chatBody;
	}

	public Chat_Title getChatTitle() {
		return chatTitle;
	}

	public void setChatTitle(Chat_Title chatTitle) {
		this.chatTitle = chatTitle;
	}

	public Model_Group getGroup() {
		return group;
	}

	public ListGroup getMember() {
		return member;
	}
	
	
      
}
