package ru.altazar.contest2016.round1c;

import ru.altazar.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SenateEvacuation extends Problem {

    @Override
    protected String solveCase(BufferedReader reader) throws IOException {
        int nParties = Integer.parseInt(reader.readLine());

        List<Party> parties = new ArrayList<>();
        int peopleInRoom = 0;

        String[] split = reader.readLine().split(" ");
        for (int i = 0; i < split.length; i++) {
            int members = Integer.parseInt(split[i]);
            parties.add(new Party((char) ('A' + i), members));
            peopleInRoom += members;
        }

        List<String> plan = new ArrayList<>();
        while (peopleInRoom > 0) {
            parties.sort(Comparator.<Party>comparingInt(v -> v.members).reversed());

            Party largeParty = parties.get(0);
            Party secondParty = parties.get(1);

            if (!isMajority(secondParty.members, peopleInRoom - 1)) {
                largeParty.members--;
                if (largeParty.isEmpty()) {
                    parties.remove(0);
                }
                plan.add(String.valueOf(largeParty.name));
                peopleInRoom -= 1;
                continue;
            } else {
                largeParty.members--;
                secondParty.members--;

                if (secondParty.isEmpty()) {
                    parties.remove(1);
                }
                if (largeParty.isEmpty()) {
                    parties.remove(0);
                }
                peopleInRoom -= 2;

                plan.add(String.valueOf(largeParty.name) + String.valueOf(secondParty.name));
                continue;
            }
        }

        return plan.stream().reduce((u, v) -> u + " " + v).get();
    }

    private boolean isMajority(double number, double totalNumber) {
        return number / totalNumber > 0.5;
    }

    private static class Party {
        final char name;
        int members;

        private Party(char name, int members) {
            this.name = name;
            this.members = members;
        }

        boolean isEmpty() {
            return members == 0;
        }
    }
}
