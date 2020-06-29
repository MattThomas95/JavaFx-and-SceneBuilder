
package Model;

public class InHouse extends Part {
    private Integer machineId;

    public InHouse(Integer id, String name, Integer stock, Double price, Integer max, Integer min, Integer machineId) {
        super(id, name, stock, price, max, min);
        this.machineId = machineId;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }
}

