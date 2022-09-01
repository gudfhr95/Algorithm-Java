class Solution {

  public int goodNodes(TreeNode root) {
    return getGoodNodes(root, root.val);
  }

  public int getGoodNodes(TreeNode node, int max) {
    int result = 0;
    if (node.val >= max) {
      result += 1;
    }

    if (node.left != null) {
      result += getGoodNodes(node.left, Math.max(max, node.val));
    }
    if (node.right != null) {
      result += getGoodNodes(node.right, Math.max(max, node.val));
    }

    return result;
  }
}