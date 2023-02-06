import java.util.PriorityQueue;
import java.util.Scanner;

public final class EfficientAdding {
    private EfficientAdding() {
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            //final int count = scanner.nextInt();
            final PriorityQueue<Long> priorityQueue = new PriorityQueue<Long>(5000);

            for (int i = 0; i < 5000; i++) {
                priorityQueue.add(1000000l);
            }

            long totalCost = 0;

            while (priorityQueue.size() >= 2) {
                final long first = priorityQueue.remove();
                final long second = priorityQueue.remove();
                final long cost = first + second;

                priorityQueue.add(cost);

                totalCost += cost;
            }

            System.out.printf("%,d\n", totalCost);
        }
    }
}
