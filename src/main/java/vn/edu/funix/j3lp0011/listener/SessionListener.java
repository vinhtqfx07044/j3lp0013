package vn.edu.funix.j3lp0011.listener;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.edu.funix.j3lp0011.service.ViewCounterService;

@Slf4j
@Component
public class SessionListener implements HttpSessionListener {

    private final ViewCounterService viewCounterService;

    public SessionListener(ViewCounterService viewCounterService) {
        this.viewCounterService = viewCounterService;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("New session created. Incrementing view count.");
        try {
            int currentViews = viewCounterService.incrementAndGetViews();
            log.info("Total views are now: {}", currentViews);

            // Format và lưu vào session để footer hiển thị
            String formattedCount = String.format("%06d", currentViews);
            String[] countArray = formattedCount.split("");

            HttpSession session = se.getSession();
            session.setAttribute("viewCounter", countArray);

        } catch (Exception e) {
            log.error("Could not increment and update view count in session.", e);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("Session destroyed.");
    }
}