package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper mapper;

    public BookDTO findByid(Long id) {
        var maybeBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This book is not found"));
        return mapper.map(maybeBook);
    }

    public List<BookDTO> getAllBooks() {
        var books = bookRepository.findAll();
        return books.stream()
                .map(mapper::map)
                .toList();
    }

    public BookDTO createBook(BookCreateDTO dto) {
        var book = mapper.map(dto);
        bookRepository.save(book);
        return mapper.map(book);
    }

    public BookDTO updateBook(BookUpdateDTO dto, Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This book is not found"));
        mapper.update(dto, book);
        bookRepository.save(book);
        return mapper.map(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
