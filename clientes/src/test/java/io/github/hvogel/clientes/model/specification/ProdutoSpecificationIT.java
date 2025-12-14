package io.github.hvogel.clientes.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
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
import io.github.hvogel.clientes.model.entity.Produto;
import io.github.hvogel.clientes.model.repository.ProdutosRepository;

@DataJpaTest
@ActiveProfiles("test")
@org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase(replace = org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE)
class ProdutoSpecificationIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProdutosRepository repository;

    private Produto produto1;
    private Produto produto2;

    @BeforeEach
    void setUp() {
        produto1 = new Produto();
        produto1.setDescricao("Produto X");
        produto1.setPreco(new BigDecimal("100.00"));
        entityManager.persist(produto1);

        produto2 = new Produto();
        produto2.setDescricao("Produto Y");
        produto2.setPreco(new BigDecimal("200.00"));
        entityManager.persist(produto2);

        entityManager.flush();
    }

    @Test
    void testSearchByDescricaoEqual() {
        ProdutoSpecification spec = new ProdutoSpecification();
        spec.add(new SearchCriteria("descricao", "Produto X", SearchOperation.EQUAL));

        List<Produto> results = repository.findAll(spec);

        assertEquals(1, results.size());
        assertEquals("Produto X", results.get(0).getDescricao());
    }

    @Test
    void testSearchByPrecoGreaterThan() {
        ProdutoSpecification spec = new ProdutoSpecification();
        spec.add(new SearchCriteria("preco", "150", SearchOperation.GREATER_THAN));

        List<Produto> results = repository.findAll(spec);

        assertEquals(1, results.size());
        assertEquals("Produto Y", results.get(0).getDescricao());
    }

    @Test
    void testSearchByDescricaoLike() {
        ProdutoSpecification spec = new ProdutoSpecification();
        spec.add(new SearchCriteria("descricao", "Produto", SearchOperation.LIKE));

        List<Produto> results = repository.findAll(spec);

        assertEquals(2, results.size());
    }

    @Test
    void testSearchByDescricaoNotEqual() {
        ProdutoSpecification spec = new ProdutoSpecification();
        spec.add(new SearchCriteria("descricao", "Produto X", SearchOperation.NOT_EQUAL));

        List<Produto> results = repository.findAll(spec);

        assertEquals(1, results.size());
        assertEquals("Produto Y", results.get(0).getDescricao());
    }

    @Test
    void testSearchByDescricaoMatchStart() {
        ProdutoSpecification spec = new ProdutoSpecification();
        spec.add(new SearchCriteria("descricao", "Produto", SearchOperation.LIKE_START));

        List<Produto> results = repository.findAll(spec);

        assertEquals(2, results.size());
    }

    @Test
    void testSearchByDescricaoMatchEnd() {
        ProdutoSpecification spec = new ProdutoSpecification();
        spec.add(new SearchCriteria("descricao", "X", SearchOperation.LIKE_END));

        List<Produto> results = repository.findAll(spec);

        assertEquals(1, results.size());
        assertEquals("Produto X", results.get(0).getDescricao());
    }

    @Test
    void testSearchByPrecoLessThan() {
        ProdutoSpecification spec = new ProdutoSpecification();
        spec.add(new SearchCriteria("preco", "150", SearchOperation.LESS_THAN));

        List<Produto> results = repository.findAll(spec);

        assertEquals(1, results.size());
        assertEquals("Produto X", results.get(0).getDescricao());
    }

    @Test
    void testSearchByDescricaoIn() {
        ProdutoSpecification spec = new ProdutoSpecification();
        spec.add(new SearchCriteria("descricao", "Produto X,Produto Y", SearchOperation.IN));

        List<Produto> results = repository.findAll(spec);

        assertEquals(2, results.size());
    }

    @Test
    void testSearchByDescricaoNotIn() {
        ProdutoSpecification spec = new ProdutoSpecification();
        spec.add(new SearchCriteria("descricao", "Produto X", SearchOperation.NOT_IN));

        List<Produto> results = repository.findAll(spec);

        assertEquals(1, results.size());
        assertEquals("Produto Y", results.get(0).getDescricao());
    }
}
