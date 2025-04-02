package com.dasad.empresa.repository;

import com.dasad.empresa.exception.EmailAlreadyExistsException;
import com.dasad.empresa.jooq.tables.Cidade;
import com.dasad.empresa.jooq.tables.Estado;
import com.dasad.empresa.jooq.tables.Pais;
import com.dasad.empresa.jooq.tables.UsuarioPerfil;
import com.dasad.empresa.model.CidadeModel;
import com.dasad.empresa.model.EnderecoModel;
import com.dasad.empresa.model.EstadoModel;
import com.dasad.empresa.model.LoginRequestDTO;
import com.dasad.empresa.model.OrganizacaoModel;
import com.dasad.empresa.model.PaisModel;
import com.dasad.empresa.model.PerfilModel;
import com.dasad.empresa.model.UsuarioFotoModel;
import com.dasad.empresa.model.UsuarioModel;
import com.dasad.empresa.model.UsuarioRequest;
import com.dasad.empresa.repository.query.UsuarioFotoQueryBuilder;
import com.dasad.empresa.repository.query.UsuarioQueryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.dasad.empresa.jooq.tables.Endereco.ENDERECO;
import static com.dasad.empresa.jooq.tables.Organizacao.ORGANIZACAO;
import static com.dasad.empresa.jooq.tables.Perfil.PERFIL;
import static com.dasad.empresa.jooq.tables.Usuario.USUARIO;
import static com.dasad.empresa.jooq.tables.UsuarioFoto.USUARIO_FOTO;
import static com.dasad.empresa.jooq.tables.UsuarioOrganizacao.USUARIO_ORGANIZACAO;
import static com.dasad.empresa.jooq.tables.UsuarioPerfil.USUARIO_PERFIL;

