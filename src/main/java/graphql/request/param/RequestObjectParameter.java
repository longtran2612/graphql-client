package graphql.request.param;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import net.lvs.ticket.sdk.mount.graphql.request.param.jsonserializer.EnumSerializer;

/**
 * 10:13 AM 10-Jun-22
 * Long Tran
 */
public class RequestObjectParameter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Object data;

    public RequestObjectParameter(Object data) {

        objectMapper.registerModule(new Jdk8Module());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Enum.class,new EnumSerializer());
        objectMapper.registerModule(simpleModule);

        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String json = dataToJson();
        json = json.replaceAll("\\\"","\\\\\"");
        return json;
    }

    private String dataToJson(){
        String str = "null";
        try {
            str = objectMapper.writeValueAsString(this.getData());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;
    }

}
