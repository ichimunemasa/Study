str = "Hi He Lied Because Boron Could Not Oxidize Fluorine. New Nations Might Also Sign Peace Security Clause. Arthur King Can."

str.replace(".","")
words = str.split(" ")
words_index = {}

for i,word in enumerate(words):
	i += 1
	if i in [1,5,6,7,8,9,15,16,19]:
		words_index[word[:1]] = i
	else:
		words_index[word[:2]] = i

print words_index
		
