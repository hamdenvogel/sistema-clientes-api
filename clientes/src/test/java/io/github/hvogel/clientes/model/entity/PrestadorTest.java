package io.github.hvogel.clientes.model.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class PrestadorTest {

    @Test
    void testGettersAndSetters() {
        Prestador entity = new Prestador();

        entity.setId(1);
        entity.setNome("João Prestador");
        entity.setCpf("52998224725");
        entity.setPix("joao@example.com");
        entity.setAvaliacao(5);
        entity.setDataCadastro(LocalDate.of(2024, 1, 1));

        assertEquals(1, entity.getId());
        assertEquals("João Prestador", entity.getNome());
        assertEquals("52998224725", entity.getCpf());
        assertEquals("joao@example.com", entity.getPix());
        assertEquals(5, entity.getAvaliacao());
        assertEquals(LocalDate.of(2024, 1, 1), entity.getDataCadastro());
    }

    @Test
    void testEquals() {
        Prestador entity1 = new Prestador();
        entity1.setId(1);
        entity1.setNome("João");

        Prestador entity2 = new Prestador();
        entity2.setId(1);
        entity2.setNome("João");

        Prestador entity3 = new Prestador();
        entity3.setId(2);

        assertEquals(entity2, entity1);
        assertNotEquals(entity3, entity1);
        assertNotEquals(null, entity1);
        assertEquals(entity1, entity1);
    }

    @Test
    void testHashCode() {
        Prestador entity1 = new Prestador();
        entity1.setId(1);
        entity1.setNome("João");

        Prestador entity2 = new Prestador();
        entity2.setId(1);
        entity2.setNome("João");

        assertEquals(entity1.hashCode(), entity2.hashCode());
    }

    @Test
    void testToString() {
        Prestador entity = new Prestador();
        entity.setNome("João");

        String result = entity.toString();
        assertNotNull(result);
        assertTrue(result.contains("João"));
    }

    @Test
    void testPrePersist() {
        Prestador entity = new Prestador();
        assertNull(entity.getDataCadastro());

        entity.prePersist();

        assertNotNull(entity.getDataCadastro());
        assertEquals(LocalDate.now(), entity.getDataCadastro());
    }
}
