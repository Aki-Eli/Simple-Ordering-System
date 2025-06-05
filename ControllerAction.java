

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControllerAction {

    // Handles the "View Order" / "Back" button logic
    public static class View implements ActionListener {
        private JButton viewOrderButton;
        private Data data;
        private ModelViewOrder view;
        private ControllerAction.View action;
        private ModelImage image;

        public View(JButton viewOrderButton, Data data, ModelViewOrder view) {
            this.viewOrderButton = viewOrderButton;
            this.data = data;
            this.view = view;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (data.isShowingOrderSummary()) {
                // Return to menu view
                data.setShowingOrderSummary(false);
                viewOrderButton.setText("View Order");
                data.getCenter().removeAll();

                // Rebuild menu items in center panel
                ModelItem itemModel = new ModelItem(data, action, image);
                itemModel.updateCenterItems(data.getItemMap().get(
                    data.getLabelCategoryMap().get(data.getSelectedLabel()))
                );
            } else {
                // Show order summary view
                data.setShowingOrderSummary(true);
                data.getSelectedLabel().setBackground(data.getColor1());
                viewOrderButton.setText("Back");
                view.showOrderSummary();
            }

            // Refresh center panel UI
            data.getCenter().revalidate();
            data.getCenter().repaint();
        }

        // Optional external control to update button label
        public void setViewOrderButtonText(String text) {
            viewOrderButton.setText(text);
        }
    }

    // Handles the "Admin" button login and panel access
    public static class Admin implements ActionListener {
        private final Data data;

        public Admin(Data data) {
            this.data = data;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Create login UI
            JPanel loginPanel = new JPanel();
            loginPanel.setBackground(Color.decode("#B9B28A"));
            loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

            JTextField userField = new JTextField(10);
            JPasswordField passField = new JPasswordField(10);

            // Add components to panel
            loginPanel.add(new JLabel("Username:"));
            loginPanel.add(userField);
            loginPanel.add(Box.createVerticalStrut(10));
            loginPanel.add(new JLabel("Password:"));
            loginPanel.add(passField);

            // Show login dialog
            int result = JOptionPane.showConfirmDialog(
                null, loginPanel, "Admin Login",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
            );

            // Check credentials
            if (result == JOptionPane.OK_OPTION) {
                String username = userField.getText().trim();
                String password = new String(passField.getPassword()).trim();

                if (username.equals("admin") && password.equals("1234")) {
                    ViewAdmin adminPanel = new ViewAdmin(data);
                    adminPanel.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(
                        null, "Invalid username or password.", 
                        "Login Failed", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }

    // Handles the "Finish Order" button logic
    static class Finish implements ActionListener {
        private final Data data;
        private final ModelFinishOrder finish;

        public Finish(Data data, ModelFinishOrder finish) {
            this.data = data;
            this.finish = finish;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Prevent finishing an empty order
            if (data.getOrder().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null, "No items in your order yet.",
                    "Empty Order", JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }

            // Proceed to order completion
            finish.finishOrder();
        }
    }
}
