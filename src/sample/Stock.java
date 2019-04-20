package sample;

public class Stock extends Valuables {
    private int shares;
    private double rate;

    public Stock(String namn, int shares, double rate){
        super(namn,0);
        this.rate = rate;
        this.shares = shares;
    }

    public double finalValue() {
        return afterVAT(rate * shares);

    }

    public int getShares(){return shares;}

    public void setRate(double nykurs) {
        this.rate = nykurs;
    }

    public double getRate(){return rate;}

    @Override
    public String toString() {
        return getName() +
                " Värde: " + finalValue() +
                " Antal: " + shares +
                " Kurs: " + rate;
    }
}


