
package Model;

public class Outsourced extends Part{
    private String companyName;

    public Outsourced(Integer id, String name, Integer stock, Double price, Integer max, Integer min, String companyName) {
        super(id, name, stock, price, max, min);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
