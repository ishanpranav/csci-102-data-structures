import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public final class HowManyWords {
    private HowManyWords() {
    }

    public static void main(String[] args) throws IOException {
        final TreeSet<String> words = new TreeSet<String>();

        try (final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                final BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            int read;
            StringBuilder word = new StringBuilder();

            do {
                read = bufferedReader.read();

                if (read >= 'a' && read <= 'z') {
                    word.append((char) read);
                } else if (read >= 'A' && read <= 'Z') {
                    word.append((char) (read - 'A' + 'a'));
                } else if (word.length() > 0) {
                    words.add(word.toString());
                    word.setLength(0);
                }
            } while (read != -1);
        } catch (IOException ioException) {
            System.err.println(ioException.getLocalizedMessage());
        }

        for (String word : words) {
            System.out.println(word);
        }
    }
}
