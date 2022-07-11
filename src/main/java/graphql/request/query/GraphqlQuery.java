package graphql.request.query;


import net.lvs.ticket.sdk.mount.graphql.request.GraphqlRequest;

/**
 * 10:13 AM 10-Jun-22
 * Long Tran
 */
public abstract class GraphqlQuery extends GraphqlRequest {

    protected GraphqlQuery(String requestName) {
        super(requestName);
    }

    @Override
    public String toString() {
        String superStr = super.toString();
        return "{\"query\":\"{"+superStr+"}\"}";
    }

}
