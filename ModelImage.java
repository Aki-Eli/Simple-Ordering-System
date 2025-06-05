

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ModelImage { 

    // Load an image based on the item name and return it as an ImageIcon
    public ImageIcon loadIcon(String itemName) {
        try {
            // Format the filename (remove spaces, lowercase) and build the path
            String imagePath = "/images/" + itemName.replaceAll(" ", "").toLowerCase() + ".jpg";
            return new ImageIcon(getClass().getResource(imagePath));
        } catch (Exception e) {
            // If image loading fails, print a message and return null
            System.out.println("Image not found for: " + itemName);
            return null;
        }
    }

    // Resize an image with high-quality rendering settings
    public Image getHighQualityScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        // Set quality rendering options
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the original image into the new scaled size
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
