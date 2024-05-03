import java.util.Scanner;

public final class SafeSteps {
    private SafeSteps() {
    }
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final long pow2N = 1L << scanner.nextInt();
            final long popCount = scanner.nextInt();
            
            for (long i = 0; i < pow2N; i++) {
                if (Long.bitCount(i) == popCount) {
                    System.out.println(String.format("%4s", Long.toBinaryString(i)).replace(' ', '0'));
                }
            }
        }
    }
}
