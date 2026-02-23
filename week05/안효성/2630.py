import sys
input=sys.stdin.readline

n=int(input())
arr=[]
for _ in range(n):
 arr.append(list(map(int,input().split())))

w=0
b=0

def f(x,y,s):
 global w,b
 c=arr[x][y]
 ok=1
 for i in range(x,x+s):
  for j in range(y,y+s):
   if arr[i][j]!=c:
    ok=0
    break
  if ok==0:
   break
 if ok==1:
  if c==0:
   w=w+1
  else:
   b=b+1
 else:
  ns=s//2
  f(x,y,ns)
  f(x,y+ns,ns)
  f(x+ns,y,ns)
  f(x+ns,y+ns,ns)

f(0,0,n)

print(w)
print(b)
