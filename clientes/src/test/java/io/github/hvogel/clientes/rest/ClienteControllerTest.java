package io.github.hvogel.clientes.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.hvogel.clientes.model.entity.Cliente;
import io.github.hvogel.clientes.service.ClienteService;
import io.github.hvogel.clientes.service.RelatorioService;
import io.github.hvogel.clientes.service.TotalClientesService;
import io.github.hvogel.clientes.util.HttpServletReqUtil;

@WebMvcTest(ClienteController.class)
@AutoConfigureMockMvc(addFilters = false)
class ClienteControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private ClienteService service;

        @MockBean
        private TotalClientesService totalClientesService;

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
        void testSave() throws Exception {
                Cliente entity = new Cliente();
                entity.setId(1);
                entity.setNome("Fulano");
                entity.setCpf("529.982.247-25");

                when(service.salvar(any(Cliente.class))).thenReturn(entity);

                mockMvc.perform(post("/api/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(entity)))
                                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.nome").value("Fulano"));
        }

        @Test
        void testFindById() throws Exception {
                Cliente entity = new Cliente();
                entity.setId(1);
                entity.setNome("Fulano");

                when(service.obterPorId(1)).thenReturn(Optional.of(entity));

                mockMvc.perform(get("/api/clientes/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.nome").value("Fulano"));
        }

        @Test
        void testDelete() throws Exception {
                Cliente entity = new Cliente();
                entity.setId(1);
                when(service.obterPorId(1)).thenReturn(Optional.of(entity));
                doNothing().when(service).deletar(any(Cliente.class));

                mockMvc.perform(delete("/api/clientes/1"))
                                .andExpect(status().isOk());
        }

        @Test
        void testUpdate() throws Exception {
                Cliente entity = new Cliente();
                entity.setId(1);
                entity.setNome("Fulano Updated");
                entity.setCpf("529.982.247-25");

                when(service.obterPorId(1)).thenReturn(Optional.of(entity));
                when(service.atualizar(any(Cliente.class))).thenReturn(entity);

                mockMvc.perform(put("/api/clientes/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(entity)))
                                .andExpect(status().isOk());
        }

        @Test
        void testObterTodos() throws Exception {
                Cliente entity = new Cliente();
                entity.setNome("Fulano");
                when(service.obterTodos()).thenReturn(java.util.Collections.singletonList(entity));

                mockMvc.perform(get("/api/clientes"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].nome").value("Fulano"));
        }

        @Test
        void testObterListaNomeClientes() throws Exception {
                Cliente entity = new Cliente();
                entity.setNome("Fulano");
                when(service.obterTodos()).thenReturn(java.util.Collections.singletonList(entity));

                mockMvc.perform(get("/api/clientes/lista-nomes"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].nome").doesNotExist()); // DTO structure check needed, but
                                                                                  // list is empty
                                                                                  // objects in generic impl?
                // The implementation puts new ListaNomesDTO() which might be empty.
                // Checking size is safer.
        }

        @Test
        void testObterTotalClientes() throws Exception {
                when(totalClientesService.obterTotalClientes()).thenReturn(10L);

                mockMvc.perform(get("/api/clientes/totalClientes"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.totalClientes").value(10));
        }

        @Test
        void testRelatorio() throws Exception {
                byte[] pdfContent = new byte[] { 1, 2, 3 };
                when(relatorioService.gerarRelatorioCliente(any(), any(), any())).thenReturn(pdfContent);

                mockMvc.perform(get("/api/clientes/relatorio")
                                .param("id", "1")
                                .param("inicio", "01/01/2023")
                                .param("fim", "31/01/2023"))
                                .andExpect(status().isOk())
                                .andExpect(
                                                org.springframework.test.web.servlet.result.MockMvcResultMatchers
                                                                .content().bytes(pdfContent));
        }

        @Test
        void testListPaginated() throws Exception {
                // Test complex pagination/search logic
                mockMvc.perform(get("/api/clientes/pesquisa-paginada")
                                .param("page", "0")
                                .param("size", "10")
                                .param("sort", "nome,asc")
                                .param("nome", "Fulano"))
                                .andExpect(status().isOk());

                // Test without name
                mockMvc.perform(get("/api/clientes/pesquisa-paginada")
                                .param("page", "0")
                                .param("size", "10")
                                .param("sort", "id,desc"))
                                .andExpect(status().isOk());
        }

        @Test
        void testFindById_NotFound() throws Exception {
                when(service.obterPorId(999)).thenReturn(Optional.empty());

                mockMvc.perform(get("/api/clientes/999"))
                                .andExpect(status().isNotFound());
        }

        @Test
        void testDelete_NotFound() throws Exception {
                when(service.obterPorId(999)).thenReturn(Optional.empty());

                mockMvc.perform(delete("/api/clientes/999"))
                                .andExpect(status().isNotFound());
        }

        @Test
        void testUpdate_NotFound() throws Exception {
                Cliente entity = new Cliente();
                entity.setId(999);
                entity.setNome("Updated");
                entity.setCpf("529.982.247-25"); // Valid CPF to pass validation

                when(service.obterPorId(999)).thenReturn(Optional.empty());

                mockMvc.perform(put("/api/clientes/999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(entity)))
                                .andExpect(status().isNotFound());
        }

        @Test
        void testSave_BadRequest() throws Exception {
                Cliente entity = new Cliente();
                entity.setId(1);
                entity.setNome("Fulano");
                entity.setCpf("123.456.789-00"); // Valid format for validation
                // Mock RegraNegocioException
                when(service.salvar(any(Cliente.class)))
                                .thenThrow(new io.github.hvogel.clientes.exception.RegraNegocioException(
                                                "Erro de negocio"));

                mockMvc.perform(post("/api/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(entity)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void testUpdate_BadRequest() throws Exception {
                Cliente entity = new Cliente();
                entity.setId(1);
                entity.setNome("Fulano");
                entity.setCpf("123.456.789-00"); // Valid format

                when(service.obterPorId(1)).thenReturn(Optional.of(entity));
                when(service.atualizar(any(Cliente.class)))
                                .thenThrow(new io.github.hvogel.clientes.exception.RegraNegocioException(
                                                "Erro de negocio"));

                mockMvc.perform(put("/api/clientes/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(entity)))
                                .andExpect(status().isBadRequest());
        }
}
