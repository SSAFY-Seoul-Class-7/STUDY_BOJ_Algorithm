import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline
import heapq as hq
n = int(input())
pq = []
for _ in range(n):
    hq.heappush(pq, int(input()))
ans = 0
while len(pq) > 1:
    c1 = hq.heappop(pq)
    c2 = hq.heappop(pq)
    hq.heappush(pq, c1 + c2)
    ans += (c1 + c2)
print(ans)