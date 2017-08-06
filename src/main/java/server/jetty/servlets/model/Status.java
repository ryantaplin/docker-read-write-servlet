package server.jetty.servlets.model;

import org.json.JSONArray;
import org.json.JSONObject;
import server.wiring.Wiring;
import utils.readers.EnvironmentVariableReader;

import java.util.List;
import java.util.stream.Stream;

public class Status {

    private List<Probe> probes;
    private Wiring wiring;

    public Status(List<Probe> probes, Wiring wiring) {
        this.probes = probes;
        this.wiring = wiring;
    }

    private String status() {
        Stream<Probe> failedProbes = probes.stream().filter(probe -> probe.status.equals("FAIL"));
        return failedProbes.count() > 0 ? "FAIL" : "OK";
    }

    public JSONObject json() {
        JSONArray jsonArray = new JSONArray();
        probes.forEach(probe -> jsonArray.put(probe.json()));

        return new JSONObject()
                .put("Status", status())
                .put("probes", jsonArray)
                .put("Environment", wiring.environmentVariableReader().getEnvironment());
    }

}
