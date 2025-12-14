package io.github.hvogel.clientes.model.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ProdutoTest {

    @Test
    void testGettersAndSetters() {
        Produto entity = new Produto();

        entity.setId(1);
        entity.setDescricao("Produto Teste");
        entity.setPreco(new BigDecimal("100.00"));
        entity.setMarca("Marca Teste");
        entity.setModelo("Modelo Teste");
        entity.setAnoFabricacao(2024);
        entity.setAnoModelo(2024);

        assertEquals(1, entity.getId());
        assertEquals("Produto Teste", entity.getDescricao());
        assertEquals(new BigDecimal("100.00"), entity.getPreco());
        assertEquals("Marca Teste", entity.getMarca());
        assertEquals("Modelo Teste", entity.getModelo());
        assertEquals(2024, entity.getAnoFabricacao());
        assertEquals(2024, entity.getAnoModelo());
    }

    @Test
    void testEquals() {
        Produto entity1 = new Produto();
        entity1.setId(1);
        entity1.setDescricao("Produto");

        Produto entity2 = new Produto();
        entity2.setId(1);
        entity2.setDescricao("Produto");

        Produto entity3 = new Produto();
        entity3.setId(2);

        assertEquals(entity1, entity2);
        assertNotEquals(entity1, entity3);
    }

    @Test
    void testHashCode() {
        Produto entity1 = new Produto();
        entity1.setId(1);

        Produto entity2 = new Produto();
        entity2.setId(1);

        assertEquals(entity1.hashCode(), entity2.hashCode());
    }

    @Test
    void testToString() {
        Produto entity = new Produto();
        entity.setDescricao("Produto Teste");

        String result = entity.toString();
        assertNotNull(result);
        assertTrue(result.contains("Produto Teste"));
    }
}
