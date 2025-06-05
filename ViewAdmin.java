

import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;

public class ViewAdmin extends JFrame {
    private final Data data;

    public ViewAdmin(Data sharedData) {
        this.data = sharedData;

        // Set window properties
        ImageIcon icon = new ImageIcon("images/logo.png"); // or "icon.png"
        setIconImage(icon.getImage());
        setTitle("Admin Panel - Order History");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main container panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(data.getColor1());

        // Title at the top
        JLabel titleLabel = new JLabel("Order History and Sales Summary", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(data.getColor4());
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Text area to show order history and total sales
        JTextPane orderTextPane = new JTextPane();
        orderTextPane.setEditable(false);
        orderTextPane.setFont(new Font("Monospaced", Font.PLAIN, 14));
        orderTextPane.setBackground(data.getColor2());
        orderTextPane.setForeground(data.getColor4());

        // Center-align text
        StyledDocument doc = orderTextPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        // Scroll pane for the text area
        JScrollPane scrollPane = new JScrollPane(orderTextPane);
        scrollPane.setBorder(BorderFactory.createLineBorder(data.getColor4(), 2));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Close button to exit the admin window
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        closeButton.setBackground(data.getColor3());
        closeButton.setForeground(data.getColor4());
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        closeButton.addActionListener(e -> dispose());

        // Footer panel containing the close button
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(data.getColor1());
        footerPanel.add(closeButton);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // Set main panel as the content of the window
        setContentPane(mainPanel);

        // Prepare the receipt history and calculate total revenue
        double totalRevenue = 0.0;
        StringBuilder content = new StringBuilder();

        for (int i = 0; i < data.getAllReceipts().size(); i++) {
            content.append("=== RECEIPT #").append(i + 1).append(" ===\n");
            content.append(data.getAllReceipts().get(i)).append("\n\n");
            totalRevenue += data.getAllTotals().get(i);
        }

        content.append("========== TOTAL REVENUE ==========\n");
        content.append(String.format("TOTAL SALES: ₱%.2f\n", totalRevenue));

        // Display the summary in the text pane
        orderTextPane.setText(content.toString());
    }
}
