package io.github.hvogel.clientes.rest.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TotalRegistrosDTOTest {

        @Test
        void testBuilder() {
                TotalRegistrosDTO dto = TotalRegistrosDTO.builder()
                                .withTotal(500)
                                .build();

                assertNotNull(dto);
                assertEquals(500, dto.getTotal());
        }

        @Test
        void testBuilderZero() {
                TotalRegistrosDTO dto = TotalRegistrosDTO.builder()
                                .withTotal(0)
                                .build();

                assertNotNull(dto);
                assertEquals(0, dto.getTotal());
        }

        @Test
        void testSetters() {
                TotalRegistrosDTO dto = TotalRegistrosDTO.builder()
                                .withTotal(100)
                                .build();

                dto.setTotal(250);

                assertEquals(250, dto.getTotal());
        }

        @Test
        void testEquals() {
                TotalRegistrosDTO dto1 = TotalRegistrosDTO.builder()
                                .withTotal(500)
                                .build();

                TotalRegistrosDTO dto2 = TotalRegistrosDTO.builder()
                                .withTotal(500)
                                .build();

                TotalRegistrosDTO dto3 = TotalRegistrosDTO.builder()
                                .withTotal(250)
                                .build();

                assertEquals(dto1, dto2);
                assertNotEquals(dto1, dto3);
                assertEquals(dto1, dto1);
                assertNotEquals(null, dto1);
                assertNotEquals(dto1, new Object());
        }

        @Test
        void testHashCode() {
                TotalRegistrosDTO dto1 = TotalRegistrosDTO.builder()
                                .withTotal(500)
                                .build();

                TotalRegistrosDTO dto2 = TotalRegistrosDTO.builder()
                                .withTotal(500)
                                .build();

                assertEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        void testToString() {
                TotalRegistrosDTO dto = TotalRegistrosDTO.builder()
                                .withTotal(500)
                                .build();

                String result = dto.toString();

                assertNotNull(result);
                assertTrue(result.contains("total=500"));
        }

        @Test
        void testNegativeNumber() {
                TotalRegistrosDTO dto = TotalRegistrosDTO.builder()
                                .withTotal(-1)
                                .build();

                assertEquals(-1, dto.getTotal());
        }
}
