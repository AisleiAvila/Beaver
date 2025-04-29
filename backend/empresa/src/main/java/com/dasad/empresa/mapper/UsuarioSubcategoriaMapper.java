package com.dasad.empresa.mapper;

import com.dasad.empresa.model.CategoriaModel;
import com.dasad.empresa.model.SubCategoriaModel;
import com.dasad.empresa.model.UsuarioSubcategoriaModel;
import org.jooq.Record;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static com.dasad.empresa.jooq.model.tables.Categoria.CATEGORIA;
import static com.dasad.empresa.jooq.model.tables.Subcategoria.SUBCATEGORIA;
import static com.dasad.empresa.jooq.model.tables.UsuarioSubcategoria.USUARIO_SUBCATEGORIA;

public class UsuarioSubcategoriaMapper {

    public static UsuarioSubcategoriaModel map(Record registro) {
        UsuarioSubcategoriaModel usuarioSubcategoria = new UsuarioSubcategoriaModel();
        usuarioSubcategoria.setUsuarioId(registro.get(USUARIO_SUBCATEGORIA.USUARIO_ID));

        // Mapeamento de datas com conversão para OffsetDateTime
        LocalDateTime dataCriacao = registro.get(USUARIO_SUBCATEGORIA.DATA_CRIACAO);
        usuarioSubcategoria.setDataCriacao(dataCriacao != null
                ? OffsetDateTime.of(dataCriacao, ZoneOffset.UTC)
                : null);

        LocalDateTime dataExclusao = registro.get(USUARIO_SUBCATEGORIA.DATA_EXCLUSAO);
        usuarioSubcategoria.setDataExclusao(dataExclusao != null
                ? OffsetDateTime.of(dataExclusao, ZoneOffset.UTC)
                : null);

        // Mapeamento da Categoria
        CategoriaModel categoria = new CategoriaModel();
        categoria.setId(registro.get(CATEGORIA.ID));
        categoria.setNome(registro.get(CATEGORIA.NOME));
        usuarioSubcategoria.setCategoria(categoria);

        // Mapeamento da SubCategoria
        SubCategoriaModel subcategoria = new SubCategoriaModel();
        subcategoria.setId(registro.get(USUARIO_SUBCATEGORIA.SUBCATEGORIA_ID));
        subcategoria.setNome(registro.get(SUBCATEGORIA.NOME));
        usuarioSubcategoria.setSubcategoria(subcategoria);

        return usuarioSubcategoria;
    }
}