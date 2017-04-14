package ru.altazar.contest2017.qualification;

import ru.altazar.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TidyNumbers extends Problem {

    @Override
    protected String solveCase(BufferedReader reader) throws IOException {

        String line = reader.readLine();

        int[] digits = new int[line.length()];
        for (int i = 0; i < line.length(); i++) {
            digits[i] = Integer.parseInt(line.substring(i, i + 1));
        }

        for (int i = digits.length - 1; i > 0; i--) {
            if (digits[i - 1] > digits[i]) {
                digits[i - 1]--;
                for (int j = i; j < digits.length; j++) {
                    digits[j] = 9;
                }
            }
        }

        int leadZeros = 0;
        for (int digit : digits) {
            if (digit != 0) {
                break;
            }
            leadZeros++;
        }

        return Arrays.stream(digits, leadZeros, digits.length)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }
}
