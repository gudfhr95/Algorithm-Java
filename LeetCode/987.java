class Solution {

  Map<Integer, Map<Integer, List<Integer>>> map = new TreeMap<>();

  public List<List<Integer>> verticalTraversal(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();

    traverse(root, 0, 0);

    for (Map<Integer, List<Integer>> m : map.values()) {
      List<Integer> entry = new ArrayList<>();

      for (List<Integer> l : m.values()) {
        Collections.sort(l);

        entry.addAll(l);
      }

      result.add(entry);
    }

    return result;
  }

  public void traverse(TreeNode node, int x, int y) {
    if (node.left != null) {
      traverse(node.left, x - 1, y + 1);
    }

    if (node.right != null) {
      traverse(node.right, x + 1, y + 1);
    }

    if (!map.containsKey(x)) {
      map.put(x, new TreeMap<>());
    }

    if (!map.get(x).containsKey(y)) {
      map.get(x).put(y, new ArrayList<>());
    }

    map.get(x).get(y).add(node.val);
  }
}