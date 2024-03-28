package com.perfume.expert.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PerfumeController {

    private final PerfumeService perfumeService;

    @Autowired
    public PerfumeController(PerfumeService perfumeService) {
        this.perfumeService = perfumeService;
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> generatePerfumeRecommendation(
            @RequestParam String gender,
            @RequestParam String age,
            @RequestParam String price,
            @RequestParam String olfactoryGroup,
            @RequestParam String season,
            @RequestParam String occasion,
            @RequestParam String dominantNote) {

        List<PerfumeModel> allPerfumes = perfumeService.getAllPerfumes();

        List<PerfumeModel> filteredPerfumes = allPerfumes.stream()
                .filter(perfume -> matchesCriteria(perfume, gender, age, price, olfactoryGroup, season, occasion, dominantNote))
                .collect(Collectors.toList());

        PerfumeModel recommendedPerfume = null;

        if (!filteredPerfumes.isEmpty()) {
            recommendedPerfume = perfumeService.getTopRecommendation(filteredPerfumes, gender, age, price, olfactoryGroup, season, occasion, dominantNote);
        } else {
            recommendedPerfume = perfumeService.getDefaultRecommendation(gender, age, price, olfactoryGroup, season, occasion, dominantNote);
        }

        String perfumeName = recommendedPerfume.getPerfume_name();
        String imageUrl = recommendedPerfume.getImage();

        String relativeImageUrl = "/perfumeImages/" + imageUrl;

        Map<String, String> response = new HashMap<>();
        response.put("name", perfumeName);
        response.put("imageUrl", relativeImageUrl);

        return ResponseEntity.ok().body(response);
    }

    private boolean matchesCriteria(PerfumeModel perfume, String gender, String age, String price, String olfactoryGroup, String season, String occasion, String dominantNote) {
        return perfume.getGender().equalsIgnoreCase(gender)
                || perfume.getAge().equals(age)
                || perfume.getPrice().equals(price)
                || perfume.getOlfactory_group().equalsIgnoreCase(olfactoryGroup)
                || perfume.getSeason().equalsIgnoreCase(season)
                || perfume.getOccasion().equalsIgnoreCase(occasion)
                || perfume.getDominant_note().equalsIgnoreCase(dominantNote);
    }
}
