package com.perfume.expert.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfumeService {

    private final PerfumeRepository perfumeRepository;

    @Autowired
    public PerfumeService(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    public List<PerfumeModel> getAllPerfumes() {
        return perfumeRepository.findAll();
    }
    public PerfumeModel getTopRecommendation(List<PerfumeModel> filteredPerfumes, String gender, String age, String price, String olfactoryGroup, String season, String occasion, String dominantNote) {
        PerfumeModel topRecommendation = null;
        int maxScore = 0;

        for (PerfumeModel perfume : filteredPerfumes) {
            int score = calculatePerfumeScore(perfume, gender, age, price, olfactoryGroup, season, occasion, dominantNote);
            if (score > maxScore || ((score == maxScore && compareDominantNotes(perfume.getDominant_note(), topRecommendation.getDominant_note(), dominantNote) > 0) && (score == maxScore && compareOlfactoryGroups(perfume.getOlfactory_group(), topRecommendation.getOlfactory_group(), olfactoryGroup) > 0))) {
                maxScore = score;
                topRecommendation = perfume;
            }
        }

        return topRecommendation;
    }

    private int calculatePerfumeScore(PerfumeModel perfume, String gender, String age, String price, String olfactoryGroup, String season, String occasion, String dominantNote) {
        int score = 0;

        if (perfume.getGender().equalsIgnoreCase(gender)) {
            score++;
        }
        if (perfume.getAge().equalsIgnoreCase(age)) {
            score++;
        }
        if (perfume.getPrice().equalsIgnoreCase(price)) {
            score++;
        }
        if (perfume.getOlfactory_group().equalsIgnoreCase(olfactoryGroup)) {
            score++;
        }
        if (perfume.getSeason().equalsIgnoreCase(season)) {
            score++;
        }
        if (perfume.getOccasion().equalsIgnoreCase(occasion)) {
            score++;
        }
        if (perfume.getDominant_note().equalsIgnoreCase(dominantNote)) {
            score++;
        }

        return score;
    }

    private int compareDominantNotes(String dominantNote1, String dominantNote2, String preferredDominantNote) {
        String[] priorityOrder = {preferredDominantNote, dominantNote1, dominantNote2};

        for (int i = 0; i < priorityOrder.length; i++) {
            if (dominantNote1.equalsIgnoreCase(priorityOrder[i])) {
                return -1;
            } else if (dominantNote2.equalsIgnoreCase(priorityOrder[i])) {
                return 1;
            }
        }

        return 0;
    }

    private int compareOlfactoryGroups(String olfactoryGroup1, String olfactoryGroup2, String preferredOlfactoryGroup) {
        String[] priorityOrder = {preferredOlfactoryGroup, olfactoryGroup1, olfactoryGroup2};

        for (int i = 0; i < priorityOrder.length; i++) {
            if (olfactoryGroup1.equalsIgnoreCase(priorityOrder[i])) {
                return -1;
            } else if (olfactoryGroup2.equalsIgnoreCase(priorityOrder[i])) {
                return 1;
            }
        }

        return 0;
    }

    public PerfumeModel getDefaultRecommendation(String gender, String age, String price, String olfactoryGroup, String season, String occasion, String dominantNote) {
        List<PerfumeModel> allPerfumes = perfumeRepository.findAll();

        PerfumeModel defaultPerfume = null;

        if (!gender.isEmpty()) {
            defaultPerfume = allPerfumes.stream()
                    .filter(perfume -> perfume.getGender().equalsIgnoreCase(gender))
                    .findFirst()
                    .orElse(null);
        }

        if (defaultPerfume == null && !age.isEmpty()) {
            defaultPerfume = allPerfumes.stream()
                    .filter(perfume -> perfume.getAge().equalsIgnoreCase(age))
                    .findFirst()
                    .orElse(null);
        }

        if (defaultPerfume == null && !price.isEmpty()) {
            defaultPerfume = allPerfumes.stream()
                    .filter(perfume -> perfume.getPrice().equalsIgnoreCase(price))
                    .findFirst()
                    .orElse(null);
        }

        if (defaultPerfume == null && !olfactoryGroup.isEmpty()) {
            defaultPerfume = allPerfumes.stream()
                    .filter(perfume -> perfume.getOlfactory_group().equalsIgnoreCase(olfactoryGroup))
                    .findFirst()
                    .orElse(null);
        }

        if (defaultPerfume == null && !season.isEmpty()) {
            defaultPerfume = allPerfumes.stream()
                    .filter(perfume -> perfume.getSeason().equalsIgnoreCase(season))
                    .findFirst()
                    .orElse(null);
        }

        if (defaultPerfume == null && !occasion.isEmpty()) {
            defaultPerfume = allPerfumes.stream()
                    .filter(perfume -> perfume.getOccasion().equalsIgnoreCase(occasion))
                    .findFirst()
                    .orElse(null);
        }

        if (defaultPerfume == null && !dominantNote.isEmpty()) {
            defaultPerfume = allPerfumes.stream()
                    .filter(perfume -> perfume.getDominant_note().equalsIgnoreCase(dominantNote))
                    .findFirst()
                    .orElse(null);
        }

        return defaultPerfume;
    }
}