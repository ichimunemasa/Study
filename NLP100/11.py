#-*- coding: utf-8 -*-

def replaceTab(filenamw):
	f = open(filename,"r")

	for line in f:
		string = line.replace("\t"," ")
		print string


if __name__ == "__main__":
	filename = "hightemp.txt"
	replaceTab(filename)
