# 정수 a를 k로 만들기
# BFS

from collections import deque

A, K = map(int, input().split())

def bfs(x):
    q = deque()
    q.append((x, 0))

    visited = [False] * (K+1)
    
    while q:
        now, cnt = q.popleft()

        if now == K:
            print(cnt)
            break

        if now > K:
            continue

        if not visited[now]:
            visited[now] = True
            q.append((now*2, cnt+1))
            q.append((now+1, cnt+1))

bfs(A)
