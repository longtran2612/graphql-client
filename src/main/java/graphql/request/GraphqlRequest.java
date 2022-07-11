package graphql.request;


import net.lvs.ticket.sdk.mount.graphql.request.param.RequestParameter;
import net.lvs.ticket.sdk.mount.graphql.request.result.ResultAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 10:13 AM 10-Jun-22
 * Long Tran
 */

public abstract class GraphqlRequest {

    protected String requestName;

    protected RequestParameter requestParameter;

    protected List<ResultAttributes> resultAttributes = new ArrayList<>();

    protected GraphqlRequest(String requestName){
        this.requestName = requestName;
    }


    public void addParameter(String key, Object val){
        getRequestParameter().addParameter(key, val);
    }

    public RequestParameter getRequestParameter(){
        if(this.requestParameter==null){
            this.requestParameter = RequestParameter.build();
        }
        return this.requestParameter;
    }

    public void addResultAttributes(String... resultAttr){
        if(resultAttr!=null&&resultAttr.length>0){
            for(String str : resultAttr){
                ResultAttributes ra = new ResultAttributes(str);
                resultAttributes.add(ra);
            }

        }
    }

    public GraphqlRequest addResultAttributes(ResultAttributes... resultAttr){
        if(resultAttr!=null&&resultAttr.length>0){
            Collections.addAll(resultAttributes, resultAttr);

        }
        return this;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    @Override
    public String toString() {

        StringBuilder requestBuffer = new StringBuilder(requestName);

        String paramStr = getRequestParameter().toString();

        StringBuilder resultAttrBuffer = new StringBuilder("");
        boolean first = true;
        for(ResultAttributes ra :resultAttributes) {
            if(first) {
                first=false;
            }else{
                resultAttrBuffer.append(" ");
            }
            resultAttrBuffer.append(ra.toString());
        }

        String resultAttrStr = resultAttrBuffer.toString();

        requestBuffer.append(paramStr);

        if(!resultAttrStr.isEmpty()){
            requestBuffer.append("{");
            requestBuffer.append(resultAttrStr);
            requestBuffer.append("}");
        }
        return requestBuffer.toString();
    }
}
