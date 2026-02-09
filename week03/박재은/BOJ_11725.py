import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

from collections import deque

n = int(input())
adj = [[] * (n+1) for _ in range(n+1)]
for _ in range(n-1):
    a, b = map(int, input().split())
    adj[a].append(b)
    adj[b].append(a)

root = [0] * (n+1)
visited = [False] * (n+1)
visited[1] = True
Q = deque()
Q.append(1)
while Q:
    cur = Q.popleft()
    for nxt in adj[cur]:
        if not visited[nxt]:
            root[nxt] = cur
            visited[nxt] = True
            Q.append(nxt)
for i in range(2, n+1):
    print(root[i])
