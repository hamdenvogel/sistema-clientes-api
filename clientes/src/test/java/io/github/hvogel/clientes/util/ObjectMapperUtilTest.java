package io.github.hvogel.clientes.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.hvogel.clientes.rest.dto.EnderecoDTO;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class ObjectMapperUtilTest {

    @Test
    void testPrivateConstructor() throws NoSuchMethodException {
        Constructor<ObjectMapperUtil> constructor = ObjectMapperUtil.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void testGetObject() {
        String json = "{\"cep\":\"12345678\",\"logradouro\":\"Rua Teste\"}";
        EnderecoDTO dto = ObjectMapperUtil.getObject(json, EnderecoDTO.class);

        assertNotNull(dto);
        assertEquals("12345678", dto.getCep());
        assertEquals("Rua Teste", dto.getLogradouro());
    }

    @Test
    void testGetObjectError() {
        String json = "invalid_json";
        ValidationException exception = assertThrows(ValidationException.class,
                () -> ObjectMapperUtil.getObject(json, EnderecoDTO.class));
        assertTrue(exception.getMessage().contains("Erro ao desserializacao"));
    }

    @Test
    void testGetJsonOfObject() {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setCep("12345678");
        dto.setLogradouro("Rua Teste");

        String json = ObjectMapperUtil.getJsonOfObject(dto);

        assertNotNull(json);
        assertTrue(json.contains("12345678"));
        assertTrue(json.contains("Rua Teste"));
    }

    // Hard to mock JsonProcessingException with simple object, but we rely on
    // Jackson behavior for IOExceptions.
    // The previous testGetObjectError covers the exception wrapping logic for
    // readValue.
    // For writeValueAsString, typical objects won't fail unless there's a problem
    // with getters or recursion,
    // which Jackson handles well or we can simulate with a broken mock if strictly
    // necessary.
    // Given 90% goal, covering the main paths is likely sufficient.
}
