package View_Controller;

import Model.Inventory;
import static Model.Inventory.lookupPart;
import static Model.Inventory.lookupProduct;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainScreenController implements Initializable {

    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> idColumn;
    @FXML
    private TableColumn<Part, String> nameColumn;
    @FXML
    private TableColumn<Part, Integer> stockColumn;
    @FXML
    private TableColumn<Part, Double> priceColumn;
    @FXML
    private TextField partsToSearch;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> productIdColumn;
    @FXML
    private TableColumn<Part, String> productNameColumn;
    @FXML
    private TableColumn<Part, Integer> productStockColumn;
    @FXML
    private TableColumn<Part, Double> productPriceColumn;
    @FXML
    private TextField productsToSearch;

    @FXML
    public void exitButtonPushed(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(parent);

        Stage screen = (Stage) ((Node) event.getSource()).getScene().getWindow();

        screen.setScene(scene);
        screen.close();
    }

    @FXML
    public void addPartButtonPushed(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("AddPartScreen.fxml"));
        Scene scene = new Scene(parent);

        Stage screen = (Stage) ((Node) event.getSource()).getScene().getWindow();

        screen.setScene(scene);
        screen.show();
    }

    @FXML
    public void modifyPartButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyPartScreen.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        ModifyPartScreenController controller = loader.getController();

        Stage screen = (Stage) ((Node) event.getSource()).getScene().getWindow();
        controller.initializeData(partsTable.getSelectionModel().getSelectedItem());

        screen.setScene(scene);
        screen.show();
    }

    @FXML
    public void addProductButtonPushed(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("AddProductScreen.fxml"));
        Scene scene = new Scene(parent);

        Stage screen = (Stage) ((Node) event.getSource()).getScene().getWindow();

        screen.setScene(scene);
        screen.show();
    }

    @FXML
    public void modifyProductButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyProductScreen.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        ModifyProductScreenController productController = loader.getController();

        Stage screen = (Stage) ((Node) event.getSource()).getScene().getWindow();
        productController.initializeProductData(productTable.getSelectionModel().getSelectedItem());

        screen.setScene(scene);
        screen.show();

    }

    @FXML
    public void deletePartButtonPushed(ActionEvent e) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Part Confirmation");
        alert.setContentText("Are you sure you want to delete this part?");

        ObservableList<Part> selectedRows;

        selectedRows = partsTable.getSelectionModel().getSelectedItems();

        Optional<ButtonType> results = alert.showAndWait();

        if (results.get() == ButtonType.OK) {
            for (Part parts : selectedRows) {
                Inventory.deletePart(parts);

            }
            searchHandler(null);
        }

    }

    @FXML
    public void deleteProductButtonPushed(ActionEvent e) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Product Confirmation");
        alert.setContentText("Are you sure you want to delete this product?");

        ObservableList<Product> selectedRows;

        selectedRows = productTable.getSelectionModel().getSelectedItems();

        Optional<ButtonType> results = alert.showAndWait();

        if (results.get() == ButtonType.OK) {
            
            for (Product products : selectedRows) {
                Inventory.deleteProduct(products);
            }
            productSearchHandler(null);
        }
    }

    @FXML
    private void searchHandler(ActionEvent event) {
        String ps = partsToSearch.getText();

        ObservableList<Part> parts = lookupPart(ps);
        try {
            if (parts.isEmpty()) {
                int id = Integer.parseInt(ps);
                Part pId = lookupPart(id);
                if (pId != null) {
                    parts.add(pId);
                }
            }
        } catch (NumberFormatException e) {
            //ignore
        }

        partsTable.setItems(parts);
    }

    @FXML
    private void productSearchHandler(ActionEvent event) {
        String ps = productsToSearch.getText();

        ObservableList<Product> products = lookupProduct(ps);
        try {
            if (products.isEmpty()) {
                int id = Integer.parseInt(ps);
                Product pId = lookupProduct(id);
                if (pId != null) {
                    products.add(pId);
                }
            }
        } catch (NumberFormatException e) {
            //ignore
        }

        productTable.setItems(products);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        partsTable.setItems(Inventory.getAllParts());
        productTable.setItems(Inventory.getAllProducts());
    }
}
