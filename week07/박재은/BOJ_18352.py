import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

from collections import deque

def BFS(start):
    ans = []
    Q = deque([start])
    visited[start] = True
    dist[start] = 0
    while Q:
        now = Q.popleft()
        for i in graph[now]:
            if not visited[i]:
                visited[i] = True
                Q.append(i)
                dist[i] = dist[now] + 1
                if dist[i] == k:
                    ans.append(i)
    if len(ans) == 0:
        print(-1)
    else:
        ans.sort()
        for i in ans:
            print(i)

n, m, k, x = map(int, input().split())
graph = [[] for _ in range(n+1)]
dist = [0] * (n+1)
visited = [False] * (n+1)

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)

BFS(x)