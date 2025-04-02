package com.dasad.empresa.repository;

import com.dasad.empresa.jooq.tables.records.UsuarioRecuperarSenhaRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import static com.dasad.empresa.jooq.tables.UsuarioRecuperarSenha.USUARIO_RECUPERAR_SENHA;

@Repository
public class SenhaRepositoryImpl implements SenhaRepository {
    private final DSLContext dsl;

    @Autowired
    public SenhaRepositoryImpl(DSLContext dsl, PasswordEncoder passwordEncoder) {
        this.dsl = dsl;
    }

    @Override
    public void create(UsuarioRecuperarSenhaRecord usuario) {
        dsl.insertInto(USUARIO_RECUPERAR_SENHA)
                .set(USUARIO_RECUPERAR_SENHA.USUARIO_ID, usuario.getUsuarioId())
                .set(USUARIO_RECUPERAR_SENHA.TOKEN, usuario.getToken())
                .set(USUARIO_RECUPERAR_SENHA.DATA_EXPIRACAO, usuario.getDataExpiracao())
                .execute();
    }
}
