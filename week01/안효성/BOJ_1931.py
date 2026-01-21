import sys
input=sys.stdin.readline

N=int(input())
arr=[]
for _ in range(N):
    s,e=map(int,input().split())
    arr.append((s,e))

arr.sort(key=lambda x: (x[1],x[0]))

t=arr[0][1]
ans=1
for i in range(1,N):
    if arr[i][0]>=t:
        ans+=1
        t=arr[i][1]
print(ans)

