package io.github.ndys.patto.llm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import io.github.cdimascio.dotenv.Dotenv;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

import java.util.Map;
import java.util.Objects;

public class GeminiClient implements LLMClient {

    private static final Dotenv dotenv = Dotenv.load();
    private static final String API_KEY = dotenv.get("GEMINI_API_KEY");

    private static final Client client = Client.builder().apiKey(API_KEY).build();

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final int MAX_RETRIES = 3;

    private static final Schema INSTRUCTIONS_SCHEMA = SchemaLoader.load(new JSONObject(LLMConfig.INSTRUCTIONS_SCHEMA_JSON));
    private static final Schema TEMPLATE_SCHEMA = SchemaLoader.load(new JSONObject(LLMConfig.TEMPLATE_SCHEMA_JSON));
    private static final Schema SOLUTION_SCHEMA = SchemaLoader.load(new JSONObject(LLMConfig.SOLUTION_SCHEMA_JSON));

    @Override
    public Map<String, Object> generateInstructions(String patternName) {
        String prompt = String.format(LLMConfig.INSTRUCTIONS_PROMPT_TEMPLATE,
                patternName, LLMConfig.INSTRUCTIONS_SCHEMA_JSON);
        return callWithSchemaRetry(prompt, INSTRUCTIONS_SCHEMA, new TypeReference<>() {});
    }

    @Override
    public Map<String, String> generateTemplates(String patternName, Map<String, Object> instructions) {
        String prompt = String.format(LLMConfig.TEMPLATE_PROMPT_TEMPLATE,
                instructions, LLMConfig.TEMPLATE_SCHEMA_JSON);
        return callWithSchemaRetry(prompt, TEMPLATE_SCHEMA, new TypeReference<>() {});
    }

    @Override
    public Map<String, Object> checkSolution(String patternName, Map<String, Object> instructions,
                                                    Map<String, String> templates, String combinedSolution) {
        StringBuilder templateContent = new StringBuilder();
        templates.forEach((k,v) -> templateContent.append("// File: ").append(k).append("\n").append(v).append("\n\n"));

        String prompt = String.format(LLMConfig.SOLUTION_PROMPT_TEMPLATE,
                patternName, instructions, templateContent, combinedSolution, LLMConfig.SOLUTION_SCHEMA_JSON);

        return callWithSchemaRetry(prompt, SOLUTION_SCHEMA, new TypeReference<>() {});
    }

    private static <T> T callWithSchemaRetry(String prompt, Schema schema, TypeReference<T> typeRef) {
        for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
            try {
                GenerateContentResponse response = client.models.generateContent("gemini-2.5-flash", prompt, null);
                String raw = Objects.requireNonNull(response.text()).replaceAll("^```(json)?\\s*", "").replaceAll("\\s*```$", "").trim();

                JSONObject json = new JSONObject(raw);
                schema.validate(json);

                return mapper.readValue(json.toString(), typeRef);
            } catch (Exception e) {
                System.out.println("Attempt " + attempt + " failed JSON/schema validation. Retrying...");
                if (attempt == MAX_RETRIES) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }
}

