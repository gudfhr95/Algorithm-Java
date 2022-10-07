class MyCalendarThree {

  Map<Integer, Integer> map;

  public MyCalendarThree() {
    map = new TreeMap<>();
  }

  public int book(int start, int end) {
    map.put(start, map.getOrDefault(start, 0) + 1);
    map.put(end, map.getOrDefault(end, 0) - 1);

    int result = 0;
    int count = 0;
    for (int key : map.keySet()) {
      count += map.get(key);

      result = Math.max(result, count);
    }

    return result;
  }
}