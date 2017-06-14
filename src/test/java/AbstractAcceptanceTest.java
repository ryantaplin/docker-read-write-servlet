import com.googlecode.yatspec.junit.SpecResultListener;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.junit.WithCustomResultListeners;
import com.googlecode.yatspec.plugin.sequencediagram.ByNamingConventionMessageProducer;
import com.googlecode.yatspec.plugin.sequencediagram.SequenceDiagramGenerator;
import com.googlecode.yatspec.plugin.sequencediagram.SvgWrapper;
import com.googlecode.yatspec.rendering.html.DontHighlightRenderer;
import com.googlecode.yatspec.rendering.html.HtmlResultRenderer;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import server.BasicServer;
import server.handlers.WriteHandlerBuilder;

import static java.util.Collections.singletonList;
import static utils.Settings.getServerSettings;

@RunWith(SpecRunner.class)
public class AbstractAcceptanceTest extends TestState implements WithCustomResultListeners {

    private BasicServer server;

    @Before
    public void setUp() throws Exception {
        server = new BasicServer(getServerSettings());
        server.withContext(new WriteHandlerBuilder().build());
        server.start();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
        capturedInputAndOutputs.add("Sequence Diagram", generateSequenceDiagram());
    }

    private SvgWrapper generateSequenceDiagram() {
        return new SequenceDiagramGenerator().generateSequenceDiagram(new ByNamingConventionMessageProducer().messages(capturedInputAndOutputs));
    }

    @Override
    public Iterable<SpecResultListener> getResultListeners() throws Exception {
        return singletonList(new HtmlResultRenderer()
                .withCustomHeaderContent(SequenceDiagramGenerator.getHeaderContentForModalWindows())
                .withCustomRenderer(SvgWrapper.class, new DontHighlightRenderer<>()));
    }
}
