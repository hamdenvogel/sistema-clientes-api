package io.github.hvogel.clientes.rest.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CredenciaisDTOTest {

    @Test
    void testGettersAndSetters() {
        CredenciaisDTO dto = new CredenciaisDTO();

        dto.setLogin("usuario@test.com");
        dto.setSenha("senha123");

        assertEquals("usuario@test.com", dto.getLogin());
        assertEquals("senha123", dto.getSenha());
    }

    @Test
    void testEquals() {
        CredenciaisDTO dto1 = new CredenciaisDTO();
        dto1.setLogin("user");
        dto1.setSenha("pass");

        CredenciaisDTO dto2 = new CredenciaisDTO();
        dto2.setLogin("user");
        dto2.setSenha("pass");

        CredenciaisDTO dto3 = new CredenciaisDTO();
        dto3.setLogin("other");
        dto3.setSenha("other");

        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
    }

    @Test
    void testHashCode() {
        CredenciaisDTO dto1 = new CredenciaisDTO();
        dto1.setLogin("user");
        dto1.setSenha("pass");

        CredenciaisDTO dto2 = new CredenciaisDTO();
        dto2.setLogin("user");
        dto2.setSenha("pass");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        CredenciaisDTO dto = new CredenciaisDTO();
        dto.setLogin("user");
        dto.setSenha("pass");

        String result = dto.toString();

        assertNotNull(result);
        assertTrue(result.contains("user"));
    }
}
