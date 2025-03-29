package controller;

import com.dasad.empresa.controller.CategoriaController;
import com.dasad.empresa.model.CategoriaModel;
import com.dasad.empresa.model.CategoriaRequest;
import com.dasad.empresa.model.StatusServico;
import com.dasad.empresa.service.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaControllerTest {

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    private CategoriaModel categoriaModelInput;
    private CategoriaModel categoriaModelOutput;
    private CategoriaRequest categoriaRequest;
    // Não precisamos mais das variáveis statusServico1, statusServico2 aqui
    // private StatusServico statusServico1;
    // private StatusServico statusServico2;

    @BeforeEach
    void setUp() {
        categoriaModelInput = new CategoriaModel();
        categoriaModelInput.setNome("Eletrônicos");

        categoriaModelOutput = new CategoriaModel();
        categoriaModelOutput.setId(1);
        categoriaModelOutput.setNome("Eletrônicos");

        categoriaRequest = new CategoriaRequest();
        categoriaRequest.setNome("Eletrônicos");

        // Remover a inicialização incorreta do StatusServico
        // statusServico1 = new StatusServico(); // INCORRETO
        // statusServico1.setDescricao("Ativo"); // INCORRETO
        // statusServico2 = new StatusServico(); // INCORRETO
        // statusServico2.setDescricao("Inativo"); // INCORRETO
    }

    @Test
    @DisplayName("Deve criar categoria com sucesso e retornar status OK")
    void createCategoria_Success() {
        when(categoriaService.create(any(CategoriaModel.class))).thenReturn(Optional.of(categoriaModelOutput));

        ResponseEntity<CategoriaModel> response = categoriaController.createCategoria(categoriaModelInput);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(categoriaModelOutput.getId(), response.getBody().getId());
        assertEquals(categoriaModelOutput.getNome(), response.getBody().getNome());
        verify(categoriaService, times(1)).create(categoriaModelInput);
    }

    @Test
    @DisplayName("Deve retornar No Content quando a criação da categoria falhar no serviço")
    void createCategoria_NoContent() {
        when(categoriaService.create(any(CategoriaModel.class))).thenReturn(Optional.empty());

        ResponseEntity<CategoriaModel> response = categoriaController.createCategoria(categoriaModelInput);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(categoriaService, times(1)).create(categoriaModelInput);
    }

    @Test
    @DisplayName("Deve deletar categoria com sucesso e retornar No Content")
    void deleteCategoria_Success() {
        Integer idParaDeletar = 1;
        when(categoriaService.findById(idParaDeletar)).thenReturn(Optional.of(categoriaModelOutput));

        ResponseEntity<Void> response = categoriaController.deleteCategoria(idParaDeletar);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(categoriaService, times(1)).findById(idParaDeletar);
        verify(categoriaService, times(1)).delete(idParaDeletar);
    }

    @Test
    @DisplayName("Deve retornar Not Found ao tentar deletar categoria inexistente")
    void deleteCategoria_NotFound() {
        Integer idInexistente = 99;
        when(categoriaService.findById(idInexistente)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = categoriaController.deleteCategoria(idInexistente);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(categoriaService, times(1)).findById(idInexistente);
        verify(categoriaService, never()).delete(idInexistente);
    }

    @Test
    @DisplayName("Deve encontrar categorias com sucesso e retornar status OK")
    void findCategoria_Success() {
        List<CategoriaModel> expectedList = Collections.singletonList(categoriaModelOutput);
        when(categoriaService.find(any(CategoriaRequest.class))).thenReturn(Optional.of(expectedList));

        ResponseEntity<List<CategoriaModel>> response = categoriaController.findCategoria(categoriaRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(expectedList.get(0).getNome(), response.getBody().get(0).getNome());
        verify(categoriaService, times(1)).find(categoriaRequest);
    }

    @Test
    @DisplayName("Deve retornar Not Found quando nenhuma categoria for encontrada")
    void findCategoria_NotFound() {
        when(categoriaService.find(any(CategoriaRequest.class))).thenReturn(Optional.empty());

        ResponseEntity<List<CategoriaModel>> response = categoriaController.findCategoria(categoriaRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(categoriaService, times(1)).find(categoriaRequest);
    }

    // --- TESTES CORRIGIDOS PARA StatusServico ENUM ---

    @Test
    @DisplayName("Deve encontrar status de serviço com sucesso e retornar status OK")
    void findStatusServico_Success() {
        // Arrange: Usar as constantes do enum diretamente
        List<StatusServico> expectedList = Arrays.asList(StatusServico.ATIVO, StatusServico.INATIVO);
        when(categoriaService.getStatus()).thenReturn(Optional.of(expectedList));

        // Act: Chama o método do controller
        ResponseEntity<List<StatusServico>> response = categoriaController.findStatusServico();

        // Assert: Verifica os resultados
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size()); // Verifica o tamanho da lista

        // Verifica se os enums retornados são os esperados
        assertTrue(response.getBody().contains(StatusServico.ATIVO));
        assertTrue(response.getBody().contains(StatusServico.INATIVO));
        // Ou comparar elemento por elemento se a ordem importar:
        // assertEquals(StatusServico.ATIVO, response.getBody().get(0));
        // assertEquals(StatusServico.INATIVO, response.getBody().get(1));

        // Verifica se o método getStatus do serviço foi chamado
        verify(categoriaService, times(1)).getStatus();
    }

    @Test
    @DisplayName("Deve retornar Not Found quando nenhum status de serviço for encontrado")
    void findStatusServico_NotFound() {
        // Arrange: Configura o mock do serviço para retornar Optional vazio
        when(categoriaService.getStatus()).thenReturn(Optional.empty());

        // Act: Chama o método do controller
        ResponseEntity<List<StatusServico>> response = categoriaController.findStatusServico();

        // Assert: Verifica os resultados
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody()); // Nenhuma lista no corpo

        // Verifica se o método getStatus do serviço foi chamado
        verify(categoriaService, times(1)).getStatus();
    }

    // --- FIM DOS TESTES CORRIGIDOS PARA StatusServico ENUM ---


    @Test
    @DisplayName("Deve retornar null para updateCategoria conforme implementação atual")
    void updateCategoria_CurrentImplementation() {
        CategoriaModel categoriaParaAtualizar = new CategoriaModel();
        categoriaParaAtualizar.setId(1);
        categoriaParaAtualizar.setNome("Eletrônicos Atualizado");

        ResponseEntity<CategoriaModel> response = categoriaController.updateCategoria(categoriaParaAtualizar);

        assertNull(response);
        verifyNoInteractions(categoriaService);

        /*
        // ---- Exemplo de como seria se o método fosse implementado ----
        when(categoriaService.update(any(CategoriaModel.class))).thenReturn(Optional.of(categoriaParaAtualizar));
        ResponseEntity<CategoriaModel> responseAlternativo = categoriaController.updateCategoria(categoriaParaAtualizar);
        assertNotNull(responseAlternativo);
        assertEquals(HttpStatus.OK, responseAlternativo.getStatusCode());
        assertNotNull(responseAlternativo.getBody());
        assertEquals(categoriaParaAtualizar.getNome(), responseAlternativo.getBody().getNome());
        verify(categoriaService, times(1)).update(categoriaParaAtualizar);
        */
    }
}