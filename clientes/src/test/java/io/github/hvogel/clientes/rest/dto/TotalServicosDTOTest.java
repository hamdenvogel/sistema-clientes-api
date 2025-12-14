package io.github.hvogel.clientes.rest.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TotalServicosDTOTest {

        @Test
        void testBuilder() {
                TotalServicosDTO dto = TotalServicosDTO.builder()
                                .withTotalServicos(50L)
                                .build();

                assertNotNull(dto);
                assertEquals(50L, dto.getTotalServicos());
        }

        @Test
        void testBuilderZero() {
                TotalServicosDTO dto = TotalServicosDTO.builder()
                                .withTotalServicos(0L)
                                .build();

                assertNotNull(dto);
                assertEquals(0L, dto.getTotalServicos());
        }

        @Test
        void testSetters() {
                TotalServicosDTO dto = TotalServicosDTO.builder()
                                .withTotalServicos(25L)
                                .build();

                dto.setTotalServicos(100L);

                assertEquals(100L, dto.getTotalServicos());
        }

        @Test
        void testEquals() {
                TotalServicosDTO dto1 = TotalServicosDTO.builder()
                                .withTotalServicos(50L)
                                .build();

                TotalServicosDTO dto2 = TotalServicosDTO.builder()
                                .withTotalServicos(50L)
                                .build();

                TotalServicosDTO dto3 = TotalServicosDTO.builder()
                                .withTotalServicos(100L)
                                .build();

                assertEquals(dto1, dto2);
                assertNotEquals(dto1, dto3);
                assertEquals(dto1, dto1);
                assertNotEquals(null, dto1);
                assertNotEquals(dto1, new Object());
        }

        @Test
        void testHashCode() {
                TotalServicosDTO dto1 = TotalServicosDTO.builder()
                                .withTotalServicos(50L)
                                .build();

                TotalServicosDTO dto2 = TotalServicosDTO.builder()
                                .withTotalServicos(50L)
                                .build();

                assertEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        void testToString() {
                TotalServicosDTO dto = TotalServicosDTO.builder()
                                .withTotalServicos(50L)
                                .build();

                String result = dto.toString();

                assertNotNull(result);
                assertTrue(result.contains("totalServicos=50"));
        }

        @Test
        void testLargeNumber() {
                TotalServicosDTO dto = TotalServicosDTO.builder()
                                .withTotalServicos(1000000L)
                                .build();

                assertEquals(1000000L, dto.getTotalServicos());
        }
}
