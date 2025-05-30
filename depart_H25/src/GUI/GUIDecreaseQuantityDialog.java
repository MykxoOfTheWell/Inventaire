package Item.GUI;

import Item.Item.Item;
import Item.Exceptions.ExceptionInsufficientQuantityInStock;

import javax.swing.*;

public class GUIDecreaseQuantityDialog {

    private final JFrame parent;
    private final Item item;

    public GUIDecreaseQuantityDialog(JFrame parent, Item item) {
        this.parent = parent;
        this.item = item;
    }

    public void Diminuer() {
        String input = JOptionPane.showInputDialog(
                parent,
                "Quantité actuelle : " + item.getQuantityInStock() + "\nEntrez la quantité à diminuer :",
                "Diminuer la quantité",
                JOptionPane.QUESTION_MESSAGE
        );

        if (input != null) {
            try {
                int quantity = Integer.parseInt(input);
                item.decreaseQuantityInStock(quantity);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(parent, "Entrée invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (ExceptionInsufficientQuantityInStock e) {
                JOptionPane.showMessageDialog(parent, "Quantité insuffisante en stock.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
