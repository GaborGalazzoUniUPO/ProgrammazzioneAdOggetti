package palyer;

public class Attempt {

    private final String guess;
    private final int maggots;
    private final int bulls;

    public Attempt(String guess, int maggots, int bulls) {
        this.guess = guess;
        this.maggots = maggots;
        this.bulls = bulls;
    }

    public String getGuess() {
        return guess;
    }

    public int getMaggots() {
        return maggots;
    }

    public int getBulls() {
        return bulls;
    }


    @Override
    public String toString() {
        return "palyer.Attempt{" +
                "guess='" + guess + '\'' +
                ", maggots=" + maggots +
                ", bulls=" + bulls +
                '}';
    }
}
