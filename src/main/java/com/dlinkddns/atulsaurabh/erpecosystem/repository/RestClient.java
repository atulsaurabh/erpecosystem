package com.dlinkddns.atulsaurabh.erpecosystem.repository;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestClient
{
    private static RestTemplate template;

    private RestClient()
    {

    }


    public static RestTemplate restTemplate()
    {

        if (template == null)
        {
            template=new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        }
        return template;
    }
}
