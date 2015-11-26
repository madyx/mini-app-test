package madyx.tripndrive.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

import org.apache.commons.io.IOUtils;

import java.util.Arrays;
import java.util.Calendar;

import madyx.tripndrive.WSContants;
import madyx.tripndrive.model.Car;
import madyx.tripndrive.model.CarsOutput;
import madyx.tripndrive.model.Site;
import madyx.tripndrive.util.CalendarUtils;

/**
 * Created by mahmoudreda on 26/11/15.
 */
public class GetCarsSpiceRequest extends GoogleHttpClientSpiceRequest<CarsOutput> {

    // FIXME find time to clean this
    private static final String URL_ARGS = "?endDate=%s&endTime=10:30&max=100&offset=0&siteCode=%s&startDate=%s&startTime=10:00";

    private static final String DATE_ARG_PATTERN = "yyyy-MM-dd";

    private static int REQUEST_TIMEOUT = 30; // in seconds

    private String url;

    public GetCarsSpiceRequest(Site location, Calendar start, Calendar end) {
        super(CarsOutput.class);

        String locationArg = location.getCode();
        String startArg = CalendarUtils.calendarToString(start, DATE_ARG_PATTERN);
        String endArg = CalendarUtils.calendarToString(start, DATE_ARG_PATTERN);

        url = String.format(WSContants.TRIPNDRIVE_API_CARS_ENDPOINT + URL_ARGS, endArg, locationArg, startArg);
    }

    @Override
    public CarsOutput loadDataFromNetwork() throws Exception {

        CarsOutput result = new CarsOutput();

        HttpRequest request = getHttpRequestFactory().buildGetRequest(new GenericUrl(url));
        request.setConnectTimeout(REQUEST_TIMEOUT * 1000);

        HttpResponse response = request.execute();

        ObjectMapper objectMapper = new ObjectMapper();
        String responseStr = IOUtils.toString(response.getContent());
        result.setCars(Arrays.asList(objectMapper.readValue(responseStr, Car[].class)));

        return result;
    }
}
