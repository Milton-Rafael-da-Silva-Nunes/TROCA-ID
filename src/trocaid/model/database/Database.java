package trocaid.model.database;

import java.sql.Connection;

/**
 *
 * @author RafaelNunes
 */
public interface Database {
    
    public Connection conectar();
    public void desconectar(Connection conn);
}
