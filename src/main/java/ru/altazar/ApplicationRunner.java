package ru.altazar;

import ru.altazar.contest2016.round1a.BestFriendForever;
import ru.altazar.contest2016.round1a.RankAndFile;
import ru.altazar.contest2016.round1a.TheLastWord;
import ru.altazar.contest2016.round1b.GettingTheDigits;
import ru.altazar.contest2016.round1c.SenateEvacuation;
import ru.altazar.contest2016.round1c.Slides;
import ru.altazar.contest2017.qualification.BathroomStalls;
import ru.altazar.contest2017.qualification.OversizedPancakeFlipper;
import ru.altazar.contest2017.qualification.TidyNumbers;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApplicationRunner {

    private final static Map<String, Problem> PROBLEMS;

    static {
        PROBLEMS = Stream.of(
            //2016
            new BestFriendForever(),
            new RankAndFile(),
            new TheLastWord(),
            new GettingTheDigits(),
            new SenateEvacuation(),
            new Slides(),
            //2017
            new OversizedPancakeFlipper(),
            new TidyNumbers(),
            new BathroomStalls()
        ).collect(Collectors.toMap(
            p -> p.getClass().getSimpleName().toLowerCase(),
            Function.identity()
        ));
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