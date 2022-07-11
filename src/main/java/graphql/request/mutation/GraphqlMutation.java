package graphql.request.mutation;


import net.lvs.ticket.sdk.mount.graphql.request.GraphqlRequest;

/**
 * 10:13 AM 10-Jun-22
 * Long Tran
 */
public abstract class GraphqlMutation extends GraphqlRequest {


    protected GraphqlMutation(String requestName) {
        super(requestName);
    }

    @Override
    public String toString() {
        String superStr = super.toString();
        return "{\"query\":\"mutation{"+superStr+"}\"}";
    }
    
}
