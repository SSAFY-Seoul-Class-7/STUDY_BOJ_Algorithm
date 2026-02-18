import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

l, w, h = map(int, input().split())
n = int(input())
cube = [list(map(int, input().split())) for _ in range(n)]
cube.sort(reverse=True)
volumn = l * w * h
ans = 0

used = 0
for a, b in cube:
    used *= 8
    i = 2 ** a
    cnt = (l // i) * (w // i) * (h // i) - used
    max_cnt = min(cnt, b)
    ans += max_cnt
    used += max_cnt
if used == volumn:
    print(ans)
else:
    print(-1)
