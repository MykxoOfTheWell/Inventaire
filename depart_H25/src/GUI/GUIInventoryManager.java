package Item.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Objects;

import Item.Inventaire.*;
import Item.Item.Category;
import Item.Item.Item;

public class GUIInventoryManager extends JFrame
{
    private InventoryManager inventoryManager;
    private DefaultListModel<Item> itemsListModel;
    private JList itemsList;
    private int nextID;

    public GUIInventoryManager(InventoryManager inventoryManager) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.inventoryManager = inventoryManager;
        nextID = 100;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        add(createTitlePane(), BorderLayout.NORTH);
        add(createContentPane(), BorderLayout.CENTER);

        setSize(400, 300);
        setVisible(true);
    }

    private JPanel createContentPane() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(border());
        contentPane.setLayout(new BorderLayout());

        contentPane.add(createItemActions(), BorderLayout.NORTH);
        contentPane.add(createItemsList(), BorderLayout.CENTER);
        contentPane.add(createNewButton(), BorderLayout.SOUTH);

        return contentPane;
    }

    private JPanel createTitlePane() {
        JPanel titlePane = new JPanel();
        titlePane.setLayout(new BoxLayout(titlePane, BoxLayout.Y_AXIS));
        titlePane.setBorder(border());

        JLabel title = new JLabel("Gestionnaire d'inventaire", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBorder(titleBorder());
        titlePane.add(title);
        titlePane.add(new JSeparator());

        return titlePane;
    }

    private JScrollPane createItemsList()
    {
        itemsListModel = new DefaultListModel<>();

        for (Item item : inventoryManager.getArrayOfItems()) {
            itemsListModel.addElement(item);
        }

        itemsList = new JList(itemsListModel);
        itemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroller = new JScrollPane(itemsList);

        return listScroller;
    }

    private JPanel createItemActions() {
        JPanel itemButtons = new JPanel();
        itemButtons.setLayout(new BoxLayout(itemButtons, BoxLayout.X_AXIS));

        itemButtons.add(createViewButton());
        itemButtons.add(createIncreaseButton());
        itemButtons.add(createDecreaseButton());
        itemButtons.add(createEditButton());
        itemButtons.add(createDeleteButton());

        return itemButtons;
    }

    private JButton createViewButton() {
        JButton button = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icons/view.png"))));
        button.setBorder(buttonBorder());

        button.addActionListener(event -> {
            Item item = (Item) itemsList.getSelectedValue();
            if (item == null) {
                showSelectErrorDialog();
            } else {
                try {
                    GUIItemViewDialog dialog = new GUIItemViewDialog(this, item);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    showErrorDialog("Erreur lors de l'ouverture de la vue de l'item.");
                }

            }
        });

        return button;
    }

    private JButton createIncreaseButton() {
        JButton button = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icons/increase.png"))));
        button.setBorder(buttonBorder());

        button.addActionListener(event -> {
            Item item = (Item) itemsList.getSelectedValue();
            if (item == null) {
                showSelectErrorDialog();
            } else {
                GUIIncreaseQuantityDialog dialog = new GUIIncreaseQuantityDialog(this, item);
                dialog.showAndProcess();
                itemsListModel.setElementAt(item, itemsList.getSelectedIndex()); // rafraîchit la liste visuellement
            }
        });

        return button;
    }


    private JButton createDecreaseButton() {
        JButton button = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icons/decrease.png"))));
        button.setBorder(buttonBorder());

        button.addActionListener(event -> {
            Item item = (Item) itemsList.getSelectedValue();
            if (item == null) {
                showSelectErrorDialog();
            } else {
                GUIDecreaseQuantityDialog dialog = new GUIDecreaseQuantityDialog(this, item);
                dialog.Diminuer();
                itemsListModel.setElementAt(item, itemsList.getSelectedIndex()); // Mise à jour visuelle
            }
        });

        return button;
    }


    private JButton createEditButton() {
        JButton button = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icons/edit.png"))));
        button.setBorder(buttonBorder());

        button.addActionListener(event -> {
            Item selectedItem = (Item) itemsList.getSelectedValue();
            if (selectedItem == null) {
                showSelectErrorDialog();
                return;
            }

            GUIEditItemDialog editDialog = new GUIEditItemDialog(this, selectedItem);
            editDialog.setVisible(true);
            itemsList.repaint();
        });

        return button;
    }

    private JButton createDeleteButton() {
        JButton button = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icons/delete.png"))));
        button.setBorder(buttonBorder());

        button.addActionListener(event -> {
            Item item = (Item) itemsList.getSelectedValue();
            if (item == null) {
                showSelectErrorDialog();
            } else {
                GUIDeleteItemDialog dialog = new GUIDeleteItemDialog(this, inventoryManager, item);
                boolean deleted = dialog.showAndDelete();
                if (deleted) {
                    itemsListModel.removeElement(item);
                }
            }
        });

        return button;
    }


    private JPanel createNewButton() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton newItemButton = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icons/new.png"))));
        newItemButton.addActionListener(event -> {
            GUIItemChoiceDialog guiItemChoiceDialog = new GUIItemChoiceDialog(this);

            guiItemChoiceDialog.addComponentListener(new ComponentListener() {
                @Override
                public void componentResized(ComponentEvent e) { }

                @Override
                public void componentMoved(ComponentEvent e) { }

                @Override
                public void componentShown(ComponentEvent e) { }

                @Override
                public void componentHidden(ComponentEvent e) {
                    Category category = guiItemChoiceDialog.getChosenCategory();
                }
            });

            guiItemChoiceDialog.setVisible(true);
        });

        newItemButton.setBorder(buttonNewBorder());
        buttonPanel.add(newItemButton);
        refreshMainPanel();
        return buttonPanel;
    }

    private void showSelectErrorDialog() {

        showErrorDialog("SVP choisir un item");
    }

    private void showErrorDialog(String message) {
        GUIErrorDialog dialog = new GUIErrorDialog(this, message);
        dialog.setVisible(true);
    }
    private void refreshMainPanel() {
        itemsList.removeAll();
        itemsList.revalidate();
        itemsList.repaint();
    }

    private Border border() {

        return BorderFactory.createEmptyBorder(5, 10, 10, 10);
    }

    private Border titleBorder() {

        return BorderFactory.createEmptyBorder(5, 0, 10, 10);
    }

    private Border buttonNewBorder() {
        return BorderFactory.createEmptyBorder(5, 0, 0, 0);
    }

    private Border buttonBorder() {
        return BorderFactory.createEmptyBorder(0, 0, 0 , 0);
    }




}
