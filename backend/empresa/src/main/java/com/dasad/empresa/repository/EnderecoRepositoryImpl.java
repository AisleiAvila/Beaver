package com.dasad.empresa.repository;

import com.dasad.empresa.jooq.model.tables.Cidade;
import com.dasad.empresa.jooq.model.tables.Estado;
import com.dasad.empresa.jooq.model.tables.Pais;
import com.dasad.empresa.model.CidadeModel;
import com.dasad.empresa.model.EnderecoModel;
import com.dasad.empresa.model.EstadoModel;
import com.dasad.empresa.model.PaisModel;
import org.jooq.DSLContext;
import org.jooq.Record15;
import org.jooq.SelectJoinStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.dasad.empresa.jooq.model.tables.Endereco.ENDERECO;

@Repository
public class EnderecoRepositoryImpl implements EnderecoRepository {
    private final DSLContext dsl;

    @Autowired
    public EnderecoRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<EnderecoModel> findAll() {
        return buildBaseQuery()
                .fetch(EnderecoRepositoryImpl::getenderecoModel);
    }

    public Optional<EnderecoModel> findById(Integer id) {
        return Optional.ofNullable(
                buildBaseQuery()
                        .where(ENDERECO.ID.eq(id))
                        .fetchOne(EnderecoRepositoryImpl::getenderecoModel)
        );
    }

    public EnderecoModel save(EnderecoModel endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereco não pode ser nulo.");
        }
        if (endereco.getCidadeId() == null || endereco.getCep() == null || endereco.getLogradouro() == null) {
            throw new IllegalArgumentException("Campos obrigatórios não podem ser nulos.");
        }

        this.dsl.insertInto(ENDERECO)
                .set(ENDERECO.ID, endereco.getId())
                .set(ENDERECO.CIDADE_ID, endereco.getCidadeId().getId())
                .set(ENDERECO.CEP, endereco.getCep())
                .set(ENDERECO.LOGRADOURO, endereco.getLogradouro())
                .set(ENDERECO.NUMERO, endereco.getNumero())
                .set(ENDERECO.COMPLEMENTO, endereco.getComplemento())
                .set(ENDERECO.BAIRRO, endereco.getBairro())
                .set(ENDERECO.LATITUDE, endereco.getLatitude())
                .set(ENDERECO.LONGITUDE, endereco.getLongitude())
                .set(ENDERECO.USUARIO_ID, endereco.getUsuarioId())
                .execute();
        return endereco;
    }

    public EnderecoModel update(EnderecoModel endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereco não pode ser nulo.");
        }
        if (endereco.getId() == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        this.dsl.update(ENDERECO)
                .set(ENDERECO.CIDADE_ID, endereco.getCidadeId().getId())
                .set(ENDERECO.CEP, endereco.getCep())
                .set(ENDERECO.LOGRADOURO, endereco.getLogradouro())
                .set(ENDERECO.NUMERO, endereco.getNumero())
                .set(ENDERECO.COMPLEMENTO, endereco.getComplemento())
                .set(ENDERECO.BAIRRO, endereco.getBairro())
                .set(ENDERECO.LATITUDE, endereco.getLatitude())
                .set(ENDERECO.LONGITUDE, endereco.getLongitude())
                .set(ENDERECO.USUARIO_ID, endereco.getUsuarioId())
                .where(ENDERECO.ID.eq(endereco.getId()))
                .execute();
        return endereco;
    }

    public void deleteById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        this.dsl.deleteFrom(ENDERECO).where(ENDERECO.ID.eq(id)).execute();
    }

    private SelectJoinStep<Record15<Integer, String, String, String, String, String, BigDecimal, BigDecimal, Integer, Integer, String, Integer, String, Integer, String>> buildBaseQuery() {
        return this.dsl.select(
                        ENDERECO.ID,
                        ENDERECO.LOGRADOURO,
                        ENDERECO.NUMERO,
                        ENDERECO.COMPLEMENTO,
                        ENDERECO.BAIRRO,
                        ENDERECO.CEP,
                        ENDERECO.LATITUDE,
                        ENDERECO.LONGITUDE,
                        ENDERECO.USUARIO_ID,
                        Cidade.CIDADE.ID.as("cidadeId"),
                        Cidade.CIDADE.NOME.as("cidadeNome"),
                        Estado.ESTADO.ID.as("estadoId"),
                        Estado.ESTADO.NOME.as("estadoNome"),
                        Pais.PAIS.ID.as("paisId"),
                        Pais.PAIS.NOME.as("paisNome")
                )
                .from(ENDERECO)
                .join(Cidade.CIDADE).on(ENDERECO.CIDADE_ID.eq(Cidade.CIDADE.ID))
                .join(Estado.ESTADO).on(Cidade.CIDADE.ESTADO_ID.eq(Estado.ESTADO.ID))
                .join(Pais.PAIS).on(Estado.ESTADO.PAIS_ID.eq(Pais.PAIS.ID));
    }

    private static EnderecoModel getenderecoModel(Record15<Integer, String, String, String, String, String, BigDecimal, BigDecimal, Integer, Integer, String, Integer, String, Integer, String> item) {
        if (item == null) {
            return null;
        }

        var endereco = new EnderecoModel();
        endereco.setId(item.get(ENDERECO.ID));
        endereco.setLogradouro(item.get(ENDERECO.LOGRADOURO));
        endereco.setNumero(item.get(ENDERECO.NUMERO));
        endereco.setComplemento(item.get(ENDERECO.COMPLEMENTO));
        endereco.setBairro(item.get(ENDERECO.BAIRRO));
        endereco.setCep(item.get(ENDERECO.CEP));
        endereco.setLatitude(item.get(ENDERECO.LATITUDE));
        endereco.setLongitude(item.get(ENDERECO.LONGITUDE));
        endereco.setUsuarioId(item.get(ENDERECO.USUARIO_ID));

        endereco.setCidadeId(createCidadeModel(item));
        return endereco;
    }

    private static CidadeModel createCidadeModel(Record15<Integer, String, String, String, String, String, BigDecimal, BigDecimal, Integer, Integer, String, Integer, String, Integer, String> item) {
        CidadeModel cidade = new CidadeModel();
        cidade.setId(item.get(Cidade.CIDADE.ID.as("cidadeId")));
        cidade.setNome(item.get(Cidade.CIDADE.NOME.as("cidadeNome")));
        cidade.setEstadoId(createEstadoModel(item));
        return cidade;
    }

    private static EstadoModel createEstadoModel(Record15<Integer, String, String, String, String, String, BigDecimal, BigDecimal, Integer, Integer, String, Integer, String, Integer, String> item) {
        EstadoModel estado = new EstadoModel();
        estado.setId(item.get(Estado.ESTADO.ID.as("estadoId")));
        estado.setNome(item.get(Estado.ESTADO.NOME.as("estadoNome")));
        estado.setPaisId(createPaisModel(item));
        return estado;
    }

    private static PaisModel createPaisModel(Record15<Integer, String, String, String, String, String, BigDecimal, BigDecimal, Integer, Integer, String, Integer, String, Integer, String> item) {
        PaisModel pais = new PaisModel();
        pais.setId(item.get(Pais.PAIS.ID.as("paisId")));
        pais.setNome(item.get(Pais.PAIS.NOME.as("paisNome")));
        return pais;
    }
}