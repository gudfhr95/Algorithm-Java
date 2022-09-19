class Solution {

  public List<List<String>> findDuplicate(String[] paths) {
    Map<String, List<String>> map = new HashMap<>();

    for (String path : paths) {
      String[] splited = path.split(" ");

      for (int i = 1; i < splited.length; i++) {
        String[] f = getFilenameAndContent(splited[i]);

        if (!map.containsKey(f[1])) {
          map.put(f[1], new ArrayList<>());
        }

        map.get(f[1]).add(splited[0] + "/" + f[0]);
      }
    }

    List<List<String>> result = new ArrayList<>();
    for (String key : map.keySet()) {
      if (map.get(key).size() > 1) {
        result.add(map.get(key));
      }
    }

    return result;
  }

  public String[] getFilenameAndContent(String file) {
    String[] result = file.split("\\(");
    result[1] = result[1].substring(0, result[1].length() - 1);
    return result;
  }
}