package Data;

import Business.Prateleira;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrateleiraDAO implements Map<String, Prateleira> {
    private static PrateleiraDAO singleton = null;

    private PrateleiraDAO () {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS prateleira (" +
                    "CodPrateleira varchar(10) NOT NULL PRIMARY KEY," +
                    "Localizacao int NOT NULL," +
                    "Palete varchar(10), foreign key(Palete) references paletes(CodPalete))";  // Assume-se uma relação 1-0..1 entre Prateleira e Palete
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Implementação do padrão Singleton
     *
     * @return devolve a instância única desta classe
     */
    public static PrateleiraDAO getInstance() {
        if (PrateleiraDAO.singleton == null) {
            PrateleiraDAO.singleton = new PrateleiraDAO();
        }
        return PrateleiraDAO.singleton;
    }

    @Override
    public int size() {
        int n = 0;
        try (Connection conn = DriverManager.getConnection(
                DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                        +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM prateleiras"))
        {
            if(rs.next()){
                n = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return n;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean r;
        try (
                Connection conn = DriverManager.getConnection(
                        DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT CodPrateleira FROM prateleira" +
                        "WHERE CodPrateleira='"+key.toString() + "'" ))
        {
            r = rs.next();
        }catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Prateleira get(Object key) {
        Prateleira p = null;
        try (Connection conn = DriverManager.getConnection(
                DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                        +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM prateleira" +
                     "WHERE CodPrateleira = '" + key.toString() + "'"))
        {
            PaleteDAO palete = new PaleteDAO();
            if(rs.next()){
                p = new Prateleira(rs.getString("CodPrateleira"), palete.get(rs.getString("Palete")),
                        rs.getString("Localizacao"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public Prateleira put(String key, Prateleira p) {
        try (Connection conn = DriverManager.getConnection(
                DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                        +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement()){
            stm.executeQuery("INSERT INTO prateleira (CodPrateleira, Palete, Localizacao)" +
                    "VALUES '" + p.getCodPrateleira() + "','" + p.getPalete() + "','" + p.getLocalizacao().toString() + "')"+
                    "ON DUPLICATE KEY UPDATE Palete=VALUES(Palete), Localizacao = VALUES (Localizacao)");


        }catch (Exception e){
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return null;
    }

    @Override
    public Prateleira remove(Object key) {
        Prateleira p = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("DELETE FROM prateleira WHERE CodPrateleira='"+key+"'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Prateleira> prateleiras) {
        for(Prateleira p : prateleiras.values()) {
            this.put(p.getCodPrateleira(), p);
        }
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE prateleiras");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Prateleira> values() {
        Collection<Prateleira> col = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT CodPalete FROM prateleira")) {
            while (rs.next()) {   // Utilizamos o get para construir as prateleiras
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
    public Set<Entry<String, Prateleira>> entrySet() {
        return null;
    }
}
