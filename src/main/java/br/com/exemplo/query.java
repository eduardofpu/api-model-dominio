package br.com.exemplo;

import br.com.connection.ConnectionFactory;
import br.com.model.Contato;

import java.sql.*;

public class query {
    static Connection con = new ConnectionFactory().getConnection();
    public static void main(String[] args) throws SQLException {

        createSchema();
        //createTableContato();
        //createIndex();

        //createTableRegistration();

        // Contato model = new Contato("Mysql");
        //save(model);

        //consulta();

        //Contato model = new Contato(3L,"java6");
        //altera(model);

        //Contato id = new Contato(3L);
        //remove(id);

        con.close();
    }

    public static void createSchema() throws SQLException{
        Statement stmt = null;
        //STEP 4: Execute a query
        System.out.println("Creating schema");
        stmt = con.createStatement();

        String sql ="CREATE SCHEMA IF NOT EXISTS "+"ApiTable";

        stmt.executeUpdate(sql);
        System.out.println("schema foi criado com sucesso!...");
    }

    public static void createIndex() throws SQLException{
        Statement stmt = null;
        //STEP 4: Execute a query
        System.out.println("Creating index");
        stmt = con.createStatement();
        String sql ="CREATE INDEX api_id_idx ON PUBLIC.contato (id)";

        stmt.executeUpdate(sql);
        System.out.println("index foi criado com sucesso!...");
    }

    public static void createTableContato() throws SQLException{
        Statement stmt = null;
        //STEP 4: Execute a query
        System.out.println("Creating model in given database...");
        stmt = con.createStatement();
        String sql ="CREATE TABLE contato"+
                "(id BIGSERIAL    NOT NULL CONSTRAINT contato_pkey PRIMARY KEY,"+
                "name varchar(45) NOT NULL)";

        stmt.executeUpdate(sql);
        System.out.println("Created model in given database...");
    }

    public static void createTableRegistration() throws SQLException {
        Statement stmt = null;
        //STEP 4: Execute a query
        System.out.println("Creating model in given database...");
        stmt = con.createStatement();

        String sql = "CREATE TABLE REGISTRATION " +
                "(id INTEGER not NULL, " +
                " first VARCHAR(255), " +
                " last VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";

        stmt.executeUpdate(sql);
        System.out.println("Created model in given database...");
    }

    public static void  save(Contato contato) throws SQLException {
        String sql = "insert into Contato" + " (name)" + " values (?)";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setString(1, contato.getName());

        // executa
        stmt.execute();
        stmt.close();

        System.out.println("Gravado name = "+ contato.getName());
        //con.close();
    }

    public static void consulta() throws SQLException {
        PreparedStatement stmt = con.prepareStatement("select * from Contato");

        // executa um select
        ResultSet rs = stmt.executeQuery();
        // itera no ResultSet
        while (rs.next()) {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            System.out.println(id + " _ " + name);
        }

        rs.close();
        stmt.close();
        //con.close();
    }

    public static void altera(Contato contato) {
        String sql = "update Contato set name=? where id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, contato.getName());

            stmt.setLong(2, contato.getId());
            stmt.execute();
            stmt.close();
            System.out.println("Alterado id = "+contato.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void remove(Contato contato) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete " +
                    "from Contato where id=?");
            stmt.setLong(1, contato.getId());
            stmt.execute();
            stmt.close();
            System.out.println("Deletado id = "+contato.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
