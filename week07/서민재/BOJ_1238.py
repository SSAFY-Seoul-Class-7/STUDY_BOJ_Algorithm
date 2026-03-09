# 파티
# 다익스트라

from collections import defaultdict
import heapq

INF = int(1e9)

N, M, X = map(int, input().split())
graph = defaultdict(list)

for _ in range(M):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))

def dijkstra(start):
    q = []
    heapq.heappush(q, (start, 0))
    distance = [INF] * (N+1)
    distance[start] = 0

    while q:
        now, time = heapq.heappop(q)

        if distance[now] < time:
            continue
        
        for v, w in graph[now]:
            cost = w + time
            if distance[v] > cost:
                distance[v] = cost
                heapq.heappush(q, (v, cost))
    
    return distance

answer = 0

x_dist = dijkstra(X)

for i in range(1, N+1):
    if i == X:
        continue
    i_dist = dijkstra(i)
    answer = max(answer, i_dist[X] + x_dist[i])

print(answer)