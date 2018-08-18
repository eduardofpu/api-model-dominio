package br.com.query;

import br.com.connection.ConnectionFactory;
import br.com.table.UpdateTableReq;
import br.com.table.column.AddColumn;

import java.sql.*;
import java.util.*;

public class CreateQuery {

    static Connection con = new ConnectionFactory().getConnection();
    static Statement stmt = null;
    static String values = "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
            "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
            "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
            "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
            "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
            "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
            "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
            "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
            "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
            "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";

       public static void createTable(String nameTable) throws SQLException {

        String primaryKey = random();

        System.out.println("Creating table in given database...");
        stmt = con.createStatement();
        String sql = "CREATE TABLE " + nameTable +
                "(id BIGSERIAL NOT NULL CONSTRAINT " + primaryKey + "_id PRIMARY KEY)";
        stmt.executeUpdate(sql);
        System.out.println("Created table in given database...");
    }

    public static void addConlumnOfTheTable(String nameTable, String nameColumn, String dataType) throws SQLException {
        System.out.println("Table isert conlum and type ...");
        stmt = con.createStatement();
        String sql = "ALTER TABLE " + nameTable + " ADD " + nameColumn + " " + dataType + "";
        stmt.execute(sql);
        System.out.println("Created colum in given database...");
    }

    public static void addConstraintNotNullConlumn(String nameTable, String nameColumn) throws SQLException {
        System.out.println("Add Constraint NOT NULL...");
        stmt = con.createStatement();
        String sql = "ALTER TABLE " + nameTable + " ALTER COLUMN " + nameColumn + " SET NOT NULL";
        stmt.execute(sql);
        System.out.println("Constraint add in given database...");
    }

    public static void addForeignKey(String nameTable1, String nameTable2) throws SQLException {
        String id = "id";
        System.out.println("Add foreign key...");
        stmt = con.createStatement();

        String sql = "ALTER TABLE " + nameTable1 + " ADD  FOREIGN KEY (" + id + ")  REFERENCES " + nameTable2 + " (" + id + ")";

        stmt.execute(sql);
        System.out.println("Constraint add in given database...");
    }

    public static void alterDataTypeIsNameColumn(String nameTable, String nameColumn, String dataType) throws SQLException {
        System.out.println("Alter table  and type data conlum and table ...");
        stmt = con.createStatement();
        String sql = "ALTER TABLE " + nameTable + " ALTER COLUMN " + nameColumn + " TYPE " + dataType + "";
        stmt.executeUpdate(sql);
        System.out.println("data type alter in given database...");
    }

    public static void alterTable(String nameCurrent, String nameModified) throws SQLException {
        stmt = con.createStatement();
        String sql = "ALTER TABLE " + nameCurrent + " RENAME TO  " + nameModified + "";
        stmt.executeUpdate(sql);
        System.out.println("Alter table com sucesso ...");
    }


    public static void dropConstraintNotNullConlumn(String nameTable, String nameColumn) throws SQLException {
        System.out.println("Drop Constraint NULL ...");
        stmt = con.createStatement();
        String sql = "ALTER TABLE " + nameTable + " ALTER COLUMN " + nameColumn + "  DROP NOT NULL";
        stmt.execute(sql);
        System.out.println("Drop in given database...");
    }

    public static void dropColumnOfTheTable(String nameTable, String nameColumn) throws SQLException {
        System.out.println("Delete  conlum and table ...");
        stmt = con.createStatement();
        String sql = "ALTER TABLE " + nameTable +" DROP "+ nameColumn + " ";
        stmt.executeUpdate(sql);
        System.out.println("Deletede colum in given database...");
    }

    public static void dropTable(String name) throws SQLException {

        stmt = con.createStatement();
        String sql = "DROP TABLE " + name + "";
        stmt.executeUpdate(sql);
        System.out.println("drop table com sucesso ...");
    }

    public static void insertInto(String nameTable, List<AddColumn> column, List<Object> objects) throws SQLException {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(String.valueOf(column), String.valueOf(objects));

        String replace = getReplaceColumn(map);

        String sql = null;

        int size = objects.size();
        sql = executeSqlInsert(nameTable, replace, sql, size);

        PreparedStatement pstm = con.prepareStatement(sql);
        executePreparedStatemant(objects, size, pstm);
        pstm.execute();
    }

    public static Object selectIdTable(String nomeTable, List<AddColumn> objectColumn, Long id) throws SQLException {

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTable + " where id = " + id + "");

        Object object = null;
        List<Object> list = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

            id = rs.getLong("id");
            list.add(id);
            for (int i = 0; i < objectColumn.size(); i++) {

                object = rs.getObject(String.valueOf(objectColumn.get(i)));
                list.add(object.toString());
            }
        }
        return Arrays.asList(list);
    }

    public static Object selectTable(String nomeTable, List<AddColumn> objectColumn) throws SQLException {

        PreparedStatement stmt = con.prepareStatement("select * from " + nomeTable + "");

        List<Object> list = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();

        Object object = null;
        while (rs.next()) {

            Long id = rs.getLong("id");
            list.add(id);

            for (int i = 0; i < objectColumn.size(); i++) {
                object = rs.getObject(String.valueOf(objectColumn.get(i)));
                list.add(object);
            }
            continue;
        }
        return list;
    }

    public static void updateAttributeTable(String nameTable, String attribute, Long id, String parameter) {
        String sql = "update " + nameTable + " set "+attribute+"=? where id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, parameter);
            stmt.setLong(2, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateTable(String nameTable, List<AddColumn> column, List<Object> objects, Long id) {

        Map<String, String> map = new LinkedHashMap<>();
        map.put(String.valueOf(column), String.valueOf(objects));

        String values = getReplaceColumnUpdate(map);

        int size = objects.size();

        String sql = "update " + nameTable + " set "+values+" where id=?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            executePreparedStatemant(objects, size, pstm);
            pstm.setLong(size+1, id);
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteTable(String nameTable, Long id) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete " +
                    "from " + nameTable + " where id=?");
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteTableAll(String nameTable) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete " +
                    "from " + nameTable + "");
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void deleteAddColumnId(Long id) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete " +
                    "from  add_column where id_table_id = ?");
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static String random() {
        String p = "p";
        Random r = new Random();
        String id = String.valueOf(r.nextInt(101));
        String k = "k";
        Random rr = new Random();
        String idd = String.valueOf(rr.nextInt(1001));
        return p.concat(id).concat(k).concat(idd);
    }


    private static String executeSqlInsert(String nameTable, String replace, String sql, int size) {
        for (int i = 1; i <= size; i++) {
            if (i == size) {
                String substring = values.substring(i);
                int total = values.length() - substring.length();
                String missingValue = values.substring(substring.length() - total + 1);

                sql = "insert into " + nameTable + " (" + replace + ")  " + "values" + " (" + missingValue + ")";
            }
        }
        return sql;
    }

    private static void executePreparedStatemant(List<Object> objects, int size, PreparedStatement pstm) throws SQLException {
        for (int j = 1; j <= size; j++) {
            if (objects != null) {
                pstm.setObject(j, objects.get(j - 1));
            }
        }
    }


    private static String getReplaceColumn(Map<String, String> map) {
        String name = String.valueOf(map.keySet()).replace("[[", "");
        return name.replace("]]", "");
    }

    private static String getReplaceColumnUpdate(Map<String, String> map) {
        String replace = getReplaceColumn(map);
        String atributos = replace.replace(",","=?,");
        return atributos.concat("=?");
    }
}
