package ru.altazar.contest2018.qualification.cgopher;

import java.io.*;

public class Solution {


    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            Integer cases = Integer.valueOf(reader.readLine());
            for (int i = 0; i < cases; i++) {
                solveCase(reader, writer);
            }

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static void solveCase(BufferedReader reader, BufferedWriter writer) throws IOException {
        int a = Integer.valueOf(reader.readLine());
        boolean[][] orchard;
        switch (a) {
            case 20:
                orchard = new boolean[4][5];
                break;
            case 200:
                orchard = new boolean[10][20];
                break;
            default:
                throw new IllegalArgumentException("Unsupported a " + a);
        }

        while (true) {
            int[] coordinates = deployGopher(orchard);
            writer.write(String.format("%d %d\n", coordinates[0] + 1, coordinates[1] + 1));
            writer.flush();

            String input = reader.readLine();
            if ("-1 -1".equals(input) || "0 0".equals(input)) {
                break;
            }

            String[] split = input.split(" ");
            Integer x = Integer.valueOf(split[0]) - 1;
            Integer y = Integer.valueOf(split[1]) - 1;
            orchard[x][y] = true;
        }
    }

    private static int[] deployGopher(boolean[][] orchard) {

        int[] result = new int[]{1, 1};
        int minPrepared = 9;

        for (int x = 1; x < orchard.length - 1; x++) {
            for (int y = 1; y < orchard[x].length - 1; y++) {
                int prepared = prepared(orchard, x, y);
                if (prepared < minPrepared) {
                    minPrepared = prepared;
                    result[0] = x;
                    result[1] = y;
                }
                if (minPrepared == 0) {
                    return result;
                }
            }
        }
        return result;
    }


    private static int prepared(boolean[][] orchard, int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (orchard[x + i][y + j]) {
                    count++;
                }
            }

        }
        return count;
    }
}
