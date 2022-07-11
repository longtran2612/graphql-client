package graphql.request.param;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 10:13 AM 10-Jun-22
 * Long Tran
 */
public class RequestParameter extends HashMap<String,Object> {

    private RequestParameter(){

    }

    public RequestParameter addParameter(String key, Object obj){
        put(key,obj);
        return this;
    }

    public void addObjectParameter(String key, Object obj){
        if(obj instanceof RequestObjectParameter){
            put(key,obj);
        }else{
            put(key,new RequestObjectParameter(obj));
        }
    }

    public static RequestParameter buildByMap(Map map){
        RequestParameter requestParameter = build();
        requestParameter.putAll(map);
        return requestParameter;
    }

    public static RequestParameter build(){
        return new RequestParameter();
    }

    @Override
    public String toString() {


        Set<String> keys = keySet();

        if(keys.size()==0){
            return "";
        }

        StringBuilder stringVal = new StringBuilder("(");

        char connect = ',';

        for(String key:keys){
            stringVal = new StringBuilder(stringVal + key + ":" + packVal(get(key)) + connect);
        }

        char last = stringVal.charAt(stringVal.length()-1);

        if(connect == last){
            stringVal = new StringBuilder(stringVal.substring(0, stringVal.length() - 1));
        }

        stringVal.append(")");

        return stringVal.toString();
    }

    private String packVal(Object val){
        if(val==null){
            return "null";
        }
        if(val instanceof Integer
                || val instanceof Boolean
                || val instanceof Float
                || val instanceof Double){
            return String.valueOf(val);
        }

        if(val instanceof Enum){
            Enum enumVal = (Enum) val;
            return enumVal.name();
        }

        if(val instanceof RequestObjectParameter){
            return val.toString();
        }

        return "\\\""+ val +"\\\"";
    }
}
