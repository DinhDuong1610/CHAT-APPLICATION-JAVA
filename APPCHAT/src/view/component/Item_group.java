package view.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Model_Group;
import service.Service;

public class Item_group extends JPanel{
	private JLabel lb_nameGroup;
	
	private boolean mouseOver;
	Model_Group group;
	
	public Item_group(Model_Group group) {
		this.group = group;
		lb_nameGroup = new JLabel(group.getGroupName());
		lb_nameGroup.setHorizontalAlignment(SwingConstants.LEFT);
		lb_nameGroup.setFont(new Font("Tahoma", Font.BOLD, 23));
		
		setBackground(Color.white);
		setBorder(new EmptyBorder(10, 20, 10, 10));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lb_nameGroup, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(lb_nameGroup, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		init();
	}
	
	public void init() {
		
    	addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(new Color(230, 230, 230));
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(new Color(255, 255, 255));
                mouseOver = false;
            }
            @Override
            public void mouseReleased(MouseEvent me) {
                if (mouseOver) { 
                	Service.getInstance().selectedGroup(group);
                }
            }
        });
    }
}
