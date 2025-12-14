package io.github.ndys.patto.llm;

import java.util.Map;

import io.github.ndys.patto.exercise.DifficultyLevel;

public interface LLMClient {

    Map<String, Object> generateInstructions(String patternName);

    Map<String, String> generateTemplates(
        String patternName, 
        Map<String, Object> instructions,
        DifficultyLevel difficultyLevel
    );

    Map<String, Object> askHelp(
        String patternName, 
        String instructions, 
        Map<String, String> exerciseFiles, 
        String userQuestion
    );

    Map<String, Object> checkSolution(
        String patternName, 
        Map<String, Object> instructions, 
        Map<String, String> templates, 
        String combinedSolution,
        DifficultyLevel difficultyLevel
    );

}
