import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

n, m = map(int, input().split())
a = []
for _ in range(n):
    a.append(int(input()))
a.sort()

ans = float('inf')
l, r = 0, 0
while r < n:
    diff = a[r] - a[l]
    if diff == m:
        ans = m
        break
    elif diff > m:
        ans = min(ans, diff)
        l += 1
    else:
        r += 1
print(ans)
