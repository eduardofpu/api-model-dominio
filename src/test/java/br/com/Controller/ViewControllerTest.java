package br.com.Controller;

import br.com.AbstractTest;
import br.com.table.NameTableReq;
import br.com.table.column.TableColumnDataType;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.SQLException;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ViewControllerTest extends AbstractTest{

    private final String PATH_TABLE = "/tables";
    private final String PATH_COLUMN = "/columns";
    private final String PATH_BAYTABLE = "/";


    private NameTableReq nameTableReq = new NameTableReq("contato");
    private NameTableReq nameTableReq2 = new NameTableReq("cidade");

    private TableColumnDataType column = new TableColumnDataType(nameTableReq.getNameTable(), "name", "text");
    private TableColumnDataType column2 = new TableColumnDataType(nameTableReq2.getNameTable(), "name", "text");

    @Test
    public void findAllTable() throws Exception {
       createTable();

        this.mockMvc.perform(MockMvcRequestBuilders.get(PATH_TABLE)
                .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("content", Matchers.notNullValue()))
                .andExpect(jsonPath("totalElements", Matchers.is(2)))
                .andExpect(jsonPath("totalPages", Matchers.is(1)))
                .andExpect(jsonPath("numberOfElements", Matchers.is(2)))
                .andExpect(jsonPath("size", Matchers.is(20)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();
      drope();
    }

    @Test
    public void findAllColumn() throws Exception {
        createTable();
        createColumn();

        this.mockMvc.perform(MockMvcRequestBuilders.get(PATH_COLUMN)
                .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("content", Matchers.notNullValue()))
                .andExpect(jsonPath("totalElements", Matchers.is(2)))
                .andExpect(jsonPath("totalPages", Matchers.is(1)))
                .andExpect(jsonPath("numberOfElements", Matchers.is(2)))
                .andExpect(jsonPath("size", Matchers.is(20)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();
        drope();
    }

    @Test
    public void findBayTable() throws Exception {
        createTable();
        createColumn();

        this.mockMvc.perform(MockMvcRequestBuilders.get(PATH_BAYTABLE + nameTableReq.getNameTable())
                .param("nameTable", nameTableReq.getNameTable())
                .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(
                        documentationResultHandler.document(
                                requestParameters(
                                        parameterWithName("nameTable").description(nameTableReq.getNameTable())
                                )
                        )
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        drope();
    }

    private void createTable() throws SQLException {
        queryService.createTable(nameTableReq);
        queryService.createTable(nameTableReq2);
    }

    private void createColumn() throws SQLException {
        queryService.createColumn(column);
        queryService.createColumn(column2);
    }


    private void drope() throws SQLException {
        queryService.dropTable(nameTableReq);
        queryService.dropTable(nameTableReq2);
    }
}