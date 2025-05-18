package controller;

import com.dasad.empresa.controller.UsuarioSubCategoriaController;
import com.dasad.empresa.model.CategoriaModel;
import com.dasad.empresa.model.SubCategoriaModel;
import com.dasad.empresa.model.UsuarioSubcategoriaModel;
import com.dasad.empresa.model.UsuarioSubcategoriaRequest;
import com.dasad.empresa.service.UsuarioSubCategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioSubCategoriaControllerTest {

    private static final Integer USUARIO_ID = 1;
    private static final Integer SUBCATEGORIA_ID = 101;
    private static final Integer CATEGORIA_ID = 1;

    @Mock
    private UsuarioSubCategoriaService usuarioSubCategoriaService;

    @InjectMocks
    private UsuarioSubCategoriaController usuarioSubCategoriaController;

    private UsuarioSubcategoriaRequest usuarioSubcategoriaRequest;
    private UsuarioSubcategoriaModel usuarioSubcategoriaModel;
    private UsuarioSubcategoriaModel usuarioSubcategoriaModelDesassociado;

    @BeforeEach
    void setup() {
        // Request
        usuarioSubcategoriaRequest = new UsuarioSubcategoriaRequest();
        usuarioSubcategoriaRequest.setUsuarioId(USUARIO_ID);
        usuarioSubcategoriaRequest.setSubcategoriaId(SUBCATEGORIA_ID);

        // Model associado
        CategoriaModel categoriaModel = new CategoriaModel();
        categoriaModel.setId(CATEGORIA_ID);

        SubCategoriaModel subCategoriaModel = new SubCategoriaModel();
        subCategoriaModel.setId(SUBCATEGORIA_ID);
        subCategoriaModel.setCategoriaId(categoriaModel.getId());

        usuarioSubcategoriaModel = new UsuarioSubcategoriaModel();
        usuarioSubcategoriaModel.setUsuarioId(USUARIO_ID);
        usuarioSubcategoriaModel.setSubcategoria(subCategoriaModel);
        usuarioSubcategoriaModel.setCategoria(categoriaModel);
        usuarioSubcategoriaModel.setDataCriacao(OffsetDateTime.now().minusDays(1));

        // Model desassociado (com dataExclusão)
        usuarioSubcategoriaModelDesassociado = new UsuarioSubcategoriaModel();
        usuarioSubcategoriaModelDesassociado.setUsuarioId(USUARIO_ID);
        usuarioSubcategoriaModelDesassociado.setSubcategoria(subCategoriaModel);
        usuarioSubcategoriaModelDesassociado.setDataCriacao(OffsetDateTime.now().minusDays(1));
        usuarioSubcategoriaModelDesassociado.setDataExclusao(OffsetDateTime.now());
    }

    @Test
    @DisplayName("GET /usuario-subcategoria - Retorna lista com sucesso")
    void testGetUsuarioSubcategoria() {
        // Arrange
        mockFindServiceRetornandoLista(usuarioSubcategoriaModel);

        // Act
        ResponseEntity<List<UsuarioSubcategoriaModel>> response = usuarioSubCategoriaController.getUsuarioSubcategoria(USUARIO_ID, true);

        // Assert
        assertResponseSuccess(response, 1);
        verify(usuarioSubCategoriaService).find(anyInt(), anyBoolean());
    }

    @Test
    @DisplayName("GET /usuario-subcategoria - Retorna lista vazia")
    void testGetUsuarioSubcategoriaEmptyList() {
        // Arrange
        when(usuarioSubCategoriaService.find(anyInt(), anyBoolean())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<List<UsuarioSubcategoriaModel>> response = usuarioSubCategoriaController.getUsuarioSubcategoria(USUARIO_ID, true);

        // Assert
        assertResponseSuccess(response, 0);
        verify(usuarioSubCategoriaService).find(anyInt(), anyBoolean());
    }

//    @Test
//    @DisplayName("POST /usuario-subcategoria/associar - Associa com sucesso")
//    void testAssociarUsuarioSubcategoria() {
//        // Arrange
//        mockFindServiceRetornandoLista(usuarioSubcategoriaModel);
//
//        // Act
//        ResponseEntity<Void> response = usuarioSubCategoriaController.associarUsuarioSubcategoria(usuarioSubcategoriaRequest);
//
//        // Assert
//        assertResponseSuccess(response, 1);
//        verify(usuarioSubCategoriaService).associarUsuarioSubcategoria(any());
//        verify(usuarioSubCategoriaService).find(anyInt(), anyBoolean());
//    }

//    @Test
//    @DisplayName("POST /usuario-subcategoria/desassociar - Desassocia com sucesso")
//    void testDesassociarUsuarioSubcategoria() {
//        // Arrange
//        mockFindServiceRetornandoLista(usuarioSubcategoriaModelDesassociado);
//
//        // Act
//        ResponseEntity<Void> response = usuarioSubCategoriaController.desassociarUsuarioSubcategoria(usuarioSubcategoriaRequest);
//
//        // Assert
//        assertResponseSuccess(response, 1);
//        verify(usuarioSubCategoriaService).desassociarUsuarioSubcategoria(any());
//        verify(usuarioSubCategoriaService).find(anyInt(), anyBoolean());
//
//        UsuarioSubcategoriaModel model = response.getBody().get(0);
//        assertNotNull(model.getDataExclusao());
//    }

    // === MÉTODOS AUXILIARES ===

    private void mockFindServiceRetornandoLista(UsuarioSubcategoriaModel... models) {
        when(usuarioSubCategoriaService.find(anyInt(), anyBoolean()))
                .thenReturn(Optional.of(List.of(models)));
    }

    private void assertResponseSuccess(ResponseEntity<List<UsuarioSubcategoriaModel>> response, int expectedSize) {
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedSize, response.getBody().size());
    }
}