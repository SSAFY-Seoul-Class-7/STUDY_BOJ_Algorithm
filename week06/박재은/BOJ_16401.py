# 과자 나눠주기
import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

m, n = map(int, input().split())
l = list(map(int, input().split()))
s = 1
e = max(l)
ans = 0

while s <= e:
    mid = (s + e) // 2
    cnt = 0
    for i in range(n):
        cnt += l[i] // mid
    if cnt >= m:
        ans = mid
        s = mid + 1
    else:
        e = mid - 1
print(ans)