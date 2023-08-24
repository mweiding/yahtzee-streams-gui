package logic;

public class RandomGenerator implements RandGen {
    @Override
    public int randDiceValue() {
        return (int) (Math.random() * 6) + 1;
    }
}
