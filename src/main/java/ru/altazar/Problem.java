package ru.altazar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public abstract class Problem {

    public void solve(BufferedReader reader, BufferedWriter writer) throws IOException {
        int cases = Integer.parseInt(reader.readLine());
        for (int i = 1; i <= cases; i++) {
            String result = solveCase(reader);
            writer.write(String.format("Case #%d: %s\n", i, result));
        }
    }

    protected abstract String solveCase(BufferedReader reader) throws IOException;
}
