/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.service;

import com.dlinkddns.atulsaurabh.erpecosystem.code.CodeAndMessage;
import com.dlinkddns.atulsaurabh.erpecosystem.code.SystemCode;
import com.dlinkddns.atulsaurabh.erpecosystem.entity.SocietyMember;
import org.springframework.stereotype.Service;

/**
 *
 * @author Atul Saurabh
 */

@Service
public interface SocietyMemberService {
    public CodeAndMessage createNewSocietyMember(SocietyMember societyMember);
}
