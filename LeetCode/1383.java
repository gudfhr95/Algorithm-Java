class Solution {

  final long MOD = 1_000_000_007;

  class Engineer {

    int speed, efficiency;

    Engineer(int speed, int efficiency) {
      this.speed = speed;
      this.efficiency = efficiency;
    }
  }

  public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
    List<Engineer> engineers = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      engineers.add(new Engineer(speed[i], efficiency[i]));
    }

    Collections.sort(engineers, (e1, e2) -> {
      if (e1.efficiency == e2.efficiency) {
        return e2.speed - e1.speed;
      }

      return e2.efficiency - e1.efficiency;
    });

    PriorityQueue<Engineer> pq = new PriorityQueue<>((e1, e2) -> e1.speed - e2.speed);

    long totalSpeed = 0;
    long result = Long.MIN_VALUE;

    for (Engineer e : engineers) {
      totalSpeed += e.speed;
      if (pq.size() >= k) {
        totalSpeed -= pq.remove().speed;
      }
      pq.add(e);

      result = Math.max(result, totalSpeed * e.efficiency);
    }

    return (int) (result % MOD);
  }
}