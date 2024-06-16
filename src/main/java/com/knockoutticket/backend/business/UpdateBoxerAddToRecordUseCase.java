package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.UpdateBoxerAddToRecordRequest;

public interface UpdateBoxerAddToRecordUseCase {
    void updateBoxerRecord(Long id, UpdateBoxerAddToRecordRequest request);
}
