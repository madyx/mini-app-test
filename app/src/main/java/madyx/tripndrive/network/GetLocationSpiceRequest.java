package madyx.tripndrive.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

import org.apache.commons.io.IOUtils;

import java.util.Arrays;

import madyx.tripndrive.WSContants;
import madyx.tripndrive.model.Site;
import madyx.tripndrive.model.SiteOutput;

/**
 * Created by mahmoudreda on 26/11/15.
 */
public class GetLocationSpiceRequest extends GoogleHttpClientSpiceRequest<SiteOutput> {

    private static int REQUEST_TIMEOUT = 30; // in seconds

    private String url;

    public GetLocationSpiceRequest() {
        super(SiteOutput.class);
        url = WSContants.TRIPNDRIVE_API_SITES_ENDPOINT;
    }

    @Override
    public SiteOutput loadDataFromNetwork() throws Exception {

        SiteOutput result = new SiteOutput();

        HttpRequest request = getHttpRequestFactory().buildGetRequest(new GenericUrl(url));
        request.setConnectTimeout(REQUEST_TIMEOUT * 1000);

        HttpResponse response = request.execute();

        ObjectMapper objectMapper = new ObjectMapper();
        String responseStr = IOUtils.toString(response.getContent());
        result.setSites(Arrays.asList(objectMapper.readValue(responseStr, Site[].class)));

        return result;
    }
}
