package com.hdc.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	
	public static String getMethod(String url){
		HttpClient http= null;
		HttpGet get = null;
		String returnValue = null;
		try {
			 http= Mock.getHttpClient();
			 get = new HttpGet(url.replaceAll(" ", "%20").replaceAll("\\+", "%2B"));
			HttpResponse reponse =  http.execute(get);
			int status = reponse.getStatusLine().getStatusCode();
			//System.out.println(status);
			HttpEntity httpEntity = reponse.getEntity();
			if(status==200){
				if (httpEntity != null) {
					
					returnValue = EntityUtils.toString(httpEntity,"GBK");
				}
			}else  if(status >= 300 || status < 200){
                throw new RuntimeException("invoke api failed, urlPath:" + url
                        + " status:" + status + " response:" +EntityUtils.toString(httpEntity));
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			get.abort();
			http.getConnectionManager().shutdown();
		}
		return returnValue;
	}
	
	public static String post(String url, Map<String, String> params) {  
        DefaultHttpClient httpclient = new DefaultHttpClient();  
        String body = null;  
        HttpPost post = postForm(url, params);  
        body = invoke(httpclient, post);  
        httpclient.getConnectionManager().shutdown();  
          
        return body;  
    }  
	private static String invoke(DefaultHttpClient httpclient,  
            HttpUriRequest httpost) {  
          
        HttpResponse response = sendRequest(httpclient, httpost);  
        String body = paseResponse(response);  
          
        return body;  
    }  
	
	private static HttpResponse sendRequest(DefaultHttpClient httpclient,  
            HttpUriRequest httpost) {  
        HttpResponse response = null;  
          
        try {  
            response = httpclient.execute(httpost);  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return response;  
    }  
	
	private static String paseResponse(HttpResponse response) {  
        HttpEntity entity = response.getEntity();  
          
        String charset = EntityUtils.getContentCharSet(entity);  
          
        String body = null;  
        try {  
            body = EntityUtils.toString(entity);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        return body;  
    }  
  
	 private static HttpPost postForm(String url, Map<String, String> params){  
         
	        HttpPost httpost = new HttpPost(url);  
	        List<NameValuePair> nvps = new ArrayList <NameValuePair>();  
	          
	        Set<String> keySet = params.keySet();  
	        for(String key : keySet) {  
	            nvps.add(new BasicNameValuePair(key, params.get(key)));  
	        }  
	          
	        try {  
	            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        }  
	          
	        return httpost;  
	    }  
	 
	public static void main(String[] args) {
		HttpUtil http = new HttpUtil();
		String str = http.getMethod("http://121.199.32.196/dingdan/dingdan!queryLoginDate.action?startDate=2014-06-04&endDate=2014-06-04&pn=1");
		System.out.println(str);
	}

}
