package com.thare.algorithm.archive.leetcode._461_hamming_distance;

public class Solution {

    public int hammingDistance(int x, int y) {
        int distance = 0;
        int z = x ^ y;
        while(z > 0) {
            z &= z - 1;
            distance++;
        }
        return distance;
    }

}
