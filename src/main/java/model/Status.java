package model;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.EnvironmentVariableReader;

import java.util.List;
import java.util.stream.Stream;

public class Status {
    private List<Probe> probes;

    public Status(List<Probe> probes) {
        this.probes = probes;
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
                .put("Environment", EnvironmentVariableReader.getEnvironment());
    }

}
