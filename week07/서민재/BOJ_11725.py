# 트리의 부모 찾기

from collections import defaultdict, deque

N = int(input())
graph = defaultdict(list)

for _ in range(N-1):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

def bfs(start):
    q = deque()
    q.append(start)
    visited = [0] * (N+1)

    while q:
        now = q.popleft()

        for nxt in graph[now]:
            if visited[nxt] == 0:
                visited[nxt] = now
                q.append(nxt)
    
    return visited

answer = bfs(1)

for ans in answer[2:]:
    print(ans)