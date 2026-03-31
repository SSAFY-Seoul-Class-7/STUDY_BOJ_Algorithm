import sys
from collections import deque
input=sys.stdin.readline

N,M=map(int,input().split())
arr=[list(map(int,input().rstrip())) for _ in range(N)]
q=deque()
#1은 벽임
#벽은 한번은 부술수 있음
#벽 부술 기회도 큐에 넣기
dxs,dys=[0,0,1,-1],[1,-1,0,0]
visited=[[[0]*2 for _ in range(M)] for _ in range(N)]

q.append((0,0,1)) #x,y,기회
visited[0][0][1]=1 #x,y,기회

while q:
    x,y,c=q.popleft()
    for dx,dy in zip(dxs,dys):
        nx,ny=x+dx,y+dy
        #벽일때
        if 0<=nx<N and 0<=ny<M and arr[nx][ny]:
            if c==1 and not visited[nx][ny][0]:
                visited[nx][ny][0]=visited[x][y][1]+1
                q.append((nx,ny,0))
        #땅일때
        elif 0<=nx<N and 0<=ny<M and not arr[nx][ny]:
            if not visited[nx][ny][c]:
                visited[nx][ny][c]=visited[x][y][c]+1
                q.append((nx,ny,c))

a=sys.maxsize
b=sys.maxsize              
if visited[N-1][M-1][0]:
    a=visited[N-1][M-1][0]
if visited[N-1][M-1][1]:
    b=visited[N-1][M-1][1]

print(min(a,b) if min(a,b)!=sys.maxsize else -1)
