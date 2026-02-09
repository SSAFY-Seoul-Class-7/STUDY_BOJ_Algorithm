from collections import deque

n,m,v=map(int,input().split())

arr=[list(map(int,input().split())) for _ in range(m)]

link=[[] for _ in range(n+1)]
for start,end in arr:
    link[start].append(end)
    link[end].append(start)

visited_dfs=[False]*(n+1)

def dfs(node):
    for i in sorted(link[node]):
        if visited_dfs[i]==False:
            visited_dfs[i]=True
            print(i,end=' ')
            dfs(i)
visited_dfs[v]=True
print(v,end=' ')
dfs(v)
print()


visited=[False]*(n+1)
q=deque()

q.append(v)
visited[v]=True
print(v,end=' ')
while q:
    node=q.popleft()
    for i in sorted(link[node]):
        if not visited[i]:
            q.append(i)
            visited[i]=True
            print(i,end=' ')

