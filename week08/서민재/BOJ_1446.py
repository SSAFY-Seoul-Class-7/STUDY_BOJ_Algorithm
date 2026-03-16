# 지름길
# 다익스트라

from collections import defaultdict
import heapq

N, D = map(int, input().split())
graph = defaultdict(list)

INF = int(1e9)

for _ in range(N):
    src, dst, length = map(int, input().split())
    if dst <= D:
        graph[src].append((dst, length))

def dijkstra(start):
    global distance

    q = []
    heapq.heappush(q, (0, start))
    distance[start] = 0

    while q:
        total_length, now = heapq.heappop(q)

        if distance[now] < total_length:
            continue

        for v, w in graph[now]:
            cost = total_length + w
            if distance[v] > cost:
                distance[v] = cost
                heapq.heappush(q, (cost, v))
        
        if now + 1 <= D:
            cost = total_length + 1
            if cost < distance[now + 1]:
                distance[now + 1] = cost
                heapq.heappush(q, (cost, now + 1))

distance = [INF] * (D + 1)
dijkstra(0)
print(distance[D])
