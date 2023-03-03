package t9;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.HashMap;

    /**
     * Translate a stream of bytes containing only T9 keyboard characters to a human-readable text.
     * Only characters 2-9, 0, space are allowed as input using standard layout representation:
     * 2 -> abc
     * 3 -> def
     * 4 -> ghi
     * 5 -> jkl
     * 6 -> mno
     * 7 -> pqrs
     * 8 -> tuc
     * 9 -> wxyz
     * 0 -> a space
     * space -> a "pause"
     * A space is used to represent some time between two presses of the same button.
     * For instance,  to write "hihi": "44 444 44 444"
     * Pauses can be repeated multiple time, including between two different key presses and should not impact output
     */

    public class T9Translator {
    private HashMap<Integer, char[]> T9KeyboardHashMap = new HashMap<>();

    public T9Translator() {
        T9KeyboardHashMap.put(2, new char[]{'a', 'b', 'c'});
        T9KeyboardHashMap.put(3, new char[]{'d', 'e', 'f'});
        T9KeyboardHashMap.put(4, new char[]{'g', 'h', 'i'});
        T9KeyboardHashMap.put(5, new char[]{'j', 'k', 'l'});
        T9KeyboardHashMap.put(6, new char[]{'m', 'n', 'o'});
        T9KeyboardHashMap.put(7, new char[]{'p', 'q', 'r', 's'});
        T9KeyboardHashMap.put(8, new char[]{'t', 'u', 'v'});
        T9KeyboardHashMap.put(9, new char[]{'w', 'x', 'y', 'z'});
        T9KeyboardHashMap.put(0, new char[]{' '});
    }
    public String translate(InputStream stream) throws IOException {
        Scanner scanner = new Scanner(stream);
        String input = scanner.nextLine();
        int position = 0;
        String message = "";

        for (int index = 0; index < input.length(); index++) {
            char currentDigit = input.charAt(index);

            // if space, new digit group is coming: reset position and skip
            if (currentDigit == ' ') {
                position = 0;
                continue;
            }

            // if last position, save the message and exit
            if (index == input.length() - 1) {
                int digitKey = Character.getNumericValue(currentDigit);
                char letter = T9KeyboardHashMap.get(digitKey)[position];
                message += letter;
                break;
            }

            char nextDigit = input.charAt(index + 1);

            // if same digit, increment position
            if (currentDigit == nextDigit) {
                position ++;

            } else {
                // if different digit, save letter into message
                int digitKey = Character.getNumericValue(currentDigit);
                char letter = T9KeyboardHashMap.get(digitKey)[position];
                message += letter;
                position = 0;
            }
        }

            return message;
        }
}
