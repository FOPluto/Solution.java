public class Solution {
    public int twoNumberSum(int v1, int v2) {
return v1+v2;
    }
public static void main(String[] args) {
    Solution solution = new Solution();
    int a = solution.twoNumberSum(1, 1);
    if (a != 2) {
        System.out.println("1");
        return;
    }
    int b = solution.twoNumberSum(1, 3);
    if (b != 4) {
        System.out.println("2");
        return;
    }
    System.out.println("0");
}}