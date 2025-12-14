package io.github.hvogel.clientes.model.grafico;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class GraficoTipoServicoTest {

    @Test
    void testBuilderAndGetters() {
        GraficoTipoServico grafico = GraficoTipoServico.builder()
                .withMesAno(Collections.singletonList("Jan"))
                .withUnitario(Collections.singletonList(10))
                .withPacote(Collections.singletonList(20))
                .build();

        assertNotNull(grafico);
        assertEquals("Jan", grafico.getMonthYear().get(0));
        assertEquals(10, grafico.getUnitario().get(0));
        assertEquals(20, grafico.getPacote().get(0));
    }

    @Test
    void testSetters() {
        GraficoTipoServico grafico = GraficoTipoServico.builder().build();
        grafico.setMonthYear(Collections.singletonList("Feb"));
        grafico.setUnitario(Collections.singletonList(30));
        grafico.setPacote(Collections.singletonList(40));

        assertEquals("Feb", grafico.getMonthYear().get(0));
        assertEquals(30, grafico.getUnitario().get(0));
        assertEquals(40, grafico.getPacote().get(0));
    }

    @Test
    void testEqualsAndHashCode() {
        GraficoTipoServico g1 = GraficoTipoServico.builder().withMesAno(Collections.singletonList("A")).build();
        GraficoTipoServico g2 = GraficoTipoServico.builder().withMesAno(Collections.singletonList("A")).build();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    @Test
    void testToString() {
        GraficoTipoServico g1 = GraficoTipoServico.builder().build();
        assertNotNull(g1.toString());
    }
}
