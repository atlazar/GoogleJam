package ru.altazar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public interface Problem {
    void solve(int cases, BufferedReader reader, BufferedWriter writer) throws IOException;
}
