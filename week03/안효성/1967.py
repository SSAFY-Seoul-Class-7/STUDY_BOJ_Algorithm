import sys
sys.setrecursionlimit(10**7)
input = sys.stdin.readline

n = int(input())

adj = [[] for _ in range(n + 1)]

for _ in range(n - 1):
    a, b, d = map(int, input().split())
    adj[a].append((b, d))
    adj[b].append((a, d))


def DFS(cur, dist):
    global max_len, last_node
    if dist > max_len:
        max_len = dist
        last_node = cur

    for nxt, w in adj[cur]:
        if visited[nxt] == -1:
            visited[nxt] = dist + w
            DFS(nxt, dist + w)

visited = [-1] * (n + 1)
visited[1] = 0
max_len = 0
last_node = 1

DFS(1, 0)

visited = [-1] * (n + 1)
visited[last_node] = 0
max_len = 0

DFS(last_node, 0)

print(max_len)
