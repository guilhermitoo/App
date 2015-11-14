package fatecriopreto.edu.br.appriori.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Reinaldo on 18/07/2015.
 */
public interface GenericDao<T, Type extends Serializable> {

    /**
     * Método que deleta uma entidade do tipo <T> passada por parâmetro da base de dados.
     * @param entity
     * @return Verdadeiro se a deleção aconteceu
     */
    boolean delete(T entity);

    /**
     * Método que devolve uma entidade cujo Id foi passado por parâmetro
     * @param idEntity
     * @return Entity do tipo <T> implementado.
     */
    T select(Type idEntity);

    /**
     * Método que seleciona todos os registros da entidade <T> implementada
     * @return List<T> Uma lista de entidades do tipo T.
     */
    List<T> selectAll();

    /**
     * Método que cria ou altera a entidade passada por parâmetro.
     * @param entity
     * @return Verdadeiro se ouve alteração ou inserção.
     */
    boolean save(T entity);

    /**
     * Método auxiliar para retornar uma entidade do tipo implementado de acordo com o DataBase.
     * @param cursor
     * @return retorna uma entidade T.
     */
     T createEntity(Cursor cursor);

}
