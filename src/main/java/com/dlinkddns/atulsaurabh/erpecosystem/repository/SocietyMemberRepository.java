/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlinkddns.atulsaurabh.erpecosystem.repository;

import com.dlinkddns.atulsaurabh.erpecosystem.entity.SocietyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Atul Saurabh
 */

@Repository
public interface SocietyMemberRepository extends JpaRepository<SocietyMember, Integer>
{
    
}
