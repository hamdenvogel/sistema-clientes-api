package io.github.hvogel.clientes.rest.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TotalClientesDTOTest {

        @Test
        void testBuilder() {
                TotalClientesDTO dto = TotalClientesDTO.builder()
                                .withTotalClientes(75L)
                                .build();

                assertNotNull(dto);
                assertEquals(75L, dto.getTotalClientes());
        }

        @Test
        void testBuilderZero() {
                TotalClientesDTO dto = TotalClientesDTO.builder()
                                .withTotalClientes(0L)
                                .build();

                assertNotNull(dto);
                assertEquals(0L, dto.getTotalClientes());
        }

        @Test
        void testSetters() {
                TotalClientesDTO dto = TotalClientesDTO.builder()
                                .withTotalClientes(30L)
                                .build();

                dto.setTotalClientes(150L);

                assertEquals(150L, dto.getTotalClientes());
        }

        @Test
        void testEquals() {
                TotalClientesDTO dto1 = TotalClientesDTO.builder()
                                .withTotalClientes(75L)
                                .build();

                TotalClientesDTO dto2 = TotalClientesDTO.builder()
                                .withTotalClientes(75L)
                                .build();

                TotalClientesDTO dto3 = TotalClientesDTO.builder()
                                .withTotalClientes(150L)
                                .build();

                assertEquals(dto1, dto2);
                assertNotEquals(dto1, dto3);
                assertEquals(dto1, dto1);
                assertNotEquals(null, dto1);
                assertNotEquals(dto1, new Object());
        }

        @Test
        void testHashCode() {
                TotalClientesDTO dto1 = TotalClientesDTO.builder()
                                .withTotalClientes(75L)
                                .build();

                TotalClientesDTO dto2 = TotalClientesDTO.builder()
                                .withTotalClientes(75L)
                                .build();

                assertEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        void testToString() {
                TotalClientesDTO dto = TotalClientesDTO.builder()
                                .withTotalClientes(75L)
                                .build();

                String result = dto.toString();

                assertNotNull(result);
                assertTrue(result.contains("totalClientes=75"));
        }

        @Test
        void testNegativeNumber() {
                TotalClientesDTO dto = TotalClientesDTO.builder()
                                .withTotalClientes(-1L)
                                .build();

                assertEquals(-1L, dto.getTotalClientes());
        }
}
