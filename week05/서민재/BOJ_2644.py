# 촌수계산
# BFS

from collections import defaultdict, deque

n = int(input())
src, dst = map(int, input().split())

m = int(input())
family = defaultdict(list)
for _ in range(m):
    x, y = map(int, input().split())
    family[x].append(y)
    family[y].append(x)

def bfs(s):
    q = deque()
    q.append((s, 0))
    visited = [False] * (n+1)

    while q:
        now, cnt = q.popleft()

        if now == dst:
            return cnt
            
        for nxt in family[now]:
            if not visited[nxt]:
                visited[nxt] =True
                q.append((nxt, cnt+1))
    return -1

print(bfs(src))
