package com.programarycafe.apachelucene;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        SearchSpecies especies = new SearchSpecies();

        System.out.println(especies.prettyGenus("Homo sapiens"));
        // consultaComplejo();
    }

    private static void consultaRapida() throws IOException {
        try (LuceneSciIndex idx = new LuceneSciIndex()) {

            // Indexación
            idx.addAll(List.of(
                    "Escherichia coli",
                    "Homo sapiens",
                    "Canis lupus",
                    "Canis lupus familiaris",
                    "Panthera leo",
                    "Panthera tigris"));

            // Consulta con error tipográfico
            String userQuery = "Escherichia";
            System.out.println("BUSCANDO: '" + userQuery + "'");

            List<String> res = idx.search(userQuery, 2, 5);
            if (res.isEmpty()) {
                System.out.println("Sin resultados.");
            } else {
                for (String r : res) {
                    System.out.println(" → " + SearchSpecies.pretty(r));
                }
            }
        }
    }

    private static void consultaComplejo() throws IOException {

        try (LuceneSciIndex idx = new LuceneSciIndex()) {
            idx.addAllSpecie(List.of(
                    "Escherichia coli",
                    "Homo sapiens",
                    "Canis lupus",
                    "Canis lupus familiaris",
                    "Panthera leo",
                    "Panthera tigris"));

            String userQuery = "lopos";
            List<String> res = idx.searchSpecie(userQuery, 2, 5);
            if (res.isEmpty()) {
                System.out.println("Sin resultados.");
            } else {
                for (String r : res) {
                    System.out.println(" → " + SearchSpecies.pretty(r));
                }
            }

            // // 1 palabra: epíteto
            // System.out.println("Q='coli' →");
            // for (String r : idx.searchSpecie("coli", 1, 5)) {
            //     System.out.println("  " + SearchSpecies.pretty(r));
            // }

            // // 1 palabra: género
            // System.out.println("Q='escherichia' →");
            // for (String r : idx.searchSpecie("escherichia", 1, 5)) {
            //     System.out.println("  " + SearchSpecies.pretty(r));
            // }

            // // Parcial por prefijo
            // System.out.println("Q='esch' →");
            // for (String r : idx.searchSpecie("esch", 1, 5)) {
            //     System.out.println("  " + SearchSpecies.pretty(r));
            // }

            // // Dos palabras con falta
            // System.out.println("Q='escherikia coli' →");
            // for (String r : idx.searchSpecie("escherikia coli", 2, 5)) {
            //     System.out.println("  " + SearchSpecies.pretty(r));
            // }
        }

    }
}