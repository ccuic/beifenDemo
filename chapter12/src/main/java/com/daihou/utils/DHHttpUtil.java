package com.daihou.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DHHttpUtil {
    public static JSONObject get(String url){
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        JSONObject jsonObject = null;
        try {
            httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
            httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String status = response.getStatusLine().toString();
            if(!status.contains("200")){
                System.out.println("请求失败");
                return null;
            }
            HttpEntity httpEntity = response.getEntity();
            jsonObject= JSONObject.parseObject(EntityUtils.toString(httpEntity,"utf-8"));
            EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(httpGet!=null){
                    httpGet.releaseConnection();
                }
                if(httpClient!=null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;

    }

    public static File getFile(String url){
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        JSONObject jsonObject = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File file = null;
        try {
            httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
            httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String status = response.getStatusLine().toString();
            if(!status.contains("200")){
                System.out.println("请求失败");
                return null;
            }
            HttpEntity httpEntity = response.getEntity();
            inputStream = httpEntity.getContent();
            file = new File("D:\\test\\test.mp3");
            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[10240000];
            int readLength = 0;
            while ((readLength=inputStream.read(buffer)) > 0) {
                 byte[] bytes = new byte[readLength];
                 System.arraycopy(buffer, 0, bytes, 0, readLength);
                 outputStream.write(bytes);
            }

            outputStream.flush();
            EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(httpGet!=null){
                    httpGet.releaseConnection();
                }
                if(httpClient!=null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(inputStream!= null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(outputStream != null){
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return file;

    }



    public static JSONObject post(String url, JSONObject params){
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        JSONObject jsonObject = null;
        try {
            httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            StringEntity entity = new StringEntity(params.toString(),"utf-8");
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type","application/json; charset=utf-8");
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String status = response.getStatusLine().toString();
            if(!status.contains("200")){
                System.out.println("请求失败");
                return null;
            }
            System.out.println(response.getParams());
            HttpEntity httpEntity = response.getEntity();
            jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity(),"UTF-8"));
            EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(httpPost!=null){
                    httpPost.releaseConnection();
                }
                if(httpClient!=null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
//实验
public static JSONObject post_with_String(String url, String strParam, Map<String, String> headers){
    CloseableHttpClient httpClient = null;
    HttpPost httpPost = null;
    JSONObject jsonObject = null;
    try {
        httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
        httpPost = new HttpPost(url);
        // 添加http headers
        if (headers != null && headers.size() > 0) {
            for (String key : headers.keySet()) {
                httpPost.addHeader(key, headers.get(key));
            }
        }
        httpPost.setConfig(requestConfig);
        StringEntity entity = new StringEntity(strParam,"utf-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type","application/json; charset=utf-8");
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String status = response.getStatusLine().toString();
        if(!status.contains("200")){
            System.out.println("请求失败");
            return null;
        }
        System.out.println(response.getParams());
        HttpEntity httpEntity = response.getEntity();
        jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity(),"UTF-8"));
        EntityUtils.consume(httpEntity);
    } catch (ClientProtocolException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }finally{
        try {
            if(httpPost!=null){
                httpPost.releaseConnection();
            }
            if(httpClient!=null){
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return jsonObject;
}
    public static JSONObject post_with_Auth(String url, JSONObject params,String authName,String authValue){
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        JSONObject jsonObject = null;
        try {
            httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            StringEntity entity = new StringEntity(params.toString(),"utf-8");
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type","application/json; charset=utf-8");
            httpPost.setHeader(authName,authValue);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String status = response.getStatusLine().toString();
            if(!status.contains("200")){
                System.out.println("请求失败");
                return null;
            }
            System.out.println(response.getParams());
            HttpEntity httpEntity = response.getEntity();
            jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity(),"UTF-8"));
            EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(httpPost!=null){
                    httpPost.releaseConnection();
                }
                if(httpClient!=null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
    public static JSONObject post(String url, Map map){
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        JSONObject jsonObject = null;
        try {
            httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            StringEntity entity = new StringEntity(map.toString(),"utf-8");
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type","application/json; charset=utf-8");
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String status = response.getStatusLine().toString();
            if(!status.contains("200")){
                System.out.println("请求失败");
                return null;
            }
            HttpEntity httpEntity = response.getEntity();
            jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity(),"UTF-8"));
            EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(httpPost!=null){
                    httpPost.releaseConnection();
                }
                if(httpClient!=null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }


    public static JSONObject post(String url, String string){
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        JSONObject jsonObject = null;
        try {
            httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            StringEntity entity = new StringEntity(string,"utf-8");
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type","application/json; charset=utf-8");
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String status = response.getStatusLine().toString();
            if(!status.contains("200")){
                System.out.println("请求失败");
                return null;
            }
            HttpEntity httpEntity = response.getEntity();
            jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity(),"UTF-8"));
            EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(httpPost!=null){
                    httpPost.releaseConnection();
                }
                if(httpClient!=null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    public static JSONObject post(String url, byte[] postContentEncoded, HashMap<String, String> header, int timeout) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        JSONObject jsonObject = null;
        try {
            httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            ByteArrayEntity entity = new ByteArrayEntity(postContentEncoded);
            httpPost.setEntity(entity);

            for(Map.Entry<String, String> entry : header.entrySet()){
                httpPost.setHeader(entry.getKey(),entry.getValue());
            }

            CloseableHttpResponse response = httpClient.execute(httpPost);
            String status = response.getStatusLine().toString();
            if(!status.contains("200")){
                System.out.println("请求失败");
                return null;
            }
            HttpEntity httpEntity = response.getEntity();
            jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity(),"UTF-8"));
            EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(httpPost!=null){
                    httpPost.releaseConnection();
                }
                if(httpClient!=null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    public static JSONObject postWithFarm(String gwCallbackRRdUrl, String callback) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        JSONObject jsonObject = null;
        try {
            httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
            httpPost = new HttpPost(gwCallbackRRdUrl);
            httpPost.setConfig(requestConfig);
            StringEntity entity = new StringEntity(callback,"utf-8");
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String status = response.getStatusLine().toString();
            if(!status.contains("200")){
                System.out.println("请求失败");
                return null;
            }
            HttpEntity httpEntity = response.getEntity();
            jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity(),"UTF-8"));
            EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(httpPost!=null){
                    httpPost.releaseConnection();
                }
                if(httpClient!=null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
}
