package io.github.hvogel.clientes.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.hvogel.clientes.model.entity.Atividade;
import io.github.hvogel.clientes.model.entity.Cliente;
import io.github.hvogel.clientes.model.entity.Natureza;
import io.github.hvogel.clientes.model.entity.Prestador;
import io.github.hvogel.clientes.model.entity.ServicoPrestado;
import io.github.hvogel.clientes.rest.dto.ServicoPrestadoDTO;
import io.github.hvogel.clientes.rest.dto.ServicoPrestadoProjectionDTO;
import io.github.hvogel.clientes.service.AtividadeService;
import io.github.hvogel.clientes.service.ClienteService;
import io.github.hvogel.clientes.service.ItemPacoteService;
import io.github.hvogel.clientes.service.NaturezaService;
import io.github.hvogel.clientes.service.PrestadorService;
import io.github.hvogel.clientes.service.RelatorioService;
import io.github.hvogel.clientes.service.ServicoPrestadoService;
import io.github.hvogel.clientes.service.TotalServicosService;
import io.github.hvogel.clientes.service.ValidadorService;
import io.github.hvogel.clientes.util.HttpServletReqUtil;

@WebMvcTest(ServicoPrestadoController.class)
@AutoConfigureMockMvc(addFilters = false)
class ServicoPrestadoControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private ClienteService clienteService;

        @MockBean
        private ServicoPrestadoService service;

        @MockBean
        private TotalServicosService totalServicosService;

        @MockBean
        private ValidadorService validadorService;

        @MockBean
        private PrestadorService prestadorService;

        @MockBean
        private NaturezaService naturezaService;

        @MockBean
        private ItemPacoteService itemPacoteService;

        @MockBean
        private AtividadeService atividadeService;

        @MockBean
        private RelatorioService relatorioService;

        @MockBean
        private HttpServletReqUtil reqUtil;

        @MockBean
        private io.github.hvogel.clientes.security.jwt.JwtUtils jwtUtils;

        @MockBean
        private io.github.hvogel.clientes.service.impl.UserDetailsServiceImpl userDetailsService;

        @MockBean
        private io.github.hvogel.clientes.security.jwt.AuthEntryPointJwt unauthorizedHandler;

        @Test
        void testSalvar() throws Exception {
                ServicoPrestadoDTO dto = new ServicoPrestadoDTO();
                dto.setDescricao("Serviço Teste");
                dto.setPreco("100.00");
                dto.setData("01/01/2023");
                dto.setIdCliente(1);
                dto.setStatus(io.github.hvogel.clientes.enums.StatusServico.E);
                dto.setTipo("Tipo Teste");
                dto.setIdNatureza(1L);
                dto.setIdAtividade(1L);

                ServicoPrestado entity = new ServicoPrestado();
                entity.setId(1);

                Cliente cliente = new Cliente();
                cliente.setId(1);
                when(clienteService.obterPorId(1)).thenReturn(Optional.of(cliente));
                when(naturezaService.obterPorId(1L)).thenReturn(Optional.of(new Natureza()));
                when(atividadeService.obterPorId(1L)).thenReturn(Optional.of(new Atividade()));

                doNothing().when(service).validarValoresIniciais(anyString(), anyString(), anyString(), anyInt(),
                                anyString(), anyString(), anyString());

                when(service.salvar(any(ServicoPrestado.class))).thenReturn(entity);

                mockMvc.perform(post("/api/servicos-prestados")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andExpect(status().isCreated());
        }

        @Test
        void testAtualizar() throws Exception {
                Integer id = 1;
                ServicoPrestadoDTO dto = new ServicoPrestadoDTO();
                dto.setDescricao("Serviço Atualizado");
                dto.setPreco("150.00");
                dto.setData("01/02/2023");
                dto.setIdCliente(1);
                dto.setStatus(io.github.hvogel.clientes.enums.StatusServico.E);
                dto.setTipo("Tipo Atualizado");
                dto.setIdNatureza(1L);
                dto.setIdAtividade(1L);
                dto.setIdPrestador(1);

                ServicoPrestado entity = new ServicoPrestado();
                entity.setId(id);

                Cliente cliente = new Cliente();
                cliente.setId(1);

                when(service.obterPorId(id)).thenReturn(Optional.of(entity));
                when(clienteService.obterPorId(1)).thenReturn(Optional.of(cliente));
                when(prestadorService.obterPorId(1)).thenReturn(Optional.of(new Prestador()));
                when(naturezaService.findOneById(1L)).thenReturn(Optional.of(new Natureza()));
                when(atividadeService.obterPorId(1L)).thenReturn(Optional.of(new Atividade()));
                when(validadorService.converter(anyString())).thenReturn(BigDecimal.valueOf(150.00));
                when(service.atualizar(any(ServicoPrestado.class))).thenReturn(entity);

                mockMvc.perform(put("/api/servicos-prestados/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.mensagem").value("Serviço atualizado com sucesso."));
        }

        @Test
        void testDeletar() throws Exception {
                Integer id = 1;
                ServicoPrestado entity = new ServicoPrestado();
                entity.setId(id);

                when(service.obterPorId(id)).thenReturn(Optional.of(entity));
                when(itemPacoteService.existsByServicoPrestado(entity)).thenReturn(false);
                doNothing().when(service).deletar(entity);

                mockMvc.perform(delete("/api/servicos-prestados/" + id))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.mensagem").value("Serviço deletado com sucesso."));
        }

        @Test
        void testDeletarServicosDeUmCliente() throws Exception {
                Integer idCliente = 1;
                Cliente cliente = new Cliente();
                cliente.setId(idCliente);
                cliente.setNome("Cliente Teste");

                when(clienteService.obterPorId(idCliente)).thenReturn(Optional.of(cliente));
                when(service.existsByCliente(cliente)).thenReturn(true);
                doNothing().when(service).deletarPorCliente(idCliente);

                mockMvc.perform(delete("/api/servicos-prestados/deleta-servicos-cliente/" + idCliente))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.mensagem")
                                                .value("Serviços do cliente Cliente Teste deletados com sucesso."));
        }

        @Test
        void testAcharPorId() throws Exception {
                Integer id = 1;
                ServicoPrestado entity = new ServicoPrestado();
                entity.setId(id);
                entity.setDescricao("Serviço Encontrado");

                when(service.obterPorId(id)).thenReturn(Optional.of(entity));

                mockMvc.perform(get("/api/servicos-prestados/" + id))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.descricao").value("Serviço Encontrado"));
        }

        @Test
        void testPesquisar() throws Exception {
                ServicoPrestado entity = new ServicoPrestado();
                entity.setId(1);
                entity.setDescricao("Serviço Teste");

                when(service.pesquisarNomeClienteEMes(anyString(), anyInt()))
                                .thenReturn(Collections.singletonList(entity));

                mockMvc.perform(get("/api/servicos-prestados")
                                .param("nome", "Fulano")
                                .param("mes", "1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].descricao").value("Serviço Teste"));
        }

        @Test
        void testPesquisarParcialPorDescricao() throws Exception {
                String descricao = "Teste";
                ServicoPrestado entity = new ServicoPrestado();
                entity.setDescricao("Teste Parcial");

                when(service.pesquisarParcialPorDescricao(descricao))
                                .thenReturn(Collections.singletonList(entity));

                mockMvc.perform(get("/api/servicos-prestados/pesquisa-descricao/" + descricao))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].descricao").value("Teste Parcial"));
        }

        @Test
        void testObterTotalServicos() throws Exception {
                when(totalServicosService.obterTotalServicos()).thenReturn(10L);

                mockMvc.perform(get("/api/servicos-prestados/totalServicos"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.totalServicos").value(10));
        }

        @Test
        void testPesquisarPorCliente() throws Exception {
                Integer idCliente = 1;
                Cliente cliente = new Cliente();
                cliente.setId(idCliente);
                ServicoPrestado servico = new ServicoPrestado();
                servico.setDescricao("Serviço Cliente");

                when(clienteService.obterPorId(idCliente)).thenReturn(Optional.of(cliente));
                when(service.pesquisarPorCliente(cliente)).thenReturn(Collections.singletonList(servico));

                mockMvc.perform(get("/api/servicos-prestados/pesquisa-cliente/" + idCliente))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].descricao").value("Serviço Cliente"));
        }

        @Test
        void testList() throws Exception {
                ServicoPrestado servico = new ServicoPrestado();
                servico.setDescricao("Paginação");
                PageImpl<ServicoPrestado> page = new PageImpl<>(Collections.singletonList(servico));

                when(service.recuperarTodos(any(Pageable.class))).thenReturn(page);

                mockMvc.perform(get("/api/servicos-prestados/pesquisa-paginada")
                                .param("page", "0")
                                .param("size", "10")
                                .param("sort", "id,asc"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.content[0].descricao").value("Paginação"));
        }

        @Test
        void testPesquisaAvancada() throws Exception {
                ServicoPrestado servico = new ServicoPrestado();
                servico.setDescricao("Avançada");
                PageImpl<ServicoPrestado> page = new PageImpl<>(Collections.singletonList(servico));

                when(service.pesquisaAvancada(any(ServicoPrestado.class), any(Pageable.class))).thenReturn(page);

                mockMvc.perform(get("/api/servicos-prestados/pesquisa-avancada")
                                .param("page", "0")
                                .param("size", "10")
                                .param("sort", "id,asc")
                                .param("descricao", "Avançada"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.content[0].descricao").value("Avançada"));
        }

        @Test
        void testObterConsultaListaServicos() throws Exception {
                ServicoPrestadoProjectionDTO projection = new ServicoPrestadoProjectionDTO("Projection", 1, "Cliente",
                                "Pix");

                when(service.findAllServicoPrestadoProjectionDTO()).thenReturn(Collections.singletonList(projection));

                mockMvc.perform(get("/api/servicos-prestados/consulta-lista-servicos"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].descricao").value("Projection"));
        }

        @Test
        void testRelatorioServicosPrestados() throws Exception {
                byte[] pdfContent = "PDF Content".getBytes();
                when(relatorioService.gerarRelatorioServicosPrestados(any(), any())).thenReturn(pdfContent);

                mockMvc.perform(get("/api/servicos-prestados/relatorio")
                                .param("inicio", "01/01/2023")
                                .param("fim", "31/01/2023"))
                                .andExpect(status().isOk())
                                .andExpect(content().bytes(pdfContent));
        }

        @Test
        void testSalvar_StatusInvalido() throws Exception {
                ServicoPrestadoDTO dto = new ServicoPrestadoDTO();
                dto.setDescricao("Serviço Teste");
                dto.setData("01/01/2023");
                dto.setPreco("100.00");
                dto.setIdCliente(1);
                // Status null

                mockMvc.perform(post("/api/servicos-prestados")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void testSalvar_ClienteInexistente() throws Exception {
                ServicoPrestadoDTO dto = new ServicoPrestadoDTO();
                dto.setDescricao("Serviço Teste");
                dto.setData("01/01/2023");
                dto.setPreco("100.00");
                dto.setIdCliente(1);
                dto.setStatus(io.github.hvogel.clientes.enums.StatusServico.E);
                dto.setTipo("Tipo Teste");
                dto.setIdNatureza(1L);
                dto.setIdAtividade(1L);

                when(clienteService.obterPorId(1)).thenReturn(Optional.empty());

                mockMvc.perform(post("/api/servicos-prestados")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andExpect(status().isNotFound());
        }

        @Test
        void testSalvar_PrestadorInexistente() throws Exception {
                ServicoPrestadoDTO dto = new ServicoPrestadoDTO();
                dto.setDescricao("Serviço Teste");
                dto.setData("01/01/2023");
                dto.setPreco("100.00");
                dto.setIdCliente(1);
                dto.setIdPrestador(99); // ID invalido
                dto.setStatus(io.github.hvogel.clientes.enums.StatusServico.E);
                dto.setTipo("Tipo Teste");
                dto.setIdNatureza(1L);
                dto.setIdAtividade(1L);

                when(clienteService.obterPorId(1)).thenReturn(Optional.of(new Cliente()));
                when(prestadorService.obterPorId(99)).thenReturn(Optional.empty());

                mockMvc.perform(post("/api/servicos-prestados")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andExpect(status().isNotFound());
        }

        @Test
        void testAtualizar_ServicoNaoEncontrado() throws Exception {
                Integer id = 99;
                ServicoPrestadoDTO dto = new ServicoPrestadoDTO();
                dto.setDescricao("Serviço Atualizado");
                dto.setPreco("150.00");
                dto.setData("01/02/2023");
                dto.setIdCliente(1);
                dto.setStatus(io.github.hvogel.clientes.enums.StatusServico.E);
                dto.setTipo("Tipo Teste");
                dto.setIdNatureza(1L);
                dto.setIdAtividade(1L);

                when(clienteService.obterPorId(1)).thenReturn(Optional.of(new Cliente()));
                when(naturezaService.findOneById(1L)).thenReturn(Optional.of(new Natureza()));
                when(atividadeService.obterPorId(1L)).thenReturn(Optional.of(new Atividade()));
                when(service.obterPorId(id)).thenReturn(Optional.empty());

                mockMvc.perform(put("/api/servicos-prestados/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andExpect(status().isNotFound());
        }

        @Test
        void testDeletar_ServicoNaoEncontrado() throws Exception {
                Integer id = 99;
                when(service.obterPorId(id)).thenReturn(Optional.empty());

                mockMvc.perform(delete("/api/servicos-prestados/" + id))
                                .andExpect(status().isNotFound());
        }

        @Test
        void testDeletar_ServicoVinculadoPacote() throws Exception {
                Integer id = 1;
                ServicoPrestado entity = new ServicoPrestado();
                entity.setId(id);

                when(service.obterPorId(id)).thenReturn(Optional.of(entity));
                when(itemPacoteService.existsByServicoPrestado(entity)).thenReturn(true);

                mockMvc.perform(delete("/api/servicos-prestados/" + id))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void testDeletarServicosDeUmCliente_SemServicos() throws Exception {
                Integer id = 1;
                Cliente cliente = new Cliente();
                cliente.setId(id);

                when(clienteService.obterPorId(id)).thenReturn(Optional.of(cliente));
                when(service.existsByCliente(cliente)).thenReturn(false);

                mockMvc.perform(delete("/api/servicos-prestados/deleta-servicos-cliente/" + id))
                                .andExpect(status().isNotFound());
        }

        @Test
        void testDeletarServicosDeUmCliente_ClienteInexistente() throws Exception {
                Integer id = 99;
                when(clienteService.obterPorId(id)).thenReturn(Optional.empty());

                mockMvc.perform(delete("/api/servicos-prestados/deleta-servicos-cliente/" + id))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void testList_MultiploSort() throws Exception {
                ServicoPrestado servico = new ServicoPrestado();
                servico.setDescricao("Paginação Sort");
                PageImpl<ServicoPrestado> page = new PageImpl<>(Collections.singletonList(servico));

                when(service.recuperarTodos(any(Pageable.class))).thenReturn(page);

                // Test parsing of sort=["field,direction", "field2,direction"]
                mockMvc.perform(get("/api/servicos-prestados/pesquisa-paginada")
                                .param("page", "0")
                                .param("size", "10")
                                .param("sort", "id,asc")
                                .param("sort", "descricao,desc"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.content[0].descricao").value("Paginação Sort"));
        }

        @Test
        void testPesquisaAvancada_Completa() throws Exception {
                ServicoPrestado servico = new ServicoPrestado();
                servico.setDescricao("Avançada Completa");
                PageImpl<ServicoPrestado> page = new PageImpl<>(Collections.singletonList(servico));

                when(service.pesquisarPeloNomeDoCliente(anyString(), any(Pageable.class))).thenReturn(page);
                // When "cliente.nome" is provided, it calls pesquisarPeloNomeDoCliente

                mockMvc.perform(get("/api/servicos-prestados/pesquisa-avancada")
                                .param("page", "0")
                                .param("size", "10")
                                .param("cliente.nome", "Fulano"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.content[0].descricao").value("Avançada Completa"));

                // Test "Orfaos" branch
                when(service.obterServicosAindaNaoVinculados(any(Pageable.class))).thenReturn(page);
                mockMvc.perform(get("/api/servicos-prestados/pesquisa-avancada")
                                .param("orfaos", "true"))
                                .andExpect(status().isOk());

                // Test "Status" and "Cliente ID" branch
                when(clienteService.obterPorId(anyInt())).thenReturn(Optional.of(new Cliente()));
                when(service.pesquisaAvancada(any(ServicoPrestado.class), any(Pageable.class))).thenReturn(page);

                mockMvc.perform(get("/api/servicos-prestados/pesquisa-avancada")
                                .param("cliente", "1")
                                .param("status", "E"))
                                .andExpect(status().isOk());
        }

        @Test
        void testSalvar_ComPrestador() throws Exception {
                ServicoPrestadoDTO dto = new ServicoPrestadoDTO();
                dto.setDescricao("Serviço Completo");
                dto.setPreco("100.00");
                dto.setData("01/01/2023");
                dto.setIdCliente(1);
                dto.setStatus(io.github.hvogel.clientes.enums.StatusServico.E);
                dto.setTipo("Tipo Teste");
                dto.setIdNatureza(1L);
                dto.setIdAtividade(1L);
                dto.setIdPrestador(1);

                ServicoPrestado entity = new ServicoPrestado();
                entity.setId(1);

                Cliente cliente = new Cliente();
                cliente.setId(1);
                when(clienteService.obterPorId(1)).thenReturn(Optional.of(cliente));
                when(naturezaService.obterPorId(1L)).thenReturn(Optional.of(new Natureza()));
                when(atividadeService.obterPorId(1L)).thenReturn(Optional.of(new Atividade()));
                when(prestadorService.obterPorId(1)).thenReturn(Optional.of(new Prestador()));

                doNothing().when(service).validarValoresIniciais(anyString(), anyString(), anyString(), anyInt(),
                                anyString(), anyString(), anyString());

                when(service.salvar(any(ServicoPrestado.class))).thenReturn(entity);

                mockMvc.perform(post("/api/servicos-prestados")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andExpect(status().isCreated());
        }

        @Test
        void testAtualizar_Completo() throws Exception {
                Integer id = 1;
                ServicoPrestadoDTO dto = new ServicoPrestadoDTO();
                dto.setDescricao("Serviço Atualizado Completo");
                dto.setPreco("150.00");
                dto.setData("01/02/2023");
                dto.setIdCliente(1);
                dto.setStatus(io.github.hvogel.clientes.enums.StatusServico.E);
                dto.setTipo("Tipo Atualizado");
                dto.setIdNatureza(1L);
                dto.setIdAtividade(1L);
                dto.setIdPrestador(1);

                ServicoPrestado entity = new ServicoPrestado();
                entity.setId(id);

                Cliente cliente = new Cliente();
                cliente.setId(1);

                when(service.obterPorId(id)).thenReturn(Optional.of(entity));
                when(clienteService.obterPorId(1)).thenReturn(Optional.of(cliente));
                when(prestadorService.obterPorId(1)).thenReturn(Optional.of(new Prestador()));
                when(naturezaService.findOneById(1L)).thenReturn(Optional.of(new Natureza()));
                when(atividadeService.obterPorId(1L)).thenReturn(Optional.of(new Atividade()));
                when(validadorService.converter(anyString())).thenReturn(BigDecimal.valueOf(150.00));

                // Mock atualizar to return entity
                when(service.atualizar(any(ServicoPrestado.class))).thenReturn(entity);

                mockMvc.perform(put("/api/servicos-prestados/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.mensagem").value("Serviço atualizado com sucesso."));
        }
}
