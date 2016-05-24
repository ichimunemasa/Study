#-*- coding: utf-8 -*-

def cipher(string):
	retString = "".join(chr(219-ord(c)) if "a"<=c<="z" else c for c in string)
	return retString

if __name__=="__main__":
	sentence="Hello, world!"
	ciphertext=cipher(sentence)
	print(sentence)
	print(ciphertext)
	print(cipher(ciphertext)) 
