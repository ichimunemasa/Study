import random

def divided_word(word):
	if len(word) <= 4:
		return word
	
	mid_list = list(word[1:-1])

	#while mid_list == list(word[1:-1]):
	random.shuffle(mid_list)
	
	return word[0] + "".join(mid_list) + word[-1]
	


def sentence(string):
	string.replace(".","")
	words = string.split(" ")
	shuffled_list = []
	for word in words:
		shuffled_list.append(divided_word(word))
	return " ".join(shuffled_list)
		


if __name__ == "__main__":
	string = "I couldn't believe that I could actually understand what I was reading the phenomenal power of the human mind ."
	print sentence(string)

