class Solution {

  List<List<Integer>> result = new ArrayList<>();

  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    if (root == null) {
      return result;
    }

    path(root, new ArrayList<>(), targetSum);

    return result;
  }

  public void path(TreeNode node, List<Integer> nodes, int targetSum) {
    nodes.add(node.val);

    if (node.left == null && node.right == null) {
      int sum = 0;
      for (int n : nodes) {
        sum += n;
      }

      if (sum == targetSum) {
        result.add(nodes);
      }

      return;
    }

    if (node.left != null) {
      path(node.left, new ArrayList<>(nodes), targetSum);
    }

    if (node.right != null) {
      path(node.right, new ArrayList<>(nodes), targetSum);
    }
  }
}