package com.SpringBoot.miniFlyway.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.SpringBoot.miniFlyway.converter.BookConverter;
import com.SpringBoot.miniFlyway.dto.BookDto;
import com.SpringBoot.miniFlyway.entity.Book;
import com.SpringBoot.miniFlyway.exception.LibraryException;
import com.SpringBoot.miniFlyway.interfaces.BasicService;
import com.SpringBoot.miniFlyway.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService implements BasicService<BookDto, Long> {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    @Override
    public List<BookDto> getAll() {
        List<Book> result = bookRepository.findAll(Sort.by("id"));
        if (result.isEmpty()) {
            throw new LibraryException("No hay ningún libro");
        }
        return result.stream().map(bookConverter::convertToDto).toList();
    }

    List<BookDto> getBooksByAuthor(String author) {
        List<Book> result = bookRepository.seeBooks(author);
        if (result.isEmpty()) {
            throw new LibraryException("Ese autor no existe");
        }
        return result.stream().map(bookConverter::convertToDto).toList();
    }

    @Override
    public BookDto getById(Long id) {
        return bookRepository.findById(id).map(bookConverter::convertToDto).orElseThrow();
    }

    public BookDto getByNameAuthor(String title) {
        return bookRepository.findByTitleIgnoreCase(title).map(bookConverter::convertToDto).orElseThrow(() -> new LibraryException(""));
    }

    @Override
    public BookDto save(BookDto dto) {
        Book book = bookConverter.convertToEntity(dto);
        Book bookSave = bookRepository.save(book);
        return bookConverter.convertToDto(bookSave);
    }

    @Override
    public BookDto update(BookDto dto) {
        Book bookEntity = bookRepository.findById(dto.getId()).orElseThrow(() -> new LibraryException("Este libro no está la lista de la base de datos"));
        Book bookUpdate = bookConverter.update(dto, bookEntity);
        return bookConverter.convertToDto(bookUpdate);
    }

    @Override
    public void deleteById(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new LibraryException("Este libro no está la lista de la base de datos");
        }
    }

}
