package Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    public final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private final Integer id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty stock;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty max;
    private SimpleIntegerProperty min;
    private static Integer idCounter = 1;

    public Product(Integer id, String name, Integer stock, Double price,
            Integer max, Integer min) {
        if (id == null) {
            this.id = idCounter++;
        } else {
            this.id = id;
        }
        this.name = new SimpleStringProperty(name);
        this.stock = new SimpleIntegerProperty(stock);
        this.price = new SimpleDoubleProperty(price);
        this.max = new SimpleIntegerProperty(max);
        this.min = new SimpleIntegerProperty(min);
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public boolean deleteAssociatedPart(Part selectedAsPart) {
        if (associatedParts.contains(selectedAsPart)) {
            associatedParts.remove(selectedAsPart);
        }
        return false;
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = idCounter;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public Integer getStock() {
        return stock.get();
    }

    public void setStock(Integer stock) {
        this.stock = new SimpleIntegerProperty(stock);
    }

    public Double getPrice() {
        return price.get();
    }

    public void setPrice(Double price) {
        this.price = new SimpleDoubleProperty(price);
    }

    public Integer getMax() {
        return max.get();
    }

    public void setMax(Integer max) {
        this.max = new SimpleIntegerProperty(max);
    }

    public Integer getMin() {
        return min.get();
    }

    public void setMin(Integer min) {
        this.min = new SimpleIntegerProperty(min);
    }

}
