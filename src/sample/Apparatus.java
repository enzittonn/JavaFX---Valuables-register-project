//19970723-9597 - Nyamgarig Naranbaatar
//19980303-0437 - Mosleh Mahamud
//19961107-8354 - Mpho Khumalo
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
