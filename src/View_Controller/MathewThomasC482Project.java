package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Product;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MathewThomasC482Project extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        addPart();
        addProduct();
        launch(args);
    }

    public static void addPart() {
        Inventory.addPart(new Outsourced(null, "Pikachu", 2, 19.99, 11, 10, "PokemonCo"));
        Inventory.addPart(new Outsourced(null, "Charizard", 10, 29.99, 12, 1, "PokemonCo"));
        Inventory.addPart(new InHouse(null, "Snorlax", 15, 9.99, 23, 13, 144));
        Inventory.addPart(new InHouse(null, "Pichu", 15, 9.99, 23, 13, 212));

    }

    public static void addProduct() {
        Inventory.addProduct(new Product(null, "Team Ash", 5, 55.99, 51, 15));
        Inventory.addProduct(new Product(null, "Team Rocket", 3, 33.99, 33, 13));
        Inventory.addProduct(new Product(null, "Team Misty", 3, 33.99, 33, 13));
    }

}
