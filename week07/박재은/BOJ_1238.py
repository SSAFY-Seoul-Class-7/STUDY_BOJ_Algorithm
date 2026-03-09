import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

import heapq
import math

def dijkstra(s, e):
    hq = []
    dist = [INF] * (n+1)
    heapq.heappush(hq, (0, s))
    dist[s] = 0

    while hq:
        d, now = heapq.heappop(hq)
        if dist[now] < d:
            continue

        for i in graph[now]:
            cost = d + i[1]
            if cost < dist[i[0]]:
                dist[i[0]] = cost
                heapq.heappush(hq, (cost, i[0]))
    return dist[e]

INF = math.inf
n, m, x = map(int, input().split())
graph = [[] for _ in range(n+1)]

for _ in range(m):
    s, e, t = map(int, input().split())
    graph[s].append((e, t))

ans = 0
for i in range(1, n+1):
    if i == x:
        continue
    ans = max(ans, dijkstra(i, x) + dijkstra(x, i))
print(ans)