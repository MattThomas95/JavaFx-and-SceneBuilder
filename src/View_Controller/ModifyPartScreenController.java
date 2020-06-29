package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ModifyPartScreenController implements Initializable {

    @FXML
    private RadioButton inHoused;
    @FXML
    private RadioButton outsourced;
    @FXML
    private ToggleGroup addPartSource;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField partNameTextField;
    @FXML
    private TextField invTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField maxTextField;
    @FXML
    private TextField minTextField;
    @FXML
    private TextField companyNameTextField;
    @FXML
    private Label companyAndMachineLabel;
    @FXML
    private Part selectedPart;

    public void partRadioButtonPushed() {
        if (this.addPartSource.getSelectedToggle().equals(this.inHoused)) {
            companyAndMachineLabel.setText("Machine ID");
            companyNameTextField.setPromptText("Machine ID");
        }
        if (this.addPartSource.getSelectedToggle().equals(this.outsourced)) {
            companyAndMachineLabel.setText("Company Name");
            companyNameTextField.setPromptText("Company Name");
        }
    }

    public void modifyPartCancelButtonPushed(ActionEvent event) throws IOException {
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

    public void modifyPartSaveButtonPushed(ActionEvent event) throws IOException {
        if (Integer.parseInt(maxTextField.getText()) < Integer.parseInt(minTextField.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Minimum Exceeds Maximum");
                alert.setContentText("Minimun cannot exceed maximum.");
                alert.showAndWait();
            } else {
        if (this.addPartSource.getSelectedToggle().equals(this.outsourced)) {
            Part newOutPart = new Outsourced(Integer.parseInt(idTextField.getText()),
                    partNameTextField.getText(),
                    Integer.parseInt(invTextField.getText()),
                    Double.parseDouble(priceTextField.getText()),
                    Integer.parseInt(maxTextField.getText()),
                    Integer.parseInt(minTextField.getText()),
                    companyNameTextField.getText());
            int index = Inventory.getPartIndex(selectedPart);
            Inventory.deletePart(selectedPart);
            Inventory.updatePart(index, newOutPart);

        }
        else {
            Part newInPart = new InHouse(Integer.parseInt(idTextField.getText()),
                    partNameTextField.getText(),
                    Integer.parseInt(invTextField.getText()),
                    Double.parseDouble(priceTextField.getText()),
                    Integer.parseInt(maxTextField.getText()),
                    Integer.parseInt(minTextField.getText()),
                    Integer.parseInt(companyNameTextField.getText()));
            int index = Inventory.getPartIndex(selectedPart);
            Inventory.deletePart(selectedPart);
            Inventory.updatePart(index, newInPart);
        }

        Parent parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(parent);

        Stage screen = (Stage) ((Node) event.getSource()).getScene().getWindow();

        screen.setScene(scene);
        screen.show();
        }
    }

    public void initializeData(Part part) {
        selectedPart = part;
        idTextField.setText(selectedPart.getId().toString());
        partNameTextField.setText(selectedPart.getName());
        invTextField.setText(selectedPart.getStock().toString());
        priceTextField.setText(selectedPart.getPrice().toString());
        maxTextField.setText(Integer.toString(selectedPart.getMax()));
        minTextField.setText(Integer.toString(selectedPart.getMin()));
        if (part instanceof Outsourced) {
            Outsourced outsourcedPart = (Outsourced) part;
            companyNameTextField.setText(outsourcedPart.getCompanyName());
            companyAndMachineLabel.setText("Company Name");
            companyNameTextField.setPromptText("Company Name");
            this.outsourced.setSelected(true);
        } else {
            InHouse inHousePart = (InHouse) part;
            companyNameTextField.setText(Integer.toString(inHousePart.getMachineId()));
            companyAndMachineLabel.setText("Machine ID");
            companyNameTextField.setPromptText("Machine ID");
            this.inHoused.setSelected(true);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        addPartSource = new ToggleGroup();
        this.inHoused.setToggleGroup(addPartSource);
        this.outsourced.setToggleGroup(addPartSource);
    }

}
