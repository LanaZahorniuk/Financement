package project.financement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.financement.service.impl.PaymentServiceImpl;

import java.util.UUID;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    @PostMapping("/upgrade/{userId}")
    public ResponseEntity<String> upgradeUserInfo(@PathVariable String userId) {
        paymentService.upgradeUserToPremium(UUID.fromString(userId));
        return ResponseEntity.ok("Congratulations! Your account has been upgraded to Premium.");
    }
}