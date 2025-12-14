package io.github.hvogel.clientes.model.grafico;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class GraficoAtendimentoTortaTest {

    @Test
    void testBuilderAndGetters() {
        GraficoAtendimentoTorta grafico = GraficoAtendimentoTorta.builder()
                .withStatusAtendimento(Collections.singletonList("Label"))
                .withQuantidade(Collections.singletonList(10))
                .build();

        assertNotNull(grafico);
        assertEquals("Label", grafico.getStatusAtendimento().get(0));
        assertEquals(10, grafico.getQuantidade().get(0));
    }

    @Test
    void testSetters() {
        GraficoAtendimentoTorta grafico = GraficoAtendimentoTorta.builder().build();
        grafico.setStatusAtendimento(Collections.singletonList("New Label"));
        grafico.setQuantidade(Collections.singletonList(20));

        assertEquals("New Label", grafico.getStatusAtendimento().get(0));
        assertEquals(20, grafico.getQuantidade().get(0));
    }

    @Test
    void testEqualsAndHashCode() {
        GraficoAtendimentoTorta g1 = GraficoAtendimentoTorta.builder()
                .withStatusAtendimento(Collections.singletonList("A")).build();
        GraficoAtendimentoTorta g2 = GraficoAtendimentoTorta.builder()
                .withStatusAtendimento(Collections.singletonList("A")).build();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    @Test
    void testToString() {
        GraficoAtendimentoTorta g1 = GraficoAtendimentoTorta.builder()
                .withStatusAtendimento(Collections.singletonList("A")).build();
        assertNotNull(g1.toString());
    }
}
