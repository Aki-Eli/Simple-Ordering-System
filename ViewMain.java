import javax.swing.*;
import java.awt.*;

public class ViewMain extends JPanel {
    private Data data;
    private JButton viewOrderButton;
    private JButton adminButton;
    private JButton finishButton;
    private ModelImage image; // Used only to resize the logo image

    public ViewMain(Data sharedData) {
        this.data = sharedData;
        this.image = new ModelImage();
        GUI(); // Build the GUI
    }

    private void GUI() {
        this.setLayout(new BorderLayout());

        // ----- Top (North) Panel -----
        data.setNorth(new JPanel(new BorderLayout()));
        data.getNorth().setBackground(data.getColor1());

        // App Title
        JLabel title = new JLabel("KusinaTech", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Logo image
        ImageIcon icon = new ImageIcon("images/logo.png");
        Image resizedImage = image.getHighQualityScaledImage(icon.getImage(), 70, 70);
        JLabel imageLabel = new JLabel(new ImageIcon(resizedImage));
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Admin button on top right
        adminButton = new JButton("Admin");
        adminButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        adminButton.setBackground(data.getColor3());
        adminButton.setForeground(Color.WHITE);
        adminButton.setFocusPainted(false);
        adminButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Add logo, title, and admin button to top panel
        data.getNorth().add(imageLabel, BorderLayout.WEST);
        data.getNorth().add(title, BorderLayout.CENTER);
        data.getNorth().add(adminButton, BorderLayout.EAST);

        // ----- Left (West) Panel -----
        data.setWest(new JPanel());
        data.getWest().setLayout(new BoxLayout(data.getWest(), BoxLayout.Y_AXIS));
        data.getWest().setBackground(data.getColor4());
        data.getWest().setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        data.getWest().setPreferredSize(new Dimension(250, 1080)); // Fixed width

        // ----- Center Panel -----
        data.setCenter(new JPanel()); // Dynamic item area

        // ----- Bottom (South) Panel -----
        data.setSouth(new JPanel(new BorderLayout()));
        data.getSouth().setBackground(data.getColor1());
        data.getSouth().setPreferredSize(new Dimension(250, 100)); // Fixed height

        // Buttons on bottom left
        JPanel leftButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftButtons.setBackground(data.getColor3());
        leftButtons.setOpaque(false);

        // View Order button
        viewOrderButton = new JButton("View Order");
        viewOrderButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        viewOrderButton.setBackground(data.getColor3());
        viewOrderButton.setForeground(Color.WHITE);
        viewOrderButton.setFocusPainted(false);
        viewOrderButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Finish Order button
        finishButton = new JButton("Finish Order");
        finishButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        finishButton.setBackground(data.getColor3());
        finishButton.setForeground(Color.WHITE);
        finishButton.setFocusPainted(false);
        finishButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        leftButtons.add(viewOrderButton);
        leftButtons.add(finishButton);

        // Order summary on bottom right
        JPanel rightInfo = new JPanel(new GridLayout(2, 2, 0, 0));
        rightInfo.setBackground(Color.decode("#D2B48C"));
        rightInfo.setBorder(BorderFactory.createLineBorder(Color.decode("#504B38"), 10));
        rightInfo.setOpaque(true);

        // Info labels
        data.setTotalItemsLabel(new JLabel("Items: 0"));
        data.setTotalQtyLabel(new JLabel("Quantity: 0"));
        data.setTotalPriceLabel(new JLabel("Total: ₱0.00"));

// Set shared font and properties for all labels (except boldness)
        Font plainFont = new Font("SansSerif", Font.PLAIN, 20);
        Font boldFont = new Font("SansSerif", Font.BOLD, 20);

        data.getTotalItemsLabel().setFont(plainFont);
        data.getTotalQtyLabel().setFont(plainFont);
        data.getTotalPriceLabel().setFont(boldFont); // Make this one bold

        for (JLabel label : new JLabel[] {
            data.getTotalItemsLabel(),
            data.getTotalQtyLabel(),
            data.getTotalPriceLabel()
      }) {
         label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
         label.setHorizontalAlignment(SwingConstants.LEFT);
}

        rightInfo.add(data.getTotalItemsLabel());
        rightInfo.add(data.getTotalQtyLabel());
        rightInfo.add(data.getTotalPriceLabel());

        data.getSouth().add(leftButtons, BorderLayout.WEST);
        data.getSouth().add(rightInfo, BorderLayout.EAST);

        // Add panels to the main layout
        this.add(data.getNorth(), BorderLayout.NORTH);
        this.add(data.getWest(), BorderLayout.WEST);
        this.add(data.getCenter(), BorderLayout.CENTER);
        this.add(data.getSouth(), BorderLayout.SOUTH);
    }

    // Expose buttons for controllers
    public JButton getViewOrderButton() {
        return viewOrderButton;
    }

    public JButton getAdminButton() {
        return adminButton;
    }

    public JButton getFinishButton() {
        return finishButton;
    }
}
