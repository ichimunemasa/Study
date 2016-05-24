# -*- coding: utf-8 -*-

def charGram(sentence,ngram):
	rangeGram = ngram-1
	charGram=[sentence[i:i+ngram] for i in range(len(sentence)-rangeGram)]
	print charGram
	
def wordGram(sentence,ngram):
	rangeGram = ngram-1
	words=[word.strip(".,") for word in sentence.split()] 
	wordGram=[" ".join(words[i:i+ngram]) for i in range(len(words)-rangeGram)]
	print wordGram
	





if __name__ == "__main__":
	sentence="I am an NLPer"
	charGram(sentence,2)
	wordGram(sentence,2)
