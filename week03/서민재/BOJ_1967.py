# 1967 트리의 지름
# DFS

from collections import defaultdict
import sys
sys.setrecursionlimit(100000)

n = int(input())
graph = defaultdict(list)

for _ in range(n-1):
    x, y, z = map(int, input().split())
    graph[x].append([y, z])
    graph[y].append([x, z])

def dfs(node, parent):
    global answer
    distances = []

    for child, w in graph[node]:
        if child == parent:
            continue
        child_max = dfs(child, node)
        distances.append(child_max+w)
    
    if len(distances) >= 2:
        distances.sort(reverse=True)
        answer = max(answer, distances[0] + distances[1])
    elif len(distances) == 1:
        answer = max(answer, distances[0])
    
    return max(distances) if distances else 0

answer = 0
dfs(1, 0)
print(answer)