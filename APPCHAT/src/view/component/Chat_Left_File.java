package view.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import decode.ImageDecoder;
import model.Model_File;
import model.Model_User;
import view.design.ImageAvatar;

public class Chat_Left_File extends JLayeredPane{
	private ImageAvatar IaImage;
	private JLayeredPane jLayeredPane;
	private Chat_Item_File txt;
	private Model_File file;
	
	public Chat_Left_File(Model_User user) {
		jLayeredPane = new JLayeredPane();
		IaImage = new ImageAvatar();
		txt = new Chat_Item_File();
		
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
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (file != null) {
                    handleFileClick();
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
            }
        });
        
        add(jLayeredPane);
        add(txt);
//        setBackground(new Color(242, 242, 242));
//        setBackground(new Color(255, 255, 255));
		txt.setBackground(new Color(51, 180, 241));
	}
	
	public void handleFileClick() {
        // Hỏi người dùng có muốn tải file hay không
        int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn tải file không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            // Người dùng chọn "Có", hiển thị hộp thoại lưu file
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File(file.getName())); // Đặt tên mặc định cho file

            int option = fileChooser.showSaveDialog(this); // Mở hộp thoại lưu file
            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                // Lưu file được chọn
                try (FileOutputStream fos = new FileOutputStream(selectedFile)) {
                    fos.write(file.getContent()); // Ghi nội dung file vào file được chọn
                    JOptionPane.showMessageDialog(this, "Tải file thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Không thể tải file: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
	
	public void setUserProfile(String user) {
		txt.setUserProfile(user);
	}
	
	public void setFile(Model_File file) {
        this.file = file;
        txt.setFile(file);
    }
	
//	public void setText(String text) {
//		if(text.equals("")) {
//			txt.hideText();
//		}
//		else {
//			txt.setText(text);
//		}
//	}
       
    public void setTime(String time) {
        txt.setTime(time); 
    }

}
