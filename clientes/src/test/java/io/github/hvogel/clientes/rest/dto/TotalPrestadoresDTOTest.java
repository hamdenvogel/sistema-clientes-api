package io.github.hvogel.clientes.rest.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TotalPrestadoresDTOTest {

        @Test
        void testBuilder() {
                TotalPrestadoresDTO dto = TotalPrestadoresDTO.builder()
                                .withTotalPrestadores(15L)
                                .build();

                assertNotNull(dto);
                assertEquals(15L, dto.getTotalPrestadores());
        }

        @Test
        void testBuilderZero() {
                TotalPrestadoresDTO dto = TotalPrestadoresDTO.builder()
                                .withTotalPrestadores(0L)
                                .build();

                assertNotNull(dto);
                assertEquals(0L, dto.getTotalPrestadores());
        }

        @Test
        void testSetters() {
                TotalPrestadoresDTO dto = TotalPrestadoresDTO.builder()
                                .withTotalPrestadores(5L)
                                .build();

                dto.setTotalPrestadores(30L);

                assertEquals(30L, dto.getTotalPrestadores());
        }

        @Test
        void testEquals() {
                TotalPrestadoresDTO dto1 = TotalPrestadoresDTO.builder()
                                .withTotalPrestadores(15L)
                                .build();

                TotalPrestadoresDTO dto2 = TotalPrestadoresDTO.builder()
                                .withTotalPrestadores(15L)
                                .build();

                TotalPrestadoresDTO dto3 = TotalPrestadoresDTO.builder()
                                .withTotalPrestadores(30L)
                                .build();

                assertEquals(dto1, dto2);
                assertNotEquals(dto1, dto3);
                assertEquals(dto1, dto1);
                assertNotEquals(null, dto1);
                assertNotEquals(dto1, new Object());
        }

        @Test
        void testHashCode() {
                TotalPrestadoresDTO dto1 = TotalPrestadoresDTO.builder()
                                .withTotalPrestadores(15L)
                                .build();

                TotalPrestadoresDTO dto2 = TotalPrestadoresDTO.builder()
                                .withTotalPrestadores(15L)
                                .build();

                assertEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        void testToString() {
                TotalPrestadoresDTO dto = TotalPrestadoresDTO.builder()
                                .withTotalPrestadores(15L)
                                .build();

                String result = dto.toString();

                assertNotNull(result);
                assertTrue(result.contains("totalPrestadores=15"));
        }
}
