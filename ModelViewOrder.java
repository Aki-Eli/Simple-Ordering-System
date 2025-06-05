
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class ModelViewOrder {
    private final Data data;

    public ModelViewOrder(Data sharedData) {
        this.data = sharedData;
    }

    // Updates labels for total items, quantity, and total price
    public void updateOrderSummary() {
        int totalItems = 0;
        int totalQty = 0;
        data.setTotalPrice(0.00);

        for (Map.Entry<String, Integer> entry : data.getOrder().entrySet()) {
            Double itemPriceObj = data.getItemPrices().get(entry.getKey());
            if (itemPriceObj == null) {
                System.err.println("Missing price for item: " + entry.getKey());
                continue;
            }

            double itemPrice = itemPriceObj;
            totalItems++;
            totalQty += entry.getValue();
            data.setTotalPrice(data.getTotalPrice() + itemPrice * entry.getValue());
        }

        data.getTotalItemsLabel().setText("Items: " + totalItems);
        data.getTotalQtyLabel().setText("Quantity: " + totalQty);
        data.getTotalPriceLabel().setText(String.format("Total: ₱%,.2f", data.getTotalPrice()));
    }

    // Displays the order summary panel
    public void showOrderSummary() {
        data.setShowingOrderSummary(true);
        data.getCenter().removeAll();
        data.getCenter().setLayout(new BorderLayout());
        data.getCenter().setBackground(data.getColor1());

        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setBackground(data.getColor1());

        // Setup table column names
        String[] columnNames = {"Item Name", "Quantity", "Price", "Total"};
        data.setTableModel(new DefaultTableModel(columnNames, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
        return false; // All cells are non-editable
            }
        });


        // Populate table rows from the order map
        for (Map.Entry<String, Integer> entry : data.getOrder().entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            double price = data.getItemPrices().get(itemName);
            double itemTotal = price * quantity;
            Object[] rowData = {
                itemName,
                quantity,
                String.format("₱%,.2f", price),
                String.format("₱%,.2f", itemTotal)
            };
            data.getTableModel().addRow(rowData);
        }

        // Setup the JTable
        data.setOrderTable(new JTable(data.getTableModel()));
        data.getOrderTable().setFont(new Font("SansSerif", Font.PLAIN, 16));
        data.getOrderTable().setRowHeight(30);
        data.getOrderTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        data.getOrderTable().setBackground(data.getColor2());
        data.getOrderTable().setForeground(Color.BLACK);
        

        JScrollPane tableScrollPane = new JScrollPane(data.getOrderTable());
        tableScrollPane.setBorder(BorderFactory.createLineBorder(data.getColor4(), 2));
        orderPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Create buttons for editing and removing items
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.decode("#EEEEEE"));

        JButton editQuantityButton = new JButton("Edit Quantity");
        JButton removeItemButton = new JButton("Remove Item");

        Font bottomButtonFont = new Font("SansSerif", Font.BOLD, 14);
        for (JButton b : new JButton[]{editQuantityButton, removeItemButton}) {
            b.setFont(bottomButtonFont);
            b.setBackground(data.getColor3());
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

            // Shared listener for both buttons
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = data.getOrderTable().getSelectedRow();
                    if (selectedRow < 0) {
                        JOptionPane.showMessageDialog(null, "Please select an item first.");
                        return;
                    }

                    String itemName = (String) data.getTableModel().getValueAt(selectedRow, 0);

                    if (e.getSource() == editQuantityButton) {
                        editOrder(itemName);
                    } else if (e.getSource() == removeItemButton) {
                        removeOrderItem(itemName);
                    }
                }
            });

            buttonPanel.add(b);
        }

        orderPanel.add(buttonPanel, BorderLayout.SOUTH);
        data.getCenter().add(orderPanel, BorderLayout.CENTER);
        data.getCenter().revalidate();
        data.getCenter().repaint();
    }

    // Removes an item from the order after confirmation
    private void removeOrderItem(String itemName) {
            data.getOrder().remove(itemName);
            updateOrderSummary();

            for (int i = 0; i < data.getTableModel().getRowCount(); i++) {
                if (data.getTableModel().getValueAt(i, 0).equals(itemName)) {
                    data.getTableModel().removeRow(i);
                    break;
                }
            }
        }

    // Allows the user to update the quantity of an existing item
    private void editOrder(String itemName) {
        if (!data.getOrder().containsKey(itemName)) return;

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Item Name:", SwingConstants.LEFT));
        panel.add(new JLabel(itemName));
        panel.add(new JLabel("New Quantity:", SwingConstants.LEFT));

        JTextField quantityField = new JTextField(String.valueOf(data.getOrder().get(itemName)), 5);
        panel.add(quantityField);

        int result = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Edit Quantity",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                int newQuantity = Integer.parseInt(quantityField.getText());

                if (newQuantity > 0) {
                    data.getOrder().put(itemName, newQuantity);
                    updateOrderSummary();

                    for (int i = 0; i < data.getTableModel().getRowCount(); i++) {
                        if (data.getTableModel().getValueAt(i, 0).equals(itemName)) {
                            data.getTableModel().setValueAt(newQuantity, i, 1);
                            double price = data.getItemPrices().get(itemName);
                            data.getTableModel().setValueAt(String.format("₱%.2f", price * newQuantity), i, 3);
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Quantity must be greater than 0.");
                    int removeOption = JOptionPane.showConfirmDialog(
                        null,
                        "Do you want to remove " + itemName + " from the order?",
                        "Remove Item",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (removeOption == JOptionPane.YES_OPTION) {
                        data.getOrder().remove(itemName);
                        for (int i = 0; i < data.getTableModel().getRowCount(); i++) {
                            if (data.getTableModel().getValueAt(i, 0).equals(itemName)) {
                                data.getTableModel().removeRow(i);
                                break;
                            }
                        }
                        updateOrderSummary();
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid quantity.");
            }
        }
    }
}
