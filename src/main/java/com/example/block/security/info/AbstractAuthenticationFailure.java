package com.example.block.security.info;

import com.example.block.global.apiPayload.code.ErrorReasonDTO;
import com.example.block.global.apiPayload.code.ExceptionDto;
import com.example.block.global.apiPayload.code.status.ErrorStatus;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONValue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AbstractAuthenticationFailure {

    protected void setErrorResponse(
            HttpServletResponse response, ErrorReasonDTO errorReason)
            throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(errorReason.getHttpStatus().value());

        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("data", null);
        result.put("error", errorReason);

        response.getWriter().write(JSONValue.toJSONString(result));
    }
}
