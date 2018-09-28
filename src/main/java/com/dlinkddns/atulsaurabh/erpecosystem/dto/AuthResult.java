package com.dlinkddns.atulsaurabh.erpecosystem.dto;

import com.dlinkddns.atulsaurabh.erpecosystem.entity.MemberRole;

import java.util.Set;

public class AuthResult
{
    private Set<MemberRole> roles;
    private boolean result;

    public Set<MemberRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<MemberRole> roles) {
        this.roles = roles;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
