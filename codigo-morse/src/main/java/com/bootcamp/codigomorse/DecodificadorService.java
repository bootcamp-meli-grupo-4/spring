package com.bootcamp.codigomorse;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class DecodificadorService {

    private static final Map<String, String> dicionarioMorse = new HashMap<>();

    {
        dicionarioMorse.put(".-", "A");
        dicionarioMorse.put("-...", "B");
        dicionarioMorse.put("-.-.", "C");
        dicionarioMorse.put("-..", "D");
        dicionarioMorse.put(".", "E");
        dicionarioMorse.put("..-.", "F");
        dicionarioMorse.put("--.", "G");
        dicionarioMorse.put("....", "H");
        dicionarioMorse.put("..", "I");
        dicionarioMorse.put(".---", "J");
        dicionarioMorse.put("-.-", "K");
        dicionarioMorse.put(".-..", "L");
        dicionarioMorse.put("--", "M");
        dicionarioMorse.put("-.", "N");
        dicionarioMorse.put("---", "O");
        dicionarioMorse.put(".--.", "P");
        dicionarioMorse.put("--.-", "Q");
        dicionarioMorse.put(".-.", "R");
        dicionarioMorse.put("...", "S");
        dicionarioMorse.put("-", "T");
        dicionarioMorse.put("..-", "U");
        dicionarioMorse.put("...-", "V");
        dicionarioMorse.put(".--", "W");
        dicionarioMorse.put("-..-", "X");
        dicionarioMorse.put("-.--", "Y");
        dicionarioMorse.put("--..", "Z");
    }

    public String decodificadorMorse(String texto) {
        String[] palavras = texto.split("  ");
        return Arrays.stream(palavras).reduce("", (result, current) -> result + decodificaPalavra(current) + " ");
    }

    private String decodificaPalavra(String palavra) {
        String[] letras = palavra.split(" ");
        return Arrays.stream(letras).reduce("", (result, current) -> result + decodificaLetra(current));
    }

    private String decodificaLetra(String letra) {
        return dicionarioMorse.get(letra);
    }
}
