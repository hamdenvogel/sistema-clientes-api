package io.github.hvogel.clientes.rest.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class ParametroDTOTest {

    @Test
    void testGettersAndSetters() {
        ParametroDTO dto = new ParametroDTO();

        dto.setId(1);
        dto.setDescricao("Parametro Teste");
        dto.setData(LocalDate.of(2024, 1, 1));

        assertEquals(1, dto.getId());
        assertEquals("Parametro Teste", dto.getDescricao());
        assertEquals(LocalDate.of(2024, 1, 1), dto.getData());
    }

    @Test
    void testEquals() {
        ParametroDTO dto1 = new ParametroDTO();
        dto1.setId(1);
        dto1.setDescricao("Param");

        ParametroDTO dto2 = new ParametroDTO();
        dto2.setId(1);
        dto2.setDescricao("Param");

        ParametroDTO dto3 = new ParametroDTO();
        dto3.setId(2);

        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
    }

    @Test
    void testHashCode() {
        ParametroDTO dto1 = new ParametroDTO();
        dto1.setId(1);

        ParametroDTO dto2 = new ParametroDTO();
        dto2.setId(1);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        ParametroDTO dto = new ParametroDTO();
        dto.setDescricao("Parametro");

        String result = dto.toString();
        assertNotNull(result);
        assertTrue(result.contains("Parametro"));
    }
}
