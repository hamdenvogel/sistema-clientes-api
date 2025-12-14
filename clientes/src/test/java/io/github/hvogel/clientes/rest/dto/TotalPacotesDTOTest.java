package io.github.hvogel.clientes.rest.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TotalPacotesDTOTest {

        @Test
        void testBuilder() {
                TotalPacotesDTO dto = TotalPacotesDTO.builder()
                                .withTotalPacotes(12L)
                                .build();

                assertNotNull(dto);
                assertEquals(12L, dto.getTotalPacotes());
        }

        @Test
        void testBuilderZero() {
                TotalPacotesDTO dto = TotalPacotesDTO.builder()
                                .withTotalPacotes(0L)
                                .build();

                assertNotNull(dto);
                assertEquals(0L, dto.getTotalPacotes());
        }

        @Test
        void testSetters() {
                TotalPacotesDTO dto = TotalPacotesDTO.builder()
                                .withTotalPacotes(8L)
                                .build();

                dto.setTotalPacotes(20L);

                assertEquals(20L, dto.getTotalPacotes());
        }

        @Test
        void testEquals() {
                TotalPacotesDTO dto1 = TotalPacotesDTO.builder()
                                .withTotalPacotes(12L)
                                .build();

                TotalPacotesDTO dto2 = TotalPacotesDTO.builder()
                                .withTotalPacotes(12L)
                                .build();

                TotalPacotesDTO dto3 = TotalPacotesDTO.builder()
                                .withTotalPacotes(20L)
                                .build();

                assertEquals(dto1, dto2);
                assertNotEquals(dto1, dto3);
                assertEquals(dto1, dto1);
                assertNotEquals(null, dto1);
                assertNotEquals(dto1, new Object());
        }

        @Test
        void testHashCode() {
                TotalPacotesDTO dto1 = TotalPacotesDTO.builder()
                                .withTotalPacotes(12L)
                                .build();

                TotalPacotesDTO dto2 = TotalPacotesDTO.builder()
                                .withTotalPacotes(12L)
                                .build();

                assertEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        void testToString() {
                TotalPacotesDTO dto = TotalPacotesDTO.builder()
                                .withTotalPacotes(12L)
                                .build();

                String result = dto.toString();

                assertNotNull(result);
                assertTrue(result.contains("totalPacotes=12"));
        }
}
