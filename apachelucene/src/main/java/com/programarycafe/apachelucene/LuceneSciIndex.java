package com.programarycafe.apachelucene;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BoostQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;

public class LuceneSciIndex implements Closeable {

    private final Directory directory;
    private final Analyzer analyzer;

    public LuceneSciIndex() {
        this.directory = new ByteBuffersDirectory(); // Índice en memoria (recomendado en Lucene 9)
        this.analyzer = new WhitespaceAnalyzer(); // Tokeniza por espacios; ideal para binomiales
    }

    /** Indexa un nombre científico (normaliza y separa en campos) */
    public void addNameSpecie(String rawScientificName) throws IOException {
        String norm = SearchSpecies.normalizeStrong(rawScientificName);
        if (norm.isEmpty()) return;

        String[] toks = SearchSpecies.tokenize(norm);
        String genus = toks.length >= 1 ? toks[0] : "";
        String species = toks.length >= 2 ? toks[1] : "";

        IndexWriterConfig cfg = new IndexWriterConfig(analyzer);
        try (IndexWriter writer = new IndexWriter(directory, cfg)) {
            Document doc = new Document();
            doc.add(new TextField("genus", genus, Field.Store.YES));
            if (!species.isEmpty()) doc.add(new TextField("species", species, Field.Store.YES));
            doc.add(new TextField("full", norm, Field.Store.YES)); // para mostrar/bonus
            writer.addDocument(doc);
            writer.commit();
        }
    }

    
    public void addName(String rawScientificName) throws IOException {
        String norm = SearchSpecies.normalizeStrong(rawScientificName);
        if (norm.isEmpty())
            return;

        IndexWriterConfig cfg = new IndexWriterConfig(analyzer);
        try (IndexWriter writer = new IndexWriter(directory, cfg)) {
            Document doc = new Document();
            // Guardamos el valor normalizado para recuperar y mostrar
            doc.add(new StringField("norm", norm, Field.Store.YES));
            writer.addDocument(doc);
            writer.commit();
        }
    }

    public void addAll(Collection<String> rawScientificNames) throws IOException {
        IndexWriterConfig cfg = new IndexWriterConfig(analyzer);
        try (IndexWriter writer = new IndexWriter(directory, cfg)) {
            for (String raw : rawScientificNames) {
                String norm = SearchSpecies.normalizeStrong(raw);
                if (norm.isEmpty())
                    continue;
                Document doc = new Document();
                doc.add(new StringField("norm", norm, Field.Store.YES));
                writer.addDocument(doc);
            }
            writer.commit();
        }
    }

        /** Indexa muchos nombres de forma eficiente */
    public void addAllSpecie(Collection<String> rawScientificNames) throws IOException {
        IndexWriterConfig cfg = new IndexWriterConfig(analyzer);
        try (IndexWriter writer = new IndexWriter(directory, cfg)) {
            for (String raw : rawScientificNames) {
                String norm = SearchSpecies.normalizeStrong(raw);
                if (norm.isEmpty()) continue;
                String[] toks = SearchSpecies.tokenize(norm);
                String genus = toks.length >= 1 ? toks[0] : "";
                String species = toks.length >= 2 ? toks[1] : "";

                Document doc = new Document();
                doc.add(new TextField("genus", genus, Field.Store.YES));
                if (!species.isEmpty()) doc.add(new TextField("species", species, Field.Store.YES));
                doc.add(new TextField("full", norm, Field.Store.YES));
                writer.addDocument(doc);
            }
            writer.commit();
        }
    }
    
