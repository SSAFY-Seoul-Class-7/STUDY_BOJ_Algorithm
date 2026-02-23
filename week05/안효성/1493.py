import sys
input=sys.stdin.readline

l,w,h=map(int,input().split())
n=int(input())

arr=[0]*20
for _ in range(n):
 a,b=map(int,input().split())
 arr[a]=b

ans=0
filled=0

for i in range(19,-1,-1):
 filled=filled*8
 size=2**i
 need=(l//size)*(w//size)*(h//size)-filled
 if need<=0:
  continue
 use=min(need,arr[i])
 ans=ans+use
 filled=filled+use

if filled==l*w*h:
 print(ans)
else:
 print(-1)