@Repository
//@Transactional
public class UsuarioRepositoryImpl implements UsuarioRepository {
    private static final Logger log = LogManager.getLogger(UsuarioRepositoryImpl.class);
    private final DSLContext dsl;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioRepositoryImpl(DSLContext dsl, PasswordEncoder passwordEncoder) {
        this.dsl = dsl;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<List<UsuarioModel>> find(UsuarioRequest usuarioRequest, Integer organizacaoId) {
        UsuarioQueryBuilder queryBuilder = new UsuarioQueryBuilder(this.dsl)
                .withOrganizacao(organizacaoId)
                .withId(usuarioRequest.getId())
                .withNome(usuarioRequest.getNome())
                .withEmail(usuarioRequest.getEmail())
//                .withDataNascimento(convertStringToLocalDate(usuarioRequest.getDataNascimento()))
                .withDataNascimento(usuarioRequest.getDataNascimento())
                .withPerfil(usuarioRequest.getPerfis())
                .withLimit(usuarioRequest.getLimit())
                .withOffset(usuarioRequest.getOffset());
        List<UsuarioModel> result = queryBuilder.build().join();
        return Optional.ofNullable(result.isEmpty() ? null : result);
    }

    public Optional<Integer> countTotalRecords(UsuarioRequest usuarioRequest, Integer organizacaoId) {
        UsuarioQueryBuilder queryBuilder = new UsuarioQueryBuilder(this.dsl)
                .withOrganizacao(organizacaoId)
                .withId(usuarioRequest.getId())
                .withNome(usuarioRequest.getNome())
                .withEmail(usuarioRequest.getEmail())
//                .withDataNascimento(convertStringToLocalDate(usuarioRequest.getDataNascimento()))
                .withDataNascimento(usuarioRequest.getDataNascimento())
                .withPerfil(usuarioRequest.getPerfis())
                .withLimit(0)
                .withOffset(0);
        var result = queryBuilder.countTotalRecords().join();
        return Optional.ofNullable(result == null ? 0 : result);
    }

    public Optional<UsuarioModel> findById(Integer id) {
        return dsl.select(
                        USUARIO.ID,
                        USUARIO.NOME,
                        USUARIO.EMAIL,
                        USUARIO.DATA_NASCIMENTO,
                        PERFIL.ID.as("perfil_id"),
                        PERFIL.NOME.as("perfil_nome"),
                        ENDERECO.ID.as("endereco_id"),
                        ENDERECO.LOGRADOURO,
                        ENDERECO.NUMERO,
                        ENDERECO.COMPLEMENTO,
                        ENDERECO.BAIRRO,
                        ENDERECO.CIDADE_ID.as("cidade_id"),
                        ENDERECO.CEP,
                        Cidade.CIDADE.NOME.as("cidade_nome"),
                        Estado.ESTADO.ID.as("estado_id"),
                        Estado.ESTADO.NOME.as("estado_nome"),
                        Pais.PAIS.ID.as("pais_id"),
                        Pais.PAIS.NOME.as("pais_nome"),
                        USUARIO_FOTO.ID.as("foto_id"),
                        USUARIO_FOTO.USUARIO_ID.as("usuario_id"),
                        USUARIO_FOTO.FOTO,
                        USUARIO_FOTO.ATIVO,
                        USUARIO_FOTO.DATA_CRIACAO,
                        USUARIO_FOTO.DATA_ATUALIZACAO
                )
                .from(USUARIO)
                .leftJoin(UsuarioPerfil.USUARIO_PERFIL)
                .on(USUARIO.ID.eq(UsuarioPerfil.USUARIO_PERFIL.USUARIO_ID))
                .leftJoin(PERFIL)
                .on(UsuarioPerfil.USUARIO_PERFIL.PERFIL_ID.eq(PERFIL.ID))
                .leftJoin(ENDERECO).on(USUARIO.ID.eq(ENDERECO.USUARIO_ID))
                .leftJoin(Cidade.CIDADE).on(ENDERECO.CIDADE_ID.eq(Cidade.CIDADE.ID))
                .leftJoin(Estado.ESTADO).on(Cidade.CIDADE.ESTADO_ID.eq(Estado.ESTADO.ID))
                .leftJoin(Pais.PAIS).on(Estado.ESTADO.PAIS_ID.eq(Pais.PAIS.ID))
                .leftJoin(USUARIO_FOTO).on(USUARIO_FOTO.USUARIO_ID.eq(USUARIO.ID))
                .where(USUARIO.ID.eq(id))
//                .and(USUARIO_FOTO.ATIVO.isTrue())
                .fetchOptional()
                .map(record -> {
                    UsuarioModel usuario = new UsuarioModel();
                    usuario.setId(record.get(USUARIO.ID));
                    usuario.setNome(record.get(USUARIO.NOME));
                    usuario.setEmail(record.get(USUARIO.EMAIL));
//                    usuario.setDataNascimento(convertLocalDateToString(record.get(Usuario.USUARIO.DATA_NASCIMENTO)));
                    usuario.setDataNascimento(record.get(USUARIO.DATA_NASCIMENTO));
                    if (record.get("perfil_id") != null) {
                        PerfilModel perfil = new PerfilModel();
                        perfil.setId(record.get("perfil_id", Integer.class));
                        perfil.setNome(record.get("perfil_nome", String.class));
                        usuario.setPerfis(new ArrayList<>(Collections.singleton(perfil)));
                    } else {
                        usuario.setPerfis(new ArrayList<>(Collections.emptySet()));
                    }
                    if (record.get("endereco_id") != null) {
                        EnderecoModel endereco = new EnderecoModel();
                        endereco.setId(record.get("endereco_id", Integer.class));
                        endereco.setLogradouro(record.get(ENDERECO.LOGRADOURO));
                        endereco.setNumero(record.get(ENDERECO.NUMERO));
                        endereco.setComplemento(record.get(ENDERECO.COMPLEMENTO));
                        endereco.setBairro(record.get(ENDERECO.BAIRRO));
                        endereco.setCep(record.get(ENDERECO.CEP));
                        if (record.get("cidade_id") != null && record.get("estado_id") != null && record.get("pais_id") != null) {
                            var estado = new EstadoModel();
                            estado.setId(record.get("estado_id", Integer.class));
                            estado.setNome(record.get("estado_nome", String.class));
                            var pais = new PaisModel();
                            pais.setId(record.get("pais_id", Integer.class));
                            estado.setPaisId(pais);
                            CidadeModel cidade = new CidadeModel();
                            cidade.setId(record.get("cidade_id", Integer.class));
                            cidade.setNome(record.get("cidade_nome", String.class));
                            cidade.setEstadoId(estado);
                            endereco.setCidadeId(cidade);
                        }
                        usuario.setEnderecos(new ArrayList<>(Collections.singleton(endereco)));
                    } else {
                        usuario.setEnderecos(new ArrayList<>(Collections.emptySet()));
                    }

                    if (record.get("foto_id") != null && record.get(USUARIO_FOTO.ATIVO) == true) {
                        var foto = new UsuarioFotoModel();
                        foto.setId(record.get("foto_id", Integer.class));
                        foto.setUsuarioId(record.get("usuario_id", Integer.class));
                        foto.setFoto(record.get(USUARIO_FOTO.FOTO));
                        foto.setAtivo(record.get(USUARIO_FOTO.ATIVO));
                        foto.setDataCriacao(record.get(USUARIO_FOTO.DATA_CRIACAO).atOffset(ZoneOffset.UTC));
                        if (record.get(USUARIO_FOTO.DATA_ATUALIZACAO) != null) {
                            foto.setDataAtualizacao(record.get(USUARIO_FOTO.DATA_ATUALIZACAO).atOffset(ZoneOffset.UTC));
                        } else {
                            foto.setDataAtualizacao(null);
                        }
                        usuario.setFoto(foto);
                    }
                    return usuario;
                });
    }


    public Optional<UsuarioModel> findByEmail(String email) {
        // Primeiro busca apenas o usuário
        Optional<UsuarioModel> usuario = dsl.select(USUARIO.fields())
                .from(USUARIO)
                .where(USUARIO.EMAIL.eq(email))
                .fetchOptionalInto(UsuarioModel.class);

        // Se encontrar, carrega as associações
        if (usuario.isPresent()) {
            UsuarioModel u = usuario.get();

            // Carrega perfis
            List<com.dasad.empresa.model.PerfilModel> perfis = dsl.select(PERFIL.fields())
                    .from(PERFIL)
                    .join(USUARIO_PERFIL)
                    .on(USUARIO_PERFIL.PERFIL_ID.eq(PERFIL.ID))
                    .where(USUARIO_PERFIL.USUARIO_ID.eq(u.getId()))
                    .fetchInto(com.dasad.empresa.model.PerfilModel.class);

            u.setPerfis(perfis);

            // Carrega organizações
            List<OrganizacaoModel> organizacoes = dsl.select(ORGANIZACAO.fields())
                    .from(ORGANIZACAO)
                    .join(USUARIO_ORGANIZACAO)
                    .on(USUARIO_ORGANIZACAO.ORGANIZACAO_ID.eq(ORGANIZACAO.ID))
                    .where(USUARIO_ORGANIZACAO.USUARIO_ID.eq(u.getId()))
                    .fetchInto(OrganizacaoModel.class);

            u.setOrganizacoes(organizacoes);

            return Optional.of(u);
        }

        return Optional.empty();
    }

    public Optional<UsuarioModel> findByEmailAndOrganizacaoId(String email, Integer organizacaoId) {
        // Primeiro busca apenas o usuário
        Optional<UsuarioModel> usuario = dsl.select(USUARIO.fields())
                .from(USUARIO)
                .where(USUARIO.EMAIL.eq(email))
                .fetchOptionalInto(UsuarioModel.class);

        // Se encontrar, carrega as associações
        if (usuario.isPresent()) {
            UsuarioModel u = usuario.get();

            // Carrega perfis
            List<com.dasad.empresa.model.PerfilModel> perfis = dsl.select(PERFIL.fields())
                    .from(PERFIL)
                    .join(USUARIO_PERFIL)
                    .on(USUARIO_PERFIL.PERFIL_ID.eq(PERFIL.ID))
                    .where(USUARIO_PERFIL.USUARIO_ID.eq(u.getId()))
                    .fetchInto(com.dasad.empresa.model.PerfilModel.class);

            u.setPerfis(perfis);

            // Carrega organizações
            List<OrganizacaoModel> organizacoes = dsl.select(ORGANIZACAO.fields())
                    .from(ORGANIZACAO)
                    .join(USUARIO_ORGANIZACAO)
                    .on(USUARIO_ORGANIZACAO.ORGANIZACAO_ID.eq(ORGANIZACAO.ID))
                    .where(USUARIO_ORGANIZACAO.USUARIO_ID.eq(u.getId()))
                    .and(organizacaoId != null ? ORGANIZACAO.ID.eq(organizacaoId) : DSL.noCondition())
                    .fetchInto(OrganizacaoModel.class);

            u.setOrganizacoes(organizacoes);

            return Optional.of(u);
        }

        return Optional.empty();
    }


    public UsuarioModel create(UsuarioModel usuario, Integer organizacaoId) {
        if (isEmailExists(usuario)) {
            throw new EmailAlreadyExistsException("Email já existe: " + usuario.getEmail());
        }

        return dsl.transactionResult(configuration -> {
            DSLContext ctx = DSL.using(configuration);

            String encryptedPassword = passwordEncoder.encode(usuario.getSenha());
            usuario.setSenha(encryptedPassword);

            ctx.insertInto(USUARIO)
                    .set(USUARIO.NOME, usuario.getNome())
                    .set(USUARIO.EMAIL, usuario.getEmail())
                    .set(USUARIO.SENHA, usuario.getSenha())
//                    .set(Usuario.USUARIO.DATA_NASCIMENTO, convertStringToLocalDate(usuario.getDataNascimento()))
                    .set(USUARIO.DATA_NASCIMENTO, usuario.getDataNascimento())
                    .execute();

            Integer userId = ctx.select(USUARIO.ID)
                    .from(USUARIO)
                    .where(USUARIO.EMAIL.eq(usuario.getEmail()))
                    .fetchOneInto(Integer.class);
            usuario.setId(userId);

            saveUserProfiles(usuario, ctx);
            saveUserAddresses(usuario, ctx);
            saveUserOrganizacao(usuario, organizacaoId, ctx);

            return usuario;
        });
    }

    public UsuarioModel update(UsuarioModel usuarioModel, Integer organizacaoId) {
        return dsl.transactionResult(configuration -> {
            DSLContext ctx = DSL.using(configuration);

            ctx.update(USUARIO)
                    .set(USUARIO.NOME, usuarioModel.getNome())
                    .set(USUARIO.EMAIL, usuarioModel.getEmail())
                    .set(USUARIO.DATA_NASCIMENTO, usuarioModel.getDataNascimento())
                    .where(USUARIO.ID.eq(usuarioModel.getId()))
                    .execute();

            deletePerfilUsuarioById(usuarioModel.getId(), ctx);
            saveUserProfiles(usuarioModel, ctx);
            saveUserAddresses(usuarioModel, ctx);
            deleteUserOrganizacaoById(usuarioModel.getId(), organizacaoId, ctx);
            saveUserOrganizacao(usuarioModel, organizacaoId, ctx);

            return findById(usuarioModel.getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        });
    }

    private static void deletePerfilUsuarioById(Integer id, DSLContext ctx) {
        ctx.deleteFrom(USUARIO_PERFIL)
                .where(USUARIO_PERFIL.USUARIO_ID.eq(id))
                .execute();
    }

    public void deleteById(Integer id) {
        dsl.deleteFrom(USUARIO).where(USUARIO.ID.eq(id)).execute();
    }

    public void updatePassword(Integer id, String password) {
        String encryptedPassword = passwordEncoder.encode(password);
        dsl.update(USUARIO)
                .set(USUARIO.SENHA, encryptedPassword)
                .where(USUARIO.ID.eq(id))
                .execute();
    }

    @Override
    public Optional<List<UsuarioFotoModel>> findFoto(Integer usuarioId, boolean isAtivo) {
        UsuarioFotoQueryBuilder queryBuilder = new UsuarioFotoQueryBuilder(this.dsl)
                .withUsuarioId(usuarioId)
                .withAtivo(isAtivo)
                .withLimit(10);
        List<UsuarioFotoModel> result = queryBuilder.build().join();
        return Optional.ofNullable(result.isEmpty() ? null : result);

    }

    @Override
    public UsuarioFotoModel createFoto(UsuarioFotoModel usuarioFotoModel) {
        if (usuarioFotoModel.getUsuarioId() == null) {
            throw new IllegalArgumentException("Usuário não informado");
        }

        if (usuarioFotoModel.getFoto() == null) {
            throw new IllegalArgumentException("Foto não informada");
        }

        return dsl.transactionResult(configuration -> {
            DSLContext ctx = DSL.using(configuration);
            ctx.insertInto(USUARIO_FOTO)
                    .set(USUARIO_FOTO.USUARIO_ID, usuarioFotoModel.getUsuarioId())
                    .set(USUARIO_FOTO.FOTO, usuarioFotoModel.getFoto())
                    .set(USUARIO_FOTO.ATIVO, true)
                    .set(USUARIO_FOTO.DATA_CRIACAO, LocalDateTime.now())
                    .execute();

            return ctx.select(USUARIO_FOTO.ID,
                            USUARIO_FOTO.USUARIO_ID,
                            USUARIO_FOTO.FOTO,
                            USUARIO_FOTO.DATA_CRIACAO,
                            USUARIO_FOTO.DATA_ATUALIZACAO,
                            USUARIO_FOTO.ATIVO)
                    .from(USUARIO_FOTO)
                    .where(USUARIO_FOTO.USUARIO_ID.eq(usuarioFotoModel.getUsuarioId()))
                    .orderBy(USUARIO_FOTO.DATA_CRIACAO.desc())
                    .limit(1)
                    .fetchOptional()
                    .map(record -> {
                        UsuarioFotoModel usuarioFoto = new UsuarioFotoModel();
                        usuarioFoto.setId(record.get(USUARIO_FOTO.ID));
                        usuarioFoto.setUsuarioId(record.get(USUARIO_FOTO.USUARIO_ID));
                        usuarioFoto.setFoto(record.get(USUARIO_FOTO.FOTO));
                        usuarioFoto.setDataCriacao(record.get(USUARIO_FOTO.DATA_CRIACAO).atOffset(ZoneOffset.UTC));
                        if (record.get(USUARIO_FOTO.DATA_ATUALIZACAO) != null) {
                            usuarioFoto.setDataAtualizacao(record.get(USUARIO_FOTO.DATA_ATUALIZACAO).atOffset(ZoneOffset.UTC));
                        } else {
                            usuarioFoto.setDataAtualizacao(null);
                        }
                        usuarioFoto.setAtivo(record.get(USUARIO_FOTO.ATIVO));
                        return usuarioFoto;
                    })
                    .orElseThrow(() -> new RuntimeException("Erro ao cadastrar foto do usuário"));
        });
    }

    @Override
    public UsuarioFotoModel updateFoto(UsuarioFotoModel usuarioFotoModel) {
        if (usuarioFotoModel.getUsuarioId() == null) {
            throw new IllegalArgumentException("Usuário não informado");
        }

        if (usuarioFotoModel.getId() == null) {
            throw new IllegalArgumentException("Foto não informada");
        }

        if (usuarioFotoModel.getFoto() == null) {
            throw new IllegalArgumentException("Foto não informada");
        }

        return dsl.transactionResult(configuration -> {
            DSLContext ctx = DSL.using(configuration);
            ctx.update(USUARIO_FOTO)
                    .set(USUARIO_FOTO.FOTO, usuarioFotoModel.getFoto())
                    .set(USUARIO_FOTO.DATA_ATUALIZACAO, LocalDateTime.now())
                    .set(USUARIO_FOTO.ATIVO, usuarioFotoModel.getAtivo())
                    .where(USUARIO_FOTO.ID.eq(usuarioFotoModel.getId()))
                    .execute();

            return ctx.select(USUARIO_FOTO.ID,
                            USUARIO_FOTO.USUARIO_ID,
                            USUARIO_FOTO.FOTO,
                            USUARIO_FOTO.DATA_CRIACAO,
                            USUARIO_FOTO.DATA_ATUALIZACAO,
                            USUARIO_FOTO.ATIVO)
                    .from(USUARIO_FOTO)
                    .where(USUARIO_FOTO.USUARIO_ID.eq(usuarioFotoModel.getUsuarioId()))
                    .orderBy(USUARIO_FOTO.DATA_CRIACAO.desc())
                    .limit(1)
                    .fetchOptional()
                    .map(record -> {
                        UsuarioFotoModel usuarioFoto = new UsuarioFotoModel();
                        usuarioFoto.setId(record.get(USUARIO_FOTO.ID));
                        usuarioFoto.setUsuarioId(record.get(USUARIO_FOTO.USUARIO_ID));
                        usuarioFoto.setFoto(record.get(USUARIO_FOTO.FOTO));
                        usuarioFoto.setDataCriacao(record.get(USUARIO_FOTO.DATA_CRIACAO).atOffset(ZoneOffset.UTC));
                        if (record.get(USUARIO_FOTO.DATA_ATUALIZACAO) != null) {
                            usuarioFoto.setDataAtualizacao(record.get(USUARIO_FOTO.DATA_ATUALIZACAO).atOffset(ZoneOffset.UTC));
                        } else {
                            usuarioFoto.setDataAtualizacao(null);
                        }
                        usuarioFoto.setAtivo(record.get(USUARIO_FOTO.ATIVO));
                        return usuarioFoto;
                    })
                    .orElseThrow(() -> new RuntimeException("Erro ao cadastrar foto do usuário"));
        });
    }

    @Override
    public Optional<PerfilModel> findPerfilUsuario(LoginRequestDTO loginRequestDTO) {
        // Validação inicial dos parâmetros
        if (loginRequestDTO == null || loginRequestDTO.getEmail() == null || loginRequestDTO.getSenha() == null) {
            return Optional.empty();
        }

        // Busca o usuário pelo email
        return findByEmail(
                loginRequestDTO.getEmail())
                .filter(usuario -> this.passwordEncoder.matches(loginRequestDTO.getSenha(), usuario.getSenha()))
                .flatMap(usuario -> {
                    List<PerfilModel> perfis = usuario.getPerfis();
                    return perfis.isEmpty() ? Optional.empty() : Optional.of(perfis.getFirst());
                });
    }


    private boolean isEmailExists(UsuarioModel usuario) {
        return dsl.fetchExists(
                dsl.selectFrom(USUARIO)
                        .where(DSL.lower(USUARIO.EMAIL).eq(usuario.getEmail().toLowerCase()))
        );
    }

    private void saveUserProfiles(UsuarioModel usuario, DSLContext ctx) {
        for (PerfilModel perfil : usuario.getPerfis()) {
            ctx.insertInto(USUARIO_PERFIL)
                    .set(USUARIO_PERFIL.USUARIO_ID, usuario.getId())
                    .set(USUARIO_PERFIL.PERFIL_ID, perfil.getId())
                    .execute();
        }
    }

    private void saveUserAddresses(UsuarioModel usuario, DSLContext ctx) {
        for (EnderecoModel endereco : usuario.getEnderecos()) {
            if (isEnderecoExists(usuario, endereco, ctx)) {
                updateEnderecoUsuario(usuario, endereco, ctx);
            } else {
                insertEnderecoUsuario(usuario, endereco, ctx);
            }
        }
    }

    private boolean isEnderecoExists(UsuarioModel usuario, EnderecoModel endereco, DSLContext ctx) {
        return ctx.fetchExists(
                ctx.selectFrom(ENDERECO)
                        .where(ENDERECO.ID.eq(endereco.getId())
                                .and(ENDERECO.USUARIO_ID.eq(usuario.getId())))
        );
    }

    private void insertEnderecoUsuario(UsuarioModel usuario, EnderecoModel endereco, DSLContext ctx) {
//        Integer ufId = endereco.getUf() != null ? endereco.getUf().getId() : null;
        ctx.insertInto(ENDERECO)
                .set(ENDERECO.BAIRRO, endereco.getBairro())
                .set(ENDERECO.CEP, endereco.getCep())
                .set(ENDERECO.CIDADE_ID, endereco.getCidadeId().getId())
                .set(ENDERECO.LOGRADOURO, endereco.getLogradouro())
                .set(ENDERECO.USUARIO_ID, usuario.getId())
                .set(ENDERECO.NUMERO, endereco.getNumero())
                .set(ENDERECO.COMPLEMENTO, endereco.getComplemento())
                .execute();
    }

    private void updateEnderecoUsuario(UsuarioModel usuario, EnderecoModel endereco, DSLContext ctx) {
        ctx.update(ENDERECO)
                .set(ENDERECO.BAIRRO, endereco.getBairro())
                .set(ENDERECO.CEP, endereco.getCep())
                .set(ENDERECO.CIDADE_ID, endereco.getCidadeId().getId())
                .set(ENDERECO.LOGRADOURO, endereco.getLogradouro())
                .set(ENDERECO.NUMERO, endereco.getNumero())
                .set(ENDERECO.COMPLEMENTO, endereco.getComplemento())
                .where(ENDERECO.ID.eq(endereco.getId())
                        .and(ENDERECO.USUARIO_ID.eq(usuario.getId())))
                .execute();
    }

    private void saveUserOrganizacao(UsuarioModel usuario, Integer organizacaoId,  DSLContext ctx) {
        ctx.insertInto(USUARIO_ORGANIZACAO)
                .set(USUARIO_ORGANIZACAO.USUARIO_ID, usuario.getId())
                .set(USUARIO_ORGANIZACAO.ORGANIZACAO_ID, organizacaoId)
                .execute();
    }

    private static void deleteUserOrganizacaoById(Integer usuarioId, Integer organizacaoId, DSLContext ctx) {
    ctx.deleteFrom(USUARIO_ORGANIZACAO)
                    .where(USUARIO_ORGANIZACAO.USUARIO_ID.eq(usuarioId))
                            .and(USUARIO_ORGANIZACAO.ORGANIZACAO_ID.eq(organizacaoId))
                                    .execute();
    }
}