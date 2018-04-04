package ru.altazar;

import ru.altazar.contest2016.round1a.BestFriendForever;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ApplicationRunner {

    private static final Map<String, Problem> PROBLEMS = new HashMap<>();

    static {
        register(new BestFriendForever());
    }

    private static void register(Problem problem) {
        PROBLEMS.put(problem.getClass().getSimpleName().toLowerCase(), problem);
    }

    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("Please specify problem to solve as first argument. Known problems are: " + PROBLEMS.keySet());
            return;
        }

        Problem problem = PROBLEMS.get(args[0]);
        if (problem == null) {
            System.err.println("Unknown problem " + args[0] + ". Known problems are: " + PROBLEMS.keySet());
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            problem.solve(reader, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}