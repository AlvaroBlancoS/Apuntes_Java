package com.programarycafe.apachelucene;

import java.text.Normalizer;
import java.util.Locale;

public class TaxonNameUtils {

    public TaxonNameUtils(){}

    /**
     * 
     * @param s
     * @return Normaliza completamente un nombre científico
     * Pasa minúsculas
     * Quita diacrítico NFKD
     * Sustitye ligaduras: æ → ae, œ → oe
     * Elimina caracteres no alfabéticos (deja letras, espacios, guiones y punto)
     * Colapsa espacios múltiples
     */
    public static String normalizeStrong(String s) {
        if (s == null) return "";
        String t = s.trim().toLowerCase(Locale.ROOT);

        // NFKD → separar diacríticos
        t = Normalizer.normalize(t, Normalizer.Form.NFKD)
                .replaceAll("\\p{M}+", "");

        // Reemplazar ligaduras latinas típicas
        t = t.replace("æ", "ae").replace("œ", "oe");

        // Mantener solo letras, espacios, puntos y guiones simples
        t = t.replaceAll("[^a-z\\s\\-\\.]", " ");

        // Colapsar espacios múltiples
        t = t.replaceAll("\\s+", " ").trim();

        return t;
    }


    /**
     * 
     * @param normalized
     * @return Tokeniza por espacios
     * Recibe una cadena ya normalizada
     * La divide por espacios y devuelve un String[]
     * Si la entrada es "Escherichia coli" (normalizada → "escherichia coli"), 
     * devuelve ["escherichia", "coli"]
     * Si el entrada  es "Escherichia", devuelve ["escherichia"]
     */
    public static String[] tokenize(String normalized) {
        if (normalized == null || normalized.isEmpty()) return new String[0];
        return normalized.split("\\s+");
    }


    /** Capitaliza género al mostrar */
    public static String firstWordOnly(String firstWord) {
        if (firstWord == null || firstWord.isEmpty()) return firstWord;
        return Character.toUpperCase(firstWord.charAt(0)) + firstWord.substring(1);
    }

    /** Formatea bonito un nombre "firstWord secondWord ..." */
    public static String firstWordAndsecondWord(String canonical) {
        if (canonical == null || canonical.isEmpty()) return canonical;

        String[] toks = canonical.split(" ");
        if (toks.length == 0) return canonical;

        String firstWord = firstWordOnly(toks[0]);
        if (toks.length == 1) return firstWord;

        String secondWord = toks[1].toLowerCase(Locale.ROOT);

        if (toks.length == 2) return firstWord + " " + secondWord;

        String rest = String.join(" ",
                java.util.Arrays.copyOfRange(toks, 2, toks.length)
        ).toLowerCase(Locale.ROOT);

        return firstWord + " " + secondWord + " " + rest;
    }

}
