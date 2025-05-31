package controller;

import com.dasad.empresa.controller.EnderecoController;
import com.dasad.empresa.model.CidadeModel;
import com.dasad.empresa.model.EnderecoModel;
import com.dasad.empresa.model.EstadoModel;
import com.dasad.empresa.model.PaisModel;
import com.dasad.empresa.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnderecoControllerTest {

    @Mock
    private EnderecoService enderecoService;

    @InjectMocks
    private EnderecoController enderecoController;

    private EnderecoModel enderecoModel;

    @BeforeEach
    void setup() {
        PaisModel paisModel = new PaisModel();
        paisModel.setId(1);
        paisModel.setNome("Brasil");
        EstadoModel estadoModel = new EstadoModel();
        estadoModel.setId(1);
        estadoModel.setNome("São Paulo");
        estadoModel.setPaisId(paisModel);
        CidadeModel cidadeModel = new CidadeModel();
        cidadeModel.setId(1);
        cidadeModel.setNome("São Paulo");
        enderecoModel = new EnderecoModel();
        enderecoModel.setId(1);
        enderecoModel.setLogradouro("Rua Teste");
        enderecoModel.setCidadeId(cidadeModel);
        enderecoModel.setCep("12345-678");
    }

    @Nested
    class FindAllTests {

        @DisplayName("Quando não há endereços cadastrados")
        @Test
        void givenEnderecoExistente_whenFindAll_thenRetornaListaSemEndereco() {
            // Given
            when(enderecoService.findAll()).thenReturn(Collections.singletonList(enderecoModel));

            // When
            var response = enderecoController.findendereco();

            // Then
            assertNotNull(response);
            assertEquals(1, response.getBody().size());
            assertEquals(enderecoModel, response.getBody().getFirst());
        }
    }

    @Nested
    class GetEnderecoByIdTests {

        @Test
        void shouldReturnEnderecoWhenExists() {
            // Arrange
            when(enderecoService.findById(1)).thenReturn(Optional.of(enderecoModel));

            // Act
            ResponseEntity<EnderecoModel> response = enderecoController.detailendereco(1);

            // Assert
            assertNotNull(response);
            assertEquals(ResponseEntity.ok(enderecoModel), response);
        }

        @Test
        void shouldReturnNotFoundWhenEnderecoDoesNotExist() {
            // Arrange
            when(enderecoService.findById(1)).thenReturn(Optional.empty());

            // Act
            ResponseEntity<EnderecoModel> response = enderecoController.detailendereco(1);

            // Assert
            assertNotNull(response);
            assertEquals(ResponseEntity.notFound().build(), response);
        }
    }

    @Nested
    class CreateEnderecoTests {

        @Test
        void shouldCreateEnderecoSuccessfully() {
            // Arrange
            when(enderecoService.save(any(EnderecoModel.class))).thenReturn(enderecoModel);

            // Act
            var response = enderecoController.createendereco(enderecoModel);

            // Assert
            assertNotNull(response);
            assertEquals(enderecoModel, response.getBody());
        }
    }

    @Nested
    class UpdateEnderecoTests {

        @Test
        void shouldUpdateEnderecoSuccessfully() {
            // Arrange
            when(enderecoService.findById(1)).thenReturn(Optional.of(enderecoModel));
            when(enderecoService.save(any(EnderecoModel.class))).thenReturn(enderecoModel);

            // Act
            ResponseEntity<EnderecoModel> response = enderecoController.updateendereco(1, enderecoModel);

            // Assert
            assertNotNull(response);
            assertEquals(ResponseEntity.ok(enderecoModel), response);
        }

        @Test
        void shouldReturnNotFoundWhenEnderecoDoesNotExist() {
            // Arrange
            when(enderecoService.findById(1)).thenReturn(Optional.empty());

            // Act
            ResponseEntity<EnderecoModel> response = enderecoController.updateendereco(1, enderecoModel);

            // Assert
            assertNotNull(response);
            assertEquals(ResponseEntity.notFound().build(), response);
        }
    }

    @Nested
    class DeleteEnderecoTests {

        @Test
        void shouldDeleteEnderecoSuccessfully() {
            // Arrange
            when(enderecoService.findById(1)).thenReturn(Optional.of(enderecoModel));
            doNothing().when(enderecoService).deleteById(1);

            // Act
            ResponseEntity<Void> response = enderecoController.deleteendereco(1);

            // Assert
            assertNotNull(response);
            assertEquals(ResponseEntity.noContent().build(), response);
        }

        @Test
        void shouldReturnNotFoundWhenEnderecoDoesNotExist() {
            // Arrange
            when(enderecoService.findById(1)).thenReturn(Optional.empty());

            // Act
            ResponseEntity<Void> response = enderecoController.deleteendereco(1);

            // Assert
            assertNotNull(response);
            assertEquals(ResponseEntity.notFound().build(), response);
        }
    }
}
