package vn.edu.funix.j3lp0011.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.funix.j3lp0011.entity.TotalViews;
import vn.edu.funix.j3lp0011.repository.TotalViewsRepository;

@Service
@RequiredArgsConstructor
public class ViewCounterServiceImpl implements ViewCounterService {

    private final TotalViewsRepository totalViewsRepository;

    @Transactional
    public synchronized int incrementAndGetViews() {
        try {
            return totalViewsRepository.incrementAndGetViewCount();
        } catch (Exception e) {
            return 0;
        }
    }

    public int getCurrentViews() {
        return totalViewsRepository.findById(1)
                .map(TotalViews::getViewCount)
                .orElse(0);
    }

    public String[] getFormattedViewsArray() {
        int currentViews = getCurrentViews();
        String formattedCount = String.format("%06d", currentViews);
        return formattedCount.split("");
    }
}