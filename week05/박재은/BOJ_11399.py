import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

n = int(input())
time = list(map(int, input().split()))
time.sort()
prefix_sum = []
tmp = 0
for t in time:
    tmp += t
    prefix_sum.append(tmp)
print(sum(prefix_sum))
