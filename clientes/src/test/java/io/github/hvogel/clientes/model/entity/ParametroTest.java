package io.github.hvogel.clientes.model.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class ParametroTest {

    @Test
    void testGettersAndSetters() {
        Parametro entity = new Parametro();

        entity.setId(1L);
        entity.setDescricao("Parametro Teste");
        entity.setData(LocalDate.of(2024, 1, 1));

        assertEquals(1L, entity.getId());
        assertEquals("Parametro Teste", entity.getDescricao());
        assertEquals(LocalDate.of(2024, 1, 1), entity.getData());
    }

    @Test
    void testEquals() {
        Parametro entity1 = new Parametro();
        entity1.setId(1L);
        entity1.setDescricao("Param");

        Parametro entity2 = new Parametro();
        entity2.setId(1L);
        entity2.setDescricao("Param");

        Parametro entity3 = new Parametro();
        entity3.setId(2L);

        assertEquals(entity1, entity2);
        assertNotEquals(entity1, entity3);
    }

    @Test
    void testHashCode() {
        Parametro entity1 = new Parametro();
        entity1.setId(1L);

        Parametro entity2 = new Parametro();
        entity2.setId(1L);

        assertEquals(entity1.hashCode(), entity2.hashCode());
    }

    @Test
    void testToString() {
        Parametro entity = new Parametro();
        entity.setDescricao("Parametro");

        String result = entity.toString();
        assertNotNull(result);
        assertTrue(result.contains("Parametro"));
    }
}
