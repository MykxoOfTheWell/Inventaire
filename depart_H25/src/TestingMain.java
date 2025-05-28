package Item;

import Item.Exceptions.ExceptionInsufficientQuantityInStock;
import Item.Exceptions.ExceptionItemAlreadyExists;
import Item.Exceptions.ExceptionItemNotFound;
import Item.GUI.GUIInventoryManager;
import Item.Inventaire.InventoryManager;
import Item.Item.Item;
import Item.Item.ItemMilk;

import java.io.*;

public class TestingMain  {

    public static void main(String[] args) throws Exception {
        //
        // TODO -- Dé-commentez les lignes autres que //IO// et //G// au fur et à mesure de votre implémentation
        //         Éventuellement, tous les tests devraient fonctionner et vous devriez
        //         obtenir le même affichage que celui montré dans l'énoncé
        // TODO --  Dé-commentez les lignes //IO// pour tester les deux fonction de lecture et d'écriture
        //          Vous devriez obtenir le format de fichier montré dans items.in et items.out
        // TODO -- Dé-commentez la ligne //G// pur tester votre implémentation graphique
        //         Éventuellement, vous devriez obtenir le même résultat que dans le clip de l'énoncé

        InventoryManager inventoryManager = new InventoryManager();
        lireInventaire("items.in",inventoryManager);                                          // 9 points
        System.out.println("\n=> TEST Création de nouveaux items");                                 // 6 points
        inventoryManager.addNewBreadItem(10, "Pain brun riche", 2.45, "brun", 200);
        inventoryManager.addNewBreadItem(11, "Pain blanc traditionnel", 1.50, "blanc", 200);
        inventoryManager.addNewEggsItem(12, "Oeufs de poules en liberté", 3.50, "Brun", 12);
        inventoryManager.addNewMilkItem(13, "Lait bio très gras", 8.45, 3.8, 2);

        System.out.println("\n=> TEST Trouver un item et afficher l'information sur cet item");     // 6 points
        Item item1 = inventoryManager.getItem(10);
        System.out.println(item1.infoToString());

        System.out.println("\n=> TEST Création d'un item avec un ID existant");                     // 6 points
        try {
            inventoryManager.addNewBreadItem(10, "Pain bio", 5, "brun", 400);
        } catch (ExceptionItemAlreadyExists e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=> TEST Enlever un item");                                            // 6 points
        inventoryManager.removeItem(10);

        System.out.println("\n=> TEST Enlever un item non existant (catch exception)");             // 6 points
        try {
            inventoryManager.removeItem(10);
        } catch (ExceptionItemNotFound e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=> TEST Incrémenter la quantité d'un item");                          // 8 points
        try {
            inventoryManager.increaseItemQuantity(10, 18);
        } catch (ExceptionItemNotFound e) {
            System.out.println(e.getMessage());
        }
        try {
            inventoryManager.increaseItemQuantity(11, 3);
        } catch (ExceptionItemNotFound e) {
            System.out.println(e.getMessage());
        }
        try {
           inventoryManager.increaseItemQuantity(12, 4);
        } catch (ExceptionItemNotFound e) {
            System.out.println(e.getMessage());
        }
        try {
            inventoryManager.increaseItemQuantity(13, 23);
        } catch (ExceptionItemNotFound e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=> TEST Incrementer la quantité d'un item et afficher les nouvelles informations"); // 6 points
        inventoryManager.increaseItemQuantity(11, 25);
        Item item2 = inventoryManager.getItem(11);
        System.out.println(item2.infoToString());

        System.out.println("\n=> TEST Incrementer la quantité d'un item et afficher les nouvelles informations");// 6 points
        inventoryManager.increaseItemQuantity(11, 3);
        Item item3 = inventoryManager.getItem(11);
        System.out.println(item3.infoToString());

        System.out.println("\n=> Décrementer la quantité d'un item non existant (catch exception)");// 6 points
        try {
            inventoryManager.decreaseItemQuantity(10, 1);
        } catch (ExceptionItemNotFound e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=> TEST Trop décrémenter la quantité d'un item (catch exception)");   // 6 points
        try {
            inventoryManager.decreaseItemQuantity(11, 32);
         } catch (ExceptionInsufficientQuantityInStock e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=> TEST Récupérer le array de items");// points
        Item[] items = inventoryManager.getArrayOfItems();
        for (Item item : items) {
            System.out.println(item.infoToString());
        }
        //IO//ecrireInventaire("items.out",inventoryManager);                                       // 9 points

        GUIInventoryManager GUIInventoryManager = new GUIInventoryManager(inventoryManager);   // 20 points
    }
    public static void lireInventaire (String nomFichier, InventoryManager inventoryManager) throws FileNotFoundException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("depart_H25/" + nomFichier));
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] attributs = ligne.split("]");

                //On garde juste les valeurs
                for (int i = 0; i < attributs.length; i++) {
                    attributs[i] = attributs[i].replaceAll(".*\\[(.*)", "$1");
                }

                int ID = Integer.parseInt(attributs[1]);
                String name = attributs[2];
                double price = Double.parseDouble(attributs[3]);


                switch(attributs[0]) {
                   case "Milk":
                       inventoryManager.addNewMilkItem(ID,name,price,
                               Double.parseDouble(attributs[4]),Double.parseDouble(attributs[5]));
                       break;
                   case "Bread":
                       inventoryManager.addNewBreadItem(ID,name,price,
                               attributs[4], Integer.parseInt(attributs[5]));
                       break;
                   case "Eggs":
                       inventoryManager.addNewEggsItem(ID,name,price,
                               attributs[4], Integer.parseInt(attributs[5]));
                       break;
                   default:
                       throw new Error("Une ligne d'entrée n'a pas une catégorie valable" + ligne);
               }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier: " + nomFichier);
            e.printStackTrace();
        }

    }
}
