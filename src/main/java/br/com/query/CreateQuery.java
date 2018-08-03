package br.com.query;

import br.com.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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

    public static void addConlumnOfTheTable(String nameTable, String nameColumn, String dataType) throws  SQLException{
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

    public static void dropColumnOfTheTable(String nameTable, String nameColumn) throws  SQLException{
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

}
