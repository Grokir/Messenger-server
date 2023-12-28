const CEASER_KEY = 17;

const eng_alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                   'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
   
                   'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                   'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];

const start_index_lower_eng = 26;

const rus_alphabet = ['А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 
                   'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я',
                   
                   'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п',
                   'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'];

const start_index_lower_rus = 33;

function char_to_pos_in_alphabet(char) {
    if (eng_alphabet.includes(char)) {
        return [eng_alphabet.indexOf(char), false];
    }

    if (rus_alphabet.includes(char)) {
        return [rus_alphabet.indexOf(char), true];
    }
}

function pos_in_alphabet_to_char(pos, isRusChar) {
    if (isRusChar) {
        return rus_alphabet[pos];
    }

    return eng_alphabet[pos];
}

function Abash_encrypt_decrypt(text) {
    let result = "";
    for (let char of text) {
        if (eng_alphabet.includes(char) || rus_alphabet.includes(char)) {
            const position_tuple = char_to_pos_in_alphabet(char);
            let shifted_char = '';
            if (position_tuple[1]) {
                shifted_char = pos_in_alphabet_to_char(rus_alphabet.length - 1 - position_tuple[0], true);
            } else {
                shifted_char = pos_in_alphabet_to_char(eng_alphabet.length - 1 - position_tuple[0], false);
            }

            result += shifted_char;
        } else {
            result += char;
        }
    }
    return result;
}

function Ceaser_encrypt(text) {
    let result = "";

    let SIZE_ALPHABET = 1;
    let START_LOWER_POS = 1;

    if (eng_alphabet.includes(text[0])) {
        SIZE_ALPHABET = 26;
        START_LOWER_POS = start_index_lower_eng;
    } else if (rus_alphabet.includes(text[0])) {
        SIZE_ALPHABET = 33;
        START_LOWER_POS = start_index_lower_rus;
    }

    for (let char of text) {
        if (eng_alphabet.includes(char) || rus_alphabet.includes(char)) {
            const char_pos_tuple = char_to_pos_in_alphabet(char);
            const isRus = char_pos_tuple[1];
            const shifted_char = ((char_pos_tuple[0] + CEASER_KEY) % SIZE_ALPHABET) + START_LOWER_POS * Number(char === char.toLowerCase());
            result += pos_in_alphabet_to_char(shifted_char, isRus);
        } else {
            result += char;
        }
    }
    return result;
}

function Ceaser_decrypt(text) {
    let result = "";

    let SIZE_ALPHABET = 1;
    let START_LOWER_POS = 1;

    if (eng_alphabet.includes(text[0])) {
        SIZE_ALPHABET = 26;
        START_LOWER_POS = start_index_lower_eng;
    } else if (rus_alphabet.includes(text[0])) {
        SIZE_ALPHABET = 33;
        START_LOWER_POS = start_index_lower_rus;
    }

    for (let char of text) {
        if (eng_alphabet.includes(char) || rus_alphabet.includes(char)) {
            const char_pos_tuple = char_to_pos_in_alphabet(char);
            const isRus = char_pos_tuple[1];
            const shifted_char = ((char_pos_tuple[0] - CEASER_KEY) % SIZE_ALPHABET) + START_LOWER_POS * Number(char === char.toLowerCase());
            result += pos_in_alphabet_to_char(shifted_char, isRus);
        } else {
            result += char;
        }
    }
    return result;
}

function primary_encrypt(string) {
    return Abash_encrypt_decrypt(Ceaser_encrypt(string));
}

function primary_decrypt(string) {
    return Ceaser_decrypt(Abash_encrypt_decrypt(string));
}


function encrypt(string){
    return btoa(primary_encrypt(string));
}

function decrypt(string){
    return primary_decrypt(atob(string));
}
