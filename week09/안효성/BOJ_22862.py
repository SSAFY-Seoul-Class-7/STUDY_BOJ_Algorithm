import sys
input=sys.stdin.readline

N,K=map(int,input().split())
arr=list(map(int,input().split()))
length=len(arr)
s,e,count,ans, delete=0,0,0,0,0
while e<length:
    if arr[e]%2: #홀수면
        if delete>=K: #삭제횟수 다 써버렸으면
            if arr[s]%2:#s가 원래 홀수 였으면
                s+=1
                delete-=1
            else: #s가 짝수였으면
                while s<e:
                    if arr[s]%2==1:
                        s+=1
                        delete-=1
                        break
                    count-=1
                    s+=1
        else:
            delete+=1
            e+=1
            
    else: #짝수면
        count+=1
        ans=max(ans,count)
        e+=1
        
print(ans)
