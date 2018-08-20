package br.com.Controller;

import br.com.AbstractTest;
import br.com.table.NameTableReq;
import net.minidev.json.JSONObject;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class QueryControllerTest extends AbstractTest {

    private final String PATH = "/";

    private final String NAME_TABLE = "nameTable";
    private final String NAME_COLUMN = "nameColumn";
    private final String DATA_TYPE = "dataType";
    private final String NAME_CURRENT = "nameCurrent";
    private final String NAME_MODIFIED = "nameModified";

    private final String NAME_TABLE_CONTATO = "contato";
    private final String NAME_TABLE_CIDADE = "cidade";
    private final String NAME_COLUMN_NAME = "name";
    private final String DATA_TYPE_TEXT = "text";
    private final String DATA_TYPE_VARCHAR = "VARCHAR(45)";

    NameTableReq nameTableReq = new NameTableReq(NAME_TABLE_CONTATO);
    NameTableReq nameTableReq2 = new NameTableReq(NAME_TABLE_CIDADE);

    @Test
    public void createTable() throws Exception {

        Map<String, String> data = new HashMap<>();
        data.put(NAME_TABLE, NAME_TABLE_CONTATO);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH + "create/table")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertThat(NAME_TABLE).isEqualTo("nameTable");
        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");

        drop_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
    }

    @Test
    public void alterTable() throws Exception {

        create_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");

        Map<String, String> data = new HashMap<>();
        data.put(NAME_CURRENT, NAME_TABLE_CONTATO);
        data.put(NAME_MODIFIED, NAME_TABLE_CIDADE);

        this.mockMvc.perform(MockMvcRequestBuilders.put(PATH + "alter/table")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");
        Assertions.assertThat(NAME_TABLE_CIDADE).isEqualTo("cidade");

        drop_table(nameTableReq2);
        Assertions.assertThat(nameTableReq2.getNameTable()).isEqualTo("cidade");
    }

    @Test
    public void createColumn() throws Exception {

        create_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");

        Map<String, String> data = mapData();

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH + "add/column")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");
        Assertions.assertThat(NAME_COLUMN_NAME).isEqualTo("name");
        Assertions.assertThat(DATA_TYPE_TEXT).isEqualTo("text");

        drop_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
    }

    @Test
    public void alterDataTypeColumn() throws Exception {

        create_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");

        Map<String, String> data = mapData();

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH + "add/column")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");
        Assertions.assertThat(NAME_COLUMN_NAME).isEqualTo("name");
        Assertions.assertThat(DATA_TYPE_TEXT).isEqualTo("text");

        Map<String, String> data2 = new HashMap<>();
        data2.put(NAME_TABLE, NAME_TABLE_CONTATO);
        data2.put(NAME_COLUMN, NAME_COLUMN_NAME);
        data2.put(DATA_TYPE, DATA_TYPE_VARCHAR);

        this.mockMvc.perform(MockMvcRequestBuilders.put(PATH + "alter/data/type/column")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data2)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");
        Assertions.assertThat(NAME_COLUMN_NAME).isEqualTo("name");
        Assertions.assertThat(DATA_TYPE_VARCHAR).isEqualTo("VARCHAR(45)");

        drop_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
    }

    @Test
    public void addConstraintNotNullConlumn() throws Exception {

        create_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");

        Map<String, String> data = mapData();

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH + "add/column")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");
        Assertions.assertThat(NAME_COLUMN_NAME).isEqualTo("name");
        Assertions.assertThat(DATA_TYPE_TEXT).isEqualTo("text");

        Map<String, String> data2 = mapData2();

        this.mockMvc.perform(MockMvcRequestBuilders.put(PATH + "add/not/null")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data2)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");
        Assertions.assertThat(NAME_COLUMN_NAME).isEqualTo("name");

        drop_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
    }

    @Test
    public void dropConstraintNotNullConlumn() throws Exception {

        create_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");

        Map<String, String> data = mapData();

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH + "add/column")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");
        Assertions.assertThat(NAME_COLUMN_NAME).isEqualTo("name");
        Assertions.assertThat(DATA_TYPE_TEXT).isEqualTo("text");

        Map<String, String> data2 = mapData2();

        this.mockMvc.perform(MockMvcRequestBuilders.put(PATH + "add/not/null")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data2)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");
        Assertions.assertThat(NAME_COLUMN_NAME).isEqualTo("name");

        Map<String, String> data3 = new HashMap<>();
        data3.put(NAME_TABLE, NAME_TABLE_CONTATO);
        data3.put(NAME_COLUMN, NAME_COLUMN_NAME);

        this.mockMvc.perform(MockMvcRequestBuilders.put(PATH + "drop/not/null")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data3)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");
        Assertions.assertThat(NAME_COLUMN_NAME).isEqualTo("name");

        drop_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
    }

    @Test
    public void dropColumnOfTheTable() throws Exception {

        create_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");

        Map<String, String> data = mapData();

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH + "add/column")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");
        Assertions.assertThat(NAME_COLUMN_NAME).isEqualTo("name");
        Assertions.assertThat(DATA_TYPE_TEXT).isEqualTo("text");

        Map<String, String> data2 = mapData2();

        this.mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "drop/column")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data2)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");
        Assertions.assertThat(NAME_COLUMN_NAME).isEqualTo("name");

        drop_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
    }

    @Test
    public void dropTable() throws Exception {

        create_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");

        Map<String, String> data = new HashMap<>();
        data.put("nameTable", NAME_TABLE_CONTATO);

        this.mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "drop/table")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");
    }

    private void create_table(NameTableReq nameTableReq) throws SQLException {
        queryService.createTable(nameTableReq);
    }

    private void drop_table(NameTableReq nameTableReq) throws SQLException {
        queryService.dropTable(nameTableReq);
    }

    private Map<String, String> mapData() {
        Map<String, String> data = new HashMap<>();
        data.put(NAME_TABLE, NAME_TABLE_CONTATO);
        data.put(NAME_COLUMN, NAME_COLUMN_NAME);
        data.put(DATA_TYPE, DATA_TYPE_TEXT);
        return data;
    }

    private Map<String, String> mapData2() {
        Map<String, String> data2 = new HashMap<>();
        data2.put(NAME_TABLE, NAME_TABLE_CONTATO);
        data2.put(NAME_COLUMN, NAME_COLUMN_NAME);
        return data2;
    }
}