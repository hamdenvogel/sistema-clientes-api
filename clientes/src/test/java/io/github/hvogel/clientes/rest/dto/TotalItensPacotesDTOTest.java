package io.github.hvogel.clientes.rest.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TotalItensPacotesDTOTest {

        @Test
        void testBuilder() {
                TotalItensPacotesDTO dto = TotalItensPacotesDTO.builder()
                                .withTotalItensPacotes(45L)
                                .build();

                assertNotNull(dto);
                assertEquals(45L, dto.getTotalItensPacotes());
        }

        @Test
        void testBuilderZero() {
                TotalItensPacotesDTO dto = TotalItensPacotesDTO.builder()
                                .withTotalItensPacotes(0L)
                                .build();

                assertNotNull(dto);
                assertEquals(0L, dto.getTotalItensPacotes());
        }

        @Test
        void testSetters() {
                TotalItensPacotesDTO dto = TotalItensPacotesDTO.builder()
                                .withTotalItensPacotes(20L)
                                .build();

                dto.setTotalItensPacotes(60L);

                assertEquals(60L, dto.getTotalItensPacotes());
        }

        @Test
        void testEquals() {
                TotalItensPacotesDTO dto1 = TotalItensPacotesDTO.builder()
                                .withTotalItensPacotes(45L)
                                .build();

                TotalItensPacotesDTO dto2 = TotalItensPacotesDTO.builder()
                                .withTotalItensPacotes(45L)
                                .build();

                TotalItensPacotesDTO dto3 = TotalItensPacotesDTO.builder()
                                .withTotalItensPacotes(60L)
                                .build();

                assertEquals(dto1, dto2);
                assertNotEquals(dto1, dto3);
                assertEquals(dto1, dto1);
                assertNotEquals(null, dto1);
                assertNotEquals(dto1, new Object());
        }

        @Test
        void testHashCode() {
                TotalItensPacotesDTO dto1 = TotalItensPacotesDTO.builder()
                                .withTotalItensPacotes(45L)
                                .build();

                TotalItensPacotesDTO dto2 = TotalItensPacotesDTO.builder()
                                .withTotalItensPacotes(45L)
                                .build();

                assertEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        void testToString() {
                TotalItensPacotesDTO dto = TotalItensPacotesDTO.builder()
                                .withTotalItensPacotes(45L)
                                .build();

                String result = dto.toString();

                assertNotNull(result);
                assertTrue(result.contains("totalItensPacotes=45"));
        }
}
