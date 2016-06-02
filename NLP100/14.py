#-*- coding: utf-8 -*-
import sys

def nLine(fileName,n):
	f = open(fileName).readlines()
	r = ''.join(f[:n])
	print r
		


if __name__ == "__main__":
	fileName = "hightemp.txt"
	sys.stdout.write("n Line : ")
	n = int(raw_input())
	nLine(fileName,n)
