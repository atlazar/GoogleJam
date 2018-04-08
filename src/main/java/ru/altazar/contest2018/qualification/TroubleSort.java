package ru.altazar.contest2018.qualification;

import ru.altazar.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TroubleSort extends Problem {

    @Override
    protected String solveCase(BufferedReader reader) throws IOException {
        reader.readLine();//Number in list
        String[] values = reader.readLine().split(" ");

        List<Integer> first = new ArrayList<>(values.length / 2 + 1);
        List<Integer> second = new ArrayList<>(values.length / 2 + 1);
        for (int i = 0; i < values.length; i++) {
            Integer current = Integer.valueOf(values[i]);
            if (i % 2 == 0) {
                first.add(current);
            } else {
                second.add(current);
            }
        }

        Collections.sort(first);
        Collections.sort(second);

        List<Integer> result = new ArrayList<>(values.length);
        for (int i = 0; i < values.length; i++) {
            if (i % 2 == 0) {
                result.add(first.get(i / 2));
            } else {
                result.add(second.get(i / 2));
            }
        }

        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i) > result.get(i + 1)) {
                return String.valueOf(i);
            }
        }

        return "OK";
    }
}
