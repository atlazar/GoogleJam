package ru.altazar.contest2018.qualification.cubicufo;

import java.io.*;

public class Solution {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            new Solution().solve(reader, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void solve(BufferedReader reader, BufferedWriter writer) throws IOException {
        int cases = Integer.parseInt(reader.readLine());
        for (int i = 1; i <= cases; i++) {
            String result = solveCase(reader);
            writer.write(String.format("Case #%d:\n%s", i, result));
        }
    }

    private String solveCase(BufferedReader reader) throws IOException {
        Double square = Double.valueOf(reader.readLine());
        if (square > 1.42) {
            //I don't know how to solve big data set
            return "0.5 0 0\n0 0.5 0\n0 0 0.5\n";
        }

        //Rotate around opposing side center. Let's fix front and back
        final String front = "0.5 0 0";

        double alpha = Math.PI / 4 - Math.acos(square / Math.sqrt(2));
        String top = String.format("0 %f %f", 0.5 * Math.cos(alpha), 0.5 * Math.sin(alpha));

        String right = String.format("0 %f %f", 0.5 * Math.sin(alpha), -0.5 * Math.cos(alpha));

        return String.format("%s\n%s\n%s\n", front, top, right);
    }
}
