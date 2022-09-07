class Solution {

  public String tree2str(TreeNode root) {
    if (root == null) {
      return null;
    }

    String result = Integer.toString(root.val);

    String left = tree2str(root.left);
    String right = tree2str(root.right);

    if (left == null && right != null) {
      result += "()" + "(" + right + ")";
    } else if (left != null && right == null) {
      result += "(" + left + ")";
    } else if (left != null && right != null) {
      result += "(" + left + ")" + "(" + right + ")";
    }

    return result;
  }
}