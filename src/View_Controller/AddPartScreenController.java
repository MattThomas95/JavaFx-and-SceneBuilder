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

public class AddPartScreenController implements Initializable {

    @FXML
    private RadioButton inHoused;
    @FXML
    private RadioButton outsourced;
    @FXML
    private ToggleGroup addPartSource;
    @FXML
    private TextField partNameTextField;
    @FXML
    private TextField invTextField;
    @FXML
    private TextField priceCostTextField;
    @FXML
    private TextField maxTextField;
    @FXML
    private TextField minTextField;
    @FXML
    private TextField companyNameTextField;
    @FXML
    private Label companyAndMachineLabel;

    public void addPartCancelButtonPushed(ActionEvent event) throws IOException {
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

    public void addPartSaveButtonPushed(ActionEvent event) throws IOException {
        if (Integer.parseInt(maxTextField.getText()) < Integer.parseInt(minTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Minimum Exceeds Maximum");
            alert.setContentText("Minimun cannot exceed maximum.");
            alert.showAndWait();
        } else {
            if (this.addPartSource.getSelectedToggle().equals(this.outsourced)) {
                Part newOutPart = new Outsourced(null,
                        partNameTextField.getText(),
                        Integer.parseInt(invTextField.getText()),
                        Double.parseDouble(priceCostTextField.getText()),
                        Integer.parseInt(maxTextField.getText()),
                        Integer.parseInt(minTextField.getText()),
                        companyNameTextField.getText());
                Inventory.addPart(newOutPart);
            } else {
                Part newInPart = new InHouse(null,
                        partNameTextField.getText(),
                        Integer.parseInt(invTextField.getText()),
                        Double.parseDouble(priceCostTextField.getText()),
                        Integer.parseInt(maxTextField.getText()),
                        Integer.parseInt(minTextField.getText()),
                        Integer.parseInt(companyNameTextField.getText()));
                Inventory.addPart(newInPart);
            }

            Parent parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(parent);

            Stage screen = (Stage) ((Node) event.getSource()).getScene().getWindow();

            screen.setScene(scene);
            screen.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        companyAndMachineLabel.setText("Company Name");
        companyNameTextField.setPromptText("Company Name");
        addPartSource = new ToggleGroup();
        this.inHoused.setToggleGroup(addPartSource);
        this.outsourced.setToggleGroup(addPartSource);

    }

}
