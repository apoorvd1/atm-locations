package com.adviqo.atm.locations.error;

import com.adviqo.atm.locations.exception.AtmLocationRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * This class throws the Generic RestTemplate Response Handler.
 */
@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    /**
     * Indicate whether the given response has any errors.
     * <p>Implementations will typically inspect the
     * {@link ClientHttpResponse#getStatusCode() HttpStatus} of the response.
     *
     * @param httpResponse the response to inspect
     * @return {@code true} if the response indicates an error; {@code false} otherwise
     * @throws IOException in case of I/O errors
     */
    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    /**
     * Handle the error in the given response.
     * <p>This method is only called when {@link #hasError(ClientHttpResponse)}
     * has returned {@code true}.
     *
     * @param httpResponse the response with the error
     * @throws IOException in case of I/O errors
     */
    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            //handle SERVER Error
            throw new AtmLocationRuntimeException("Throws SERVER Error");
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            //handle CLIENT Error
            throw new AtmLocationRuntimeException("Throws CLIENT Error");
        } else if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new AtmLocationRuntimeException("NOT Found The URL");

        }
    }

}
