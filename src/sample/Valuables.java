package sample;

public abstract class Valuables {
    private String name;
    private int value;


    public Valuables(String name, int value) {
        this.name = name;
        this.value = value;
    }

    abstract public double finalValue();

    public void setName(String name) {
        this.name = this.name;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(){
        this.value = value;
    }

    public double afterVAT(double värde) {
        double eftermoms = värde + (värde * 0.25);
        return eftermoms;
    }

    @Override
    public String toString() {
        return name;
    }
}
