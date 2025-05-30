package Item.GUI;

import javax.swing.*;
import java.awt.*;
import Item.Item.*;

public class GUIItemChoiceDialog extends JDialog {
    private JFrame frame;
    private Category chosenCategory;
    private Item createdItem;

    public GUIItemChoiceDialog(JFrame frame) {
        super(frame, "Choisir un type", Dialog.ModalityType.DOCUMENT_MODAL);
        this.frame = frame;
        chosenCategory = Category.Unknown;
        createAndShowGUI();
    }

    public Category getChosenCategory() {
        return chosenCategory;
    }

    public Item getCreatedItem() {
        return createdItem;
    }

    private void createAndShowGUI() {
        setLayout(new BorderLayout());

        JButton cancelButton = new JButton("Annuler");
        cancelButton.addActionListener(e -> {
            chosenCategory = Category.Unknown;
            setVisible(false);
        });
        add(cancelButton, BorderLayout.SOUTH);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new GridLayout(3, 1));
        add(contentPane, BorderLayout.CENTER);

        JButton breadButton = new JButton("Pain");
        breadButton.addActionListener(e -> {
            chosenCategory = Category.Bread;
            GUIItemBreadInputDialog dialog = new GUIItemBreadInputDialog(frame);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                createdItem = dialog.getItem();
            }
            setVisible(false);
        });
        contentPane.add(breadButton);

        JButton eggsButton = new JButton("Oeufs");
        eggsButton.addActionListener(e -> {
            chosenCategory = Category.Eggs;
            GUIItemEggsInputDialog dialog = new GUIItemEggsInputDialog(frame);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                createdItem = dialog.getItem();
            }
            setVisible(false);
        });
        contentPane.add(eggsButton);

        JButton milkButton = new JButton("Lait");
        milkButton.addActionListener(e -> {
            chosenCategory = Category.Milk;
            GUIItemMilkInputDialog dialog = new GUIItemMilkInputDialog(frame);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                createdItem = dialog.getItem();
            }
            setVisible(false);
        });
        contentPane.add(milkButton);

        pack();
        setLocationRelativeTo(frame);
    }

    private abstract class GUIItemInputDialog<T extends Item> extends JDialog {
        protected boolean confirmed = false;
        public GUIItemInputDialog(JFrame owner) {
            super(owner, true);
        }
        public boolean isConfirmed() { return confirmed; }
        public abstract T getItem();
    }

    private class GUIItemBreadInputDialog extends GUIItemInputDialog<ItemBread> {
        private JTextField idField = new JTextField();
        private JTextField nameField = new JTextField();
        private JTextField priceField = new JTextField();
        private JTextField colorField = new JTextField();
        private JTextField weightField = new JTextField();

        public GUIItemBreadInputDialog(JFrame owner) {
            super(owner);
            setSize(300, 200);
            setTitle("Nouvel Item - Pain");
            setLayout(new GridLayout(6, 2));
            add(new JLabel("ID:")); add(idField);
            add(new JLabel("Nom:")); add(nameField);
            add(new JLabel("Prix:")); add(priceField);
            add(new JLabel("Couleur:")); add(colorField);
            add(new JLabel("Poids:")); add(weightField);

            JButton okButton = new JButton("OK");
            okButton.addActionListener(e -> {
                confirmed = true;
                setVisible(false);
            });
            add(okButton);
        }

        @Override
        public ItemBread getItem() {
            return new ItemBread(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    colorField.getText(),
                    Double.parseDouble(weightField.getText())
            );
        }
    }

    private class GUIItemEggsInputDialog extends GUIItemInputDialog<ItemEggs> {
        private JTextField idField = new JTextField();
        private JTextField nameField = new JTextField();
        private JTextField priceField = new JTextField();
        private JTextField colorField = new JTextField();
        private JTextField numberField = new JTextField();

        public GUIItemEggsInputDialog(JFrame owner) {
            super(owner);
            setSize(300, 200);
            setTitle("Nouvel Item - Oeufs");
            setLayout(new GridLayout(6, 2));
            add(new JLabel("ID:")); add(idField);
            add(new JLabel("Nom:")); add(nameField);
            add(new JLabel("Prix:")); add(priceField);
            add(new JLabel("Couleur:")); add(colorField);
            add(new JLabel("Nombre:")); add(numberField);

            JButton okButton = new JButton("OK");
            okButton.addActionListener(e -> {
                confirmed = true;
                setVisible(false);
            });
            add(okButton);

        }

        @Override
        public ItemEggs getItem() {
            return new ItemEggs(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    colorField.getText(),
                    Integer.parseInt(numberField.getText())
            );
        }
    }

    private class GUIItemMilkInputDialog extends GUIItemInputDialog<ItemMilk> {
        private JTextField idField = new JTextField();
        private JTextField nameField = new JTextField();
        private JTextField priceField = new JTextField();
        private JTextField fatField = new JTextField();
        private JTextField litersField = new JTextField();

        public GUIItemMilkInputDialog(JFrame owner) {
            super(owner);
            setSize(300, 200);
            setTitle("Nouvel Item - Lait");
            setLayout(new GridLayout(6, 2));
            add(new JLabel("ID:")); add(idField);
            add(new JLabel("Nom:")); add(nameField);
            add(new JLabel("Prix:")); add(priceField);
            add(new JLabel("Gras:")); add(fatField);
            add(new JLabel("Litres:")); add(litersField);

            JButton okButton = new JButton("OK");
            okButton.addActionListener(e -> {
                confirmed = true;
                setVisible(false);
            });
            add(okButton);
        }

        @Override
        public ItemMilk getItem() {
            return new ItemMilk(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Double.parseDouble(fatField.getText()),
                    Double.parseDouble(litersField.getText())
            );
        }
    }
}
