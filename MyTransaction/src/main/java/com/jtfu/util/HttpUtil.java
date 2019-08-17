package com.jtfu.util;

import com.jtfu.transaction.Transaction;
import com.jtfu.transaction.TransactionMangage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpUtil {
    public  static RestTemplate restTemplate=new RestTemplate();

    public static Object post(String url){
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set(TransactionType.GROUPID, TransactionMangage.getCurrent());
        HttpEntity<MultiValueMap<String, String>> httpEntity=new HttpEntity(null,httpHeaders);
        return restTemplate.postForObject(url,httpEntity,Object.class);
    }
}
