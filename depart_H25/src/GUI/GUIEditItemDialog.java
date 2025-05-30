package Item.GUI;

import Item.Item.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIEditItemDialog extends JDialog {

    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;

    // Champs spécifiques
    private JTextField fatField;     // Milk
    private JTextField litersField;  // Milk

    private JTextField colorField;   // Bread & Eggs
    private JTextField weightField;  // Bread

    private JTextField countField;   // Eggs

    private Item item;

    public GUIEditItemDialog(JFrame parent, Item itemToEdit) {
        super(parent, "Éditer un item", true);
        this.item = itemToEdit;

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(350, 350);
        setLocationRelativeTo(parent);

        add(createFormPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Champs communs
        panel.add(new JLabel("Nom:"));
        nameField = new JTextField(item.getName());
        panel.add(nameField);

        panel.add(new JLabel("Prix:"));
        priceField = new JTextField(String.valueOf(item.getPrice()));
        panel.add(priceField);

        panel.add(new JLabel("Quantité:"));
        quantityField = new JTextField(String.valueOf(item.getQuantityInStock()));
        panel.add(quantityField);

        // Champs spécifiques selon la classe
        if (item instanceof ItemMilk milk) {
            panel.add(new JLabel("Gras (%):"));
            fatField = new JTextField(String.valueOf(milk.getFat()));
            panel.add(fatField);

            panel.add(new JLabel("Litres:"));
            litersField = new JTextField(String.valueOf(milk.getLiters()));
            panel.add(litersField);
        } else if (item instanceof ItemBread bread) {
            panel.add(new JLabel("Couleur:"));
            colorField = new JTextField(bread.getColor());
            panel.add(colorField);

            panel.add(new JLabel("Poids (g):"));
            weightField = new JTextField(String.valueOf(bread.getWeight()));
            panel.add(weightField);
        } else if (item instanceof ItemEggs eggs) {
            panel.add(new JLabel("Couleur:"));
            colorField = new JTextField(eggs.getColor());
            panel.add(colorField);

            panel.add(new JLabel("Nombre d'œufs:"));
            countField = new JTextField(String.valueOf(eggs.getNumber()));
            panel.add(countField);
        }

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton saveButton = new JButton("Enregistrer");
        JButton cancelButton = new JButton("Annuler");

        saveButton.addActionListener(this::saveChanges);
        cancelButton.addActionListener(e -> dispose());

        panel.add(cancelButton);
        panel.add(saveButton);
        return panel;
    }

    private void saveChanges(ActionEvent e) {
        try {
            item.setName(nameField.getText());
            item.setPrice(Double.parseDouble(priceField.getText()));
            item.setQuantityInStock(Integer.parseInt(quantityField.getText()));

            if (item instanceof ItemMilk milk) {
                milk.setFat(Double.parseDouble(fatField.getText()));
                milk.setLiters(Double.parseDouble(litersField.getText()));
            } else if (item instanceof ItemBread bread) {
                bread.setColor(colorField.getText());
                bread.setWeight(Integer.parseInt(weightField.getText()));
            } else if (item instanceof ItemEggs eggs) {
                eggs.setNumber(Integer.parseInt(colorField.getText()));
                eggs.setNumber(Integer.parseInt(countField.getText()));
            }

            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Veuillez entrer des valeurs valides (nombres, texte, etc.)",
                    "Erreur de saisie",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
