import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline
def DFS(num, cnt):
    global ans
    if num > b:
        return
    if num == b:
        print(cnt)
        exit(0)
    DFS(int(str(num) + "1"), cnt+1)
    DFS(num * 2, cnt+1)

a, b = map(int, input().split())
DFS(a, 1)
print(-1)