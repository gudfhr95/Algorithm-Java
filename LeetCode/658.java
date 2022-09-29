class Solution {

  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      list.add(arr[i]);
    }

    Collections.sort(list, (e1, e2) -> {
      int abs1 = Math.abs(e1 - x);
      int abs2 = Math.abs(e2 - x);

      if (abs1 == abs2) {
        return e1 - e2;
      }

      return abs1 - abs2;
    });

    List<Integer> result = list.subList(0, k);
    Collections.sort(result);

    return result;
  }
}