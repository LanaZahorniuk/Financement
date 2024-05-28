package project.financement.service;

import java.util.UUID;

public interface PaymentService {

    void upgradeUserToPremium(UUID userId);
}
