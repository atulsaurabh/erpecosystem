package com.dlinkddns.atulsaurabh.erpecosystem.repository;


import com.dlinkddns.atulsaurabh.erpecosystem.code.CodeAndMessage;
import com.dlinkddns.atulsaurabh.erpecosystem.code.SystemCode;
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
    public CodeAndMessage registerMember(SocietyMember member)
    {
        CodeAndMessage codeAndMessage = new CodeAndMessage();
        try {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            String url = erpUtility.resolvKey("rest_base_url")+erpUtility.resolvKey("rest_member_add");
            ResponseEntity<String> memberResponseEntity = template.postForEntity(url,member,String.class);

            codeAndMessage.setMessage(memberResponseEntity.getBody());
            if (memberResponseEntity.getStatusCode() == HttpStatus.FOUND)
                codeAndMessage.setErpCode(SystemCode.ALREADY_EXIST);
            else if (memberResponseEntity.getStatusCode() ==  HttpStatus.CREATED)
            codeAndMessage.setErpCode(SystemCode.SUCCESSFULLY_CREATED);
            else if (memberResponseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
                codeAndMessage.setErpCode(SystemCode.SERVER_ERROR);
            else {
                codeAndMessage.setMessage("Database Communication fail");
                codeAndMessage.setErpCode(SystemCode.DATABASE_COMM_FAILURE);
            }
            return codeAndMessage;
        }
        catch (Exception ex)
        {
           logger.logError(SocietyMemberRepositoryImpl.class,ex.getMessage(),ex);
           codeAndMessage.setMessage("Server communication fail");
           codeAndMessage.setErpCode(SystemCode.COMMUNICATION_FAIL);
           return codeAndMessage;
        }
    }
}
