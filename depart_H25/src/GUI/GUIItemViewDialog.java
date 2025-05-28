package Item.GUI;

import Item.Item.Item;

import javax.swing.*;
import java.awt.*;

public class GUIItemViewDialog extends JDialog {
    public GUIItemViewDialog(JFrame parent, Item item) {
        super(parent, "Détails de l'item", true);
        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        infoPanel.add(new JLabel("ID : " + item.getID()));
        infoPanel.add(new JLabel("Nom : " + item.getName()));
        infoPanel.add(new JLabel("Prix : " + item.getPrice() + "$"));
        infoPanel.add(new JLabel("Quantité en stock : " + item.getQuantityInStock()));
        infoPanel.add(new JLabel("Catégorie : " + item.getCategory()));
        add(infoPanel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(350, 300);
        setLocationRelativeTo(parent);
    }
}
