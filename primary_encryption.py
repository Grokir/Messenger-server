# -*- coding: cp1251 -*-

#primary encryption - первичное шифрование
#То есть здесь шифруем текст простыми алгоритмами

CEASER_KEY = 17


#-------------------------------------------------------------------------------------------------------------------------------


eng_alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',

                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']

start_index_lower_eng = 26


rus_alphabet = ['А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 
                'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я',
                
                'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п',
                'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я']

start_index_lower_rus = 33


#-------------------------------------------------------------------------------------------------------------------------------


def char_to_pos_in_alphabet(char, isRusChar):

    if char in eng_alphabet:
        return eng_alphabet.index(char)

    if char in rus_alphabet:
        isRusChar = True
        return rus_alphabet.index(char) 
    
def pos_in_alphabet_to_char(pos, isRusChar):
    if isRusChar:
        return rus_alphabet[pos]

    return eng_alphabet[pos]


#-------------------------------------------------------------------------------------------------------------------------------


def crypto_Abash(text):
    result = ""
    for char in text:
        if char in eng_alphabet or char in rus_alphabet:

            isRusChar = False
            position = char_to_pos_in_alphabet(char, isRusChar)
            shifted_char = ''
            if isRusChar:
                shifted_char = pos_in_alphabet_to_char(len(rus_alphabet) - position, True)
            else:
                shifted_char = pos_in_alphabet_to_char(len(eng_alphabet) - position, False)

            result += shifted_char

        else:
            result += char
    return result


#-------------------------------------------------------------------------------------------------------------------------------


def crypto_Ceaser(text):
    result = ""

    SIZE_ALPHABET = 1

    if text[0] in eng_alphabet:
        SIZE_ALPHABET = 26
    elif text[0] in rus_alphabet:
        SIZE_ALPHABET = 33

    isRus = False
    for char in text:
        if char in eng_alphabet or char in rus_alphabet:
            shifted_char = (char_to_pos_in_alphabet(char, isRus) + CEASER_KEY) % SIZE_ALPHABET
            result += pos_in_alphabet_to_char(shifted_char, isRus)
        else:
            result += char
    return result
