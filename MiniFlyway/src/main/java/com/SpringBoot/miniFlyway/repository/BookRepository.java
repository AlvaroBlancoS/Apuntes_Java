package com.SpringBoot.miniFlyway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SpringBoot.miniFlyway.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //--- CONSULTAS NORMALES
    // Buscar por título (ignorando may/min)
    Optional<Book> findByTitleIgnoreCase(String title);

    // Buscar por autor (ignorando maymin)
    Optional<Book> findByAuthorIgnoreCase(String author);

    // Buscar por título y autor (ignorando may/min)
    Optional<Book> findByTitleIgnoreCaseAndAuthorIgnoreCase(String title, String author);

    // Comprobar el título si existe (ignorando may/min)
    boolean existsByTitleIgnoreCase(String title);

    // Comprobar el autor si existe (ignorando may/min) --
    boolean existsByAuthorIgnoreCase(String author);

    // Comprobar el libro si existe (ignorando may/min) --
    boolean existsByTitleIgnoreCaseAndAuthorIgnoreCase(String title, String author);

    //--- CONSULTAS DE QUERY
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) = LOWER(:title)")
    Optional<Book> findByTitleIgnoreCaseQuery(@Param("title") String title);

    @Query("""
           SELECT b FROM Book b 
           WHERE LOWER(b.title) = LOWER(:title)
           AND LOWER(b.author) = LOWER(:author)
           """)
    Optional<Book> findByTitleIgnoreCaseAndAuthorIgnoreCaseQuery(
            @Param("title") String title,
            @Param("author") String author);

    @Query("""
           SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END
           FROM Book b 
           WHERE LOWER(b.title) = LOWER(:title)
           """)
    boolean existsByTitleIgnoreCaseQuery(@Param("title") String title);

    @Query("""
           SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END
           FROM Book b 
           WHERE LOWER(b.title) = LOWER(:title)
           AND LOWER(b.author) = LOWER(:author)
           """)
    boolean existsByTitleIgnoreCaseAndAuthorIgnoreCaseQuery(
            @Param("title") String title,
            @Param("author") String author);

    // Una lista de libros por autores        
    @Query("SELECT b FROM Book b WHERE b.author= :findAuthor")
    List<Book> seeBooks(@Param("findAuthor") String author);

    //--- CONSULTAS DE NATIVE QUERY
    @Query(
            value = "SELECT * FROM books WHERE LOWER(title) = LOWER(:title)",
            nativeQuery = true
    )
    Optional<Book> findByTitleIgnoreCaseNativeQuery(@Param("title") String title);

    @Query(
            value = """
            SELECT * FROM books 
            WHERE LOWER(title) = LOWER(:title)
            AND LOWER(author) = LOWER(:author)
            """,
            nativeQuery = true
    )
    Optional<Book> findByTitleIgnoreCaseAndAuthorIgnoreCaseNativeQuery(
            @Param("title") String title,
            @Param("author") String author);

    @Query(
            value = """
            SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END
            FROM books 
            WHERE LOWER(title) = LOWER(:title)
            """,
            nativeQuery = true
    )
    boolean existsByTitleIgnoreCaseNativeQuery(@Param("title") String title);

    @Query(
            value = """
            SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END
            FROM books
            WHERE LOWER(title) = LOWER(:title)
            AND LOWER(author) = LOWER(:author)
            """,
            nativeQuery = true
    )
    boolean existsByTitleIgnoreCaseAndAuthorIgnoreCaseNativeQuery(
            @Param("title") String title,
            @Param("author") String author);
}
