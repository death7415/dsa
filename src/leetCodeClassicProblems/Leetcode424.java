package leetCodeClassicProblems;

public class Leetcode424 {
    public static int characterReplacement(String s, int k) {
        int left = 0, right = 0, maxFreq = 0, ans = 0;
        int[] freqArr = new int[26];
        while (right < s.length()){
            char ch = s.charAt(right);
            freqArr[ch- 'A']++;
            maxFreq = Math.max(maxFreq, freqArr[ch - 'A']);
            if ((right - left + 1 ) - maxFreq > k){
                freqArr[s.charAt(left) - 'A']--;
                left++;
            }
            right ++;
            ans = Math.max(right - left , ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(
                characterReplacement("AABABBA", 1)
        );
    }
}
