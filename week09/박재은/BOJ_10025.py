import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

n, k = map(int, input().split())
ice = [0] * 1000001
ans = 0

for _ in range(n):
    g, x = map(int, input().split())
    ice[x] = g

left = 0
right = 2 * k + 1
ans = sum(ice[:right])
total = ans

while right < 1000001:
    total -= ice[left]
    total += ice[right]
    left += 1
    right += 1

    ans = max(ans, total)
print(ans)