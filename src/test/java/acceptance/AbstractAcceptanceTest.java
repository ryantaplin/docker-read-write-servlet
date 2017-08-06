package acceptance;

import com.googlecode.yatspec.junit.SpecResultListener;
import com.googlecode.yatspec.junit.WithCustomResultListeners;
import com.googlecode.yatspec.plugin.sequencediagram.ByNamingConventionMessageProducer;
import com.googlecode.yatspec.plugin.sequencediagram.SequenceDiagramGenerator;
import com.googlecode.yatspec.plugin.sequencediagram.SvgWrapper;
import com.googlecode.yatspec.rendering.html.DontHighlightRenderer;
import com.googlecode.yatspec.rendering.html.HtmlResultRenderer;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import org.junit.After;
import org.junit.Before;
import org.mockito.BDDMockito;
import server.wiring.TestWiringImpl;
import setup.ServerSetup;
import utils.readers.EnvironmentVariableReader;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.mock;
import static setup.ServerSetup.startServer;
import static setup.ServerSetup.stopServer;

public class AbstractAcceptanceTest extends TestState implements WithCustomResultListeners {

    private ServerSetup server = new ServerSetup();
    private EnvironmentVariableReader environmentVariableReader = mock(EnvironmentVariableReader.class);

    public TestWiringImpl wiring = new TestWiringImpl();

    @Before
    public void setUp() throws Exception {
        givenEnvironmentIsAcceptanceTest();
    }

    @After
    public void tearDown() throws Exception {
        capturedInputAndOutputs.add("Sequence Diagram", generateSequenceDiagram());
        stopServer();
    }

    @Override
    public Iterable<SpecResultListener> getResultListeners() throws Exception {
        return singletonList(new HtmlResultRenderer()
                .withCustomHeaderContent(SequenceDiagramGenerator.getHeaderContentForModalWindows())
                .withCustomRenderer(SvgWrapper.class, new DontHighlightRenderer<>()));
    }

    public void whenApplicationIsStarted() {
        startServer(wiring);
    }

    private SvgWrapper generateSequenceDiagram() {
        return new SequenceDiagramGenerator().generateSequenceDiagram(new ByNamingConventionMessageProducer().messages(capturedInputAndOutputs));
    }

    private void givenEnvironmentIsAcceptanceTest() {
        BDDMockito.given(environmentVariableReader.getEnvironment()).willReturn("acceptancetest");
        BDDMockito.given(environmentVariableReader.getAppRole()).willReturn("read");
        wiring.setEnvironmentVariableReader(environmentVariableReader);
    }
}
