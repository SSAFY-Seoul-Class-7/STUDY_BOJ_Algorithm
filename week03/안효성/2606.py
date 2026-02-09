from collections import deque

n=int(input())
edge_num=int(input())
link=[[] for _ in range(n+1)]
for i in range(edge_num):
    a,b=map(int,input().split())
    link[a].append(b)
    link[b].append(a)
visited=[0 for _ in range(n+1)]

#1번 빼고 걸리는 컴터 수

q=deque()
q.append(1)
visited[1]=1

ans=0
while q:
    node=q.popleft()
    for i in link[node]:
        if visited[i]==0:
            q.append(i)
            ans+=1
            visited[i]=1

print(ans)






