package Data;

import Business.Localizacao;
import Business.Transporte.Robot;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RobotDAO implements Map<String, Robot> {
    private static RobotDAO singleton = null;

    private RobotDAO() {
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
            sql = "CREATE TABLE IF NOT EXISTS robots (" +
                  "CodRobot varchar(20) NOT NULL PRIMARY KEY, " +
                  "Disponibilidade varchar(45) DEFAULT NULL, " +
                  "Localizacao varchar(45), foreign key(Localizacao) references localizacoes(Localizacao))";
            stm.execute(sql);
        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static RobotDAO getInstance()
    {
        if (RobotDAO.singleton == null)
        {
            RobotDAO.singleton = new RobotDAO();
        }
        return RobotDAO.singleton;
    }

    @Override
    public Robot put(String key, Robot p) {
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
            stm.execute("INSERT INTO robots (CodRobot, Disponibilidade, Localizacao) " +
                            "VALUES ('" + p.getCodRobot() + "', '" + p.getDisponivel() + "', '" + local.getLocalizacao() + "')" +
                            "ON DUPLICATE KEY UPDATE Disponibilidade=VALUES(Disponibilidade), Localizacao=VALUES(Localizacao)");
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
                        ResultSet rs = stm.executeQuery("SELECT count(*) FROM robots")
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
                        ResultSet rs = stm.executeQuery("SELECT CodRobot FROM robots WHERE CodRobot = '" + key + "'")
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
    public Robot get(Object key) {
        Robot p = null;
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement();
                        ResultSet rs = stm.executeQuery("SELECT * FROM robots WHERE CodRobot='" + key + "'")
                ) {
            if (rs.next()) {
                p = new Robot(rs.getString("CodRobot"),
                              rs.getInt("Disponibilidade"),
                              rs.getString("Localizacao"));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public Robot remove(Object key) {
        Robot t = this.get(key);
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement()
                ) {
            stm.execute("DELETE FROM robots WHERE CodRobot = '" + key + "'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Robot> robots) {
        for (Robot p : robots.values()) {
            this.put(p.getCodRobot(), p);
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
            stm.execute("TRUNCATE robots");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Collection<Robot> values() {
        Collection<Robot> col = new HashSet<>();
        try
                (
                        Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user=" +
                                DAOConfig.USERNAME + "&password=" +
                                DAOConfig.PASSWORD +
                                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                        Statement stm = conn.createStatement();
                        ResultSet rs = stm.executeQuery("SELECT CodRobot FROM robots")
                ) {
            while (rs.next()) {
                col.add(this.get(rs.getString("CodRobot")));
            }
        } catch (Exception e)
        {
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
    public Set<Entry<String, Robot>> entrySet() {
        throw new NullPointerException("Not Implemented!");
    }

    @Override
    public boolean containsValue(Object value) {
        throw new NullPointerException("Not Implemented!");
    }


}