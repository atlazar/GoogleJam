package ru.altazar.contest2016.round1a;

import ru.altazar.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RankAndFile extends Problem {

    @Override
    protected String solveCase(BufferedReader reader) throws IOException {
        int squareSize = Integer.parseInt(reader.readLine());

        List<List<Integer>> lines = new ArrayList<>();
        for (int i = 0; i < 2 * squareSize - 1; i++) {
            List<Integer> line = Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            lines.add(line);
        }


        DiagElement[] diag = prepareDiag(squareSize, lines);

        int missingLineIndex = 0;
        for (int i = 0; i < diag.length; i++) {
            if (diag[i].line2 == null) {
                missingLineIndex = i;
                break;
            }
        }

        DiagElement missingLineElement = diag[missingLineIndex];

        List<Integer> lostLine = new ArrayList<>();
        for (int i = 0; i < diag.length; i++) {
            Integer positionValue = missingLineElement.line1.get(i);
            if (i == missingLineIndex) {
                lostLine.add(positionValue);
                continue;
            }

            if (diag[i].line1.get(missingLineIndex).equals(positionValue)) {
                lostLine.add(diag[i].line2.get(missingLineIndex));
            } else {
                lostLine.add(diag[i].line1.get(missingLineIndex));
            }
        }

        return lostLine.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    private DiagElement[] prepareDiag(int squareSize, List<List<Integer>> lines) {
        DiagElement[] diag = new DiagElement[squareSize];
        for (int i = 0; i < diag.length; i++) {
            int value = Integer.MAX_VALUE;

            int first = -1, second = -1;
            for (int j = 0; j < lines.size(); j++) {
                Integer lineValue = lines.get(j).get(i);
                if (lineValue < value) {
                    value = lineValue;
                    first = j;
                    second = -1;
                    continue;
                }
                if (lineValue == value) {
                    second = j;
                }
            }
            diag[i] = new DiagElement(
                    value,
                    lines.get(first),
                    second == -1 ? null : lines.get(second)
            );
            lines.remove(first);
            if (second != -1) {
                lines.remove(second - 1);
            }
        }
        return diag;
    }


    private class DiagElement {
        int value;
        List<Integer> line1;
        List<Integer> line2;

        public DiagElement(int value, List<Integer> line1, List<Integer> line2) {
            this.value = value;
            this.line1 = line1;
            this.line2 = line2;
        }
    }
}
