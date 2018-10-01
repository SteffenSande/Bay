import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import entities.Feedback;
import org.eclipse.persistence.internal.oxm.ByteArraySource;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;

import rest.DebugMapper;
import rest.RestService;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class Tests extends JerseyTest {

    @Override
    protected Application configure() {

        return new ResourceConfig(RestService.class)
                .registerClasses(DebugMapper.class);
    }

    private void testFeedback(String mediaType) {
        Response response = target("/auctions/testFeedback").request(mediaType).get();
        String result = response.readEntity(String.class);
        try {
            Feedback f = convertToObject(result, Feedback.class, mediaType);
            assertEquals("Content", f.getContent());
        } catch (JAXBException e) {
            fail("Not valid " + mediaType);
        }
    }

    @Test
    public void feedBackXML() {
        testFeedback(MediaType.APPLICATION_XML);
    }

    @Test
    public void feedBackJSON() throws JAXBException {
        Response response = target("/auctions/testFeedback").request(MediaType.APPLICATION_JSON).get();
        String result = response.readEntity(String.class);
        assertEquals('{', result.charAt(0)); // Then its json and not xml
    }

    private <T> T convertToObject(String s, Class<T> type, String mediaType) throws JAXBException {
        Map<String, Object> properties = new HashMap<>(1);
        properties.put("eclipselink.media-type", mediaType);
        JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] {type}, properties);
        Unmarshaller u = jaxbContext.createUnmarshaller();
        return u.unmarshal(new StreamSource(new StringReader(s)), type).getValue();
    }
}
