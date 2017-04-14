package ru.altazar.contest2017.qualification;

import ru.altazar.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class BathroomStalls extends Problem {

    @Override
    protected String solveCase(BufferedReader reader) throws IOException {

        String[] split = reader.readLine().split(" ");
        long stalls = Long.valueOf(split[0]);
        long peoples = Long.valueOf(split[1]);

        Map<Long, long[]> intervals = new TreeMap<>(Comparator.reverseOrder());
        intervals.put(stalls, new long[]{1L});

        long min = 0, max = 0;
        while (peoples > 0) {
            Map.Entry<Long, long[]> maxInterval = intervals.entrySet().iterator().next();
            Long intervalSize = maxInterval.getKey();
            if (intervalSize > 1) {
                boolean odd = (intervalSize % 2 == 0);
                long left = intervalSize / 2 - ((odd) ? 1 : 0);
                count(left, intervals);

                long right = intervalSize / 2;
                count(right, intervals);

                min = Math.min(left, right);
                max = Math.max(left, right);
            } else {
                min = max = 0;
            }
            if (--maxInterval.getValue()[0] == 0) {
                intervals.remove(intervalSize);
            }
            peoples--;
        }

        return String.format("%d %d", max, min);
    }

    private void count(long interval, Map<Long, long[]> intervals) {
        if (interval == 0) {
            return;
        }
        intervals.computeIfAbsent(interval, k -> new long[]{0})[0]++;
    }


}