    public List<String> search(String rawQuery, int maxEdits, int topK) throws IOException {
        String q = SearchSpecies.normalizeStrong(rawQuery);
        List<String> results = new ArrayList<>();
        if (q.isEmpty())
            return results;

        try (DirectoryReader reader = DirectoryReader.open(directory)) {
            IndexSearcher searcher = new IndexSearcher(reader);

            // FuzzyQuery sobre el campo "norm". Si tuvieras campos separados, harías una
            // BooleanQuery.
            FuzzyQuery query = new FuzzyQuery(new Term("norm", q), Math.min(Math.max(maxEdits, 0), 2));

            TopDocs hits = searcher.search(query, Math.max(topK, 1));
            for (ScoreDoc sd : hits.scoreDocs) {
                Document d = searcher.doc(sd.doc);
                results.add(d.get("norm"));
            }
        }
        return results;
    }

    
    /**
     * Busca permitiendo 1 o 2 palabras; soporta errores tipográficos y prefijos.
     * @param rawQuery entrada del usuario; se normaliza
     * @param maxEdits 0–2 ediciones por token (recomendado 1–2)
     * @param topK número de resultados
     */
    public List<String> searchSpecie(String rawQuery, int maxEdits, int topK) throws IOException {
        String q = SearchSpecies.normalizeStrong(rawQuery);
        String[] qt = SearchSpecies.tokenize(q);

        List<String> out = new ArrayList<>();
        if (q.isEmpty()) return out;

        try (DirectoryReader reader = DirectoryReader.open(directory)) {
            IndexSearcher searcher = new IndexSearcher(reader);
            Query query;

            int edits = Math.min(Math.max(maxEdits, 0), 2);

            if (qt.length == 1) {
                String tok = qt[0];

                // species tiene más peso, genus un poco menos
                BooleanQuery.Builder b = new BooleanQuery.Builder();

                // Coincidencias exactas por término
                b.add(new TermQuery(new Term("species", tok)), BooleanClause.Occur.SHOULD);
                b.add(new BoostQuery(new TermQuery(new Term("genus", tok)), 0.8f), BooleanClause.Occur.SHOULD);

                // Fuzzy por token (tolerancia a errores)
                b.add(new BoostQuery(new FuzzyQuery(new Term("species", tok), edits), 1.2f), BooleanClause.Occur.SHOULD);
                b.add(new BoostQuery(new FuzzyQuery(new Term("genus", tok), edits), 0.9f), BooleanClause.Occur.SHOULD);

                // Prefijo para autocompletar parciales ("esch" o "col")
                b.add(new BoostQuery(new PrefixQuery(new Term("species", tok)), 1.1f), BooleanClause.Occur.SHOULD);
                b.add(new BoostQuery(new PrefixQuery(new Term("genus", tok)), 0.9f), BooleanClause.Occur.SHOULD);

                query = b.build();

            } else {
                // Tomamos solo los dos primeros tokens: genus + species
                String qg = qt[0];
                String qs = qt[1];

                BooleanQuery.Builder b = new BooleanQuery.Builder();

                // Fuzzy por campo con pesos
                b.add(new BoostQuery(new FuzzyQuery(new Term("genus", qg), edits), 1.5f), BooleanClause.Occur.SHOULD);
                b.add(new BoostQuery(new FuzzyQuery(new Term("species", qs), edits), 1.3f), BooleanClause.Occur.SHOULD);

                // Bonus por coincidencia exacta de tokens
                b.add(new BoostQuery(new TermQuery(new Term("genus", qg)), 1.2f), BooleanClause.Occur.SHOULD);
                b.add(new BoostQuery(new TermQuery(new Term("species", qs)), 1.1f), BooleanClause.Occur.SHOULD);

                // Bonus por frase completa en "full" (cuando es exactamente dos tokens)
                PhraseQuery pq = new PhraseQuery(0, "full", qg, qs);
                b.add(new BoostQuery(pq, 1.4f), BooleanClause.Occur.SHOULD);

                query = b.build();
            }

            TopDocs hits = searcher.search(query, Math.max(topK, 1));
            for (ScoreDoc sd : hits.scoreDocs) {
                Document d = searcher.doc(sd.doc);
                out.add(d.get("full")); // devolvemos la forma normalizada completa
            }
        }
        return out;
    }


    @Override
    public void close() throws IOException {
        directory.close();
    }

}