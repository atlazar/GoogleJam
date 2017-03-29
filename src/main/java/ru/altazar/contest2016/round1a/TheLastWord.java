package ru.altazar.contest2016.round1a;

import ru.altazar.Problem;

import java.io.BufferedReader;
import java.io.IOException;

public class TheLastWord extends Problem {

    @Override
    protected String solveCase(BufferedReader reader) throws IOException {
        StringBuilder lastWord = new StringBuilder();
        for (char letter : reader.readLine().toCharArray()) {

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
