package graphql.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 10:13 AM 10-Jun-22
 * Long Tran
 */
public class HttpClientUtil {

    public String doGet(String url, Map<String, String> param) throws IOException, URISyntaxException {
        //Httpclient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // http GET
            HttpGet httpGet = new HttpGet(uri);
            response = httpclient.execute(httpGet);
            // status = 200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpclient.close();
        }
        return resultString;
    }

    public String doGet(String url) throws IOException, URISyntaxException {
        return doGet(url, null);
    }

    public String doPost(String url, Map<String, String> param,Map<String,String> headers) throws IOException {
        // Httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString;
        try {
            //  Post
            HttpPost httpPost = new HttpPost(url);
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,"utf-8");
                httpPost.setEntity(entity);
            }
            if (headers != null) {
                for (String key : headers.keySet()) {
                    httpPost.addHeader(key,headers.get(key));
                }
            }
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        }finally {
            assert response != null;
            response.close();
        }

        return resultString;
    }

    public String doPost(String url,Map<String, String> param) throws IOException {
        return doPost(url, param,null);
    }

    public String doPost(String url) throws IOException {
        return doPost(url, null,null);
    }

    public String doPostJson(String url,String json) throws IOException {
        return doPostJson(url,json,null);
    }

    public String doPostJson(String url, String json,Map<String,String> headers) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString;
        try {
            HttpPost httpPost = new HttpPost(url);
            if (headers != null) {
                for (String key : headers.keySet()) {
                    httpPost.addHeader(key,headers.get(key));
                }
            }
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        }finally {
            assert response != null;
            response.close();
        }
        return resultString;
    }
}
