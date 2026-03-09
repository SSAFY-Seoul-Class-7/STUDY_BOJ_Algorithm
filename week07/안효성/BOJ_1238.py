import sys, heapq
input=sys.stdin.readline
N,M,X=map(int,input().split())
roads=[[] for _ in range(N+1)] #(시작,끝,비용)
for _ in range(M):
    a,b,c=map(int,input().split())
    roads[a].append((b,c)) #(끝,비용)
    
     
pq=[]
MAX_SIZE=sys.maxsize
dist=[MAX_SIZE]*(N+1) #x에서 각마을용

# N이 1000이하라서 플루이드 워셜은 안됨. 다익스트라로 풀어야됨
# X-각마을 다익스트라 한번 돌리고
# 각마을마다 다익돌리다가 마을-X 최단거리가 나오면 바로 break 하는 로직으로 하면 좋을듯

heapq.heappush(pq,(0,X)) #cost, 노드 순
dist[X]=0
while pq:
    cost,node=heapq.heappop(pq)
    if dist[node]!=cost:
        continue
    for next_node,next_cost in roads[node]:
        new_dist=cost+next_cost
        if dist[next_node]>new_dist:
            dist[next_node]=new_dist
            heapq.heappush(pq,(new_dist,next_node))       
max_dist=-MAX_SIZE
for i in range(1,N+1): # 각 노드마다 다익 돌리기
    if i==X:
        continue
    distance=[MAX_SIZE]*(N+1)
    pq.clear()
    heapq.heappush(pq,(0,i)) #cost, 노드 순
    distance[i]=0
    while pq:
        cost,node=heapq.heappop(pq)
        if node==X:
            #답갱신 로직. max_dist보다 distance[X]+dist[i]가 더 크다면 갱신
            max_dist=max(max_dist,distance[X]+dist[i])
            break
        
        if distance[node]!=cost:
            continue
        for next_node,next_cost in roads[node]:
            new_dist=cost+next_cost
            if distance[next_node]>new_dist:
                distance[next_node]=new_dist
                heapq.heappush(pq,(new_dist,next_node))

print(max_dist)
                  
