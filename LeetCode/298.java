/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode() {} TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left,
 * TreeNode right) { this.val = val; this.left = left; this.right = right; } }
 */
class Solution {

  public int longestConsecutive(TreeNode root) {
    return getLongestConsecutive(root, 1);
  }

  public int getLongestConsecutive(TreeNode node, int n) {
    int result = n;
    if (node.left != null) {
      if (node.left.val == node.val + 1) {
        result = Math.max(result, getLongestConsecutive(node.left, n + 1));
      } else {
        result = Math.max(result, getLongestConsecutive(node.left, 1));
      }
    }

    if (node.right != null) {
      if (node.right.val == node.val + 1) {
        result = Math.max(result, getLongestConsecutive(node.right, n + 1));
      } else {
        result = Math.max(result, getLongestConsecutive(node.right, 1));
      }
    }

    return result;
  }
}