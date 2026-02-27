import sys
input=sys.stdin.readline

N,K=map(int,input().split())
arr=list(map(int,input().split()))

count=[0]*100001
ans=0
j=0
for i in range(N):
    while j<N:
        count[arr[j]]+=1
        if count[arr[j]]>K:
            count[arr[j]]-=1
            break
        j+=1
    ans=max(ans,j-i)
    count[arr[i]]-=1

print(ans)
    
