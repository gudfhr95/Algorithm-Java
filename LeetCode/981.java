class TimeMap {

  HashMap<String, TreeMap<Integer, String>> map;

  public TimeMap() {
    map = new HashMap<>();
  }

  public void set(String key, String value, int timestamp) {
    TreeMap<Integer, String> entry = map.getOrDefault(key, new TreeMap<>());
    entry.put(timestamp, value);

    map.put(key, entry);
  }

  public String get(String key, int timestamp) {
    if (!map.containsKey(key)) {
      return "";
    }

    TreeMap<Integer, String> entry = map.get(key);
    Integer k = entry.floorKey(timestamp);

    if (k == null) {
      return "";
    }

    return entry.get(k);
  }
}