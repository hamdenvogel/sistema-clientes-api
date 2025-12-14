package io.github.hvogel.clientes.rest.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NaturezaDTOTest {

    @Test
    void testGettersAndSetters() {
        NaturezaDTO dto = new NaturezaDTO();

        dto.setId(1L);
        dto.setDescricao("Natureza Teste");

        assertEquals(1L, dto.getId());
        assertEquals("Natureza Teste", dto.getDescricao());
    }

    @Test
    void testEquals() {
        NaturezaDTO dto1 = new NaturezaDTO();
        dto1.setId(1L);
        dto1.setDescricao("Natureza");

        NaturezaDTO dto2 = new NaturezaDTO();
        dto2.setId(1L);
        dto2.setDescricao("Natureza");

        NaturezaDTO dto3 = new NaturezaDTO();
        dto3.setId(2L);

        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
    }

    @Test
    void testHashCode() {
        NaturezaDTO dto1 = new NaturezaDTO();
        dto1.setId(1L);

        NaturezaDTO dto2 = new NaturezaDTO();
        dto2.setId(1L);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        NaturezaDTO dto = new NaturezaDTO();
        dto.setDescricao("Natureza Teste");

        String result = dto.toString();
        assertNotNull(result);
        assertTrue(result.contains("Natureza Teste"));
    }
}
