class Solution {

  public List<String> topKFrequent(String[] words, int k) {
    Map<String, Integer> map = new HashMap<>();
    for (String word : words) {
      map.put(word, map.getOrDefault(word, 0) + 1);
    }

    List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
    Collections.sort(list, (e1, e2) -> {
      if (e1.getValue() == e2.getValue()) {
        return e1.getKey().compareTo(e2.getKey());
      }

      return e2.getValue() - e1.getValue();
    });

    List<String> result = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      result.add(list.get(i).getKey());
    }

    return result;
  }
}