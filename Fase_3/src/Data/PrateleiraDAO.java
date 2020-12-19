package Data;

import Business.Armazenamento.Palete;
import Business.Armazenamento.Prateleira;
import Business.Localizacao;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrateleiraDAO implements Map<String, Prateleira>
{
    private static PrateleiraDAO singleton = null;

    private PrateleiraDAO ()
    {
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
            sql = "CREATE TABLE IF NOT EXISTS prateleiras (" +
                    "CodPrateleira varchar(20) NOT NULL PRIMARY KEY, " +
                    "Palete varchar(10) DEFAULT NULL, foreign key(Palete) references paletes(CodPalete), " +
                    "Localizacao varchar(45), foreign key(Localizacao) references localizacoes(Localizacao))";
            stm.execute(sql);
        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static PrateleiraDAO getInstance()
    {
        if (PrateleiraDAO.singleton == null)
        {
            PrateleiraDAO.singleton = new PrateleiraDAO();
        }
        return PrateleiraDAO.singleton;
    }

    @Override
    public Prateleira put(String key, Prateleira p)
    {
        Palete palete = p.getPalete();
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

            if (palete == null) {
                stm.execute("INSERT INTO prateleiras (CodPrateleira, Palete, Localizacao) " +
                        "VALUES('" + p.getCodPrateleira() + "'," + null + ",'" + local.getLocalizacao() + "') " +
                        "ON DUPLICATE KEY UPDATE Palete=VALUES(Palete)");
            } else {
                stm.execute("INSERT INTO prateleiras (CodPrateleira, Palete, Localizacao) " +
                        "VALUES('" + p.getCodPrateleira() + "','" + palete.getCodPalete() + "','" + local.getLocalizacao() + "') " +
                        "ON DUPLICATE KEY UPDATE Palete=VALUES(Palete)");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return null;
    }

    @Override
    public int size()
    {
        int n = 0;
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement();
                        ResultSet rs = stm.executeQuery("SELECT count(*) FROM prateleiras")
                ) {
            if (rs.next()) {
                n = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return n;
    }

    @Override
    public boolean isEmpty()
    {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key)
    {
        boolean r;
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement();
                        ResultSet rs = stm.executeQuery("SELECT CodPrateleira FROM prateleiras " + "WHERE CodPrateleira = '" + key + "'")
                ) {
            r = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public Prateleira get(Object key)
    {
        Prateleira p = null;
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement();
                        ResultSet rs = stm.executeQuery("SELECT * FROM prateleiras " + "WHERE CodPrateleira = '" + key + "'")
                ) {
            if (rs.next()) {
                p = new Prateleira(rs.getString("CodPrateleira"), PaleteDAO.getInstance().get(rs.getString("Palete")), rs.getString("Localizacao"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }


    @Override
    public Prateleira remove(Object key)
    {
        Prateleira p = this.get(key);
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement()
                ) {
            stm.execute("DELETE FROM prateleiras WHERE CodPrateleira = '" + key + "'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Prateleira> prateleiras)
    {
        for(Prateleira p : prateleiras.values()) {
            this.put(p.getCodPrateleira(), p);
        }
    }

    @Override
    public void clear()
    {
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement()) {
            stm.execute("SET FOREIGN_KEY_CHECKS = 0;");
            stm.executeUpdate("TRUNCATE prateleiras");
            stm.execute("SET FOREIGN_KEY_CHECKS = 1;");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Collection<Prateleira> values()
    {
        Collection<Prateleira> col = new HashSet<>();
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement();
                        ResultSet rs = stm.executeQuery("SELECT CodPrateleira FROM prateleiras")) {
            while (rs.next()) {
                col.add(this.get(rs.getString("CodPrateleira")));
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return col;
    }

    @Override
    public Set<String> keySet()
    {
        throw new NullPointerException("Not Implemented!");
    }

    @Override
    public Set<Entry<String, Prateleira>> entrySet()
    {
        throw new NullPointerException("Not Implemented!");
    }

    @Override
    public boolean containsValue(Object value)
    {
        throw new NullPointerException("Not Implemented!");
    }
}