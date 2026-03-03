import sys
input=sys.stdin.readline

N,L,R,X=map(int,input().split())
arr=list(map(int,input().split()))

#문제는 2문제 이상이어야함
#문제 난이도 합은 L이상 R이하 여야함
#젤 쉬운 문제와 젤 어려운 문제 난이도 차이는 X 이상이어야함
ans=0
def recur(idx,array):
    global ans
    if idx==N:
        if len(array)>=2:
            if abs(max(array)-min(array))>=X:
                if L<=sum(array)<=R:
                    ans+=1
        return
    #할래
    recur(idx+1,array+[arr[idx]])

    #안할래
    recur(idx+1,array)
recur(0,[])
print(ans)
