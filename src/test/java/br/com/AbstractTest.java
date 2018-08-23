package br.com;

import br.Application;
import br.com.repository.AddColumnRepository;
import br.com.repository.NameTableRepository;
import br.com.service.QueryService;
import br.com.service.TableService;
import br.com.service.ValidatorService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
public abstract class AbstractTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    protected RestDocumentationResultHandler documentationResultHandler;

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected AddColumnRepository addColumnRepository;

    @Autowired
    protected NameTableRepository nameTableRepository;

    @Autowired
    protected QueryService queryService;

    @Autowired
    protected TableService tableService;

    @Autowired
    protected ValidatorService validatorService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    protected MockMvc mockMvc;

    @Before
    public void setUp() {

        documentationResultHandler = MockMvcRestDocumentation.document("{method-name}",
                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()));
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation))
                .alwaysDo(this.documentationResultHandler)
                .build();
    }
}
