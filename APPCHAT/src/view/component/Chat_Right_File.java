package view.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import model.Model_File;

public class Chat_Right_File extends JLayeredPane{
	
	private Chat_Item_File txt;
	private Model_File file;
	
	public Chat_Right_File() {
		txt = new Chat_Item_File();
		setLayer(txt, JLayeredPane.DEFAULT_LAYER);
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(txt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
//		txt.setBackground(new Color(179, 233, 255));
		txt.setBackground(new Color(51, 180, 241));
		
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
	
//    public void setText(String text) {
//        if (text.equals("")) {
//            txt.hideText();
//        } else {
//            txt.setText(text);
//        }
//    }
    
	public void setFile(Model_File file) {
        this.file = file;
        txt.setFile(file);
    }
    
    public void setUserProfile(String user) {
    	txt.setUserProfile(user);
    }

    public void setTime(String time) {
        txt.setTime(time); 
    }

}
