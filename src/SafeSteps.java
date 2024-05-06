import java.util.Scanner;

public final class SafeSteps {
    private SafeSteps() {
    }
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final int exponent = scanner.nextInt();
            final int popCount = scanner.nextInt();
            final long pow2N = 1L << exponent;
            
            for (long i = 0; i < pow2N; i++) {
                if (Long.bitCount(i) == popCount) {
                    System.out.println(String.format("%" + exponent + "s", Long.toBinaryString(i)).replace(' ', '0'));
                }
            }
        }
    }
}
