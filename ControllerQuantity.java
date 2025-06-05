import javax.swing.*;
import java.awt.*;

public class ControllerQuantity {
    private Data data;
    private ModelImage image;
    private ModelViewOrder modelView;

    // Constructor receives shared data, image handler, and order summary updater
    public ControllerQuantity(Data sharedData, ModelImage image, ModelViewOrder modelView) {
        this.data = sharedData;
        this.image = image;
        this.modelView = modelView;
    }

    // Display dialog for selecting quantity of an item to add to the order
    public void showQuantityDialog(String itemName, double price) {
        JDialog dialog = new JDialog((Frame) null, "Add to Order", true);
        dialog.setUndecorated(true);
        dialog.getContentPane().setBackground(data.getColor2());
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.getRootPane().setBorder(BorderFactory.createLineBorder(data.getColor1(), 2));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(data.getColor2());

        // Load and scale item image
        JLabel imageLabel = new JLabel();
        ImageIcon icon = image.loadIcon(itemName);
        if (icon != null) {
            Image scaledImage = image.getHighQualityScaledImage(icon.getImage(), 200, 200);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            imageLabel.setText("No Image");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
            imageLabel.setPreferredSize(new Dimension(200, 200));
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(imageLabel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        rightPanel.setBackground(data.getColor2());

        // Top section for name and price
        JPanel topRight = new JPanel(new GridLayout(2, 1));
        topRight.setBackground(data.getColor2());

        JLabel nameLabel = new JLabel(itemName);
        nameLabel.setFont(new Font("Georgia", Font.BOLD, 18));

        JLabel priceLabel = new JLabel("Price: ₱" + String.format("%.2f", price));
        priceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

        topRight.add(nameLabel);
        topRight.add(priceLabel);
        rightPanel.add(topRight, BorderLayout.NORTH);

        // Item description
        String description = data.getItemDescriptions().getOrDefault(itemName, "No description available.");
        JLabel descriptionLabel = new JLabel("<html><p style='width: 200px;'>" + description + "</p></html>");
        descriptionLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        rightPanel.add(descriptionLabel, BorderLayout.CENTER);

        // Quantity control section (+/- buttons and text field)
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        quantityPanel.setBackground(data.getColor2());

        JButton subtract = new JButton("-");
        subtract.setFont(new Font("Segoe UI", Font.BOLD, 16));
        subtract.setBackground(data.getColor3());
        subtract.setForeground(Color.WHITE);
        subtract.setFocusPainted(false);

        JTextField quantityField = new JTextField("1", 3);
        quantityField.setHorizontalAlignment(JTextField.CENTER);
        quantityField.setFont(new Font("Serif", Font.BOLD, 16));

        JButton addition = new JButton("+");
        addition.setFont(new Font("Segoe UI", Font.BOLD, 16));
        addition.setBackground(data.getColor3());
        addition.setForeground(Color.WHITE);
        addition.setFocusPainted(false);

        quantityPanel.add(subtract);
        quantityPanel.add(quantityField);
        quantityPanel.add(addition);

        // Buttons to add to order or cancel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(data.getColor2());

        JButton addOrderButton = new JButton("Add to Order");
        addOrderButton.setBackground(data.getColor3());
        addOrderButton.setForeground(Color.WHITE);
        addOrderButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addOrderButton.setFocusPainted(false);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(data.getColor5());
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelButton.setFocusPainted(false);

        buttonPanel.add(addOrderButton);
        buttonPanel.add(cancelButton);

        // Add quantity controls and buttons to bottom of the panel
        JPanel bottomContainer = new JPanel(new BorderLayout());
        bottomContainer.setBackground(data.getColor2());
        bottomContainer.add(quantityPanel, BorderLayout.NORTH);
        bottomContainer.add(buttonPanel, BorderLayout.SOUTH);
        rightPanel.add(bottomContainer, BorderLayout.SOUTH);

        mainPanel.add(rightPanel, BorderLayout.CENTER);
        dialog.add(mainPanel, BorderLayout.CENTER);

        // Increase quantity
        addition.addActionListener(e -> {
            try {
                int qty = Integer.parseInt(quantityField.getText());
                quantityField.setText(String.valueOf(qty + 1));
            } catch (NumberFormatException ex) {
                quantityField.setText("1");
            }
        });

        // Decrease quantity
        subtract.addActionListener(e -> {
            try {
                int qty = Integer.parseInt(quantityField.getText());
                if (qty > 1) {
                    quantityField.setText(String.valueOf(qty - 1));
                }
            } catch (NumberFormatException ex) {
                quantityField.setText("1");
            }
        });

        // Add item to order and update summary
        addOrderButton.addActionListener(e -> {
            try {
                int qty = Integer.parseInt(quantityField.getText());
                if (qty > 0) {
                    data.getOrder().put(itemName, data.getOrder().getOrDefault(itemName, 0) + qty);
                    if (!data.getItemPrices().containsKey(itemName)) {
                        data.getItemPrices().put(itemName, price);
                    }
                    modelView.updateOrderSummary();
                    JOptionPane.showMessageDialog(null, qty + " x " + itemName + " added to order.", "Order Updated", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Quantity must be greater than 0.", "Invalid Quantity", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException ex1) {
                JOptionPane.showMessageDialog(null, "Please enter a valid quantity.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Cancel button closes dialog
        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
