package infra.security;

import com.dasad.empresa.infra.security.AuthorizationService;
import com.dasad.empresa.model.OrganizacaoModel;
import com.dasad.empresa.model.PerfilModel;
import com.dasad.empresa.model.UsuarioModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTest {

    @InjectMocks
    private AuthorizationService authorizationService;

    @Mock
    private UsuarioModel usuarioModel;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(authorizationService, "secret", "mySecretKey");
    }

    @Test
    void testGenerateToken() {
        PerfilModel perfilModel = new PerfilModel();
        perfilModel.setNome("USER");
        OrganizacaoModel organizacaoModel = new OrganizacaoModel();
        organizacaoModel.setId(1);

        when(usuarioModel.getEmail()).thenReturn("user@example.com");
        when(usuarioModel.getPerfis()).thenReturn(Collections.singletonList(perfilModel));
        when(usuarioModel.getOrganizacoes()).thenReturn(Collections.singletonList(organizacaoModel));

        String token = authorizationService.generateToken(usuarioModel);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void testGenerateTokenWithoutSecret() {
        // Configura o mock do usuário para evitar NoSuchElementException
        PerfilModel perfilModel = new PerfilModel();
        perfilModel.setNome("USER");
        OrganizacaoModel organizacaoModel = new OrganizacaoModel();
        organizacaoModel.setId(1);

        lenient().when(usuarioModel.getEmail()).thenReturn("user@example.com");
        lenient().when(usuarioModel.getPerfis()).thenReturn(Collections.singletonList(perfilModel));
        lenient().when(usuarioModel.getOrganizacoes()).thenReturn(Collections.singletonList(organizacaoModel));

        // Configura o campo secret como vazio para forçar a exceção
        ReflectionTestUtils.setField(authorizationService, "secret", "");

        // Verifica se a exceção correta é lançada
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            authorizationService.generateToken(usuarioModel);
        });

        assertEquals("Token secret is not configured properly.", exception.getMessage());
    }

    @Test
    void testValidateToken() {
        PerfilModel perfilModel = new PerfilModel();
        perfilModel.setNome("USER");
        OrganizacaoModel organizacaoModel = new OrganizacaoModel();
        organizacaoModel.setId(1);

        when(usuarioModel.getEmail()).thenReturn("user@example.com");
        when(usuarioModel.getPerfis()).thenReturn(Collections.singletonList(perfilModel));
        when(usuarioModel.getOrganizacoes()).thenReturn(Collections.singletonList(organizacaoModel));

        String token = authorizationService.generateToken(usuarioModel);
        String userEmail = authorizationService.validateToken(token);

        assertNotNull(userEmail);
        assertEquals("user@example.com", userEmail);
    }


    @Test
    void testRevokeToken() {
        String token = "sampleToken";
        authorizationService.revokeToken(token);

        Set<String> revokedTokens = (Set<String>) ReflectionTestUtils.getField(authorizationService, "revokedTokens");
        assert revokedTokens != null;
        assertTrue(revokedTokens.contains(token));
    }

    @Test
    void testRevokeTokenAlreadyRevoked() {
        String token = "sampleToken";
        authorizationService.revokeToken(token);
        authorizationService.revokeToken(token);

        Set<String> revokedTokens = (Set<String>) ReflectionTestUtils.getField(authorizationService, "revokedTokens");
        assert revokedTokens != null;
        assertTrue(revokedTokens.contains(token));
    }
}