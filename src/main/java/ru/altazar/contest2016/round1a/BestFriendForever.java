package ru.altazar.contest2016.round1a;

import ru.altazar.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class BestFriendForever extends Problem {

    @Override
    protected String solveCase(BufferedReader reader) throws IOException {
        int classSize = Integer.parseInt(reader.readLine());

        String[] split = reader.readLine().split(" ");
        if (classSize != split.length) {
            throw new RuntimeException();
        }

        Map<Integer, Kid> kids = new HashMap<>();
        for (int id = 0; id < split.length; id++) {
            int friendId = Integer.parseInt(split[id]) - 1;

            Kid kid = kids.computeIfAbsent(id, Kid::new);
            kid.friend = friendId;

            Kid friend = kids.computeIfAbsent(friendId, Kid::new);
            friend.isFriendOf.add(id);
        }

        Set<HappyPair> happyPairs = findHappyPairs(kids);
        Set<Integer> reachable = fillLoopsSizes(happyPairs, kids);


        int totalHappyPairLoop = happyPairs.stream()
                .map(p -> p.loopSize)
                .reduce((u, v) -> u + v)
                .orElse(0);

        kids.keySet().removeAll(reachable);
        int maxNonHappyPairLoop = maxNonHappyPairLoop(kids);
        return String.valueOf(Math.max(totalHappyPairLoop, maxNonHappyPairLoop));
    }

    private int maxNonHappyPairLoop(Map<Integer, Kid> kids) {
        int max = 0;
        while (!kids.isEmpty()) {
            Integer kidId = kids.keySet().iterator().next();
            Kid kid;

            List<Kid> loopWithTail = new ArrayList<>();
            while ((kid = kids.remove(kidId)) != null) {
                loopWithTail.add(kid);
                kidId = kid.friend;
            }
            for (Iterator<Kid> it = loopWithTail.iterator(); it.hasNext(); ) {
                if (it.next().id == kidId) {
                    if (loopWithTail.size() > max) {
                        max = loopWithTail.size();
                    }
                    break;
                }
                it.remove();
            }
        }

        return max;
    }

    private Set<Integer> fillLoopsSizes(Set<HappyPair> happyPairs, Map<Integer, Kid> kids) {
        Set<Integer> reachable = new HashSet<>();
        for (HappyPair happyPair : happyPairs) {
            reachable.addAll(fillLoopSize(happyPair, kids));
        }
        return reachable;
    }

    private Set<Integer> fillLoopSize(HappyPair happyPair, Map<Integer, Kid> kids) {
        Set<Integer> reachable = new HashSet<>();
        happyPair.loopSize
                += maxFriendOfSequence(happyPair.first, kids, reachable)
                + maxFriendOfSequence(happyPair.second, kids, reachable);
        return reachable;
    }

    private int maxFriendOfSequence(Kid kid, Map<Integer, Kid> kids, Set<Integer> reachable) {
        reachable.add(kid.id);
        int max = 0;
        for (Integer friendOfId : kid.isFriendOf) {
            Kid friendOf = kids.get(friendOfId);
            if (friendOf.isFriendOf.contains(kid.id)) {
                continue;
            }
            int sequenceLength = maxFriendOfSequence(friendOf, kids, reachable);
            if (sequenceLength > max) {
                max = sequenceLength;
            }
        }
        return max + 1;
    }

    private Set<HappyPair> findHappyPairs(Map<Integer, Kid> kids) {
        Set<HappyPair> happyPairs = new HashSet<>();
        for (Kid kid : kids.values()) {
            Kid friend = kids.get(kid.friend);
            if (friend.friend == kid.id) {
                HappyPair happyPair = new HappyPair(kid, friend);
                if (!happyPairs.contains(happyPair)) {
                    happyPairs.add(happyPair);
                }
            }
        }
        return happyPairs;
    }

    private static class HappyPair {
        final Kid first;
        final Kid second;

        private int loopSize;

        private HappyPair(Kid first, Kid second) {
            if (first.id < second.id) {
                this.first = first;
                this.second = second;
            } else {
                this.first = second;
                this.second = first;
            }
            this.loopSize = 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            HappyPair happyPair = (HappyPair) o;

            if (!first.equals(happyPair.first)) return false;
            return second.equals(happyPair.second);
        }

        @Override
        public int hashCode() {
            int result = first.hashCode();
            result = 31 * result + second.hashCode();
            return result;
        }
    }

    private static class Kid {
        int id;
        int friend;
        Set<Integer> isFriendOf;

        Kid(int id) {
            this.id = id;
            this.isFriendOf = new HashSet<>();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Kid kid = (Kid) o;

            return id == kid.id;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }
}
