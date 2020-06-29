package View_Controller;

import Model.Inventory;
import static Model.Inventory.lookupPart;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddProductScreenController implements Initializable {

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
    private TableView<Part> productTable;
    @FXML
    private TableColumn<Part, Integer> productIdColumn;
    @FXML
    private TableColumn<Part, String> productNameColumn;
    @FXML
    private TableColumn<Part, Integer> productStockColumn;
    @FXML
    private TableColumn<Part, Double> productPriceColumn;
    @FXML
    private TextField productNameTextField;
    @FXML
    private TextField invTextField;
    @FXML
    private TextField priceCostTextField;
    @FXML
    private TextField maxTextField;
    @FXML
    private TextField minTextField;
    @FXML
    private TextField partsToSearch;

    private ObservableList<Part> selectedPart = FXCollections.observableArrayList();

    @FXML
    public void cancelAddProductButtonPushed(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Modification Confirmation");
        alert.setContentText("Are you sure you want to cancel modifying this part?");

        Optional<ButtonType> results = alert.showAndWait();

        if (results.get() == ButtonType.OK) {
            Parent parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(parent);

            Stage screen = (Stage) ((Node) event.getSource()).getScene().getWindow();

            screen.setScene(scene);
            screen.show();
        }
    }

    @FXML
    public void addPartButtonPushed(ActionEvent event) {
        Part partSelected = partsTable.getSelectionModel().getSelectedItem();
        selectedPart.add(partSelected);
        updateTableView();
    }

    public void addProductSaveButtonPushed(ActionEvent event) throws IOException {

        if (Integer.parseInt(maxTextField.getText()) < Integer.parseInt(minTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Minimum Exceeds Maximum");
            alert.setContentText("Minimun cannot exceed maximum.");
            alert.showAndWait();
        } else {
            Product newProduct = new Product(null,
                    productNameTextField.getText(),
                    Integer.parseInt(invTextField.getText()),
                    Double.parseDouble(priceCostTextField.getText()),
                    Integer.parseInt(maxTextField.getText()),
                    Integer.parseInt(minTextField.getText()));
            Inventory.addProduct(newProduct);

            for (Part sp : selectedPart) {
                newProduct.addAssociatedPart(sp);
            }
            Parent parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(parent);

            Stage screen = (Stage) ((Node) event.getSource()).getScene().getWindow();

            screen.setScene(scene);
            screen.show();
        }

    }
    
    public void updateTableView() {
        productTable.setItems(selectedPart);
    }

    @FXML
    public void deletePartButtonPushed() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Part Confirmation");
        alert.setContentText("Are you sure you want to delete this part from the list?");

        ObservableList<Part> selectedRows;

        selectedRows = productTable.getSelectionModel().getSelectedItems();

        Optional<ButtonType> results = alert.showAndWait();

        if (results.get() == ButtonType.OK) {
            for (Part parts : selectedRows) {
                selectedPart.remove(parts);
            }
        }
    }

    @FXML
    private void searchHandler(ActionEvent event) {
        String ps = partsToSearch.getText();

        ObservableList<Part> parts = lookupPart(ps);

        if (parts.isEmpty()) {
            int id = Integer.parseInt(ps);
            Part pId = lookupPart(id);
            if (pId != null) {
                parts.add(pId);
            }
        }

        partsTable.setItems(parts);
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
    }
}
