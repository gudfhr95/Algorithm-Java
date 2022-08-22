import java.util.*;

class Solution {

  public int solution(int n, int[] cores) {
    if (n <= cores.length) {
      return n;
    }

    int maxCore = Arrays.stream(cores).max().getAsInt();

    int remaining = n - cores.length;
    int left = 1;
    int right = remaining * maxCore / cores.length;

    while (left < right) {
      int mid = (left + right) / 2;

      int finished = 0;
      for (int core : cores) {
        finished += mid / core;
      }

      if (finished >= remaining) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    for (int core : cores) {
      remaining -= (right - 1) / core;
    }

    for (int i = 0; i < cores.length; i++) {
      if (right % cores[i] == 0) {
        remaining -= 1;

        if (remaining == 0) {
          return i + 1;
        }
      }
    }

    return -1;
  }
}