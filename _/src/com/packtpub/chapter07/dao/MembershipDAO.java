package com.packtpub.chapter07.dao;

import com.packtpub.chapter07.dto.MembershipStatusDto;

public interface MembershipDAO {

	MembershipStatusDto getStatusFor(String id);

}
