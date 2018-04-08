package ru.altazar.contest2018.qualification;

import ru.altazar.Problem;

import java.io.BufferedReader;
import java.io.IOException;

public class UniverseProblem extends Problem {

    @Override
    protected String solveCase(BufferedReader reader) throws IOException {
        String[] split = reader.readLine().split(" ");

        int shield = Integer.valueOf(split[0]);

        int programDamage = 0;
        int currentDamage = 1;
        int shots = 0;
        int[] program = new int[split[1].length()];
        for (int i = 0; i < program.length; i++) {
            switch (split[1].charAt(i)) {
                case 'S':
                    program[i] = 0;
                    programDamage += currentDamage;
                    shots++;
                    break;
                case 'C':
                    program[i] = currentDamage;
                    currentDamage *= 2;
                    break;
            }
        }

        if (programDamage <= shield) {
            return "0";
        }

        if (shots > shield) {
            return "IMPOSSIBLE";
        }

        int swaps = 0;
        while (programDamage > shield) {
            for (int i = program.length - 1; i > 0; i--) {
                if (program[i] == 0 && program[i - 1] > 0) {
                    programDamage -= program[i - 1];
                    program[i] = program[i - 1];
                    program[i - 1] = 0;
                    swaps++;
                    break;
                }
            }
        }

        return String.valueOf(swaps);
    }
}
