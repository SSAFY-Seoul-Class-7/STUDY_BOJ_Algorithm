# 기본 다익문제임

import sys, heapq
input=sys.stdin.readline
MAX_SIZE=sys.maxsize
pq=[]
V,E=map(int,input().split())
K=int(input())
edges=[[] for _ in range(V+1)]
dist=[MAX_SIZE]*(V+1)
for _ in range(E):
    u,v,w=map(int,input().split())
    edges[u].append((v,w)) #v는 정점, w는 가중치
    
dist[K]=0
heapq.heappush(pq,(0,K)) #누적 비용, 정점

while pq:
    cost,node=heapq.heappop(pq)
    
    if cost!=dist[node]:
        continue
    
    for next_node, next_cost in edges[node]:
        new_dist=cost+next_cost
        if dist[next_node]>new_dist:
            dist[next_node]=new_dist
            heapq.heappush(pq,(new_dist,next_node))

for i in range(1,V+1):
    print(dist[i] if dist[i]!=MAX_SIZE else "INF")
