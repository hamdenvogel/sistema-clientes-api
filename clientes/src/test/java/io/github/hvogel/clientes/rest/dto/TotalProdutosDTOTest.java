package io.github.hvogel.clientes.rest.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TotalProdutosDTOTest {

        @Test
        void testBuilder() {
                TotalProdutosDTO dto = TotalProdutosDTO.builder()
                                .withTotalProdutos(25L)
                                .build();

                assertNotNull(dto);
                assertEquals(25L, dto.getTotalProdutos());
        }

        @Test
        void testBuilderZero() {
                TotalProdutosDTO dto = TotalProdutosDTO.builder()
                                .withTotalProdutos(0L)
                                .build();

                assertNotNull(dto);
                assertEquals(0L, dto.getTotalProdutos());
        }

        @Test
        void testSetters() {
                TotalProdutosDTO dto = TotalProdutosDTO.builder()
                                .withTotalProdutos(10L)
                                .build();

                dto.setTotalProdutos(50L);

                assertEquals(50L, dto.getTotalProdutos());
        }

        @Test
        void testEquals() {
                TotalProdutosDTO dto1 = TotalProdutosDTO.builder()
                                .withTotalProdutos(25L)
                                .build();

                TotalProdutosDTO dto2 = TotalProdutosDTO.builder()
                                .withTotalProdutos(25L)
                                .build();

                TotalProdutosDTO dto3 = TotalProdutosDTO.builder()
                                .withTotalProdutos(50L)
                                .build();

                assertEquals(dto1, dto2);
                assertNotEquals(dto1, dto3);
                assertEquals(dto1, dto1);
                assertNotEquals(null, dto1);
                assertNotEquals(dto1, new Object());
        }

        @Test
        void testHashCode() {
                TotalProdutosDTO dto1 = TotalProdutosDTO.builder()
                                .withTotalProdutos(25L)
                                .build();

                TotalProdutosDTO dto2 = TotalProdutosDTO.builder()
                                .withTotalProdutos(25L)
                                .build();

                assertEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        void testToString() {
                TotalProdutosDTO dto = TotalProdutosDTO.builder()
                                .withTotalProdutos(25L)
                                .build();

                String result = dto.toString();

                assertNotNull(result);
                assertTrue(result.contains("totalProdutos=25"));
        }
}
