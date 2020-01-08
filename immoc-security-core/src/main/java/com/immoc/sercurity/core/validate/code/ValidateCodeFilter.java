package com.immoc.sercurity.core.validate.code;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValidateCodeFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(StringUtils.equals("/authentication/form",request.getRequestURI())
            && StringUtils.endsWithIgnoreCase(request.getMethod(), "post")){
            try{
                validate(new ServletWebRequest(request));
            }catch (ValidateCodeException e){

            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest servletWebRequest) {
    }
}
