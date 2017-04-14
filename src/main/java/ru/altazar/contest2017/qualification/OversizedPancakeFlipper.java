package ru.altazar.contest2017.qualification;

import ru.altazar.Problem;

import java.io.BufferedReader;
import java.io.IOException;

public class OversizedPancakeFlipper extends Problem {

    @Override
    protected String solveCase(BufferedReader reader) throws IOException {
        String[] split = reader.readLine().split(" ");

        String pancakes = split[0];
        int flipperSize = Integer.parseInt(split[1]);
        int flipCount = 0;

        while (pancakes.length() >= flipperSize) {
            if (pancakes.startsWith("-")) {
                pancakes = flip(pancakes, flipperSize);
                flipCount++;
            }
            while (pancakes.startsWith("+")) {
                pancakes = pancakes.substring(1);
            }
        }

        return pancakes.isEmpty() ? String.valueOf(flipCount) : "IMPOSSIBLE";
    }

    private String flip(String pancakes, int flipperSize) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < flipperSize; i++) {
            char ch = pancakes.charAt(i);
            builder.append(ch == '-' ? '+' : '-');
        }
        builder.append(pancakes.substring(flipperSize));
        return builder.toString();
    }


}
