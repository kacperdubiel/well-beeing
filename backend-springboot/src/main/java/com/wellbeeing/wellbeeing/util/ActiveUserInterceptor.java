package com.wellbeeing.wellbeeing.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Component
public class ActiveUserInterceptor implements HandlerInterceptor {
    private UserDAO userDAO;

    public ActiveUserInterceptor(@Qualifier("userDAO") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        System.out.println(request);
        System.out.println("Request from user: " + SecurityContextHolder.getContext().getAuthentication().getName());
        User requestingUser = userDAO.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        if (requestingUser != null)
        {
            LocalDateTime time = LocalDateTime.now();
            requestingUser.setLastRequestTime(time);
            requestingUser.getProfile().setLastRequestTime(time);
            userDAO.save(requestingUser);
        }
        System.out.println("Pre Handle method is Calling");
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("Post Handle method is Calling");
    }
    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) throws Exception {

        System.out.println("Request and Response is completed");
    }
}
