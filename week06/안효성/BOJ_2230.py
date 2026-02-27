import sys
input=sys.stdin.readline

N,M=map(int,input().split())
arr=[int(input()) for _ in range(N)]

arr.sort()
ans=sys.maxsize
i=0
j=0

while i<N and j<N:
    if abs(arr[i]-arr[j])<M:
        j+=1
    else: #abs(arr[i]-arr[j])>=M
        ans=min(ans,abs(arr[i]-arr[j]))
        i+=1
print(ans)
