import java.util.*;

public class SplitPositiveNegative {
    public static void main(String[] args) {
        try (final Scanner in = new Scanner(System.in)) {
            final ArrayList<Integer> positives = new ArrayList<Integer>();
            final ArrayList<Integer> negatives = new ArrayList<Integer>();
            final TreeSet<Integer> list = new TreeSet<Integer>();

            while (in.hasNextInt()) {
                list.add(in.nextInt());
            }

            while (!list.isEmpty()) {
                final int first = list.pollFirst();

                if (first >= 0) {
                    positives.add(first);
                } else {
                    negatives.add(first);
                }
            }

            for (int positive : positives) {
                System.out.print(positive + " ");
            }

            System.out.println();

            for (int negative : negatives) {
                System.out.print(negative + " ");
            }
        }
    }
}
