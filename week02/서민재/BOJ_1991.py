# 1991 트리 순회
# DFS

from collections import defaultdict

N = int(input())
graph = defaultdict(list)

for _ in range(N):
    node, left, right = input().split()
    graph[node].append(left)
    graph[node].append(right)

def pre(node):
    if node == ".":
        return 
    
    left, right = graph[node]
    print(node, end="")
    pre(left)
    pre(right)

def in_order(node):
    if node == ".":
        return 
    
    left, right = graph[node]
    in_order(left)
    print(node, end="")
    in_order(right)

def post(node):
    if node == ".":
        return 
    
    left, right = graph[node]
    post(left)
    post(right)
    print(node, end="")

pre("A")
print()
in_order("A")
print()
post("A")