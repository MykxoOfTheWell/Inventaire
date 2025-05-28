package Item.GUI;

import Item.Inventaire.InventoryManager;
import Item.Exceptions.ExceptionItemNotFound;
import Item.Item.Item;

import javax.swing.*;

public class GUIDeleteItemDialog {

    private final JFrame parent;
    private final InventoryManager inventoryManager;
    private final Item item;

    public GUIDeleteItemDialog(JFrame parent, InventoryManager inventoryManager, Item item) {
        this.parent = parent;
        this.inventoryManager = inventoryManager;
        this.item = item;
    }

    public boolean showAndDelete() {
        int response = JOptionPane.showConfirmDialog(
                parent,
                "Voulez-vous vraiment supprimer l’item suivant ?\n\n" + item,
                "Confirmation de suppression",
                JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.YES_OPTION) {
            try {
                inventoryManager.removeItem(item.getID());
                return true;
            } catch (ExceptionItemNotFound e) {
                JOptionPane.showMessageDialog(
                        parent,
                        "Erreur : l’item n’existe pas dans l’inventaire.",
                        "Suppression impossible",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        return false;
    }
}
