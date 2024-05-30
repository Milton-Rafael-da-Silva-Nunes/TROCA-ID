package trocaid.model.database;

/**
 *
 * @author RafaelNunes
 */
public class DatabaseFactory {

    public static Database getDatabase() {
        return new DatabaseMysql();
    }
}
