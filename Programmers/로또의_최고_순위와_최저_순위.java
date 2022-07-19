import java.util.*;

class Solution {

  public int[] solution(int[] lottos, int[] win_nums) {
    boolean[] check = new boolean[6];

    Arrays.sort(lottos);
    Arrays.sort(win_nums);

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        if (lottos[i] == win_nums[j]) {
          check[i] = true;
        }
      }
    }

    int count = 0;
    int zeros = 0;
    for (int i = 0; i < 6; i++) {
      if (check[i]) {
        count += 1;
      }

      if (lottos[i] == 0) {
        zeros += 1;
      }
    }

    return new int[]{Math.min(6, 7 - (count + zeros)), Math.min(6, 7 - count)};
  }
}