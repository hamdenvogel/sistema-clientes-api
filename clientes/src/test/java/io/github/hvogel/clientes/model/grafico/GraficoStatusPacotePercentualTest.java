package io.github.hvogel.clientes.model.grafico;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class GraficoStatusPacotePercentualTest {

    @Test
    void testBuilderAndGetters() {
        GraficoStatusPacotePercentual grafico = GraficoStatusPacotePercentual.builder()
                .withIniciadoPercentual(Collections.singletonList(BigDecimal.ONE))
                .withAprovadoPercentual(Collections.singletonList(BigDecimal.TEN))
                .withExecutandoPercentual(Collections.singletonList(BigDecimal.ZERO))
                .withCanceladoPercentual(Collections.singletonList(BigDecimal.valueOf(5)))
                .withFinalizadoPercentual(Collections.singletonList(BigDecimal.valueOf(100)))
                .build();

        assertNotNull(grafico);
        assertEquals(BigDecimal.ONE, grafico.getIniciadoPercentual().get(0));
        assertEquals(BigDecimal.TEN, grafico.getAprovadoPercentual().get(0));
        assertEquals(BigDecimal.ZERO, grafico.getExecutandoPercentual().get(0));
        assertEquals(BigDecimal.valueOf(5), grafico.getCanceladoPercentual().get(0));
        assertEquals(BigDecimal.valueOf(100), grafico.getFinalizadoPercentual().get(0));
    }

    @Test
    void testSetters() {
        GraficoStatusPacotePercentual grafico = GraficoStatusPacotePercentual.builder().build();
        grafico.setIniciadoPercentual(Collections.singletonList(BigDecimal.ONE));
        grafico.setAprovadoPercentual(Collections.singletonList(BigDecimal.TEN));
        grafico.setExecutandoPercentual(Collections.singletonList(BigDecimal.ZERO));
        grafico.setCanceladoPercentual(Collections.singletonList(BigDecimal.valueOf(5)));
        grafico.setFinalizadoPercentual(Collections.singletonList(BigDecimal.valueOf(100)));

        assertEquals(BigDecimal.ONE, grafico.getIniciadoPercentual().get(0));
        assertEquals(BigDecimal.TEN, grafico.getAprovadoPercentual().get(0));
        assertEquals(BigDecimal.ZERO, grafico.getExecutandoPercentual().get(0));
        assertEquals(BigDecimal.valueOf(5), grafico.getCanceladoPercentual().get(0));
        assertEquals(BigDecimal.valueOf(100), grafico.getFinalizadoPercentual().get(0));
    }

    @Test
    void testEqualsAndHashCode() {
        GraficoStatusPacotePercentual g1 = GraficoStatusPacotePercentual.builder()
                .withIniciadoPercentual(Collections.singletonList(BigDecimal.ONE)).build();
        GraficoStatusPacotePercentual g2 = GraficoStatusPacotePercentual.builder()
                .withIniciadoPercentual(Collections.singletonList(BigDecimal.ONE)).build();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    @Test
    void testToString() {
        GraficoStatusPacotePercentual g1 = GraficoStatusPacotePercentual.builder().build();
        assertNotNull(g1.toString());
    }
}
