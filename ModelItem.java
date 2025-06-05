import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModelItem {
    private Data data;
    private ControllerAction.View action;
    private ModelImage image;

    public ModelItem(Data sharedData, ControllerAction.View action, ModelImage image) {
        this.data = sharedData;
        this.action = action;
        this.image = new ModelImage();
    }

    // Populates item map with buttons for each category
    public void createItemMap() {
        data.getItemMap().put("Sulit Meals", new JButton[]{
            createItemButton("Sulit Meal A", 170.00),
            createItemButton("Sulit Meal B", 180.00),
            createItemButton("Sulit Meal C", 190.00),
            createItemButton("Sulit Meal D", 200.00),
            createItemButton("Sulit Meal E", 210.00),
        });

        data.getItemMap().put("Family Meals", new JButton[]{
            createItemButton("Family Meal 1", 750.00),
            createItemButton("Family Meal 2", 800.00),
            createItemButton("Family Meal 3", 900.00)
        });

        data.getItemMap().put("Pork", new JButton[]{
            createItemButton("Paksiw na Pata", 100.00),
            createItemButton("Hamonado", 130.00),
            createItemButton("Giniling", 100.00),
            createItemButton("Pochero", 110.00),
            createItemButton("Igado", 120.00),
            createItemButton("Steak", 140.00)
        });

        data.getItemMap().put("Chicken", new JButton[]{
            createItemButton("Adobo", 90.00),
            createItemButton("Ginataang Manok", 100.00),
            createItemButton("Creamy Chicken with Mushrooms", 110.00),
            createItemButton("Caldereta", 120.00),
            createItemButton("Chicken Broccoli", 90.00),
            createItemButton("Fillet", 80.00)
        });

        data.getItemMap().put("Beef", new JButton[]{
            createItemButton("Mechado", 140.00),
            createItemButton("Bulalo", 160.00),
            createItemButton("Bopis", 100.00),
            createItemButton("Garlic Pepper Beef", 150.00),
            createItemButton("Menudo", 150.00),
            createItemButton("Papaitan", 120.00)
        });

        data.getItemMap().put("Vegetables", new JButton[]{
            createItemButton("Ginataang Kalabasa at Sitaw", 80.00),
            createItemButton("Ginisang Ampalaya", 60.00),
            createItemButton("Tortang Talong", 40.00),
            createItemButton("Ginisang Upo", 50.00),
            createItemButton("Lumpiang Sariwa", 70.00),
            createItemButton("Ginataang Langka", 90.00)
        });

        data.getItemMap().put("Desserts", new JButton[]{
            createItemButton("Maja Blanca", 50.00),
            createItemButton("Mango Tapioca", 60.00),
            createItemButton("Mango Graham", 50.00),
            createItemButton("Halo-Halo", 70.00),
            createItemButton("Palitaw with Ube", 60.00),
            createItemButton("Buchi with Yema", 50.00)
        });

        data.getItemMap().put("Beverages", new JButton[]{
            createItemButton("Mango Graham Shake", 80.00),
            createItemButton("Iced Tea", 60.00),
            createItemButton("Iced Coffee", 50.00),
            createItemButton("Classic Lemonade", 70.00),
            createItemButton("Lemon Yakult", 80.00),
            createItemButton("Lemon Orange Juice", 65.00)
        });

        data.getItemMap().put("Addons", new JButton[]{
            createItemButton("Rice", 25.00),
            createItemButton("Water", 0.00),
            createItemButton("Ice Bucket", 0.00)
            
        });
    }

    // Adds item descriptions for each item
    public void loadItemDescriptions() {
        data.getItemDescriptions().put("Sulit Meal A", "Giniling + Tortang Talong + Rice + Classic Lemonade.");
        data.getItemDescriptions().put("Sulit Meal B", "Paksiw na Pata + Ginataang Langka + Rice + Iced Tea.");
        data.getItemDescriptions().put("Sulit Meal C", "Chicken Adobo + Kalabasa at Sitaw + Rice + Mango Shake.");
        data.getItemDescriptions().put("Sulit Meal D", "Ginataang Manok + Ginisang Upo + Rice + Lemon Yakult.");
        data.getItemDescriptions().put("Sulit Meal E", "Beef Kaldereta + Lumpiang Sariwa + Rice + Iced Coffee.");

        data.getItemDescriptions().put("Family Meal 1", "Creamy Chicken with Mushrooms, Chicken Caldereta, Lumpiang Sariwa, Ginataang Langka, Rice Platter, and 4x Drinks (Mango Shake or Lemon Orange)");
        data.getItemDescriptions().put("Family Meal 2", "Pork Hamonado, Pork Pochero, Ginataang Kalabasa at Sitaw, Ginisang Upo, Rice Platter, and 4x Drinks (Ice Tea or Classic Lemonade)");
        data.getItemDescriptions().put("Family Meal 3", "Beef Menudo, Garlic Pepper Beef, Ginisang Ampalaya, Tortang Talong, Rice Platter, and 5x Drinks (Iced Coffee or Lemon Yakult)");

        // Pork descriptions
        data.getItemDescriptions().put("Paksiw na Pata", "Braised pork hock cooked in vinegar, soy sauce, and spices.");
        data.getItemDescriptions().put("Hamonado", "Pork slices stewed in pineapple juice for a sweet-savory flavor.");
        data.getItemDescriptions().put("Giniling", "Ground pork sautéed with vegetables and tomato sauce.");
        data.getItemDescriptions().put("Pochero", "Pork stew with saba bananas, pechay, and tomato sauce.");
        data.getItemDescriptions().put("Igado", "Ilocano pork dish with liver, bell peppers, and soy vinegar.");
        data.getItemDescriptions().put("Steak", "Tender pork steak marinated and pan-seared in savory sauce.");

        // Chicken descriptions
        data.getItemDescriptions().put("Adobo", "Filipino classic: chicken stewed in vinegar, soy sauce, and garlic.");
        data.getItemDescriptions().put("Ginataang Manok", "Chicken cooked in coconut milk with vegetables.");
        data.getItemDescriptions().put("Creamy Chicken with Mushrooms", "Chicken in a creamy mushroom sauce.");
        data.getItemDescriptions().put("Caldereta", "Spicy tomato-based chicken stew with liver spread.");
        data.getItemDescriptions().put("Chicken Broccoli", "Stir-fried chicken with fresh broccoli in garlic sauce.");
        data.getItemDescriptions().put("Fillet", "Breaded chicken fillet, crispy and golden.");

        // Beef descriptions
        data.getItemDescriptions().put("Mechado", "Beef stewed in tomato sauce with potatoes and carrots.");
        data.getItemDescriptions().put("Bulalo", "Beef shank soup with bone marrow and vegetables.");
        data.getItemDescriptions().put("Bopis", "Spicy Filipino dish of beef lungs and heart sautéed in chilies.");
        data.getItemDescriptions().put("Garlic Pepper Beef", "Thin slices of beef sautéed with garlic and pepper.");
        data.getItemDescriptions().put("Menudo", "Pork and liver stew with potatoes, carrots, and raisins.");
        data.getItemDescriptions().put("Papaitan", "Bitter beef soup made with innards and bile.");

        // Vegetable descriptions
        data.getItemDescriptions().put("Ginataang Kalabasa at Sitaw", "Squash and long beans cooked in coconut milk.");
        data.getItemDescriptions().put("Ginisang Ampalaya", "Bitter melon sautéed with egg and tomatoes.");
        data.getItemDescriptions().put("Tortang Talong", "Grilled eggplant omelette.");
        data.getItemDescriptions().put("Ginisang Upo", "Sautéed bottle gourd with garlic and onions.");
        data.getItemDescriptions().put("Lumpiang Sariwa", "Fresh vegetable spring roll with peanut sauce.");
        data.getItemDescriptions().put("Ginataang Langka", "Young jackfruit cooked in coconut milk.");

        // Desserts
        data.getItemDescriptions().put("Maja Blanca", "Coconut milk pudding with corn and creamy topping.");
        data.getItemDescriptions().put("Mango Tapioca", "Sweet mango cubes mixed with tapioca pearls and cream.");
        data.getItemDescriptions().put("Mango Graham", "Layered dessert with graham crackers, mango, and cream.");
        data.getItemDescriptions().put("Halo-Halo", "Mixed dessert with shaved ice, sweet beans, fruits, and leche flan.");
        data.getItemDescriptions().put("Palitaw with Ube", "Sticky rice cake with coconut, sesame, and ube filling.");
        data.getItemDescriptions().put("Buchi with Yema", "Glutinous rice balls filled with sweet custard yema.");

        // Beverages
        data.getItemDescriptions().put("Mango Graham Shake", "Cold mango and graham blend with creamy milk.");
        data.getItemDescriptions().put("Iced Tea", "Chilled sweet tea, a perfect refreshing drink.");
        data.getItemDescriptions().put("Iced Coffee", "Strong brewed coffee over ice with milk.");
        data.getItemDescriptions().put("Classic Lemonade", "Freshly squeezed lemonade with a sweet-tart flavor.");
        data.getItemDescriptions().put("Lemon Yakult", "Yakult drink mixed with tangy lemon juice.");
        data.getItemDescriptions().put("Lemon Orange Juice", "Zesty blend of lemon and orange juice.");

        data.getItemDescriptions().put("Rice", "Steamed white rice, the perfect meal companion.");
        data.getItemDescriptions().put("Water", "Refreshing filtered water, served chilled.");
        data.getItemDescriptions().put("Ice Bucket", "A bucket of ice cubes, perfect for chilling your drinks.");

    }

    // Creates clickable category labels on the left panel
    public void createCategoryLabels() {
        JLabel[] labels = {
            new JLabel("Sulit Meals"), new JLabel("Family Meals"), new JLabel("Pork"),
            new JLabel("Chicken"), new JLabel("Beef"), new JLabel("Vegetables"),
            new JLabel("Desserts"), new JLabel("Beverages"), new JLabel("Addons")
        };

        data.setCategoryLabels(labels);

        for (JLabel label : labels) {
            label.setFont(new Font("Garamond", Font.BOLD, 20));
            label.setForeground(Color.WHITE);
            label.setOpaque(true);
            label.setBackground(data.getColor1());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setPreferredSize(new Dimension(200, 45));
            label.setMaximumSize(new Dimension(200, 45));
            label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            data.getLabelCategoryMap().put(label, label.getText());

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (data.isShowingOrderSummary()) {
                        data.setShowingOrderSummary(false);
                        data.getCenter().removeAll();
                        data.getCenter().setBorder(null);
                    }

                    if (data.getSelectedLabel() != null) {
                        data.getSelectedLabel().setBackground(data.getColor1());
                    }

                    data.setSelectedLabel(label);
                    label.setBackground(data.getColor5());

                    JButton[] items = data.getItemMap().get(label.getText());
                    if (items != null) updateCenterItems(items);

                    action.setViewOrderButtonText("View Order");

                    data.getCenter().revalidate();
                    data.getCenter().repaint();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    label.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    if (label != data.getSelectedLabel()) {
                        label.setBackground(data.getHighlightColor());
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (label != data.getSelectedLabel()) {
                        label.setBackground(data.getColor1());
                    }
                }
            });

            data.getWest().add(Box.createVerticalStrut(10));
            data.getWest().add(label);
        }

        // Auto-select first category
        if (labels.length > 0) {
            JLabel firstLabel = labels[0];
            data.setSelectedLabel(firstLabel);
            firstLabel.setBackground(data.getColor5());

            JButton[] items = data.getItemMap().get(firstLabel.getText());
            if (items != null) updateCenterItems(items);
        }
    }

    // Creates a button with image and name/price for an item
    public JButton createItemButton(String name, double price) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.setPreferredSize(new Dimension(600, 160));
        Color color2 = new Color(202, 194, 161);
        button.setBackground(color2);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ImageIcon icon = image.loadIcon(name);
        if (icon != null) {
            Image scaled = image.getHighQualityScaledImage(icon.getImage(), 300, 148);
            JLabel iconLabel = new JLabel(new ImageIcon(scaled));
            iconLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
            iconLabel.setPreferredSize(new Dimension(310, 160));
            iconLabel.setHorizontalAlignment(SwingConstants.LEFT);
            iconLabel.setVerticalAlignment(SwingConstants.TOP);
            button.add(iconLabel, BorderLayout.WEST);
        }

        String priceFormatted = String.format("%.2f", price);
        JLabel textLabel = new JLabel("<html><center>" + name + "<br>₱" + priceFormatted + "</center></html>");
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setFont(new Font("Serif", Font.BOLD, 20));
        button.add(textLabel, BorderLayout.CENTER);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(245, 245, 245));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color2);
            }
        });

        button.setActionCommand(name);
        data.getItemPrices().put(name, price);

        return button;
    }

    // Displays given item buttons in the center panel
    public void updateCenterItems(JButton[] newItems) {
        if (data.getCenter() == null) return;

        data.getCenter().removeAll();
        data.getCenter().setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridBagLayout());
        gridPanel.setBackground(data.getColor2());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.NORTHWEST;

        for (int i = 0; i < newItems.length; i++) {
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            gridPanel.add(newItems[i], gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = newItems.length / 2 + 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gridPanel.add(Box.createVerticalGlue(), gbc);

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setBorder(null);

        data.getCenter().add(scrollPane, BorderLayout.CENTER);
        data.getCenter().revalidate();
        data.getCenter().repaint();
    }
}
