package view.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;

import view.design.JIMSendTextPane;

public class Chat_Item extends JLayeredPane{
	private JIMSendTextPane txt;
	private JLabel label;
	
	public Chat_Item() {
		txt = new JIMSendTextPane();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		txt.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		txt.setSelectionColor(new Color(92, 188, 255));
		
		add(txt);
		txt.setEditable(false);
		txt.setBackground(new Color(0, 0 , 0, 0));
		txt.setOpaque(false);
	}
	
	public void setText(String text) {
		txt.setText(text);
	}
	
	public void setTime(String time) {
		JLayeredPane layer = new JLayeredPane();
		layer.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		layer.setBorder(new EmptyBorder(0, 5, 10, 5));
		label = new JLabel(time);
		label.setForeground(new Color(110, 110, 110));
		label.setHorizontalTextPosition(JLabel.LEFT);
		label.setFont(new Font("tohoma", Font.PLAIN, 15));
		layer.add(label);
		add(layer);
	}
	
	public void setUserProfile(String user) {
		JLayeredPane layer = new JLayeredPane();
		layer.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		layer.setBorder(new EmptyBorder(10, 10, 0, 10));
		JButton cmd = new JButton(user);
		cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cmd.setBorder(null);
		cmd.setContentAreaFilled(false);
		cmd.setFocusable(false);
		cmd.setForeground(new Color(30, 121, 213));
		txt.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		cmd.setFont(new Font("sansserif", Font.BOLD, 18));
		layer.add(cmd);
		add(layer, 0);
	}
	
	public void hideText() {
		txt.setVisible(false);
	}
	
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }
}
