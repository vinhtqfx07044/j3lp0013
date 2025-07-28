package vn.edu.funix.j3lp0011.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.edu.funix.j3lp0011.listener.SessionListener;

@Configuration
public class ServletConfig {

    @Bean
    public ServletListenerRegistrationBean<SessionListener> sessionListenerRegistration(SessionListener sessionListener) {
        ServletListenerRegistrationBean<SessionListener> registrationBean = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(sessionListener);
        return registrationBean;
    }
}