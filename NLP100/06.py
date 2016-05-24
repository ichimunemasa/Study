#-*- coding: utf-8 -*-

def charGram(sentence,ngram):
	rangeGram = ngram-1
	charGram = set([sentence[i:i+ngram] for i in range(len(sentence)-rangeGram)])
	return charGram

if __name__ == "__main__":
	set01 = charGram("paraparaparadise",2)
	set02 = charGram("paragraph",2)

	print str(set01 | set02)
	print set01 & set02
	print set01 - set02

	if "se" in set01:
		print u"set01 に含んでいます"
	
	if "se" in set02:
		print u"set02 に含んでいます"
