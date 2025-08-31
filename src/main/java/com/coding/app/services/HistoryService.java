package com.coding.app.services;

import com.coding.app.models.History;
import com.coding.app.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    public void addHistory(final String action) {
        historyRepository.save(new History(action));
    }

    public List<History> getAllHistories() {
        return historyRepository.findAll();
    }

    public void clearHistory() {
        historyRepository.deleteAll();
    }
}
