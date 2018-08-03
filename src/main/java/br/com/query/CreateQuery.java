package br.com.query;

import br.com.connection.ConnectionFactory;
import br.com.model.Contato;

import java.sql.*;
//www.tutorialspoint.com/jdbc/jdbc-drop-database.htm
//www.bosontreinamentos.com.br/sql-com-sql-server/7-t-sql-alter-e-drop-table-alterar-e-excluir-tabelas-e-colunas-sql-server/
public class CreateQuery {

    static Connection con = new ConnectionFactory().getConnection();
    static Statement stmt = null;

    public static void createSchema(String nameSchema) throws SQLException {

        System.out.println("Creating schema");
        stmt = con.createStatement();

        String sql ="CREATE SCHEMA IF NOT EXISTS "+nameSchema+"";

        stmt.executeUpdate(sql);
        con.close();
        System.out.println("schema foi criado com sucesso!...");
    }

    public static void createIndex(String nomeIndex) throws SQLException{

        System.out.println("Creating index");
        stmt = con.createStatement();
        String sql ="CREATE INDEX "+nomeIndex+"_idx ON "+nomeIndex+" (id)";

        stmt.executeUpdate(sql);
        con.close();
        System.out.println("index foi criado com sucesso!...");
    }

    public static void createTable(String nameTable) throws SQLException{
        System.out.println("Creating model in given database...");
        stmt = con.createStatement();
        String sql ="CREATE TABLE "+nameTable+
                "(id BIGSERIAL NOT NULL CONSTRAINT "+nameTable+"_id PRIMARY KEY)";
        stmt.executeUpdate(sql);
        System.out.println("Created model in given database...");
        //con.close();
        createIndex(nameTable);
    }

    public static void addConlumnEndTable(String nameTable, String nameColumn, String dataType) throws  SQLException{
        System.out.println("Alter Table isert conlum and type ...");
        stmt = con.createStatement();
        String sql ="ALTER TABLE "+nameTable+" ADD "+nameColumn+" "+dataType+"";
        stmt.execute(sql);
        //con.close();
        System.out.println("Created colum in given database...");
    }

    public static void addConstraintNotNullConlumn(String nameTable, String nameColumn) throws  SQLException{
        System.out.println("Add Constraint NOT NULL...");
        stmt = con.createStatement();
        String sql ="ALTER TABLE "+nameTable+" ALTER COLUMN "+nameColumn+" SET NOT NULL";
        stmt.execute(sql);
        //con.close();
        System.out.println("Constraint add in given database...");
    }

    public static void addForeignKey(String nameTable1, String id, String nameTable2) throws  SQLException{
        System.out.println("Add foreign key...");
        stmt = con.createStatement();

        String sql = "ALTER TABLE "+nameTable1+" ADD  FOREIGN KEY ("+id+")  REFERENCES "+nameTable2+" ("+id+")";

        stmt.execute(sql);
        //con.close();
        System.out.println("Constraint add in given database...");
    }

    public static void alterDataTypeColumn(String nameTable, String nameColumn, String dataType) throws  SQLException{
        System.out.println("Alter table  and type data conlum and table ...");
        stmt = con.createStatement();
        String sql ="ALTER TABLE "+nameTable+" ALTER COLUMN "+nameColumn+" TYPE "+dataType+"";
        stmt.executeUpdate(sql);
        //con.close();
        System.out.println("data type alter in given database...");
    }

    public static void alterTable(String nameCurrent, String nameModified) throws SQLException {
        stmt = con.createStatement();
        String sql ="ALTER TABLE "+nameCurrent+" RENAME TO  "+nameModified+"";
        stmt.executeUpdate(sql);
        con.close();
        System.out.println("Alter table com sucesso ...");
    }


    public static void dropConstraintNotNullConlumn(String nameTable, String nameColumn) throws  SQLException{
        System.out.println("Add Constraint NULL ...");
        stmt = con.createStatement();
        String sql ="ALTER TABLE "+nameTable+" ALTER COLUMN "+nameColumn+"  DROP NOT NULL";
        stmt.execute(sql);
        //con.close();
        System.out.println("Constraint add in given database...");
    }

    public static void dropColumnAndTable(String nameTable, String nameColumn) throws  SQLException{
        System.out.println("Delete  conlum and table ...");
        stmt = con.createStatement();
        String sql ="ALTER TABLE "+nameTable+" DROP "+nameColumn+"";
        stmt.executeUpdate(sql);
        //con.close();
        System.out.println("Deletede colum in given database...");
    }

    public static void dropTable(String name) throws SQLException {

        stmt = con.createStatement();
        String sql ="DROP TABLE "+name+"";
        stmt.executeUpdate(sql);
        con.close();
        System.out.println("drop table com sucesso ...");
    }

    public static void dropDataBase(String nameDataBase) throws  SQLException{
        System.out.println("Delete  data base and table ...");
        stmt = con.createStatement();
        String sql ="DROP DATABASE "+nameDataBase+"";
        stmt.executeUpdate(sql);
        con.close();
        System.out.println("Deletede data base ...");
    }


    public static void createTableContato(String nameSchema, String nameTable, String atributo, String variante) throws SQLException{
        createSchema(nameSchema);
        System.out.println("Creating model in given database...");
        stmt = con.createStatement();
        String sql ="CREATE TABLE "+nameTable+
                "(id BIGSERIAL NOT NULL CONSTRAINT "+nameTable+"_pkey PRIMARY KEY, " +
                ""+atributo+"  "+variante+" NOT NULL)";
        stmt.executeUpdate(sql);
        System.out.println("Created model in given database...");
        createIndex(nameTable);
    }

    public static void createTableRegistration() throws SQLException {

        System.out.println("Creating model in given database...");
        stmt = con.createStatement();

        String sql = "CREATE TABLE REGISTRATION " +
                "(id INTEGER not NULL, " +
                " first VARCHAR(255), " +
                " last VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";

        stmt.executeUpdate(sql);
        con.close();
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

    public static void consulta(String nomeTable) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("select * from "+nomeTable+"");

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

    public static void remove(String nameTable, Long id) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete " +
                    "from "+nameTable+" where id=?");
            stmt.setLong(1, id);
            stmt.execute();
            stmt.close();
            System.out.println("Deletado id = "+id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
