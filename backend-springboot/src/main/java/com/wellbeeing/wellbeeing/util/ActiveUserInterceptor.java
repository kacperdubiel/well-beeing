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
        User requestingUser = userDAO.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        if (requestingUser != null)
        {
            LocalDateTime time = LocalDateTime.now();
            requestingUser.setLastRequestTime(time);
            requestingUser.getProfile().setLastRequestTime(time);
            userDAO.save(requestingUser);
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) throws Exception {

    }
}
