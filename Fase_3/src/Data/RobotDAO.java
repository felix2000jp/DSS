package Data;

import Business.Localizacao;
import Business.Palete;
import Business.Transporte.Robot;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RobotDAO implements Map<String, Robot> {
    private static RobotDAO singleton = null;

    public RobotDAO () {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS robots (" +
                    "CodRobot varchar(10) NOT NULL PRIMARY KEY," +
                    "Localizacao varchar(45) DEFAULT NULL)";
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
    public static RobotDAO getInstance() {
        if (RobotDAO.singleton == null) {
            RobotDAO.singleton = new RobotDAO();
        }
        return RobotDAO.singleton;
    }

    @Override
    public int size() {
        int n = 0;
        try (Connection conn = DriverManager.getConnection(
                DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                        +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM robots"))
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
                ResultSet rs = stm.executeQuery("SELECT CodRobot FROM robots WHERE CodRobot='"+key.toString() + "'" ))
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
        Robot p = (Robot) value;
        return this.containsKey(p.getCodRobot());
    }

    @Override
    public Robot get(Object key) {
        Robot p = null;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM robots WHERE CodRobot='"+key+"'")) {
            if (rs.next()) {  // A chave existe na tabela
                // Reconstruir o aluno com os dados obtidos da BD - a chave estranjeira da turma, não é utilizada aqui.
                p = new Robot(rs.getString("CodRobot"), null, rs.getString("Localizacao"));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public Robot put(String key, Robot p) {
        Robot r = null;
        try (Connection conn = DriverManager.getConnection(
                DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                        +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement()){
            stm.executeUpdate("INSERT INTO robots (CodRobot,Localizacao) VALUES ('"+p.getCodRobot()+"','"+p.getLocalizacao().getLocalizacao()+"') " +
                    "ON DUPLICATE KEY UPDATE Localizacao= VALUES(Localizacao)");


        }catch (Exception e){
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return r;
    }

    @Override
    public Robot remove(Object key) {
        Robot p = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("DELETE FROM robots WHERE CodRobot='"+key+"'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Robot> robots) {
        for(Robot p : robots.values()) {
            this.put(p.getCodRobot(), p);
        }
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE robots");
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
    public Collection<Robot> values() {
        Collection<Robot> col = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT CodRobot FROM robots")) {
            while (rs.next()) {   // Utilizamos o get para construir os robots
                col.add(this.get(rs.getString("CodRobot")));
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return col;
    }

    @Override
    public Set<Entry<String, Robot>> entrySet() {
        return null;
    }
}
