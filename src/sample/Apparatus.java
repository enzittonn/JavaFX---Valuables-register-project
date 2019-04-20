package sample;

public class Apparatus extends Valuables {
    private double originalPrice;
    private double condition;


    public Apparatus(String namn, double originalPrice, double condition) {
        super(namn,0);
        this.originalPrice = originalPrice;
        this.condition = condition;
    }

    public double finalValue() {
        return afterVAT(originalPrice * (condition / 10));

    }

    public double getOriginalPrice() {return originalPrice;}

    public double getCondition() {return condition;}

    @Override
    public String toString() {
        return getName()  +
                " Värde: " + finalValue() +
                " Inköpspris: " + originalPrice +
                " Slitage: " + (int) condition;
    }
}
