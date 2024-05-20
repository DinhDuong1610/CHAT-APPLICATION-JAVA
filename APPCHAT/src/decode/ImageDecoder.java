package decode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageDecoder {
    public static ImageIcon decodeStringToImageIcon(String encodedImage) {
        try {
            byte[] byteArr = Base64.getDecoder().decode(encodedImage);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArr);
            BufferedImage image = ImageIO.read(byteArrayInputStream);
            return new ImageIcon(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
