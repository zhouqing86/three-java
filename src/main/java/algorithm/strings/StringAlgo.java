package algorithm.strings;

import java.util.HashMap;
import java.util.HashSet;

public class StringAlgo {

    public static int lengthOfLongestSubstring(String s) {
        int nmax=0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int j=0,i=0; j<s.length(); j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            nmax = Math.max(nmax, j - i + 1);
            map.put(s.charAt(j), j+1);
        }
        return nmax;
    }

}
