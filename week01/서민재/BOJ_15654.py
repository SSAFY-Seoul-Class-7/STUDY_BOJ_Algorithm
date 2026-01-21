# 15654 N과 M (5)
# 순열, 정렬

def backtracking(path):
    if len(path) == M:
        print(*path)
        return 
    
    for i in range(N):
        if not visited[i]:
            visited[i] = True
            path.append(num[i])
            backtracking(path)
            path.pop()
            visited[i] = False

N, M = map(int, input().split())
num = list(map(int, input().split()))

num.sort()

visited = [False] * (N)
backtracking([])