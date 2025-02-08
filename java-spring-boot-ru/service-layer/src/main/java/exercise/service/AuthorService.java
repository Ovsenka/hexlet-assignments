package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper mapper;

    public AuthorDTO findById(Long id) {
        var maybeAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This author is not found"));
        return mapper.map(maybeAuthor);
    }

    public List<AuthorDTO> getAllAuthors() {
        var authors = authorRepository.findAll();
        return authors.stream()
                .map(mapper::map)
                .toList();
    }

    public AuthorDTO createAuthor(AuthorCreateDTO dto) {
        var author = mapper.map(dto);
        authorRepository.save(author);
        return mapper.map(author);
    }

    public AuthorDTO updateAuthor(AuthorUpdateDTO dto, Long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This author is not found"));
        mapper.update(dto, author);
        authorRepository.save(author);
        return mapper.map(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
    // END
}
