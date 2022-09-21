class Solution {

  public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] % 2 == 0) {
        sum += nums[i];
      }
    }

    int[] result = new int[queries.length];
    int index = 0;
    for (int[] query : queries) {
      if (nums[query[1]] % 2 == 0) {
        if (query[0] % 2 == 0) {
          sum += query[0];
        } else {
          sum -= nums[query[1]];
        }

        nums[query[1]] += query[0];
      } else {
        if (Math.abs(query[0] % 2) == 1) {
          sum += nums[query[1]] + query[0];
        }

        nums[query[1]] += query[0];
      }

      result[index++] = sum;
    }

    return result;
  }
}