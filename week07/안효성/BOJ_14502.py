import sys
from collections import deque

input=sys.stdin.readline
from itertools import combinations

N,M=map(int,input().split())
arr=[list(map(int,input().split())) for _ in range(N)]
q=deque()
#벽을 3개 더 세워야함
#0은 빈칸, 1은 벽, 2는 바이러스

zeros=[] #빈칸 좌표들 저장용
virus=[] #바이러스 좌표

for i in range(N):
    for j in range(M):
        if not arr[i][j]:
            zeros.append((i,j))
        elif arr[i][j]==2:
            virus.append((i,j))

ans=0
for a,b,c in combinations(zeros,3):
    temp=[row[:] for row in arr]
    temp[a[0]][a[1]],temp[b[0]][b[1]],temp[c[0]][c[1]]=1,1,1
    q.clear()
    q.extend(virus)
    while q:
        x,y=q.popleft()
        for dx,dy in zip([1,-1,0,0],[0,0,1,-1]):
            nx,ny=x+dx,y+dy
            if 0<=nx<N and 0<=ny<M and temp[nx][ny]==0:
                temp[nx][ny]=2
                q.append((nx,ny))
    cnt=0
    for i in range(N):
        for j in range(M):
            if not temp[i][j]:
                cnt+=1
    
    ans=max(ans,cnt)    

print(ans)
    
    
    
