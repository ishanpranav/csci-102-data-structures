import java.util.NoSuchElementException;
import java.util.Scanner;

public final class LuckyDraw {
    private static class ValueStack {
        private static int[] EMPTY;

        private int count;
        private int[] buffer;

        public ValueStack() {
            if (EMPTY == null) {
                EMPTY = new int[0];
            }

            buffer = EMPTY;
        }

        public int size() {
            return count;
        }

        public void push(int value) {
            if (buffer.length == 0) {
                buffer = new int[4];
            } else if (count == buffer.length) {
                int[] newBuffer = new int[buffer.length * 2];

                System.arraycopy(buffer, 0, newBuffer, 0, count);

                buffer = newBuffer;
            }

            buffer[count] = value;
            count++;
        }

        public int peek() {
            if (count == 0) {
                throw new NoSuchElementException();
            }

            return buffer[count - 1];
        }

        public int pop() {
            if (count == 0) {
                throw new NoSuchElementException();
            }

            count--;

            return buffer[count];
        }
    }

    private LuckyDraw() {
    }

    public static void main(String[] args) {
        final ValueStack stack = new ValueStack();

        try (Scanner scanner = new Scanner(System.in)) {
            final int count = scanner.nextInt();

            for (int i = 0; i < count; i++) {
                final int current = scanner.nextInt();
                
                if (stack.size() == 0) {
                    stack.push(current);
                
                    continue;
                }

                final int sum = current + stack.peek();

                if (sum % 2 == 0) {
                    stack.pop();
                } else {
                    stack.push(current);
                }
            }
        }

        System.out.print(stack.size());
    }
}
