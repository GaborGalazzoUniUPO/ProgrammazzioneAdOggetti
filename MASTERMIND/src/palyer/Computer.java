package palyer;

import util.Judge;

public class Computer extends Player {
    @Override
    public String genGuess() {
        return Judge.genTarget();
    }

    @Override
    public String genTarget() {
        return Judge.genTarget();
    }
}
