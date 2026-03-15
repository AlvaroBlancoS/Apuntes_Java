package com.programarycafe;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.programarycafe.apachelucene.LuceneSciIndex;
import com.programarycafe.catalog.Catalogs;
import com.programarycafe.model.Item;

public class Main {

    private static final Catalogs c = new Catalogs();

    public static void main(String[] args) throws IOException {
        // System.out.println(c.SpeciesCatalog().stream().map(l ->
        // "\n"+l.getName()).toList());

        // c.SpeciesCatalog().forEach(System.out::println);

        speciesCatalog("cani lipiuos");
    }

    private static void speciesCatalog(String name) throws IOException {
        Collection<Item> speciesCollection = c.SpeciesCatalog();
        List<String> speciesNames = speciesCollection.stream()
                .map(Item::getName)
                .collect(Collectors.toList());

        try (LuceneSciIndex idx = new LuceneSciIndex()) {
            idx.addAllAtwoWordsName(speciesNames);

            List<String> res = idx.searchWithTwoWords(name, 2, 5);
            if (res.isEmpty()) {
                System.out.println("Sin resultados.");
            } else {
                for (String r : res) {
                System.out.println(r);
                }
            }
        }   
    }

    private static void MedicinesCatalog(String name) throws IOException{

    }
}