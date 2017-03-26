package ru.altazar.contest2016.round1a;

import ru.altazar.Problem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by andrey on 3/25/17.
 */
public class TheLastWord implements Problem {

    public TheLastWord() {
    }

    @Override
    public void solve(int cases, BufferedReader reader, BufferedWriter writer) throws IOException {
        for (int i = 1; i <= cases; i++) {
            String solution = playSingleRound(reader.readLine());
            writer.write(String.format("Case #%d: %s\n", i, solution));
        }
    }

    private static String playSingleRound(String word) {
        StringBuilder lastWord = new StringBuilder();
        for (char letter : word.toCharArray()) {

            boolean append = true;
            for (int i = 0; i < lastWord.length(); i++) {
                if (lastWord.charAt(i) != letter) {
                    append = letter < lastWord.charAt(i);
                    break;
                }
            }

            if (append) {
                lastWord.append(letter);
            } else {
                lastWord.insert(0, letter);
            }
        }
        return lastWord.toString();
    }
}
