import sys
input=sys.stdin.readline

N=int(input())
K=int(input())

arr=list(map(int,input().split()))

arr.sort()

for i in range(len(arr)):
    arr[i]+=1000000

dist=[]

for i in range(1,len(arr)):
    dist.append(arr[i]-arr[i-1])


dist.sort()

for i in range(K-1):
    if dist:
        dist.pop()
print(sum(dist))
