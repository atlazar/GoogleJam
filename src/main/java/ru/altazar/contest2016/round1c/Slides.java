package ru.altazar.contest2016.round1c;

import ru.altazar.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Slides extends Problem {

    @Override
    protected String solveCase(BufferedReader reader) throws IOException {
        String[] line = reader.readLine().split(" ");

        int buildings = Integer.valueOf(line[0]);
        long milliseconds = Long.valueOf(line[1]);


        String binWays = Long.toBinaryString(milliseconds);

        if (binWays.length() >= buildings) {
            return "IMPOSSIBLE";
        }

        int[][] sliders = new int[buildings][buildings];
        if (binWays.length() == buildings - 1) {
            for (int i = 1; i < binWays.length(); i++) {
                if (binWays.charAt(i) != '0') {
                    return "IMPOSSIBLE";
                }
            }
            for (int i = 1; i < buildings; i++) {
                sliders[0][i] = 1;
            }
        } else {
            //binWays.length() <= buildings - 2
            binWays = binWays + "0";
            while (binWays.length() < buildings) {
                binWays = "0" + binWays;
            }

            for (int i = 0; i < binWays.length(); i++) {
                sliders[0][i] = (binWays.charAt(i) == '1') ? 1 : 0;
            }
        }

        fillRemain(sliders);

        String matrix = Stream.of(sliders)
                .map(row ->
                        Arrays.stream(row)
                                .mapToObj(String::valueOf)
                                .collect(Collectors.joining()))
                .collect(Collectors.joining("\n"));

        return "POSSIBLE\n" + matrix;
    }

    private void fillRemain(int[][] sliders) {
        int fromRow = 0;
        for (int i = 0; i < sliders.length; i++) {
            if (sliders[0][i] == 1) {
                fromRow = i;
                break;
            }
        }

        for (int i = fromRow; i < sliders.length - 1; i++) {
            for (int j = i + 1; j < sliders.length; j++) {
                sliders[i][j] = 1;
            }
        }

    }

}
