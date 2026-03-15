package com.programarycafe.apachelucene;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        // String normalizar = TaxonNameUtils.normalizeStrong("Homo Sapiens");

        // String[] resultado = TaxonNameUtils.tokenize(normalizar);

        // if (resultado.length==1) {
        //     System.out.println("Tiene una sola palabra");
        // }else if(resultado.length==2){
        //     System.out.println("Tiene dos palabras");
        // }else if (resultado.length==3){
        //     System.out.println("Tiene tres palabras");
        // }
       
        queryWithTwoWords();
    }

    /**
     * 
     * @throws IOException
     * Consultar la lista con una palabra
     */
    private static void QueryWithAword() throws IOException {
        try (LuceneSciIndex idx = new LuceneSciIndex()) {

            // Indexación
            idx.addAllAWordName(List.of(
                    "Escherichia coli",
                    "Homo sapiens",
                    "Canis lupus",
                    "Canis lupus familiaris",
                    "Panthera leo",
                    "Panthera tigris"));

            // Consulta con error tipográfico
            String userQuery = "Escherichia";
            System.out.println("BUSCANDO: '" + userQuery + "'");

            List<String> res = idx.searchWithAsingleWord(userQuery, 2, 5);
            if (res.isEmpty()) {
                System.out.println("Sin resultados.");
            } else {
                for (String r : res) {
                    System.out.println(TaxonNameUtils.firstWordAndsecondWord(r));
                }
            }
        }
    }
    /**
     * 
     * @throws IOException
     * Consultar la lista con dos palabras
     */
    private static void queryWithTwoWords() throws IOException {

        try (LuceneSciIndex idx = new LuceneSciIndex()) {

            // 1 palabra: epíteto
            System.out.println("Q='coli' →");
            for (String r : idx.searchWithTwoWords("coli", 1, 5)) {
                System.out.println("  " + TaxonNameUtils.firstWordAndsecondWord(r));
            }

            // 1 palabra: género
            System.out.println("Q='escherichia' →");
            for (String r : idx.searchWithTwoWords("escherichia", 1, 5)) {
                System.out.println("  " + TaxonNameUtils.firstWordAndsecondWord(r));
            }

            // Parcial por prefijo
            System.out.println("Q='esch' →");
            for (String r : idx.searchWithTwoWords("esch", 1, 5)) {
                System.out.println("  " + TaxonNameUtils.firstWordAndsecondWord(r));
            }

            // Dos palabras con falta
            System.out.println("Q='escherikia coli' →");
            for (String r : idx.searchWithTwoWords("escherikia coli", 2, 5)) {
                System.out.println("  " + TaxonNameUtils.firstWordAndsecondWord(r));
            }
        }

    }
}