class Solution {

  public int[] findOriginalArray(int[] changed) {
    if (changed.length % 2 == 1) {
      return new int[0];
    }

    Arrays.sort(changed);

    Map<Integer, Integer> map = new TreeMap<>();

    for (int e : changed) {
      map.put(e, map.getOrDefault(e, 0) + 1);
    }

    int[] result = new int[changed.length / 2];

    int index = 0;
    for (int key : changed) {
      int doubleKey = key * 2;

      if (map.get(key) <= 0) {
        continue;
      }

      if (map.getOrDefault(doubleKey, 0) <= 0) {
        return new int[0];
      }

      map.put(doubleKey, map.get(doubleKey) - 1);
      map.put(key, map.get(key) - 1);

      result[index++] = key;
    }

    return result;
  }
}