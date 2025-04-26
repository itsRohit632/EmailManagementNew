package com.projectmanagement.controller;

import com.projectmanagement.dto.MarketingDTO;
import com.projectmanagement.model.Marketing;
import com.projectmanagement.service.MarketingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/marketing")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class MarketingController {

    private final MarketingService marketingService;

    public MarketingController(MarketingService marketingService) {
        this.marketingService = marketingService;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitMarketing(@RequestBody MarketingDTO dto) {
        String response = marketingService.processMarketing(dto.getEmail());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/result")
    public ResponseEntity<?> getMarketingResult(@RequestParam String email) {
        Marketing marketing = marketingService.getMarketing(email);
        if (marketing == null) {
            return ResponseEntity.status(404).body("No marketing data found for: " + email);
        }
        return ResponseEntity.ok(marketing);
    }
}
