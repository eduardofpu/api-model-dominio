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
    private final String nameTable = "contato";
    NameTableReq nameTableReq = new NameTableReq(nameTable);

    @Test
    public void createTable() throws Exception {


        Map<String, String> data = new HashMap<>();
        data.put("nameTable", nameTable);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH + "create/table")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();


        drop_table(nameTableReq);
    }

    @Test
    public void createColumn() throws Exception {
        create_table(nameTableReq);


        Map<String, String> data = new HashMap<>();
        data.put("nameTable", nameTable);
        data.put("nameColumn", "name");
        data.put("dataType", "text");

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH + "add/column")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        drop_table(nameTableReq);

    }

    @Test
    public void addConstraintNotNullConlumn() throws Exception {
        create_table(nameTableReq);


        Map<String, String> data = new HashMap<>();
        data.put("nameTable", nameTable);
        data.put("nameColumn", "name");
        data.put("dataType", "text");

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH + "add/column")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Map<String, String> data2 = new HashMap<>();
        data2.put("nameTable", nameTable);
        data2.put("nameColumn", "name");

        this.mockMvc.perform(MockMvcRequestBuilders.put(PATH + "add/not/null")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data2)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        drop_table(nameTableReq);

    }

    @Test
    public void dropTable() throws Exception {
        create_table(nameTableReq);

        Map<String, String> data = new HashMap<>();
        data.put("nameTable", nameTable);

        Assertions.assertThat(nameTable).isNotNull();

        this.mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "drop/table")
                .contentType(APPLICATION_JSON)
                .content(JSONObject.toJSONString(data)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private void create_table(NameTableReq nameTableReq) throws SQLException {
        queryService.createTable(nameTableReq);
    }

    private void drop_table(NameTableReq nameTableReq) throws SQLException {
        queryService.dropTable(nameTableReq);
    }
}