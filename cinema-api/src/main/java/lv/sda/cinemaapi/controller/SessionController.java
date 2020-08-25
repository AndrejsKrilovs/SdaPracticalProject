package lv.sda.cinemaapi.controller;

import lombok.RequiredArgsConstructor;
import lv.sda.cinemaapi.dto.SessionDTO;
import lv.sda.cinemaapi.entity.Film;
import lv.sda.cinemaapi.entity.Session;
import lv.sda.cinemaapi.mapper.SessionMapper;
import lv.sda.cinemaapi.repository.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @AleksandrKozirev
 * 1. Make an AbstractController and extend from it.
 * 2. In findAll() method pass limit and offset arguments. Should be findAll(int offset, limit).
 *  Where {offset} - parameter from which page start, {limit} - parameter how many records on page to display
 *  Use {@link PageRequest} class documentation
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/session.svc")
@CrossOrigin(origins="http://localhost:4200")
public class SessionController {
    private final SessionRepository sessionRepository;
    private final SessionMapper mapper;

    @GetMapping("/Sessions")
    public List<SessionDTO> findAll() {
        return sessionRepository.findAll(PageRequest.of(1, 25))
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/Session({id})")
    public SessionDTO getOne(@PathVariable("id") Session session) {
        return mapper.toDTO(session);
    }

    @PostMapping("/Session")
    private SessionDTO add(@RequestBody SessionDTO sessionDTO) {
        Session entity = mapper.fromDTO(sessionDTO);
        Session newEntity = sessionRepository.save(entity);
        return mapper.toDTO(newEntity);
    }

    @PutMapping("/Session({id})")
    public SessionDTO update(@PathVariable("id") Session sessionFromDB, @RequestBody SessionDTO sessionDTO) {
        Session sessionToUpdate = mapper.fromDTO(sessionDTO);
        BeanUtils.copyProperties(sessionToUpdate, sessionFromDB, "id");
        Session result = sessionRepository.save(sessionFromDB);
        return mapper.toDTO(result);
    }

    @DeleteMapping("/Session({id})")
    public void delete(@PathVariable("id") Session session) {
        sessionRepository.delete(session);
    }

    @GetMapping("/Sessions({film_id})")
    public List<SessionDTO> findSessionsByFilm(@PathVariable("film_id") Film film) {
        return sessionRepository.findAllByFilm(film, PageRequest.of(0, 8))
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
