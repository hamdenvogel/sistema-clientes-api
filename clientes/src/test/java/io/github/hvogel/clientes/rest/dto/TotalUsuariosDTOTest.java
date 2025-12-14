package io.github.hvogel.clientes.rest.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TotalUsuariosDTOTest {

        @Test
        void testBuilder() {
                TotalUsuariosDTO dto = TotalUsuariosDTO.builder()
                                .withTotalUsuarios(100L)
                                .build();

                assertNotNull(dto);
                assertEquals(100L, dto.getTotalUsuarios());
        }

        @Test
        void testBuilderZero() {
                TotalUsuariosDTO dto = TotalUsuariosDTO.builder()
                                .withTotalUsuarios(0L)
                                .build();

                assertNotNull(dto);
                assertEquals(0L, dto.getTotalUsuarios());
        }

        @Test
        void testSetters() {
                TotalUsuariosDTO dto = TotalUsuariosDTO.builder()
                                .withTotalUsuarios(50L)
                                .build();

                dto.setTotalUsuarios(200L);

                assertEquals(200L, dto.getTotalUsuarios());
        }

        @Test
        void testEquals() {
                TotalUsuariosDTO dto1 = TotalUsuariosDTO.builder()
                                .withTotalUsuarios(100L)
                                .build();

                TotalUsuariosDTO dto2 = TotalUsuariosDTO.builder()
                                .withTotalUsuarios(100L)
                                .build();

                TotalUsuariosDTO dto3 = TotalUsuariosDTO.builder()
                                .withTotalUsuarios(200L)
                                .build();

                assertEquals(dto1, dto2);
                assertNotEquals(dto1, dto3);
                assertEquals(dto1, dto1);
                assertNotEquals(null, dto1);
                assertNotEquals(dto1, new Object());
        }

        @Test
        void testHashCode() {
                TotalUsuariosDTO dto1 = TotalUsuariosDTO.builder()
                                .withTotalUsuarios(100L)
                                .build();

                TotalUsuariosDTO dto2 = TotalUsuariosDTO.builder()
                                .withTotalUsuarios(100L)
                                .build();

                assertEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        void testToString() {
                TotalUsuariosDTO dto = TotalUsuariosDTO.builder()
                                .withTotalUsuarios(100L)
                                .build();

                String result = dto.toString();

                assertNotNull(result);
                assertTrue(result.contains("totalUsuarios=100"));
        }
}
