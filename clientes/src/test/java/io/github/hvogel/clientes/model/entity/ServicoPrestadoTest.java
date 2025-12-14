package io.github.hvogel.clientes.model.entity;

import io.github.hvogel.clientes.enums.StatusServico;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ServicoPrestadoTest {

    @Test
    void testGettersAndSetters() {
        ServicoPrestado servico = new ServicoPrestado();

        servico.setId(1);
        servico.setDescricao("Servico Teste");
        servico.setValor(new BigDecimal("150.00"));
        servico.setData(LocalDate.of(2025, 1, 15));
        servico.setDataConclusao(LocalDate.of(2025, 1, 20));
        servico.setStatus(StatusServico.E);
        servico.setCaptcha("abc123");
        servico.setTipo("M");
        servico.setLocalAtendimento("Rua A, 123");
        servico.setConclusao("Concluido com sucesso");

        Cliente cliente = new Cliente();
        cliente.setId(100);
        servico.setCliente(cliente);

        Prestador prestador = new Prestador();
        prestador.setId(200);
        servico.setPrestador(prestador);

        Natureza natureza = new Natureza();
        natureza.setId(1L);
        servico.setNatureza(natureza);

        Atividade atividade = new Atividade();
        atividade.setId(1L);
        servico.setAtividade(atividade);

        assertEquals(1, servico.getId());
        assertEquals("Servico Teste", servico.getDescricao());
        assertEquals(new BigDecimal("150.00"), servico.getValor());
        assertEquals(LocalDate.of(2025, 1, 15), servico.getData());
        assertEquals(LocalDate.of(2025, 1, 20), servico.getDataConclusao());
        assertEquals(StatusServico.E, servico.getStatus());
        assertEquals("abc123", servico.getCaptcha());
        assertEquals("M", servico.getTipo());
        assertEquals("Rua A, 123", servico.getLocalAtendimento());
        assertEquals("Concluido com sucesso", servico.getConclusao());
        assertEquals(100, servico.getCliente().getId());
        assertEquals(200, servico.getPrestador().getId());
        assertEquals(1L, servico.getNatureza().getId());
        assertEquals(1L, servico.getAtividade().getId());
    }

    @Test
    void testEquals() {
        ServicoPrestado servico1 = new ServicoPrestado();
        servico1.setId(1);
        servico1.setDescricao("Teste");
        servico1.setStatus(StatusServico.E);

        ServicoPrestado servico2 = new ServicoPrestado();
        servico2.setId(1);
        servico2.setDescricao("Teste");
        servico2.setStatus(StatusServico.E);

        ServicoPrestado servico3 = new ServicoPrestado();
        servico3.setId(2);
        servico3.setDescricao("Outro");
        servico3.setStatus(StatusServico.C);

        assertEquals(servico1, servico2);
        assertNotEquals(servico1, servico3);
        assertNotEquals(null, servico1);
        assertNotEquals(servico1, new Object());
        assertEquals(servico1, servico1);
    }

    @Test
    void testHashCode() {
        ServicoPrestado servico1 = new ServicoPrestado();
        servico1.setId(1);
        servico1.setDescricao("Teste");

        ServicoPrestado servico2 = new ServicoPrestado();
        servico2.setId(1);
        servico2.setDescricao("Teste");

        assertEquals(servico1.hashCode(), servico2.hashCode());
    }

    @Test
    void testToString() {
        ServicoPrestado servico = new ServicoPrestado();
        servico.setId(1);
        servico.setDescricao("Teste");
        servico.setStatus(StatusServico.E);

        String result = servico.toString();

        assertNotNull(result);
        assertTrue(result.contains("ServicoPrestado"));
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("descricao=Teste"));
        assertTrue(result.contains("status=E"));
    }
}
