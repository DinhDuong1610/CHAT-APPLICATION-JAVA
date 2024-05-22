package view.component;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JLayeredPane;

public class Chat_Left_Group extends JLayeredPane{
	private Chat_Item txt;
	
	public Chat_Left_Group() {
		txt = new Chat_Item();
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(txt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGap(0, 0, 0)));
		
		txt.setBackground(new Color(179, 233, 255));
	}
	
    public void setText(String text) {
        if(text.equals("")) {
        	txt.hideText();
        }
        else {
        	txt.setText(text);
        }
    }
 
    
    public void setUserProfile(String user) {
    	txt.setUserProfile(user);
    }

    public void setTime() {
        Date currentTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String formattedTime = dateFormat.format(currentTime);
        txt.setTime(formattedTime); 
    }
	

}
