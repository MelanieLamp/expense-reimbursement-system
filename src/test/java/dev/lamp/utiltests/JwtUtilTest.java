package dev.lamp.utiltests;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.lamp.utilities.JwtUtil;
import org.junit.jupiter.api.Test;

public class JwtUtilTest {
    @Test
    void creates_jwt_employee(){
        String jwt1 = JwtUtil.generate("employee","Andvari");
        System.out.println(jwt1);
    }

    @Test
    void creates_jwt_manager(){
        String jwt2 = JwtUtil.generate("manager","Nimara");
        System.out.println(jwt2);
    }

    @Test
    void decode_jwt(){
        DecodedJWT jwt1 = JwtUtil.isValidJWT("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiZW1wbG95ZWUiLCJlbXBOYW1lIjoiQW5kdmFyaSJ9.IN9NhA_LCrwCJM1Eg84WBGCIojSJOF8Fgg_NRIsrDhQ");
        String role = jwt1.getClaim("role").asString();
        System.out.println(role);
    }
    @Test
    void edited_jwt(){
        DecodedJWT jwt2 =JwtUtil.isValidJWT("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoibWFuYWdlciIsImVtcE5hbWUiOiJOaW1hcmEifQ.bDgioZYQHlJ0WnV2CyZ4A6MJqnzv3jcVvBbR6fVPLoU");
        String role = jwt2.getClaim("role").asString();
        System.out.println(role);
    }
}
