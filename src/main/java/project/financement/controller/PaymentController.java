package project.financement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.financement.service.PaymentService;

import java.util.UUID;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/upgrade/{userId}")
    public ResponseEntity<String> upgradeUserInfo(@PathVariable UUID userId) {
        paymentService.upgradeUserToPremium(userId);
        return ResponseEntity.ok("Congratulations! Your account has been upgraded to Premium.");
    }
}