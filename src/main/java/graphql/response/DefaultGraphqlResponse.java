package graphql.response;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 10:13 AM 10-Jun-22
 * Long Tran
 */
public class DefaultGraphqlResponse extends LinkedHashMap implements GraphqlResponse {
    @Override
    public Map getData() {
        return this;
    }
}
