

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Data {

    private JPanel North, West, South, Center;                        // Main layout panels
    private JLabel selectedLabel = null;                              // Currently selected category label
    private JLabel[] categoryLabels;                                  // Array of category labels
    private Map<String, JButton[]> itemMap = new HashMap<>();         // Stores item buttons per category
    private Map<JLabel, String> labelCategoryMap = new HashMap<>();   // Maps label to category name
    private JTextArea orderSummaryArea;                               // Text area for displaying order summary
    private JLabel totalItemsLabel;                                   // Label for total distinct items
    private JLabel totalQtyLabel;                                     // Label for total quantity
    private JLabel totalPriceLabel;                                   // Label for total price
    private String date = java.time.LocalDateTime.now()
        .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")); // Timestamp for receipt
    private JButton selectedItemButton = null;                        // Currently selected item button
    private String selectedItemName = null;                           // Currently selected item name
    private Map<String, Integer> order = new HashMap<>();             // Order contents: item → quantity
    private Map<String, Double> itemPrices = new HashMap<>();         // Item prices: item → price
    private double totalPrice = 0.0;                                  // Total price of the current order
    private boolean showingOrderSummary = false;                      // Flag for toggling summary view
    private java.util.List<String> allReceipts = new ArrayList<>();   // Stores all order receipts
    private java.util.List<Double> allTotals = new ArrayList<>();     // Stores all order totals
    private JTable orderTable;                                        // Table for showing order in rows
    private DefaultTableModel tableModel;                             // Table model for dynamic rows
    private JLabel orderTotalLabel;                                   // Label to display final order total
    private Map<String, String> itemDescriptions = new HashMap<>();   // Item descriptions: item → text

    // Color scheme used throughout UI
    private Color color1 = Color.decode("#706D54");                   // Base background color
    private Color color2 = Color.decode("#F8F3D9");                   // Light beige
    private Color color3 = Color.decode("#B9B28A");                   // Tan
    private Color color4 = Color.decode("#504B38");                   // Dark shade
    private Color color5 = Color.decode("#C9B194");                   // Accent beige
    private Color highlightColor = Color.decode("#A08963");          // Highlight color

    // Getters and setters for layout panels
    public JPanel getNorth() { return North; }
    public void setNorth(JPanel north) { North = north; }

    public JPanel getWest() { return West; }
    public void setWest(JPanel west) { West = west; }

    public JPanel getSouth() { return South; }
    public void setSouth(JPanel south) { South = south; }

    public JPanel getCenter() { return Center; }
    public void setCenter(JPanel center) { Center = center; }

    // Getters and setters for selected UI elements
    public JLabel getSelectedLabel() { return selectedLabel; }
    public void setSelectedLabel(JLabel selectedLabel) { this.selectedLabel = selectedLabel; }

    public JLabel[] getCategoryLabels() { return categoryLabels; }
    public void setCategoryLabels(JLabel[] categoryLabels) { this.categoryLabels = categoryLabels; }

    public Map<String, JButton[]> getItemMap() { return itemMap; }
    public Map<JLabel, String> getLabelCategoryMap() { return labelCategoryMap; }

    public JTextArea getOrderSummaryArea() { return orderSummaryArea; }

    public JLabel getTotalItemsLabel() { return totalItemsLabel; }
    public void setTotalItemsLabel(JLabel totalItemsLabel) { this.totalItemsLabel = totalItemsLabel; }

    public JLabel getTotalQtyLabel() { return totalQtyLabel; }
    public void setTotalQtyLabel(JLabel totalQtyLabel) { this.totalQtyLabel = totalQtyLabel; }

    public JLabel getTotalPriceLabel() { return totalPriceLabel; }
    public void setTotalPriceLabel(JLabel totalPriceLabel) { this.totalPriceLabel = totalPriceLabel; }

    public String getDate() { return date; }

    public JButton getSelectedItemButton() { return selectedItemButton; }

    public String getSelectedItemName() { return selectedItemName; }

    public Map<String, Integer> getOrder() { return order; }

    public Map<String, Double> getItemPrices() { return itemPrices; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public boolean isShowingOrderSummary() { return showingOrderSummary; }
    public void setShowingOrderSummary(boolean showingOrderSummary) {
        this.showingOrderSummary = showingOrderSummary;
    }

    public java.util.List<String> getAllReceipts() { return allReceipts; }

    public java.util.List<Double> getAllTotals() { return allTotals; }

    public JTable getOrderTable() { return orderTable; }
    public void setOrderTable(JTable orderTable) { this.orderTable = orderTable; }

    public DefaultTableModel getTableModel() { return tableModel; }
    public void setTableModel(DefaultTableModel tableModel) { this.tableModel = tableModel; }

    public JLabel getOrderTotalLabel() { return orderTotalLabel; }
    public void setOrderTotalLabel(JLabel orderTotalLabel) { this.orderTotalLabel = orderTotalLabel; }

    public Map<String, String> getItemDescriptions() { return itemDescriptions; }

    // Getters for UI color palette
    public Color getColor1() { return color1; }
    public Color getColor2() { return color2; }
    public Color getColor3() { return color3; }
    public Color getColor4() { return color4; }
    public Color getColor5() { return color5; }
    public Color getHighlightColor() { return highlightColor; }
}
