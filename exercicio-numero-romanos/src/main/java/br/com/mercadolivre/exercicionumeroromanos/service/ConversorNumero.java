package br.com.mercadolivre.exercicionumeroromanos.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConversorNumero {
    private Map<Integer, String> mapaNumeros = new HashMap<>();

    {
        mapaNumeros.put(1,"I");
        mapaNumeros.put(4,"IV");
        mapaNumeros.put(5,"V");
        mapaNumeros.put(9,"IX");
        mapaNumeros.put(10,"X");
        mapaNumeros.put(40,"XL");
        mapaNumeros.put(50,"L");
        mapaNumeros.put(90,"XC");
        mapaNumeros.put(100,"C");
        mapaNumeros.put(400,"CD");
        mapaNumeros.put(500,"D");
        mapaNumeros.put(900,"CM");
        mapaNumeros.put(1000,"M");
    }

    public String converteDecimalParaRomano(Integer numeroDecimal) {
        StringBuilder resultado = new StringBuilder();

        Integer quocienteMarcado = 0;

        if(numeroDecimal > 3999) {
            quocienteMarcado = numeroDecimal/1000;
            resultado.append("/").append(converter(quocienteMarcado)).append("\\");
        }

        numeroDecimal = numeroDecimal - (quocienteMarcado * 1000);

        return resultado.append(converter(numeroDecimal)).toString();
    }

    private String converter(Integer numeroDecimal) {
        StringBuilder resultado = new StringBuilder();
        Set<Integer> keys = mapaNumeros.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(LinkedHashSet::new));

        for (Integer m : keys)
        {
            Integer quociente = numeroDecimal / m;
            if (quociente > 0) {
                for (int i = 0; i < quociente; i++) {
                    resultado.append(mapaNumeros.get(m));
                }
                numeroDecimal = numeroDecimal - (quociente * m);
            }
        }

        return resultado.toString();
    }
}
