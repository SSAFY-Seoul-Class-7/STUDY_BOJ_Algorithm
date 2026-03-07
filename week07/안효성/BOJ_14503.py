import sys
input=sys.stdin.readline

N,M=map(int,input().split())
r,c,d=map(int,input().split()) #d가 0은 북, 1은 동, 2는 남, 3은 서
if d==1:
    d=3
elif d==3:
    d=1
arr=[list(map(int,input().split())) for _ in range(N)]

dxs,dys=[-1,0,1,0],[0,-1,0,1] #북서남동-시계반대방향순

#청소된 방은 2라고 두기. 1은 벽임
#회전은 (x+1)%4

ans=0
while True:
    if arr[r][c]==0:
        ans+=1
        arr[r][c]=2
    flag=0 #0은 청소되지 않은 빈칸이 없는 경우. 1은 청소되지 않은 빈칸이 있는경우
    for dx,dy in zip(dxs,dys):
        nx,ny=r+dx,c+dy
        if 0<=nx<N and 0<=ny<M:
            if arr[nx][ny]==0:
                flag=1
    
    if not flag:
        if 0<=r-dxs[d]<N and 0<=c-dys[d]<M:
            if arr[r-dxs[d]][c-dys[d]]==1: #후진이 벽인경우
                break
            else:
                r,c=r-dxs[d],c-dys[d]
                continue
    
    else: #flag가 1인 경우. 즉 청소안된 빈칸이 있는 경우
        d=(d+1)%4
        if 0<=r+dxs[d]<N and 0<=c+dys[d]<M:
            if arr[r+dxs[d]][c+dys[d]]==0: # 앞칸이 청소되지 않은 빈칸인 경우 한칸 전진
                arr[r+dxs[d]][c+dys[d]]=2
                ans+=1
                r,c=r+dxs[d],c+dys[d]
                continue

print(ans)
