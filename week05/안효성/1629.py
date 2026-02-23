import sys
input=sys.stdin.readline

a,b,c=map(int,input().split())

def f(x,y):
 if y==1:
  return x%c
 t=f(x,y//2)
 if y%2==0:
  return (t*t)%c
 else:
  return (t*t*x)%c

print(f(a,b))
