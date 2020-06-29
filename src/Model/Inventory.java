
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static Part lookupPart(int partId) {

        for (Part sp : allParts) {
            if (sp.getId() == partId) {
                return sp;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> foundPart = FXCollections.observableArrayList();

        for (Part sp : allParts) {
            if (sp.getName().contains(partName)) {
                foundPart.add(sp);
            }
        }
        return foundPart;

    }

    public static void updatePart(int index, Part selectedPart) {
        allParts.add(index, selectedPart);

    }

    public static int getPartIndex(Part p) {
        return allParts.indexOf(p);
    }

    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);

        }
        return false;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Product lookupProduct(int productId) {

        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> foundProduct = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().contains(productName)) {
                foundProduct.add(product);
            }
        }
        return foundProduct;

    }

    public static void updateProduct(int index, Product newProduct) {
        allProducts.add(index, newProduct);

    }

    public static int getProductIndex(Product p) {
        return allProducts.indexOf(p);
    }

    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);

        }
        return false;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}
