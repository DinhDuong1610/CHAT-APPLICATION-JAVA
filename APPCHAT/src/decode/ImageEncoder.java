package decode;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class ImageEncoder {
    public static String encodeImageToString(JLabel label) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(file);
                ImageIcon icon = new ImageIcon(image.getScaledInstance(350, 350, Image.SCALE_SMOOTH));
                label.setIcon(icon);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(image, "png", byteArrayOutputStream);
                byte[] byteArr = byteArrayOutputStream.toByteArray();
                String encodedImage = Base64.getEncoder().encodeToString(byteArr);
                return encodedImage;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static String encodeImageToString(ImageIcon imageIcon) {
        try {
            Image image = imageIcon.getImage();
            BufferedImage bufferedImage = toBufferedImage(image);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            byte[] byteArr = byteArrayOutputStream.toByteArray();
            String encodedImage = Base64.getEncoder().encodeToString(byteArr);
            return encodedImage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();

        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }
}

