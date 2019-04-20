package sample;

public class Jewelry extends Valuables {
    private int gemStones;
    private boolean guld;


    public Jewelry(String namn, int gemStones, boolean guld) {
        super(namn, 0);
        this.gemStones = gemStones;
        this.guld = guld;
    }

    public double finalValue() {
        if(guld) {
            double värde = 2000 + (gemStones * 500);
            return afterVAT(värde);
        } else {
            double värde = 700 + (gemStones * 500);
            return afterVAT(värde);
        }
    }

    public int getGemStones() {
        return gemStones;
    }

    public boolean getGuld() {
        return guld;
    }

    public String avGuldd(){
        String guld;
        if(getGuld() != true){
            guld = "av: guld";
        } else {
            guld = "av: silver";
        }
        return guld;
    }

    @Override
    public String toString() {
        return getName() +
                " Värde: " + finalValue() +
                " " + avGuldd() +
                " Ädelstenar: " + gemStones;
    }

}
