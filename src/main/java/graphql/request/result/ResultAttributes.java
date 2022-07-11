package graphql.request.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 10:13 AM 10-Jun-22
 * Long Tran
 */

public class ResultAttributes {

    private final String name;

    public List<ResultAttributes> resultAttributes = new ArrayList<>();

    public ResultAttributes(String name){
        this.name = name;
    }

    public ResultAttributes addResultAttributes(String... resultAttr){
        if(resultAttr!=null&&resultAttr.length>0){
            for(String str : resultAttr){
                ResultAttributes ra = new ResultAttributes(str);
                resultAttributes.add(ra);
            }

        }
        return this;
    }

    public ResultAttributes addResultAttributes(ResultAttributes... resultAttr){
        if(resultAttr!=null&&resultAttr.length>0){
            Collections.addAll(resultAttributes, resultAttr);

        }
        return this;
    }
    @Override
    public String toString() {
        if(resultAttributes.size()==0){
            return name;
        }
        StringBuilder str = new StringBuilder(name + "{");
        for(ResultAttributes ra : resultAttributes){
            str = new StringBuilder(str + " " + ra.toString());
        }
        str.append(" }");
        return str.toString();
    }
}
