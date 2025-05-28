package Item.GUI;

import Item.Item.Item;

import javax.swing.*;

public class GUIIncreaseQuantityDialog {

    private final JFrame parent;
    private final Item item;

    public GUIIncreaseQuantityDialog(JFrame parent, Item item) {
        this.parent = parent;
        this.item = item;
    }

    public void showAndProcess() {
        try {
            String input = JOptionPane.showInputDialog(
                    parent,
                    "Quantité actuelle : " + item.getQuantityInStock() + "\nCombien voulez-vous en ajouter ?",
                    "Augmenter la quantité",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (input != null) {
                int valeurAjout = Integer.parseInt(input.trim());

                if (valeurAjout <= 0) {
                    showError("Veuillez entrer un nombre positif.");
                    return;
                }

                item.increaseQuantityInStock(valeurAjout);
            }

        } catch (NumberFormatException e) {
            showError("Veuillez entrer un nombre valide.");
        } catch (Exception e) {
            showError("Erreur : " + e.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(parent, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
