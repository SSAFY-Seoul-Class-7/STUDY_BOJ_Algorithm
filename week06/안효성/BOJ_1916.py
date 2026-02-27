import sys, heapq

input=sys.stdin.readline
pq=[]
MAX_SIZE=sys.maxsize
N=int(input())
M=int(input())
arr=[[] for _ in range(N+1)]
dist=[MAX_SIZE]*(N+1)
for _ in range(M):
    a,b,c=map(int,input().split())
    arr[a].append((b,c))

s,e=map(int,input().split())

dist[s]=0
heapq.heappush(pq,(0,s))

while pq:
    cost,curr=heapq.heappop(pq)

    if dist[curr]!=cost:
        continue
    for next,nc in arr[curr]:
        new_cost=cost+nc
        if dist[next]>new_cost:
            dist[next]=new_cost
            heapq.heappush(pq,(new_cost,next))

print(dist[e])
