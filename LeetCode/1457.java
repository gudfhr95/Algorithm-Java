class Solution {

  public int pseudoPalindromicPaths(TreeNode root) {
    int[] count = new int[10];
    return pseudoPalindromicPathsRec(root, count);
  }

  public int pseudoPalindromicPathsRec(TreeNode node, int[] count) {
    int result = 0;

    int[] newCount = Arrays.copyOf(count, count.length);
    newCount[node.val] += 1;

    if (node.left == null && node.right == null) {
      int odd = 0;
      for (int i = 1; i <= 9; i++) {
        if (newCount[i] % 2 == 1) {
          odd += 1;
        }
      }

      if (odd > 1) {
        return 0;
      }

      return 1;
    }

    if (node.left != null) {
      result += pseudoPalindromicPathsRec(node.left, newCount);
    }

    if (node.right != null) {
      result += pseudoPalindromicPathsRec(node.right, newCount);
    }

    return result;
  }
}