package Item.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Item.Item.Item;

public class GUIItemDialog extends JDialog {
    private Item item;
    private JFrame frame;

    public GUIItemDialog(JFrame frame, Item item, boolean editable) {
        super(frame, item.getCategoryString(), Dialog.ModalityType.DOCUMENT_MODAL);
        this.frame = frame;
        this.item = item;
        createAndShowGUI(editable);
    }

    private void createAndShowGUI(boolean editable) {
        setLayout(new BorderLayout());

        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new GridLayout(6, 2));

        contentPane.add(new JLabel("ID"));
        JTextField fieldID = new JTextField("" + item.getID());
        fieldID.setEditable(false);
        fieldID.setBackground(contentPane.getBackground());
        contentPane.add(fieldID);

        contentPane.add(new JLabel("Nom"));
        JTextField fieldName = new JTextField(item.getName());
        fieldName.setEditable(editable);
        if (!editable) {
            fieldName.setBackground(contentPane.getBackground());
        }
        contentPane.add(fieldName);

        contentPane.add(new JLabel("Prix"));
        JTextField fieldPrice = new JTextField("" + item.getPrice());
        fieldPrice.setEditable(editable);
        if (!editable) {
            fieldPrice.setBackground(contentPane.getBackground());
        }
        contentPane.add(fieldPrice);

        contentPane.add(new JLabel("Quantité en stock"));
        JTextField fieldStock = new JTextField("" + item.getQuantityInStock());
        fieldStock.setEditable(editable);
        if (!editable) {
            fieldStock.setBackground(contentPane.getBackground());
        }
        contentPane.add(fieldStock);

        JTextField fieldOther1 = new JTextField();
        fieldOther1.setEditable(editable);
        if (!editable) {
            fieldOther1.setBackground(contentPane.getBackground());
        }
        if (item instanceof ItemBread) {
            contentPane.add(new JLabel("Couleur"));
            fieldOther1.setText(((ItemBread)item).getColor());
        }
        else if (item instanceof ItemEggs) {
            contentPane.add(new JLabel("Couleur"));
            fieldOther1.setText(((ItemEggs)item).getColor());
        }
        else if (item instanceof ItemMilk) {
            contentPane.add(new JLabel("Gras"));
            fieldOther1.setText("" + ((ItemMilk)item).getFat());
        }
        contentPane.add(fieldOther1);

        JTextField fieldOther2 = new JTextField();
        fieldOther2.setEditable(editable);
        if (!editable) {
            fieldOther2.setBackground(contentPane.getBackground());
        }
        if (item instanceof ItemBread) {
            contentPane.add(new JLabel("Poids"));
            fieldOther2.setText("" + ((ItemBread)item).getWeight());
        }
        else if (item instanceof ItemEggs) {
            contentPane.add(new JLabel("Quantité"));
            fieldOther2.setText("" + ((ItemEggs)item).getNumber());
        }
        else if (item instanceof ItemMilk) {
            contentPane.add(new JLabel("Litres"));
            fieldOther2.setText("" + ((ItemMilk)item).getLiters());
        }
        contentPane.add(fieldOther2);

        JPanel buttons = new JPanel(new FlowLayout());

        JButton cancelButton = new JButton("Annuler");
        if (!editable) {
            cancelButton.setText("OK");
        }
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        buttons.add(cancelButton);

        if (editable) {
            JButton okButton = new JButton("Sauver");
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        item.setName(fieldName.getText());
                        item.setPrice(Double.parseDouble(fieldPrice.getText()));
                        item.setQuantityInStock(Integer.parseInt(fieldStock.getText()));
                        if (item instanceof ItemBread) {
                            ((ItemBread) item).setColor(fieldOther1.getText());
                            ((ItemBread) item).setWeight(Integer.parseInt(fieldOther2.getText()));
                        }
                        else if (item instanceof ItemEggs) {
                            ((ItemEggs) item).setColor(fieldOther1.getText());
                            ((ItemEggs) item).setNumber(Integer.parseInt(fieldOther2.getText()));
                        }
                        else if (item instanceof ItemMilk) {
                            ((ItemMilk) item).setFat(Double.parseDouble(fieldOther1.getText()));
                            ((ItemMilk) item).setLiters(Double.parseDouble(fieldOther2.getText()));
                        }
                        setVisible(false);
                    } catch (NumberFormatException exception) {
                        GUIErrorDialog guiErrorDialog = new GUIErrorDialog(frame, "Mauvais format de données: " + exception.getMessage());
                        guiErrorDialog.setVisible(true);
                    }
                }
            });
            buttons.add(okButton);
        }

        add(contentPane, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        pack();
    }
}
