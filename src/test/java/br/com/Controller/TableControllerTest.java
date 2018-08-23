package br.com.Controller;

import br.com.AbstractTest;
import br.com.query.CreateQuery;
import br.com.table.NameTableReq;
import br.com.table.ObjectParameter;
import br.com.table.UpdateTableReq;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class TableControllerTest extends AbstractTest {

    private final String PATH = "/";

    private final String NAME_TABLE = "nameTable";
    private final String NAME_COLUMN = "nameColumn";
    private final String DATA_TYPE = "dataType";
    private final String ATTRIBUTE = "attribute";
    private final String PARAMETERS = "parameters";
    private final String PARAMETER = "parameter";
    private final String ID = "id";

    private final String NAME_TABLE_CONTATO = "contato";
    private final String NAME_TABLE_CIDADE = "cidade";
    private final String NAME_COLUMN_NAME = "name";
    private final String NAME_COLUMN_AGE = "idade";
    private final String NAME_COLUMN_SEX = "sexo";
    private final String DATA_TYPE_TEXT = "text";

    private final List<String> PARAMETERS_LIST = Arrays.asList("Goku","27","M");
    private final List<String> PARAMETERS_LIST2 = Arrays.asList("Titi","21","F");
    private final List<String> PARAMETERS_LIST_EDIT = Arrays.asList("Vegeta","30","M");

    NameTableReq nameTableReq = new NameTableReq(NAME_TABLE_CONTATO);
    NameTableReq nameTableReq2 = new NameTableReq(NAME_TABLE_CIDADE);

    @Test
    public void findAll() throws Exception {

        create_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
        addColumnNameAgeSex();
        insert();
        insert2();

        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");

        this.mockMvc.perform(MockMvcRequestBuilders.get(PATH + "get/"+NAME_TABLE_CONTATO+"")
                .param(NAME_TABLE, NAME_TABLE_CONTATO)
                .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath(NAME_TABLE, Matchers.is(NAME_TABLE_CONTATO)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        drop_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
    }

    @Test
    public void findById() throws Exception {


        create_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
        addColumnNameAgeSex();
        insert();
        insert2();

        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");

        this.mockMvc.perform(MockMvcRequestBuilders.get(PATH + "get/"+NAME_TABLE_CONTATO+"/2")
                .param(NAME_TABLE, NAME_TABLE_CONTATO)
                .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath(NAME_TABLE, Matchers.is(NAME_TABLE_CONTATO)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        drop_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
    }

    @Test
    public void insertInto() throws Exception {

        create_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
        addColumnNameAgeSex();
        insert();
        drop_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
    }

    @Test
    public void updateAttributeRg() throws Exception{
        create_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");

        addColumnNameAgeSex();
        insert();

        Object cod = CreateQuery.selectId(nameTableReq.getNameTable(), NAME_COLUMN_NAME,"Goku");

        String id = String.valueOf(cod);
        Long idL = Long.parseLong(id);
        String parameter = "Kuririn";

        Map<String, String> data = new HashMap<>();
        data.put(NAME_TABLE, NAME_TABLE_CONTATO);
        data.put(ATTRIBUTE, NAME_COLUMN_NAME);
        data.put(ID, id);
        data.put(PARAMETER, parameter);

        String jsonResponse =  this.mockMvc.perform(MockMvcRequestBuilders.put(PATH + "update")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(jsonPath(NAME_TABLE, Matchers.is(NAME_TABLE_CONTATO)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");
        Assertions.assertThat(NAME_COLUMN_NAME).isEqualTo("name");
        Assertions.assertThat(id).isNotNull();
        Assertions.assertThat(parameter).isEqualTo("Kuririn");

        ObjectMapper mapper = new ObjectMapper();
        UpdateTableReq nameTable = mapper.readValue(jsonResponse, UpdateTableReq.class);

        Assertions.assertThat(nameTable.getNameTable()).isEqualTo(NAME_TABLE_CONTATO);
        Assertions.assertThat(nameTable.getAttribute()).isEqualTo(NAME_COLUMN_NAME);
        Assertions.assertThat(nameTable.getId()).isEqualTo(idL);
        Assertions.assertThat(nameTable.getParameter()).isEqualTo("Kuririn");

        drop_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
    }

    @Test
    public void updateRg() throws Exception{
        create_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");

        addColumnNameAgeSex();
        insert();

        String jsonContent = IOUtils.toString(getClass()
                .getClassLoader()
                .getResourceAsStream("json/updateAll.json"));

        Object cod = CreateQuery.selectId(nameTableReq.getNameTable(), NAME_COLUMN_NAME,"Goku");
        String id = String.valueOf(cod);

        String jsonResponse =  this.mockMvc.perform(MockMvcRequestBuilders.put(PATH + "update/"+id)
                .param("id", id)
                .contentType(APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(jsonPath(NAME_TABLE, Matchers.is(NAME_TABLE_CONTATO)))
                .andExpect(jsonPath(PARAMETERS, Matchers.is(PARAMETERS_LIST_EDIT)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertThat(id).isNotNull();

        ObjectMapper mapper = new ObjectMapper();
        ObjectParameter nameTable = mapper.readValue(jsonResponse, ObjectParameter.class);

        Assertions.assertThat(nameTable.getNameTable()).isEqualTo(NAME_TABLE_CONTATO);
        Assertions.assertThat(nameTable.getParameters()).isEqualTo(PARAMETERS_LIST_EDIT);

        drop_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
    }

    @Test
    public void deleteRg() throws Exception {

        create_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
        addColumnNameAgeSex();
        insert();

        Object cod = CreateQuery.selectId(nameTableReq.getNameTable(), NAME_COLUMN_NAME,"Goku");
        String id = String.valueOf(cod);

        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
        Assertions.assertThat(NAME_COLUMN_NAME).isEqualTo("name");
        Assertions.assertThat(id).isNotNull();

        this.mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "delete/"+NAME_TABLE_CONTATO+"/"+id)
                .param(ID, id)
                .param(NAME_TABLE, NAME_TABLE_CONTATO)
                .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        drop_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
    }

    @Test
    public void deleteRgAll() throws Exception {

        create_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
        addColumnNameAgeSex();
        insert();
        insert2();

        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");

        this.mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "delete/"+NAME_TABLE_CONTATO+"")
                .param(NAME_TABLE, NAME_TABLE_CONTATO)
                .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        drop_table(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
    }

    private void insert() throws Exception {
        String jsonContent = IOUtils.toString(getClass()
                .getClassLoader()
                .getResourceAsStream("json/insert.json"));

        String jsonResponse =  this.mockMvc.perform(MockMvcRequestBuilders.post(PATH + "insert/table")
                .contentType(APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath(NAME_TABLE, Matchers.is(NAME_TABLE_CONTATO)))
                .andExpect(jsonPath(PARAMETERS, Matchers.is(PARAMETERS_LIST)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        ObjectParameter nameTable = mapper.readValue(jsonResponse, ObjectParameter.class);

        Assertions.assertThat(nameTable.getNameTable()).isEqualTo(NAME_TABLE_CONTATO);
        Assertions.assertThat(nameTable.getParameters()).isEqualTo(PARAMETERS_LIST);
    }

    private void insert2() throws Exception {
        String jsonContent = IOUtils.toString(getClass()
                .getClassLoader()
                .getResourceAsStream("json/insert2.json"));

        String jsonResponse =  this.mockMvc.perform(MockMvcRequestBuilders.post(PATH + "insert/table")
                .contentType(APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath(NAME_TABLE, Matchers.is(NAME_TABLE_CONTATO)))
                .andExpect(jsonPath(PARAMETERS, Matchers.is(PARAMETERS_LIST2)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        ObjectParameter nameTable = mapper.readValue(jsonResponse, ObjectParameter.class);

        Assertions.assertThat(nameTable.getNameTable()).isEqualTo(NAME_TABLE_CONTATO);
        Assertions.assertThat(nameTable.getParameters()).isEqualTo(PARAMETERS_LIST2);
    }

    private void addColumnNameAgeSex() throws Exception {
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
        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH + "add/column")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data2)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");
        Assertions.assertThat(NAME_COLUMN_AGE).isEqualTo("idade");
        Assertions.assertThat(DATA_TYPE_TEXT).isEqualTo("text");

        Map<String, String> data3 = mapData3();
        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH + "add/column")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data3)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");
        Assertions.assertThat(NAME_COLUMN_SEX).isEqualTo("sexo");
        Assertions.assertThat(DATA_TYPE_TEXT).isEqualTo("text");
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
        Map<String, String> data = new HashMap<>();
        data.put(NAME_TABLE, NAME_TABLE_CONTATO);
        data.put(NAME_COLUMN, NAME_COLUMN_AGE);
        data.put(DATA_TYPE, DATA_TYPE_TEXT);
        return data;
    }

    private Map<String, String> mapData3() {
        Map<String, String> data = new HashMap<>();
        data.put(NAME_TABLE, NAME_TABLE_CONTATO);
        data.put(NAME_COLUMN, NAME_COLUMN_SEX);
        data.put(DATA_TYPE, DATA_TYPE_TEXT);
        return data;
    }

}