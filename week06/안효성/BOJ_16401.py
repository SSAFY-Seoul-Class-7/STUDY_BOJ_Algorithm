import sys
input=sys.stdin.readline

M,N=map(int,input().split())
arr=list(map(int,input().split()))

left=1
right=max(arr)
ans=0
while left<=right:
    mid=(left+right)//2
    cnt=0
    for i in arr:
        cnt+=i//mid
    
    if cnt>=M:
        ans=mid
        left=mid+1
        
    else:
        right=mid-1
        
print(ans)
