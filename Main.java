

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        // Run the app in the Swing event thread
        SwingUtilities.invokeLater(() -> {
            OrderingSystemApp app = new OrderingSystemApp();
            app.start(); // Start the app
        });
    }
}

// Main application class
class OrderingSystemApp {
    private JFrame frame;
    private Data data;
    private ViewMain viewMain;
    private ModelViewOrder viewOrderModel;
    private ModelFinishOrder finishOrderModel;
    private ModelItem itemModel;
    private ModelImage imageModel;
    private ControllerQuantity quantityController;
    private ControllerAction.View action;

    public void start() {
        data = new Data(); // Initialize shared data

        viewMain = new ViewMain(data); // Create main view

        // Set colors for popup dialogs
        UIManager.put("OptionPane.background", data.getColor3());
        UIManager.put("Panel.background", data.getColor3());
        UIManager.put("OptionPane.messageForeground", Color.DARK_GRAY);
        UIManager.put("OptionPane.buttonBackground", data.getColor3());
        UIManager.put("OptionPane.buttonForeground", Color.WHITE);

        // Create main window
        frame = new JFrame("KusinaTech");
        ImageIcon icon = new ImageIcon("images/logo.png"); // or "icon.png"
        frame.setIconImage(icon.getImage());
        frame.setContentPane(viewMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize window
        frame.setLocationRelativeTo(null); // Center on screen

        viewOrderModel = new ModelViewOrder(data); // Create view order model

        // Setup view order button and its action
        JButton viewOrderButton = viewMain.getViewOrderButton();
        action = new ControllerAction.View(viewOrderButton, data, viewOrderModel);

        // Create image model before passing to item model
        imageModel = new ModelImage();

        // Create item model
        itemModel = new ModelItem(data, action, imageModel);
        itemModel.createCategoryLabels();
        itemModel.createItemMap();
        itemModel.loadItemDescriptions();

        // Set first category label as selected and highlight it
        data.setSelectedLabel(data.getCategoryLabels()[0]);
        data.getSelectedLabel().setBackground(data.getColor5());

        // Show items from the selected category
        itemModel.updateCenterItems(
            data.getItemMap().get(data.getLabelCategoryMap().get(data.getSelectedLabel()))
        );

        // Create finish order model
        finishOrderModel = new ModelFinishOrder(data, viewOrderModel);

        // Create controller for selecting item quantity
        quantityController = new ControllerQuantity(data, imageModel, viewOrderModel);

        // Set up all button actions
        setupActionListeners();

        frame.setVisible(true); // Show the window
    }

    // Add or replace action listeners for buttons
    private void setupActionListeners() {
        JButton viewOrderButton = viewMain.getViewOrderButton();
        if (viewOrderButton != null) {
            for (ActionListener al : viewOrderButton.getActionListeners()) {
                viewOrderButton.removeActionListener(al); // Remove old actions
            }
            viewOrderButton.addActionListener(action); // Add new action
        }

        JButton adminButton = viewMain.getAdminButton();
        if (adminButton != null) {
            for (ActionListener al : adminButton.getActionListeners()) {
                adminButton.removeActionListener(al);
            }
            adminButton.addActionListener(new ControllerAction.Admin(data));
        }

        JButton finishButton = viewMain.getFinishButton();
        if (finishButton != null) {
            for (ActionListener al : finishButton.getActionListeners()) {
                finishButton.removeActionListener(al);
            }
            finishButton.addActionListener(new ControllerAction.Finish(data, finishOrderModel));
        }

        // Add click listeners to all item buttons
        for (JButton[] buttons : data.getItemMap().values()) {
            for (JButton button : buttons) {
                for (ActionListener al : button.getActionListeners()) {
                    button.removeActionListener(al); // Remove any previous listeners
                }

                // Get item name and price for this button
                String itemName = button.getActionCommand(); 
                Double itemPriceObj = data.getItemPrices().get(itemName);
                double itemPrice = (itemPriceObj != null) ? itemPriceObj : 0.0;

                // Show quantity dialog when item is clicked
                button.addActionListener(e -> {
                    quantityController.showQuantityDialog(itemName, itemPrice);
                });
            }
        }
    }
}
