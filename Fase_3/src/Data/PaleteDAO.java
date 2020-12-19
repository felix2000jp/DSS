package Data;

import Business.Armazenamento.Palete;
import Business.Localizacao;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PaleteDAO implements Map<String, Palete> {
    private static PaleteDAO singleton = null;

    private PaleteDAO() {
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement()
                ) {
            String sql = "";
            sql = "CREATE TABLE IF NOT EXISTS localizacoes (Localizacao varchar(45) NOT NULL PRIMARY KEY)";
            stm.execute(sql);
            sql = "CREATE TABLE IF NOT EXISTS paletes (" +
                    "CodPalete varchar(20) NOT NULL PRIMARY KEY, " +
                    "Conteudo varchar(45) DEFAULT NULL, " +
                    "EntidadeRegisto varchar(45) DEFAULT NULL, " +
                    "NecessidadeTransporte int(4) DEFAULT NULL, " +
                    "Localizacao varchar(45), foreign key(Localizacao) references localizacoes(Localizacao))";
            stm.execute(sql);
        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static PaleteDAO getInstance() {
        if (PaleteDAO.singleton == null) {
            PaleteDAO.singleton = new PaleteDAO();
        }
        return PaleteDAO.singleton;
    }

    @Override
    public Palete put(String key, Palete p) {
        Localizacao local = p.getLocalizacao();
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement()
                ) {
            stm.execute("INSERT INTO localizacoes (Localizacao) VALUES ('" + local.getLocalizacao() + "') " +
                    "ON DUPLICATE KEY UPDATE Localizacao=VALUES(Localizacao)");
            stm.execute("INSERT INTO paletes (CodPalete, Conteudo, EntidadeRegisto, Localizacao, NecessidadeTransporte) " +
                    "VALUES ('" + p.getCodPalete() + "', '" + p.getConteudo() + "', '" + p.getEntidadeRegisto() + "', '" +
                    local.getLocalizacao() + "', " + p.getNecessidadeTransporte() + "')" +
                    "ON DUPLICATE KEY UPDATE Conteudo=VALUES(Conteudo), EntidadeRegisto=VALUES(EntidadeRegisto), " +
                    "Localizacao=VALUES(Localizacao), NecessidadeTransporte=VALUES(NecessidadeTransporte)");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return null;
    }

    @Override
    public int size() {
        int i = 0;
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement();
                        ResultSet rs = stm.executeQuery("SELECT count(*) FROM paletes")
                ) {
            if (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (Exception e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean r;
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement();
                        ResultSet rs = stm.executeQuery("SELECT CodPalete FROM paletes WHERE CodPalete='" + key + "'")
                ) {
            r = rs.next();
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public Palete get(Object key) {
        Palete p = null;
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement();
                        ResultSet rs = stm.executeQuery("SELECT * FROM paletes WHERE CodPalete='" + key + "'")
                ) {
            if (rs.next()) {
                p = new Palete(rs.getString("CodPalete"), rs.getString("Conteudo"),
                        rs.getString("EntidadeRegisto"), rs.getString("Localizacao"),
                        rs.getString("NecessidadeTransporte"));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public Palete remove(Object key) {
        Palete t = this.get(key);
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement()
                ) {
            stm.execute("SET FOREIGN_KEY_CHECKS = 0;");
            stm.execute("UPDATE prateleiras SET Palete=NULL WHERE Palete='" + key + "'");
            stm.execute("DELETE FROM paletes WHERE CodPalete='" + key + "'");
            stm.execute("SET FOREIGN_KEY_CHECKS = 1;");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Palete> paletes) {
        for (Palete p : paletes.values()) {
            this.put(p.getCodPalete(), p);
        }
    }

    @Override
    public void clear() {
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement()
                ) {
            stm.execute("SET FOREIGN_KEY_CHECKS = 0;");
            stm.execute("UPDATE prateleiras SET Palete=NULL");
            stm.executeUpdate("TRUNCATE paletes");
            stm.execute("SET FOREIGN_KEY_CHECKS = 1;");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Collection<Palete> values() {
        Collection<Palete> col = new HashSet<>();
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement();
                        ResultSet rs = stm.executeQuery("SELECT CodPalete FROM paletes")
                ) {
            while (rs.next()) {
                col.add(this.get(rs.getString("CodPalete")));
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return col;
    }

    @Override
    public Set<String> keySet() {
        throw new NullPointerException("Not Implemented!");
    }

    @Override
    public Set<Entry<String, Palete>> entrySet() {
        throw new NullPointerException("Not Implemented!");
    }

    @Override
    public boolean containsValue(Object value) {
        throw new NullPointerException("Not Implemented!");
    }


}