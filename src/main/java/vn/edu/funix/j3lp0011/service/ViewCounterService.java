package vn.edu.funix.j3lp0011.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.funix.j3lp0011.entity.TotalViews;
import vn.edu.funix.j3lp0011.repository.TotalViewsRepository;

@Service
@RequiredArgsConstructor
public class ViewCounterService {

    private final TotalViewsRepository totalViewsRepository;

    @Transactional
    public synchronized int incrementAndGetViews() {
        totalViewsRepository.incrementViewCount();
        return totalViewsRepository.findById(1)
                .map(TotalViews::getViewCount)
                .orElse(0);
    }
}