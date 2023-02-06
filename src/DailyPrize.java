import java.util.Objects;
import java.util.Scanner;
import java.util.TreeSet;

public final class DailyPrize {
    private DailyPrize() {
    }

    private static class UniqueInteger implements Comparable<UniqueInteger> {
        private static int sequence;

        private final int id;
        private final int value;

        public int evaluate() {
            return value;
        }

        public UniqueInteger(int value) {
            id = sequence;
            this.value = value;
            sequence++;
        }

        @Override
        public int compareTo(UniqueInteger o) {
            if (value == o.value) {
                return id - o.id;
            } else {
                return value - o.value;
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || !(obj instanceof UniqueInteger)) {
                return false;
            }

            final UniqueInteger other = (UniqueInteger) obj;

            return id == other.id && value == other.value;
        }
    }

    public static void main(String[] args) {
        final TreeSet<UniqueInteger> doubleEndedPriorityQueue = new TreeSet<UniqueInteger>();

        try (Scanner scanner = new Scanner(System.in)) {
            final int days = scanner.nextInt();

            int result = 0;

            for (int day = 0; day < days; day++) {
                final int bills = scanner.nextInt();

                for (int bill = 0; bill < bills; bill++) {
                    doubleEndedPriorityQueue.add(new UniqueInteger(scanner.nextInt()));
                }

                final UniqueInteger min = doubleEndedPriorityQueue.first();
                final UniqueInteger max = doubleEndedPriorityQueue.last();

                result += max.evaluate() - min.evaluate();

                doubleEndedPriorityQueue.remove(min);
                doubleEndedPriorityQueue.remove(max);
            }

            System.out.println(result);
        }
    }
}
