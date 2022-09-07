package leetcode.hard;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class _1206_EligibleCompanyNames {

    public long distinctNames1(String[] ideas) {
        if (ideas == null || ideas.length < 2) {
            return 0;
        }

        int len = ideas.length;
        System.out.printf("len: %s\n", len);

        Set<Character> row = new TreeSet<>();
        Set<String> col = new TreeSet<>();
        /**
         *
         */
        for (String idea : ideas) {
            char p = idea.charAt(0);
            String s = idea.substring(1);
            row.add(p);
            if (s.length() > 0) {
                col.add(s);
            }
        }

        int m = row.size() + 1;
        int n = col.size() + 1;
        int[][] B = new int[m][n];

        List<Character> prefix = new LinkedList<>(row);
        List<String> suffix = new LinkedList<>(col);

        for (String idea : ideas) {
            char p = idea.charAt(0);
            String s = idea.substring(1);
            int indx_row = Collections.binarySearch(prefix, p);
            int indx_col = s.length() > 0 ? Collections.binarySearch(suffix, s) : -1;
            B[indx_row + 1][indx_col + 1] = 1;
        }
        for (int i = 0; i < m; i++) {
            System.out.printf("B: %s\n", Arrays.toString(B[i]));
        }


        List<Integer> rowCount = IntStream.range(0, m).map(i -> Arrays.stream(B[i]).sum()).boxed().collect(Collectors.toList());
        List<Integer> colCount = IntStream.range(0, n).
                map(i -> IntStream.range(0,m).map(j -> B[j][i]).sum()).boxed().collect(Collectors.toList());

        System.out.printf("row: %s, col: %s\n", rowCount, colCount);
        long total = 0;
        for (int i = 0; i < m; i++) {
            total += len;
            for (int j = 0; j < n; j++) {
                if (B[i][j] == 1) {
                    for (int x = 0; x < m; x++) {
                        if (B[x][j] == 1) {
                            total -= rowCount.get(x);
                        }
                    }
                    for (int y = 0; y < n; y++) {
                        if (B[i][y] == 1 && y != j) {
                            total -= colCount.get(y);
                            total += 1;
                        }
                    }
                }
            }
        }
        return total;

    }
}
