class MyCircularQueue {

  Deque<Integer> q;
  int size;

  public MyCircularQueue(int k) {
    this.q = new ArrayDeque<>();
    this.size = k;
  }

  public boolean enQueue(int value) {
    if (this.q.size() == this.size) {
      return false;
    }

    this.q.addLast(value);
    return true;
  }

  public boolean deQueue() {
    if (this.q.size() == 0) {
      return false;
    }

    this.q.removeFirst();
    return true;
  }

  public int Front() {
    if (this.q.size() == 0) {
      return -1;
    }

    return this.q.peekFirst();
  }

  public int Rear() {
    if (this.q.size() == 0) {
      return -1;
    }

    return this.q.peekLast();
  }

  public boolean isEmpty() {
    return this.q.size() == 0;
  }

  public boolean isFull() {
    return this.q.size() == this.size;
  }
}