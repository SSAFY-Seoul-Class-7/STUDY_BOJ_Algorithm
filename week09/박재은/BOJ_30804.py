import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

from collections import defaultdict
n = int(input())
s = list(map(int, input().split()))
fruit = defaultdict(int)
l, r = 0, 0
ans = 0

while r < n:
    fruit[s[r]] += 1

    while len(fruit) > 2:
        fruit[s[l]] -= 1
        if fruit[s[l]] == 0:
            del fruit[s[l]]
        l += 1
    ans = max(ans, r - l + 1)
    r += 1
print(ans)