package com.frame2.server.core.member.api;

import com.frame2.server.core.member.payload.response.MyInformationResponse;
import com.frame2.server.core.support.request.User;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "회원 API")
public interface MemberAPISpec {

    MyInformationResponse getMe(@Schema(hidden = true) User user);
}
