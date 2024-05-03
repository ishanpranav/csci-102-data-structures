import java.util.Scanner;
import java.util.TreeSet;

public final class DancingHippos {
    private DancingHippos() {
    }
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final int capacity = scanner.nextInt();
            final int n = scanner.nextInt();
            final TreeSet<Integer> sortedSet = new TreeSet<Integer>();
            
            int totalWeight = 0;
            int max = 0;
            
            for (int i = 0; i < n; i++) {
                sortedSet.add(scanner.nextInt());
            }
            
            for (Integer weight : sortedSet) {
                int newWeight = totalWeight + weight;
                
                if (newWeight > capacity) {
                    break;
                }
            
                totalWeight = newWeight;
                max++;
            }
            
            System.out.println(max);
        }
    }
}
