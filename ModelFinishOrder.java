

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ModelFinishOrder {
    private final Data data;
    private final ModelViewOrder view;

    public ModelFinishOrder(Data data, ModelViewOrder view) {
        this.data = data;
        this.view = view;
    }

    public void finishOrder() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("********** FOOD ORDER RECEIPT **********\n");
        receipt.append("Date: ").append(new Date()).append("\n\n");

        int totalQty = 0;
        int totalItems = 0;
        double totalPrice = 0.0;

        for (Map.Entry<String, Integer> entry : data.getOrder().entrySet()) {
            double price = data.getItemPrices().get(entry.getKey());
            double itemTotal = price * entry.getValue();
            receipt.append(String.format("%2d x %-20s ₱%7.2f (₱%,.2f)\n",
                entry.getValue(), entry.getKey(), price, itemTotal));
            totalItems++;
            totalQty += entry.getValue();
            totalPrice += itemTotal;
        }

        receipt.append("\n");
        receipt.append(String.format("%-25s ₱%,.2f\n", "Total Amount:", totalPrice));
        receipt.append("***************************************\n");

        data.getAllReceipts().add(receipt.toString());
        data.getAllTotals().add(totalPrice);

        try {
            String filePath = "receipts/receipt_" + data.getDate() + ".txt";
            java.io.FileWriter writer = new java.io.FileWriter(filePath);
            writer.write(receipt.toString());
            writer.close();

            JOptionPane.showMessageDialog(null, "Order finished!\nReceipt saved as " + filePath);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to save receipt.", "File Error", JOptionPane.ERROR_MESSAGE);
        }

        data.getOrder().clear();
        view.updateOrderSummary();

        if (data.isShowingOrderSummary()) {
            view.showOrderSummary();
        }
    }
}
