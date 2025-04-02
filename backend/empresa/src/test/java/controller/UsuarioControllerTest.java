package controller;

import com.dasad.empresa.controller.UsuarioController;
import com.dasad.empresa.model.RegisterRequestDTO;
import com.dasad.empresa.model.UsuarioModel;
import com.dasad.empresa.model.UsuarioRequest;
import com.dasad.empresa.model.UsuarioResponseDTO;
import com.dasad.empresa.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    UsuarioService usuarioService;

    @InjectMocks
    UsuarioController usuarioController;

    private UsuarioRequest usuarioRequest;
    private UsuarioModel usuarioModel;

    @BeforeEach
    void setup() {
        // Executa antes de cada teste, se for necessário algum setup adicional
        usuarioRequest = new UsuarioRequest();

        usuarioModel = new UsuarioModel();
        usuarioModel.setId(1);
        usuarioModel.setNome("Nome Teste");
        usuarioModel.setEmail("email@email.com");
    }

    @Nested
    class FindTests {

        @Test
        void shouldReturnSuccess() {
            // Arrange
            when(usuarioService.find(any(UsuarioRequest.class), any())).thenReturn(
                    Optional.of(Collections.emptyList()));
            when(usuarioService.countTotalRecords(any(UsuarioRequest.class), anyInt())).thenReturn(
                    Optional.of(0));

            // Act
            var response = usuarioController.findUsuario(1, usuarioRequest);
            // Assert
            assertNotNull(response, "A resposta não deve ser nula");
        }

        @Test
        void shouldPassCorrectParameters() {
            // Arrange
            var nome = "nome";
            var id = 1;
            var email = "email@email.com";
            var dataNascimento = LocalDate.now();

            usuarioRequest.setId(id);
            usuarioRequest.setNome(nome);
            usuarioRequest.setEmail(email);
            usuarioRequest.setDataNascimento(dataNascimento);

            when(usuarioService.find(any(UsuarioRequest.class), any())).thenReturn(
                    Optional.of(Collections.emptyList()));
            when(usuarioService.countTotalRecords(any(UsuarioRequest.class), anyInt())).thenReturn(
                    Optional.of(0));

            // Act
            var response = usuarioController.findUsuario(1, usuarioRequest);

            // Assert
            assertNotNull(response, "A resposta não deve ser nula");
        }

        @Test
        void shouldReturnError() {
            // Arrange
            UsuarioRequest usuarioRequest = new UsuarioRequest();
            when(usuarioService.find(any(UsuarioRequest.class), any())).thenThrow(
                    new RuntimeException("Erro interno do servidor"));

            // Act & Assert
            assertThrows(RuntimeException.class, () -> usuarioController.findUsuario(1, usuarioRequest),
                    "Deveria lançar RuntimeException");
        }

        @Test
        void testUpdateUsuario() {
            // Arrange: Configura o comportamento esperado do mock usuarioService
            when(usuarioService.update(any(UsuarioModel.class), anyInt())).thenReturn(usuarioModel);

            // Act: Chama o método updateUsuario do usuarioController
            ResponseEntity<UsuarioModel> response = usuarioController.updateUsuario(1, usuarioModel);

            // Assert: Verifica se a resposta é a esperada e se o método update foi chamado uma vez
            assertEquals(ResponseEntity.ok(usuarioModel), response);
            verify(usuarioService, times(1)).update(usuarioModel, 1);
        }

    }

    @Nested
    class DeleteUsuarioTests {

        @Test
        void shouldReturnNoContentWhenUsuarioExists() {
            // Arrange
            Integer id = 1;
            Integer organizacaoId = 1;
            when(usuarioService.findById(id)).thenReturn(Optional.of(usuarioModel));

            // Act
            ResponseEntity<Void> response = usuarioController.deleteUsuario(organizacaoId, id);

            // Assert
            assertEquals(ResponseEntity.noContent().build(), response);
            verify(usuarioService, times(1)).findById(id);
            verify(usuarioService, times(1)).deleteById(id);
        }

        @Test
        void shouldReturnNotFoundWhenUsuarioDoesNotExist() {
            // Arrange
            Integer organizacaoId = 1;
            Integer id = 1;
            when(usuarioService.findById(id)).thenReturn(Optional.empty());

            // Act
            ResponseEntity<Void> response = usuarioController.deleteUsuario(organizacaoId, id);

            // Assert
            assertEquals(ResponseEntity.notFound().build(), response);
            verify(usuarioService, times(1)).findById(id);
            verify(usuarioService, times(0)).deleteById(id);
        }
    }

    @Nested
    class DetailUsuarioTests {

        @Test
        void shouldReturnUsuarioWhenExists() {
            // Arrange
            Integer id = 1;
            when(usuarioService.findById(id)).thenReturn(Optional.of(usuarioModel));

            // Act
            ResponseEntity<UsuarioResponseDTO> response = usuarioController.detailUsuario(id);

            // Assert
            assertNotNull(response);
            assertEquals(ResponseEntity.ok().build().getStatusCode(), response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(1, response.getBody().getUsuarios().size());
            assertEquals(usuarioModel, response.getBody().getUsuarios().get(0));
            verify(usuarioService, times(1)).findById(id);
        }

        @Test
        void shouldReturnNotFoundWhenUsuarioDoesNotExist() {
            // Arrange
            Integer id = 1;
            when(usuarioService.findById(id)).thenReturn(Optional.empty());

            // Act
            ResponseEntity<UsuarioResponseDTO> response = usuarioController.detailUsuario(id);

            // Assert
            assertNotNull(response);
            assertEquals(ResponseEntity.notFound().build().getStatusCode(), response.getStatusCode());
            verify(usuarioService, times(1)).findById(id);
        }
    }

    @Nested
    class CreateUsuarioTests {

        private RegisterRequestDTO registerRequestDTO;

        @BeforeEach
        void setup() {
            // Cria uma instância de RegisterRequestDTO e define seus atributos
            registerRequestDTO = new RegisterRequestDTO();
            registerRequestDTO.setId(1);
            registerRequestDTO.setNome("Nome Teste");
            registerRequestDTO.setEmail("email@teste.com");
            registerRequestDTO.setSenha("senha123");
            registerRequestDTO.setDataNascimento(LocalDate.now());
            registerRequestDTO.setEnderecos(Collections.emptyList());
            registerRequestDTO.setPerfis(Collections.emptyList());
        }

        @Test
        void shouldCreateUsuarioSuccessfully() {
            // Arrange: Cria uma instância de UsuarioModel e define seus atributos
            UsuarioModel usuarioModel = new UsuarioModel();
            usuarioModel.setId(registerRequestDTO.getId());
            usuarioModel.setNome(registerRequestDTO.getNome());
            usuarioModel.setEmail(registerRequestDTO.getEmail());
            usuarioModel.setSenha(registerRequestDTO.getSenha());
            usuarioModel.setDataNascimento(registerRequestDTO.getDataNascimento());
            usuarioModel.setEnderecos(registerRequestDTO.getEnderecos());
            usuarioModel.setPerfis(registerRequestDTO.getPerfis());

            // Configura o comportamento esperado do mock usuarioService
            when(usuarioService.create(any(UsuarioModel.class), anyInt())).thenReturn(usuarioModel);

            // Act: Chama o método createUsuario do usuarioController
            ResponseEntity<UsuarioModel> response = usuarioController.createUsuario(1, registerRequestDTO);

            // Assert: Verifica se a resposta não é nula
            assertNotNull(response);
            // Verifica se a resposta é igual a ResponseEntity.ok(usuarioModel)
            assertEquals(ResponseEntity.ok(usuarioModel), response);
            // Verifica se o método create do usuarioService foi chamado exatamente uma vez
            verify(usuarioService, times(1)).create(any(UsuarioModel.class), anyInt());
        }
    }
}
