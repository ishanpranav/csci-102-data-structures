import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

public final class BuggyKeyboard {
    private static class SymbolStack {
        private static char[] EMPTY;

        private int count;
        private char[] buffer;

        public SymbolStack() {
            if (EMPTY == null) {
                EMPTY = new char[0];
            }

            buffer = EMPTY;
        }

        public void push(char symbol) {
            if (buffer.length == 0) {
                buffer = new char[4];
            } else if (count == buffer.length) {
                char[] newBuffer = new char[buffer.length * 2];

                System.arraycopy(buffer, 0, newBuffer, 0, count);

                buffer = newBuffer;
            }

            buffer[count] = symbol;
            count++;
        }

        public char pop() {
            if (count == 0) {
                throw new NoSuchElementException();
            }

            count--;

            return buffer[count];
        }

        @Override
        public String toString() {
            return new String(buffer, 0, count);
        }
    }

    private BuggyKeyboard() {
    }

    public static void main(String[] args) {
        final SymbolStack symbols = new SymbolStack();

        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            int read;

            do {
                read = bufferedReader.read();

                switch (read) {
                    case -1:
                        continue;

                    case '<':
                        symbols.pop();
                        break;

                    default:
                        symbols.push((char) read);
                }
            } while (read != -1);
        } catch (IOException ioException) {
            System.err.println(ioException.getLocalizedMessage());
        }

        System.out.print(symbols);
    }
}
