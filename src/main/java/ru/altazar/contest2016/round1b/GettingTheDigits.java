package ru.altazar.contest2016.round1b;

import ru.altazar.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GettingTheDigits extends Problem {

    Map<Character, int[]> keyLetters = new HashMap<>();

    private void prepareKeyLetters() {
        keyLetters.put('Z', new int[1]);
        keyLetters.put('O', new int[1]);
        keyLetters.put('W', new int[1]);
        keyLetters.put('H', new int[1]);
        keyLetters.put('U', new int[1]);
        keyLetters.put('F', new int[1]);
        keyLetters.put('X', new int[1]);
        keyLetters.put('S', new int[1]);
        keyLetters.put('G', new int[1]);
        keyLetters.put('N', new int[1]);
    }

    @Override
    protected String solveCase(BufferedReader reader) throws IOException {
        prepareKeyLetters();

        for (char letter : reader.readLine().toCharArray()) {
            if (keyLetters.containsKey(letter)) {
                keyLetters.get(letter)[0]++;
            }
        }

        int[] phone = new int[10];
        phone[0] = keyLetters.get('Z')[0];
        phone[2] = keyLetters.get('W')[0];
        phone[4] = keyLetters.get('U')[0];
        phone[6] = keyLetters.get('X')[0];
        phone[8] = keyLetters.get('G')[0];

        phone[1] = keyLetters.get('O')[0] - (phone[0] + phone[2] + phone[4]);
        phone[3] = keyLetters.get('H')[0] - phone[8];
        phone[5] = keyLetters.get('F')[0] - phone[4];
        phone[7] = keyLetters.get('S')[0] - phone[6];
        phone[9] = (keyLetters.get('N')[0] - (phone[1] + phone[7])) / 2;


        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < phone.length; i++) {
            for (int j = 0; j < phone[i]; j++) {
                builder.append(i);
            }
        }


        return builder.toString();
    }
}
