package io.github.hvogel.clientes.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import io.github.hvogel.clientes.criteria.SearchCriteria;
import io.github.hvogel.clientes.enums.SearchOperation;
import io.github.hvogel.clientes.model.entity.Prestador;
import io.github.hvogel.clientes.model.repository.PrestadorRepository;

@DataJpaTest
@ActiveProfiles("test")
@org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase(replace = org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE)
class PrestadorSpecificationIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PrestadorRepository repository;

    private Prestador prestador1;
    private Prestador prestador2;

    @BeforeEach
    void setUp() {
        io.github.hvogel.clientes.model.entity.Profissao profissao = new io.github.hvogel.clientes.model.entity.Profissao();
        profissao.setDescricao("Desenvolvedor");
        entityManager.persist(profissao);

        prestador1 = new Prestador();
        prestador1.setNome("João Silva");
        prestador1.setCpf("56485476415"); // Valid CPF
        prestador1.setDataCadastro(LocalDate.of(2024, 1, 1));
        prestador1.setAvaliacao(5);
        prestador1.setProfissao(profissao);
        entityManager.persist(prestador1);

        prestador2 = new Prestador();
        prestador2.setNome("Maria Santos");
        prestador2.setCpf("59244337304"); // Valid CPF
        prestador2.setDataCadastro(LocalDate.of(2024, 2, 1));
        prestador2.setAvaliacao(4);
        prestador2.setProfissao(profissao);
        entityManager.persist(prestador2);

        entityManager.flush();
    }

    @Test
    void testSearchByNomeEqual() {
        PrestadorSpecification spec = new PrestadorSpecification();
        spec.add(new SearchCriteria("nome", "João Silva", SearchOperation.EQUAL));

        List<Prestador> results = repository.findAll(spec);

        assertEquals(1, results.size());
        assertEquals("João Silva", results.get(0).getNome());
    }

    @Test
    void testSearchByNomeLike() {
        PrestadorSpecification spec = new PrestadorSpecification();
        spec.add(new SearchCriteria("nome", "Silva", SearchOperation.LIKE));

        List<Prestador> results = repository.findAll(spec);

        assertEquals(1, results.size());
        assertEquals("João Silva", results.get(0).getNome());
    }

    @Test
    void testSearchByDateBetween() {
        PrestadorSpecification spec = new PrestadorSpecification();
        spec.add(new SearchCriteria("dataCadastro", "01/01/2024;31/01/2024", SearchOperation.BETWEEN_DATE));

        List<Prestador> results = repository.findAll(spec);

        assertEquals(0, results.size());
    }

    @Test
    void testSearchByNomeNotEqual() {
        PrestadorSpecification spec = new PrestadorSpecification();
        spec.add(new SearchCriteria("nome", "João Silva", SearchOperation.NOT_EQUAL));

        List<Prestador> results = repository.findAll(spec);

        assertEquals(1, results.size());
        assertEquals("Maria Santos", results.get(0).getNome());
    }

    @Test
    void testSearchByNomeMatchStart() {
        PrestadorSpecification spec = new PrestadorSpecification();
        spec.add(new SearchCriteria("nome", "João", SearchOperation.LIKE_START));

        List<Prestador> results = repository.findAll(spec);

        assertEquals(1, results.size());
        assertEquals("João Silva", results.get(0).getNome());
    }

    @Test
    void testSearchByNomeMatchEnd() {
        PrestadorSpecification spec = new PrestadorSpecification();
        spec.add(new SearchCriteria("nome", "Silva", SearchOperation.LIKE_END));

        List<Prestador> results = repository.findAll(spec);

        assertEquals(1, results.size());
        assertEquals("João Silva", results.get(0).getNome());
    }

    @Test
    void testSearchByNomeGreaterThan() {
        PrestadorSpecification spec = new PrestadorSpecification();
        // "Maria" > "João"
        spec.add(new SearchCriteria("nome", "João Silva", SearchOperation.GREATER_THAN));

        List<Prestador> results = repository.findAll(spec);

        assertEquals(1, results.size());
        assertEquals("Maria Santos", results.get(0).getNome());
    }

    @Test
    void testSearchByNomeLessThan() {
        PrestadorSpecification spec = new PrestadorSpecification();
        // "João" < "Maria"
        spec.add(new SearchCriteria("nome", "Maria Santos", SearchOperation.LESS_THAN));

        List<Prestador> results = repository.findAll(spec);

        assertEquals(1, results.size());
        assertEquals("João Silva", results.get(0).getNome());
    }

    @Test
    void testSearchByNomeIn() {
        PrestadorSpecification spec = new PrestadorSpecification();
        spec.add(new SearchCriteria("nome", "João Silva,Maria Santos", SearchOperation.IN));

        List<Prestador> results = repository.findAll(spec);

        assertEquals(2, results.size());
    }

    @Test
    void testSearchByNomeNotIn() {
        PrestadorSpecification spec = new PrestadorSpecification();
        spec.add(new SearchCriteria("nome", "João Silva", SearchOperation.NOT_IN));

        List<Prestador> results = repository.findAll(spec);

        assertEquals(1, results.size());
        assertEquals("Maria Santos", results.get(0).getNome());
    }
}
