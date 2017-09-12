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
import setup.ServerWrapper;
import utils.readers.EnvironmentVariableReader;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.mock;

public class AbstractAcceptanceTest extends TestState implements WithCustomResultListeners {

    TestWiringImpl wiring = new TestWiringImpl();

    private ServerWrapper server = new ServerWrapper(wiring);
    private EnvironmentVariableReader environmentVariableReader = mock(EnvironmentVariableReader.class);

    @Before
    public void setUp() throws Exception {
        givenEnvironmentIsAcceptanceTest();
    }

    @After
    public void tearDown() throws Exception {
        capturedInputAndOutputs.add("Sequence Diagram", generateSequenceDiagram());
        server.stopServer();
    }

    @Override
    public Iterable<SpecResultListener> getResultListeners() throws Exception {
        return singletonList(new HtmlResultRenderer()
                .withCustomHeaderContent(SequenceDiagramGenerator.getHeaderContentForModalWindows())
                .withCustomRenderer(SvgWrapper.class, new DontHighlightRenderer<>()));
    }

    public void givenTheServerIsRunning() {
        server.startServer();
    }

    private SvgWrapper generateSequenceDiagram() {
        return new SequenceDiagramGenerator().generateSequenceDiagram(new ByNamingConventionMessageProducer().messages(capturedInputAndOutputs));
    }

    private void givenEnvironmentIsAcceptanceTest() {
        BDDMockito.when(environmentVariableReader.getEnvironment()).thenReturn("acceptancetest");
        BDDMockito.when(environmentVariableReader.getAppRole()).thenReturn("READ");

//        BDDMockito.when(wiring.environmentVariableReader()).thenReturn(environmentVariableReader);
    }
}
