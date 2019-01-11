package com.erstens.utility;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *     compile group: 'org.apache.httpcomponents', name: 'httpclient', version: '4.5.3'
 */
public class HttpUtils {
    /**
     * 3秒操时
     */
    public static final int HTTP_TIME_OUT = 3 * 1000 ;

    public static String postReturnBody(String url, String params, String body, Map<String,String> formMap, Map<String,String> headers) {
        HttpResponse response = post(url, params, body, formMap, headers);
        BufferedReader rd = null;
        try {
            rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line ;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            String s = result.toString();
            System.out.println(s);
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "" ;
    }

    public static String get(String url ) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        HttpEntity entity = null;

        try {
            response = httpclient.execute(httpGet);

            entity = response.getEntity();
            return EntityUtils.toString(entity);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "" ;
    }

    public static HttpResponse post(String url, String params, String body, Map<String,String> formMap, Map<String,String> headers) {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);

        // add header
        ADD_HEADER:{
            if (null == headers) {
                break ADD_HEADER ;
            }

            Iterator<Map.Entry<String, String>> it = headers.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> next = it.next();
                String k = next.getKey();
                String v = next.getValue();

                post.addHeader(k,v);
            }
        }

        // add form_map
        FORM_MAP:{
            if (null == formMap) {
                break FORM_MAP ;
            }
            List<NameValuePair> formKvList = new LinkedList<NameValuePair>();

            Iterator<Map.Entry<String, String>> it = formMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> next = it.next();
                String k = next.getKey();
                String v = next.getValue();

                formKvList.add(new BasicNameValuePair(k,v));
                try {
                    post.setEntity(new UrlEncodedFormEntity(formKvList));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        }

        // add form_body
        //todo wang'ao

        // add param
        //todo wang'ao

        HttpResponse response = null ;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response ;
    }
}
