package es.daw.bibliografia.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TemaService {

	@Autowired
	private TemaRepository repository;

	public Optional<Tema> findOne(long id) {
		return repository.findById(id);
	}

	public List<Tema> findAll() {
		return repository.findAll();
	}

	public Page<Tema> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Optional<Tema> findOneByContenido(String contenido) {
		return repository.findOneByContenido(contenido);
	}

	public void save(Tema tema) {
		repository.save(tema);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

	public Tema findByObra(Obra obra) {
		return repository.findByObras(obra);
	}

}
