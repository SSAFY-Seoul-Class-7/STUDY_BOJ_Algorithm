import sys
from collections import deque
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

n = int(input())
cnt = int(input())
adj = [[] for _ in range(n+1)]
ans = 0

for _ in range(cnt):
    a, b = map(int, input().split())
    adj[a].append(b)
    adj[b].append(a)

visited = [False] * (n+1)
visited[1] = True
Q = deque()
Q.append(1)
while Q:
    cur = Q.popleft()
    for nxt in adj[cur]:
        if not visited[nxt]:
            ans += 1
            visited[nxt] = True
            Q.append(nxt)
print(ans)