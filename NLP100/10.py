#-*- coding: utf-8 -*-

def lineCount(filename):
	lineCounter = 0
	f = open(filename,"r")
	for  line in f:
		lineCounter += 1
	print str(lineCounter)

if __name__ == "__main__":
	filename = "hightemp.txt"
	lineCount(filename)
