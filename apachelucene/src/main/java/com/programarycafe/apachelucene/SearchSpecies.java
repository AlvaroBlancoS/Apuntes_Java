package com.programarycafe.apachelucene;

import java.text.Normalizer;
import java.util.Locale;

public class SearchSpecies {

    public SearchSpecies(){}


    /** Normaliza completamente un nombre científico */
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


    /** Tokeniza por espacios */
    public static String[] tokenize(String normalized) {
        if (normalized == null || normalized.isEmpty()) return new String[0];
        return normalized.split("\\s+");
    }


    /** Capitaliza género al mostrar */
    public static String prettyGenus(String genus) {
        if (genus == null || genus.isEmpty()) return genus;
        return Character.toUpperCase(genus.charAt(0)) + genus.substring(1);
    }


    

    /** Formatea bonito un nombre "genus species ..." */
    public static String pretty(String canonical) {
        if (canonical == null || canonical.isEmpty()) return canonical;

        String[] toks = canonical.split(" ");
        if (toks.length == 0) return canonical;

        String genus = prettyGenus(toks[0]);
        if (toks.length == 1) return genus;

        String species = toks[1].toLowerCase(Locale.ROOT);

        if (toks.length == 2) return genus + " " + species;

        String rest = String.join(" ",
                java.util.Arrays.copyOfRange(toks, 2, toks.length)
        ).toLowerCase(Locale.ROOT);

        return genus + " " + species + " " + rest;
    }

}
