package io.github.hvogel.clientes.model.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NaturezaTest {

    @Test
    void testGettersAndSetters() {
        Natureza entity = new Natureza();

        entity.setId(1L);
        entity.setDescricao("Natureza Teste");

        assertEquals(1L, entity.getId());
        assertEquals("Natureza Teste", entity.getDescricao());
    }

    @Test
    void testEquals() {
        Natureza entity1 = new Natureza();
        entity1.setId(1L);
        entity1.setDescricao("Natureza");

        Natureza entity2 = new Natureza();
        entity2.setId(1L);
        entity2.setDescricao("Natureza");

        Natureza entity3 = new Natureza();
        entity3.setId(2L);

        assertEquals(entity1, entity2);
        assertNotEquals(entity1, entity3);
    }

    @Test
    void testHashCode() {
        Natureza entity1 = new Natureza();
        entity1.setId(1L);

        Natureza entity2 = new Natureza();
        entity2.setId(1L);

        assertEquals(entity1.hashCode(), entity2.hashCode());
    }

    @Test
    void testToString() {
        Natureza entity = new Natureza();
        entity.setDescricao("Natureza Teste");

        String result = entity.toString();
        assertNotNull(result);
        assertTrue(result.contains("Natureza Teste"));
    }
}
