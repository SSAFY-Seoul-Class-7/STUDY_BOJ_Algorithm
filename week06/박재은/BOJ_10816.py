# 숫자 카드 2
import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

n = int(input())
nums = list(map(int, input().split()))
m = int(input())
target = list(map(int, input().split()))
nums.sort()
dic = dict()

for num in nums:
    if num in dic:
        dic[num] += 1
    else:
        dic[num] = 1

for t in target:
    if t in dic:
        print(dic[t], end = ' ')
    else:
        print(0, end = ' ')