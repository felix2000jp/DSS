package Data;

import Business.Localizacao;
import Business.Palete;
import Business.Prateleira;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public class PrateleiraDAO {
    private static PrateleiraDAO singleton = null;

    private PrateleiraDAO () {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
             String sql = "CREATE TABLE IF NOT EXISTS prateleira (" +
                          "CodPrateleira varchar(10) NOT NULL PRIMARY KEY," +
                          "Palete varchar(10), foreign key(Palete) references palete(CodPalete))"+
                          "Localizacao int NOT NULL,";
                          // Assume-se uma relação 1-n entre Turma e Aluno
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static PrateleiraDAO getInstance() {
        if (PrateleiraDAO.singleton == null) {
            PrateleiraDAO.singleton = new PrateleiraDAO();
        }
        return PrateleiraDAO.singleton;
    }

    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM prateleiras")) {
            if( rs.next() ) {
                i = rs.getInt(1);
            }
        }
        catch (Exception e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT Num FROM prateleiras WHERE CodPrateleira = '" + key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public boolean containsValue(Object value) {
        Prateleira p = (Prateleira) value;
        return this.containsKey( p.getCodPrateleira() );
    }

    public Prateleira get(Object key) {
        Prateleira p = null;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM paletes WHERE CodPalete='"+key+"'")) {
            if (rs.next()) {  // A chave existe na tabela
                p = new Prateleira( rs.getString("CodPrateleira"), rs.getObject("Palete", Palete.class),
                                    rs.getObject("Localizacao", Localizacao.class));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    public Prateleira put(String key, Prateleira p) {
        Prateleira res = null;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            // Actualizar o aluno
            stm.executeUpdate("INSERT INTO prateleira VALUES ('" +
                                  p.getCodPrateleira() + "', '" + p.getPalete() + "', '" + p.getLocalizacao() +
                                  "ON DUPLICATE KEY UPDATE CodPrateleira = VALUES(CodPrateleira), Palete = VALUES(Palete)");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    public Prateleira remove(Object key) {
        Prateleira t = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("DELETE FROM Prateleira WHERE CodPrateleira = '" + key + "'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    public void putAll(Map<? extends String, ? extends Prateleira> paletes) {
        for( Prateleira p : paletes.values() ) {
            this.put( p.getCodPrateleira(), p );
        }
    }

    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE Prateleira");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public Collection<Prateleira> values() {
        Collection<Prateleira> col = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL, DAOConfig.USERNAME, DAOConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT CodPrateleira FROM prateleiras")) {
            while (rs.next()) {   // Utilizamos o get para construir as alunos
                col.add(this.get(rs.getString("Num")));
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return col;
    }
}
