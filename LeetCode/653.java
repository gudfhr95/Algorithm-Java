class Solution {

  List<Integer> list = new ArrayList<>();

  public boolean findTarget(TreeNode root, int k) {
    dfs(root);

    for (int i = 0; i < list.size(); i++) {
      for (int j = i + 1; j < list.size(); j++) {
        if (list.get(i) + list.get(j) == k) {
          return true;
        }
      }
    }

    return false;
  }

  public void dfs(TreeNode node) {
    if (node == null) {
      return;
    }

    list.add(node.val);

    if (node.left != null) {
      dfs(node.left);
    }

    if (node.right != null) {
      dfs(node.right);
    }
  }
}