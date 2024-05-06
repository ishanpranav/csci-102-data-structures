import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public final class AirlineBoarding {
    private AirlineBoarding() {
    }
    
    public static void main(String[] args) {
        int m;
        
        final Queue<Integer> queue = new ArrayDeque<Integer>();
        final PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>((left, right) -> right - left);
        
        try (Scanner scanner = new Scanner(System.in)) {
            final int n = scanner.nextInt();
            
            m = scanner.nextInt();
            
            for (int i = 0; i < n; i++) {
                final int number = scanner.nextInt();
                
                queue.add(number);
                priorityQueue.add(number);
            }
        }
        
        int count = 0;
        
        for (;;) {
            int current = queue.remove();
            
            if (current == priorityQueue.peek()) {
                priorityQueue.remove();
                
                if (m == 0) {
                    break;
                }
                
                count++;
            } else {
                queue.add(current);
                
                if (m == 0) {
                    m = queue.size();
                }
            }
            
            m--;
        }
    
        System.out.println(count);
    }
}
