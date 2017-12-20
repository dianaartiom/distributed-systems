public class PowerComputer {

    public static int compute(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        return (base * compute(base, exponent-1));
    }

    public static void main(String[] args) {
        int pow = compute(8, 4);
        System.out.println(pow);
    }
}
