package project.financement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.financement.annotation.UpgradeUserInfo;
import project.financement.annotation.UuidFormatChecker;
import project.financement.service.impl.PaymentServiceImpl;

import java.util.UUID;

/**
 * Controller class handling HTTP requests related to payments.
 * Contains an endpoint for upgrading user to premium account.
 */
@Validated
@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    //@PreAuthorize("hasRole('FreeUser')")
    @UpgradeUserInfo(path = "/upgrade/{userId}")
    public ResponseEntity<String> upgradeUserInfo(@UuidFormatChecker @PathVariable String userId) {
        paymentService.upgradeUserToPremium(UUID.fromString(userId));
        return ResponseEntity.ok("Congratulations! Your account has been upgraded to Premium.");
    }
}