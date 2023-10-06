# -*- coding: utf-8 -*-

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


def char_to_pos_in_alphabet(char):

    if char in eng_alphabet:
        return (eng_alphabet.index(char), False)

    if char in rus_alphabet:
        return (rus_alphabet.index(char), True) 
    
def pos_in_alphabet_to_char(pos, isRusChar):
    if isRusChar:
        return rus_alphabet[pos]

    return eng_alphabet[pos]


#-------------------------------------------------------------------------------------------------------------------------------


def Abash_encrypt_decrypt(text):
    result = ""
    for char in text:
        if char in eng_alphabet or char in rus_alphabet:
            position_tuple = char_to_pos_in_alphabet(char)
            shifted_char = ''
            if position_tuple[1]:
                shifted_char = pos_in_alphabet_to_char(len(rus_alphabet) - 1 - position_tuple[0], True)
            else:
                shifted_char = pos_in_alphabet_to_char(len(eng_alphabet) - 1 - position_tuple[0], False)

            result += shifted_char

        else:
            result += char
    return result


#-------------------------------------------------------------------------------------------------------------------------------


def Ceaser_encrypt(text):
    result = ""

    SIZE_ALPHABET = 1
    START_LOWER_POS = 1

    if text[0] in eng_alphabet:
        SIZE_ALPHABET = 26
        START_LOWER_POS = start_index_lower_eng
    elif text[0] in rus_alphabet:
        SIZE_ALPHABET = 33
        START_LOWER_POS = start_index_lower_rus

        
    for char in text:
        if char in eng_alphabet or char in rus_alphabet:
            char_pos_tuple = char_to_pos_in_alphabet(char)
            isRus = char_pos_tuple[1]
            shifted_char = ((char_pos_tuple[0] + CEASER_KEY) % SIZE_ALPHABET) + START_LOWER_POS * int(char.islower())
            result += pos_in_alphabet_to_char(shifted_char, isRus)

        else:
            result += char
    return result

def Ceaser_decrypt(text):
    result = ""

    SIZE_ALPHABET = 1
    START_LOWER_POS = 1

    if text[0] in eng_alphabet:
        SIZE_ALPHABET = 26
        START_LOWER_POS = start_index_lower_eng
    elif text[0] in rus_alphabet:
        SIZE_ALPHABET = 33
        START_LOWER_POS = start_index_lower_rus
        

    for char in text:
        if char in eng_alphabet or char in rus_alphabet:
            char_pos_tuple = char_to_pos_in_alphabet(char)
            isRus = char_pos_tuple[1]
            shifted_char = ((char_pos_tuple[0] - CEASER_KEY) % SIZE_ALPHABET) + START_LOWER_POS * int(char.islower())
            result += pos_in_alphabet_to_char(shifted_char, isRus)

        else:
            result += char
    return result


def primary_encrypt(string):
    return Abash_encrypt_decrypt(Ceaser_encrypt(string))

def primary_decrypt(string):
    return Ceaser_decrypt(Abash_encrypt_decrypt(string))
