package clients;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import util.Formatters;

public class TestApiClient implements Formatters {
    private final Logger log = Logger.getLogger(TestApiClient.class);
    final String baseUri;

    public TestApiClient(String baseUri) {
        this.baseUri = baseUri;
    }

    public Response doPost(String name, PathIdentifier path, RequestSpecification spec) {
        log.info(sf("Handling Post Request: '%s'", name));
        spec = spec.baseUri(baseUri).basePath(path.toString());
        Response r = spec.post();
        log.info(r.asPrettyString());
        return r;
    }

    public void doGet() {
        log.warn("To be implemented");
    }

    public void doPut() {
        log.warn("To be implemented");
    }

    public void doDelete() {
        log.warn("To be implemented");
    }
}
