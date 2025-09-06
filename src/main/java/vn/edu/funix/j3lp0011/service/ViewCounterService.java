package vn.edu.funix.j3lp0011.service;

public interface ViewCounterService {

    int incrementAndGetViews();

    int getCurrentViews();

    String[] getFormattedViewsArray();
}