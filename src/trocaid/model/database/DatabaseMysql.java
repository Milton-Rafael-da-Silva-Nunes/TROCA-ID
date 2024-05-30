package trocaid.model.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author RafaelNunes
 */
public class DatabaseMysql implements Database {

    final private String driver = "com.mysql.jdbc.Driver";
    private Connection connection;
    public static String ip;
    public static String bancoDados;
    public static String usuario;
    public static String senha;
    private String porta;

    public DatabaseMysql() {
        setarLoginBanco();
    }

    @Override
    public Connection conectar() {
        try {
            String url = "jdbc:mysql://" + ip + ":" + porta + "/" + bancoDados
                    + "?useUnicode=true&characterEncoding=UTF-8";
            Class.forName(driver);
            this.connection = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conectado");
            return this.connection;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.getMessage();
            JOptionPane.showMessageDialog(null,
                    "\nNão Foi Possivel Conctar ao Servidor\nVerifique a Conexão com o Banco\n\n\n" + ex.getMessage(),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public void desconectar(Connection connection) {
        try {
            connection.close();
            System.out.println("Desconectado");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setarLoginBanco() {
        File file = new File(System.getProperty("user.dir") + "\\rede.txt");

        if (!file.exists()) {
            System.out.println("Arquivo rede.txt inexistente.");
        } else if (file.length() == 0) {
            System.out.println("Arquivo rede.txt vazio.");
        } else {

            String linha;
            try (FileReader fileReader = new FileReader(file); BufferedReader reader = new BufferedReader(fileReader)) {

                while ((linha = reader.readLine()) != null) {

                    String[] split = linha.split(":");

                    if (split[0].equalsIgnoreCase("IP")) {
                        ip = (linha.split(":")[1]);
                    }
                    if (split[0].equalsIgnoreCase("DB")) {
                        bancoDados = (linha.split(":")[1]);
                    }
                    if (split[0].equalsIgnoreCase("USER")) {
                        usuario = (linha.split(":")[1]);
                    }
                    if (split[0].equalsIgnoreCase("KEY")) {
                        senha = (linha.split(":")[1]);
                    }
                    if (split[0].equalsIgnoreCase("PORT")) {
                        porta = (linha.split(":")[1]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
