package io.github.hvogel.clientes.model.grafico;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class GraficoAtendimentoLinearTest {

    @Test
    void testBuilderAndGetters() {
        GraficoAtendimentoLinear grafico = GraficoAtendimentoLinear.builder()
                .withMesAno(Arrays.asList("Jan", "Feb"))
                .withEmAtendimento(Arrays.asList(1, 2))
                .withCancelado(Arrays.asList(0, 1))
                .withFinalizado(Arrays.asList(5, 6))
                .build();

        assertNotNull(grafico);
        assertEquals(2, grafico.getMonthYear().size());
        assertEquals("Jan", grafico.getMonthYear().get(0));
        assertEquals(1, grafico.getEmAtendimento().get(0));
        assertEquals(0, grafico.getCancelado().get(0));
        assertEquals(5, grafico.getFinalizado().get(0));
    }

    @Test
    void testSetters() {
        GraficoAtendimentoLinear grafico = GraficoAtendimentoLinear.builder().build();
        grafico.setMonthYear(Collections.singletonList("Mar"));
        grafico.setEmAtendimento(Collections.singletonList(10));
        grafico.setCancelado(Collections.singletonList(2));
        grafico.setFinalizado(Collections.singletonList(20));

        assertEquals("Mar", grafico.getMonthYear().get(0));
        assertEquals(10, grafico.getEmAtendimento().get(0));
        assertEquals(2, grafico.getCancelado().get(0));
        assertEquals(20, grafico.getFinalizado().get(0));
    }

    @Test
    void testEqualsAndHashCode() {
        GraficoAtendimentoLinear g1 = GraficoAtendimentoLinear.builder()
                .withMesAno(Collections.singletonList("A"))
                .build();

        GraficoAtendimentoLinear g2 = GraficoAtendimentoLinear.builder()
                .withMesAno(Collections.singletonList("A"))
                .build();

        GraficoAtendimentoLinear g3 = GraficoAtendimentoLinear.builder()
                .withMesAno(Collections.singletonList("B"))
                .build();

        assertEquals(g1, g2);
        assertNotEquals(g1, g3);
        assertEquals(g1.hashCode(), g2.hashCode());
        assertNotEquals(g1.hashCode(), g3.hashCode());
    }

    @Test
    void testToString() {
        GraficoAtendimentoLinear g1 = GraficoAtendimentoLinear.builder()
                .withMesAno(Collections.singletonList("A"))
                .build();
        assertNotNull(g1.toString());
    }
}
