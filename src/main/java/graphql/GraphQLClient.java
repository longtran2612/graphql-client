package graphql;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.lvs.ticket.sdk.mount.graphql.response.DefaultGraphqlResponse;
import net.lvs.ticket.sdk.mount.graphql.response.GraphqlResponse;
import net.lvs.ticket.sdk.mount.graphql.util.HttpClientUtil;
import net.lvs.ticket.sdk.mount.graphql.request.GraphqlRequestType;
import net.lvs.ticket.sdk.mount.graphql.request.mutation.GraphqlMutation;
import net.lvs.ticket.sdk.mount.graphql.request.query.GraphqlQuery;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 10:13 AM 10-Jun-22
 * Long Tran
 */
public class GraphQLClient {

    /**
     * http
     */
    private final HttpClientUtil httpClientUtil = new HttpClientUtil();

    /**
     * graphql server
     */
    private final String graphqlServerUrl;

    /**
     * http
     */
    private Map<String,String> httpHeaders = new HashMap<>();

    /**
     * json mapper
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * GraphqlUtil
     * @param graphqlUrl graphql server url
     */
    private GraphQLClient(String graphqlUrl){
        this.graphqlServerUrl = graphqlUrl;
    }

    /**
     * Graphql
     * @param graphqlUrl  graphql server url
     * @return GraphqlClient
     */
    public static GraphQLClient buildGraphqlClient(String graphqlUrl){
        return new GraphQLClient(graphqlUrl);
    }
////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @param query exec query
     * @param <T> query
     * @return response
     * @throws IOException Exception
     */
    public <T extends GraphqlQuery> GraphqlResponse doQuery(T query) throws IOException {
        return doQuery(query, GraphqlRequestType.POST);
    }

    /**
     * @param query  exec query
     * @param graphqlRequestType request type get or post,but no get now
     * @param <T> query
     * @return response
     * @throws IOException Exception
     */
    public <T extends GraphqlQuery> GraphqlResponse doQuery(T query, GraphqlRequestType graphqlRequestType) throws IOException {
        String json = query.toString();
        String result = doHttpRequest(json,graphqlRequestType);
        if(result==null){
            return null;
        }
        return objectMapper.readValue(result, DefaultGraphqlResponse.class);
    }
/////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param mutation exec mutation
     * @param <T> Mutation
     * @return response
     * @throws IOException Exception
     */
    public <T extends GraphqlMutation> GraphqlResponse doMutation(T mutation) throws IOException {
        return doMutation(mutation,GraphqlRequestType.POST);
    }

    /**
     * @param mutation exec mutation
     * @param graphqlRequestType request type get or post,but no get now
     * @param <T>  Mutation
     * @return response
     * @throws IOException Exception
     */
    public <T extends GraphqlMutation> GraphqlResponse doMutation(T mutation, GraphqlRequestType graphqlRequestType) throws IOException {
        String json = mutation.toString();
        String result = doHttpRequest(json,graphqlRequestType);
        if(result==null){
            return null;
        }

        return objectMapper.readValue(result, DefaultGraphqlResponse.class);
    }

/////////////////////////////////////////////////////////////////////////////////////

    /***
     * @param json json
     * @param type
     * @return result
     * @throws IOException  Exception
     */
    private String doHttpRequest(String json,GraphqlRequestType type) throws IOException {
        String result = null;
        if(type.equals(GraphqlRequestType.POST)){
            result = httpClientUtil.doPostJson(graphqlServerUrl,json,this.httpHeaders);
        }
        return result;
    }

    /**
     * http
     * @param headers http
     */
    public void setHttpHeaders(Map<String,String> headers){
        this.httpHeaders = headers;
    }


}
