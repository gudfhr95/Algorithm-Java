class Solution {

  int result = 0;

  public boolean hasPathSum(TreeNode root, int targetSum) {
    pathSum(root, 0, targetSum);

    return result != 0;
  }

  public void pathSum(TreeNode node, int sum, int targetSum) {
    if (node == null) {
      return;
    }

    sum += node.val;

    if (node.left == null && node.right == null) {
      if (sum == targetSum) {
        result += 1;
      }

      return;
    }

    if (node.left != null) {
      pathSum(node.left, sum, targetSum);
    }

    if (node.right != null) {
      pathSum(node.right, sum, targetSum);
    }
  }
}