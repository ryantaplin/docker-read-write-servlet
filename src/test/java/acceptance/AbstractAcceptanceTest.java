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
import server.wiring.TestWiring;
import server.wiring.WiringImpl;
import setup.ServerWrapper;

import static java.util.Collections.singletonList;

public class AbstractAcceptanceTest extends TestState implements WithCustomResultListeners {

    private ServerWrapper server;

    public TestWiring testWiring = new TestWiring();
    public WiringImpl wiring;

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
        wiring = new WiringImpl(
                testWiring.environment,
                testWiring.appRole,
                testWiring.serverProperties,
                testWiring.databaseProperties,
                testWiring.database,
                testWiring.staffRepository);

        server = new ServerWrapper(wiring);
        server.startServer();
    }

    private SvgWrapper generateSequenceDiagram() {
        return new SequenceDiagramGenerator().generateSequenceDiagram(new ByNamingConventionMessageProducer().messages(capturedInputAndOutputs));
    }
}
