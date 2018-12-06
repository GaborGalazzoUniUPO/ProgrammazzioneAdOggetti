package palyer;

public class Attempt {

    private final String guess;
    private final int maggot;
    private final int bulls;

    public Attempt(String guess, int maggot, int bulls) {
        this.guess = guess;
        this.maggot = maggot;
        this.bulls = bulls;
    }

    public String getGuess() {
        return guess;
    }

    public int getMaggot() {
        return maggot;
    }

    public int getBulls() {
        return bulls;
    }


    @Override
    public String toString() {
        return "palyer.Attempt{" +
                "guess='" + guess + '\'' +
                ", maggot=" + maggot +
                ", bulls=" + bulls +
                '}';
    }
}
