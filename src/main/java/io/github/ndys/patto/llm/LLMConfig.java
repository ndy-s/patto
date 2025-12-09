package io.github.ndys.patto.llm;

public class LLMConfig {

    // JSON Schemas
    public static final String INSTRUCTIONS_SCHEMA_JSON = """
            {
              "type": "object",
              "required": ["title","instructions","objectives","constraints"],
              "properties": {
                "title": {"type":"string"},
                "instructions":{"type":"string"},
                "objectives":{"type":"array","items":{"type":"string"}},
                "constraints":{"type":"array","items":{"type":"string"}},
                "hints":{"type":"array","items":{"type":"string"}}
              },
              "additionalProperties": false
            }
            """;

    public static final String TEMPLATE_SCHEMA_JSON = """
            {
              "type": "object",
              "patternProperties": {
                ".*\\\\.java$": {"type":"string"}
              },
              "additionalProperties": false
            }
            """;

    public static final String SOLUTION_SCHEMA_JSON = """
            {
              "type": "object",
              "required": ["exerciseTitle","feedback","suggestions"],
              "properties": {
                "exerciseTitle":{"type":"string"},
                "feedback":{"type":"string"},
                "suggestions":{"type":"array","items":{"type":"string"}}
              },
              "additionalProperties": false
            }
            """;

    // LLM prompt templates
    public static final String INSTRUCTIONS_PROMPT_TEMPLATE = """
            Generate a JSON object for a Java coding exercise on the design pattern: %s.
            
            Include the following fields in JSON strictly according to the schema:
              - title: a clear title of the exercise
              - instructions: step-by-step instructions
              - objectives: list of goals for the user to achieve
              - constraints: rules or limitations to follow
              - hints (optional): tips to help solve the exercise
            
            Detailed Guidance:
              - Explain clearly what the user should implement.
              - Include objectives like "Implement classes X and Y" or "Use interfaces and inheritance appropriately."
              - Constraints can include things like "Do not use external libraries" or "Follow naming conventions."
              - Hints should be optional but helpful, without giving full solutions.
            
            IMPORTANT:
              - Do NOT include any solution code.
              - Return strictly JSON, nothing else.
            
            JSON Schema:
            %s
            """;

    public static final String TEMPLATE_PROMPT_TEMPLATE = """
            Based on the following instructions:\n%s
            
            Generate a multi-file Java template for the exercise.
            
            Requirements:
              - Keys: filenames (can include subfolders like 'handler/Handler.java')
              - Values: Java class stubs, interface definitions, method signatures, and comments
              - Do NOT include any solution code
              - Include TODO comments where the user should implement functionality
              - Do NOT include markdown blocks or explanations outside JSON
            
            Example JSON format:
            {
              "handler/Handler.java": "public class Handler {\\n    // TODO: implement methods\\n}",
              "client/Client.java": "public class Client {\\n    // TODO: implement main\\n}"
            }
            
            Return strictly JSON, following the schema:
            %s
            """;

    public static final String SOLUTION_PROMPT_TEMPLATE = """
            You are a code reviewer. Check the Java exercise solution and return structured feedback in JSON.
            
            Exercise Title: %s
            Instructions: %s
            Template Files:\n%s
            User Solution:\n%s
            
            The JSON must include the following fields:
              - exerciseTitle: the name of the exercise
              - feedback: constructive comments on correctness and style
              - suggestions: list of improvements or tips
            
            Guidance:
              - Provide clear, actionable feedback.
              - Highlight any errors, missing functionality, or bad practices.
              - Suggest ways to improve code readability, design, or maintainability.
            
            IMPORTANT:
              - Return strictly JSON, nothing else.
              - Follow this schema strictly:
            %s
            """;
}

