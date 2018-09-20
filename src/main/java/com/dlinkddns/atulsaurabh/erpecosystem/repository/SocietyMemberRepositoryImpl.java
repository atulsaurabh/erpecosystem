package com.dlinkddns.atulsaurabh.erpecosystem.repository;


import com.dlinkddns.atulsaurabh.erpecosystem.entity.SocietyMember;
import com.dlinkddns.atulsaurabh.erpecosystem.util.ErpUtility;
import net.dlinkddns.atulsaurabh.hasselfreelogger.api.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


@Repository
public class SocietyMemberRepositoryImpl implements SocietyMemberRepository {
    @Autowired
    private ErpUtility erpUtility;
    @Autowired
    private Logger logger;

    @Override
    public boolean registerMember(SocietyMember member)
    {
        try {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            String url = erpUtility.resolvKey("rest_base_url")+erpUtility.resolvKey("rest_member_add");
            ResponseEntity<SocietyMember> memberResponseEntity = template.postForEntity(url,member,SocietyMember.class);
            if (memberResponseEntity.getStatusCode() == HttpStatus.OK)
            {
                if(memberResponseEntity.getBody() != null)
                    return true;
                else
                    return false;
            }
            else
                return false;
        }
        catch (Exception ex)
        {
           logger.logError(SocietyMemberRepositoryImpl.class,ex.getMessage(),ex);
           return false;
        }
    }
}
