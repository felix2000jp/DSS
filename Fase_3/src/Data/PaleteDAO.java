package Data;

import Business.Palete;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PaleteDAO implements Map<String, Palete> {
    private static PaleteDAO singleton = null;

    public PaleteDAO()
    {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS paletes (" +
                    "CodPalete varchar(20) NOT NULL PRIMARY KEY," +
                    "Conteudo varchar(45) DEFAULT NULL," +
                    "EntidadeRegisto varchar(45) DEFAULT NULL," +
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
    public static PaleteDAO getInstance() {
        if (PaleteDAO.singleton == null) {
            PaleteDAO.singleton = new PaleteDAO();
        }
        return PaleteDAO.singleton;
    }

    /** Rever este
     * Insere uma palete na base de dados
     *
     * ATENÇÂO: Falta devolver o valor existente (caso exista um)
     *
     * @param key o codigo da palete
     * @param p a palete
     * @return para já retorna sempre null (deverá devolver o valor existente, caso exista um)
     * @throws NullPointerException Em caso de erro - deveriam ser criadas exepções do projecto
     */
    public Palete put(String key, Palete p)
    {
        Palete res = null;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement()) {

            // Actualizar a palete
            stm.executeUpdate(
                    "INSERT INTO paletes (CodPalete, Conteudo, EntidadeRegisto, Localizacao) VALUES ('"+p.getCodPalete()+"', '"+
                            p.getConteudo()+"', '"+p.getEntidadeRegisto()+"', '"+ p.getLocalizacao().getLocalizacao()+"')" +
                            "ON DUPLICATE KEY UPDATE Conteudo=VALUES(Conteudo),  EntidadeRegisto=VALUES(EntidadeRegisto), " +
                            "Localizacao=VALUES(Localizacao)");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return res;
    }

    /**
     * @return número de paletes na base de dados
     */
    public int size()
    {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM paletes")) {
            if(rs.next()) {
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

    /**
     * Método que verifica se existem paletes
     *
     * @return true se existirem 0 paletes
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /** Talvez apagar
     * Método que verifica se um codigo de palete existe na base de dados
     *
     * @param key id da turma
     * @return true se a turma existe
     * @throws NullPointerException Em caso de erro - deveriam ser criadas exepções do projecto
     */
    public boolean containsKey(Object key)
    {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT CodPalete FROM paletes WHERE CodPalete='"+key+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    /**
     * Verifica se uma palete existe na base de dados
     *
     * @param value ...
     * @return ...
     * @throws NullPointerException Em caso de erro - deveriam ser criadas exepções do projecto
     */
    public boolean containsValue(Object value) { // Tem de ser mudada
        Palete p = (Palete) value;
        return this.containsKey(p.getCodPalete());
    }

    /**
     * Obter uma palete, dado o seu codigo
     *
     * @param key codigo da palete
     * @return a palete caso exista (null noutro caso)
     * @throws NullPointerException Em caso de erro - deveriam ser criadas exepções do projecto
     */
    public Palete get(Object key) {
        Palete p = null;
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM paletes WHERE CodPalete='"+key+"'")) {
            if (rs.next()) {  // A chave existe na tabela
                // Reconstruir o aluno com os dados obtidos da BD - a chave estranjeira da turma, não é utilizada aqui.
                p = new Palete(rs.getString("CodPalete"), rs.getString("Conteudo"),
                        rs.getString("EntidadeRegisto"), rs.getString("Localizacao"));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    /**
     * Remover uma turma, dado o seu id
     *
     * NOTA: Não estamos a apagar a sala...
     *
     * @param key id da turma a remover
     * @return a turma removida
     * @throws NullPointerException Em caso de erro - deveriam ser criadas exepções do projecto
     */
    public Palete remove(Object key) {
        Palete t = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement()) {
            stm.execute("SET FOREIGN_KEY_CHECKS = 0;");
            stm.execute("UPDATE prateleiras SET Palete=NULL WHERE Palete='" + key + "'");
            stm.execute("DELETE FROM paletes WHERE CodPalete='"+key+"'");
            stm.execute("SET FOREIGN_KEY_CHECKS = 1;");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    /**
     * Adicionar um conjunto de alunos à base de dados
     *
     * @param paletes as alunos a adicionar
     * @throws NullPointerException Em caso de erro - deveriam ser criadas exepções do projecto
     */
    public void putAll(Map<? extends String, ? extends Palete> paletes) {
        for(Palete p : paletes.values()) {
            this.put(p.getCodPalete(), p);
        }
    }

    /**
     * Apagar todos os alunos
     *
     * @throws NullPointerException ...
     * @throws NullPointerException Em caso de erro - deveriam ser criadas exepções do projecto
     */
    public void clear()
    {
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            Statement stm = conn.createStatement()) {
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
    public Set<String> keySet() {
        return null;
    }

    /**
     * @return Todos as paletes da base de dados
     */
    public Collection<Palete> values() {
        Collection<Palete> col = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOConfig.URL + "?user="+DAOConfig.USERNAME + "&password="+DAOConfig.PASSWORD
                +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT CodPalete FROM paletes")) {
            while (rs.next()) {   // Utilizamos o get para construir as alunos
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
    public Set<Entry<String, Palete>> entrySet() {
        return null;
    }


}
