package com.SpringBoot.miniFlyway.converter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.SpringBoot.miniFlyway.dto.BookDto;
import com.SpringBoot.miniFlyway.entity.Book;
import com.SpringBoot.miniFlyway.exception.LibraryException;
import com.SpringBoot.miniFlyway.interfaces.Mapper;
import com.SpringBoot.miniFlyway.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookConverter implements Mapper<Book, BookDto> {

    private final BookRepository bookRepository;

    @Override
    public Book convertToEntity(BookDto dto) {
        if (ifExistByBook(dto.getTitle(), dto.getAuthor())) {
            throw new LibraryException("Ya existe el libro");
        }
        if (ifExistByTitle(dto.getTitle())) {
            throw new LibraryException("Ya existe el título");
        }
        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .cant(dto.getCant())
                .build();
    }

    @Override
    public BookDto convertToDto(Book entity) {
        return BookDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .cant(entity.getCant())
                .build();
    }

    @Override
    public Book update(BookDto dto, Book existingEntity) {
        if (ifExistByTitle(dto.getTitle())) {
            throw new LibraryException("Ya existe el título");
        }
        existingEntity.setTitle(dto.getTitle() != null ? dto.getTitle() : existingEntity.getTitle());
        existingEntity.setAuthor(dto.getAuthor() != null ? dto.getAuthor() : existingEntity.getAuthor());
        existingEntity.setCant(dto.getCant() > 0 ? existingEntity.getCant() + dto.getCant() : existingEntity.getCant());
        return existingEntity;
    }

    public String findBookByTitleOriginal(String nameTitle) {
        Optional<Book> book = bookRepository.findByTitleIgnoreCase(nameTitle);
        if (book.isEmpty()) {
            throw new LibraryException("El título del libro no encontrado");
        }

        Book resultBook = book.get();
        return resultBook.getTitle();
    }

    public String findBookByTitle(String nameTitle) {
        return bookRepository.findByTitleIgnoreCase(nameTitle)
                .map(Book::getTitle).orElseThrow(() -> new LibraryException("El título del libro no encontrado"));
    }

    public String findBookAutor(String author) {
        return bookRepository.findByAuthorIgnoreCase(author)
                .map(b -> b.getAuthor()).orElseThrow(() -> new LibraryException("Autor no encontrado"));
    }

    private boolean ifExistByTitle(String title) {
        return bookRepository.existsByTitleIgnoreCase(title);
    }

    private boolean ifExistByBook(String title, String author) {
        return bookRepository.existsByTitleIgnoreCaseAndAuthorIgnoreCase(title, author);
    }
}
