package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.UpdateBoxerAddToRecordRequest;

public interface UpdateBoxerAddToRecordUseCase {
    void UpdateBoxerRecord(Long id, UpdateBoxerAddToRecordRequest request);
}
