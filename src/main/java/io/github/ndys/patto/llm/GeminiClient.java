package io.github.ndys.patto.llm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import java.util.HashMap;
import java.util.Map;

public class GeminiClient {

    private static final Client client = new Client();

    public static String generateExerciseInstructions(String patternName) {
        String prompt = "Generate clear instructions for a Java coding exercise on the design pattern: " + patternName + ".\n" +
                "Include:\n" +
                "  - The objectives of the exercise.\n" +
                "  - What the user should implement.\n" +
                "  - Any constraints and guidance to complete the task.\n\n" +
                "IMPORTANT RESTRICTIONS:\n" +
                "  - Do NOT provide the solution or any full code implementation.\n" +
                "  - Only provide instructions, requirements, and hints if needed.\n" +
                "  - Make it clear what the user is expected to do without giving answers.";

        GenerateContentResponse response = client.models.generateContent("gemini-2.5-flash", prompt, null);

        System.out.println("=== DEBUG: Instructions Raw Response ===");
        System.out.println(response.text());
        System.out.println("======================================");

        return response.text();
    }

    public static Map<String, String> generateExerciseTemplates(String patternName, String instructions) {
        String prompt = "Based on the following exercise instructions:\n" + instructions +
                "\nGenerate a multi-file Java template for the exercise. " +
                "Return it strictly as a JSON object where the keys are filenames (they can include subfolders like 'handler/Handler.java') " +
                "and the values are the corresponding file contents. " +
                "Do NOT include solutions, only class stubs, method signatures, and comments for the user to fill in. " +
                "Do NOT include any markdown code blocks or explanations outside of JSON.\n\n" +
                "Here is an example of the JSON structure you should return:\n" +
                "{\n" +
                "  \"handler/Handler.java\": \"public class Handler {\\n    // TODO: implement methods\\n}\",\n" +
                "  \"client/Client.java\": \"public class Client {\\n    // TODO: implement main\\n}\"\n" +
                "}\n" +
                "Return only JSON, nothing else.";

        GenerateContentResponse response = client.models.generateContent("gemini-2.5-flash", prompt, null);
        String raw = response.text();

        System.out.println("=== DEBUG: Template Raw Response ===");
        System.out.println(raw);
        System.out.println("===================================");

        raw = raw.replaceAll("^```(json)?\\s*", "").replaceAll("\\s*```$", "").trim();

        Map<String, String> files = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            files = mapper.readValue(raw, new TypeReference<Map<String, String>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to parse LLM response as JSON.");
        }

        return files;
    }

    public static String checkSolution(String instructions, Map<String, String> templates, String combinedSolution) {
        StringBuilder templateContent = new StringBuilder();
        templates.forEach((k, v) -> templateContent.append("// File: ").append(k).append("\n").append(v).append("\n\n"));

        String prompt = "Instructions:\n" + instructions +
                "\nTemplate:\n" + templateContent +
                "\nUser Solution:\n" + combinedSolution +
                "\nCheck correctness, provide constructive feedback, and suggest improvements.";

        GenerateContentResponse response = client.models.generateContent("gemini-2.5-flash", prompt, null);

        System.out.println("=== DEBUG: Solution Check Raw Response ===");
        System.out.println(response.text());
        System.out.println("==========================================");

        return response.text();
    }
}

