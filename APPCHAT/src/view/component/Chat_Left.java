package view.component;

import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

import decode.ImageDecoder;
import model.Model_User;
import view.design.ImageAvatar;

public class Chat_Left extends JLayeredPane{
	private ImageAvatar IaImage;
	private JLayeredPane jLayeredPane;
	private Chat_Item txt;
	
	public Chat_Left(Model_User user) {
		jLayeredPane = new JLayeredPane();
		IaImage = new ImageAvatar();
		txt = new Chat_Item();
		
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		IaImage.setBorderSize(0);
//		IaImage.setImage(new ImageIcon(getClass().getResource("/images/testing/avatar.png")));
		IaImage.setImage(ImageDecoder.decodeStringToImageIcon(user.getAvatar_path()));
		IaImage.setMaximumSize(new Dimension(31, 31));
        IaImage.setMinimumSize(new Dimension(31, 31));
        IaImage.setPreferredSize(new Dimension(31, 31));
        
        jLayeredPane.setLayer(IaImage, JLayeredPane.DEFAULT_LAYER);

        GroupLayout jLayeredPane1Layout = new GroupLayout(jLayeredPane);
        jLayeredPane.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(IaImage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(IaImage, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
        );
        
        add(jLayeredPane);
        add(txt);
//        setBackground(new Color(242, 242, 242));
        setBackground(new Color(255, 255, 255));
	}
	
	public void setUserProfile(String user) {
		txt.setUserProfile(user);
	}
	
	public void setText(String text) {
		if(text.equals("")) {
			txt.hideText();
		}
		else {
			txt.setText(text);
		}
	}
       
    public void setTime(String time) {
        txt.setTime(time); 
    }

}
