package vn.edu.funix.j3lp0011.service;

import jakarta.servlet.http.HttpServletRequest;

public interface SessionService {

    void handleNewSession(HttpServletRequest request);
}