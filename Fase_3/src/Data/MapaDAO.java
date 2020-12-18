package Data;
import Business.Localizacao;

import java.sql.*;
import java.util.*;

public class MapaDAO implements Map<Localizacao, List<Localizacao>> {
    private static MapaDAO singleton = null;

    MapaDAO() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" + DAOConfig.USERNAME + "&password=" + DAOConfig.PASSWORD
                + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS mapa (" +
                    "src int(4) NOT NULL," +
                    "des int(4) NOT NULL,"+
                    "primary key (src,des))";
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
    public static MapaDAO getInstance() {
        if (MapaDAO.singleton == null) {
            MapaDAO.singleton = new MapaDAO();
        }
        return MapaDAO.singleton;
    }

    @Override
    public int size() {
        int n = 0;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" + DAOConfig.USERNAME + "&password=" + DAOConfig.PASSWORD
                + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM mapa")) {
            if(rs.next()){
                n = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return n;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean b = false;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" + DAOConfig.USERNAME + "&password=" + DAOConfig.PASSWORD
                + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM mapa WHERE src = '" + key.toString() + "'")) {
            b = rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return b;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public List<Localizacao> get(Object key) {
        List<Localizacao> l = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" + DAOConfig.USERNAME + "&password=" + DAOConfig.PASSWORD
                + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT des FROM mapa WHERE src = '" + key.toString() + "'")) {

            while (rs.next()){
                l.add(new Localizacao(rs.getString(1)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return l;
    }

    @Override
    public List<Localizacao> put(Localizacao key, List<Localizacao> value) {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" + DAOConfig.USERNAME + "&password=" + DAOConfig.PASSWORD
                + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement()) {

            for (Localizacao l : value){
                stm.executeUpdate("INSERT INTO `armazem`.`mapa` values ('" + key.getLocalizacao() + "','" + l.getLocalizacao() + "')"+
                        "ON DUPLICATE KEY UPDATE src=VALUES(src),  des=VALUES(des)" );
            }
        }catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return value;
    }

    @Override
    public List<Localizacao> remove(Object key) {
        List<Localizacao> ll = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" + DAOConfig.USERNAME + "&password=" + DAOConfig.PASSWORD
                + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("DELETE FROM mapa WHERE src='" + key + "'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return ll;
    }

    @Override
    public void putAll(Map<? extends Localizacao, ? extends List<Localizacao>> m) {
        for (Localizacao l : m.keySet()) {
            List<Localizacao> aux = new ArrayList<>();
            for (Localizacao ll : m.get(l)) {
                aux.add(ll);
            }
            this.put(l, aux);
        }
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" + DAOConfig.USERNAME + "&password=" + DAOConfig.PASSWORD
                + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE mapa");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<Localizacao> keySet() {
        return null;
    }

    @Override
    public Collection<List<Localizacao>> values() {
        Collection<List<Localizacao>> col = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" + DAOConfig.USERNAME + "&password=" + DAOConfig.PASSWORD
                + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT src FROM mapa group by src")) {
            while (rs.next()) {   // Utilizamos o get para construir as alunos
                col.add(this.get(rs.getInt("src")));
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return col;
    }

    @Override
    public Set<Entry<Localizacao, List<Localizacao>>> entrySet() {
        return null;
    }
}
