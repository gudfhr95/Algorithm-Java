class Solution {

  public List<List<Integer>> levelOrder(Node root) {
    List<List<Integer>> result = new ArrayList<>();

    if (root == null) {
      return result;
    }

    Queue<Node> currentQueue = new LinkedList<>();
    Queue<Node> nextQueue = new LinkedList<>();

    currentQueue.add(root);

    while (!currentQueue.isEmpty()) {
      List<Integer> list = new ArrayList<>();

      while (!currentQueue.isEmpty()) {
        Node cur = currentQueue.poll();

        list.add(cur.val);

        for (Node child : cur.children) {
          nextQueue.add(child);
        }
      }

      result.add(list);

      currentQueue = nextQueue;
      nextQueue = new LinkedList<>();
    }

    return result;
  }
}