class Solution {

  public List<Double> averageOfLevels(TreeNode root) {
    List<Double> result = new ArrayList<>();

    Queue<TreeNode> q = new LinkedList<>();

    q.add(root);

    while (!q.isEmpty()) {
      long sum = 0;
      int size = q.size();

      for (int i = 0; i < size; i++) {
        TreeNode cur = q.poll();

        sum += cur.val;
        if (cur.left != null) {
          q.add(cur.left);
        }
        if (cur.right != null) {
          q.add(cur.right);
        }
      }

      result.add(sum / (double) size);
    }

    return result;
  }
}