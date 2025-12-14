package io.github.hvogel.clientes.model.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AtividadeTest {

    @Test
    void testGettersAndSetters() {
        Atividade entity = new Atividade();

        entity.setId(1L);
        entity.setDescricao("Atividade Teste");

        assertEquals(1L, entity.getId());
        assertEquals("Atividade Teste", entity.getDescricao());
    }

    @Test
    void testEquals() {
        Atividade entity1 = new Atividade();
        entity1.setId(1L);
        entity1.setDescricao("Atividade");

        Atividade entity2 = new Atividade();
        entity2.setId(1L);
        entity2.setDescricao("Atividade");

        Atividade entity3 = new Atividade();
        entity3.setId(2L);

        assertEquals(entity1, entity2);
        assertNotEquals(entity1, entity3);
    }

    @Test
    void testHashCode() {
        Atividade entity1 = new Atividade();
        entity1.setId(1L);

        Atividade entity2 = new Atividade();
        entity2.setId(1L);

        assertEquals(entity1.hashCode(), entity2.hashCode());
    }

    @Test
    void testToString() {
        Atividade entity = new Atividade();
        entity.setDescricao("Atividade Teste");

        String result = entity.toString();
        assertNotNull(result);
        assertTrue(result.contains("Atividade Teste"));
    }
}
