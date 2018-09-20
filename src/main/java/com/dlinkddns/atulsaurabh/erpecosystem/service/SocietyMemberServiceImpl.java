/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.service;

import com.dlinkddns.atulsaurabh.erpecosystem.entity.SocietyMember;
import com.dlinkddns.atulsaurabh.erpecosystem.repository.SocietyMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Suyojan
 */

@Service
public class SocietyMemberServiceImpl implements SocietyMemberService{

  @Autowired
  private SocietyMemberRepository societyMemberRepository;
    
    @Override
    public boolean createNewSocietyMember(SocietyMember societyMember) {
        return societyMemberRepository.registerMember(societyMember);
    }
    
}
