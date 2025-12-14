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

    public static final String HELP_SCHEMA_JSON = """
        {
            "type": "object",
            "required": ["answer"],
            "properties": {
                "answer": { "type": "string" },
                "extraTips": { "type": "array", "items": { "type": "string" } },
                "relatedFiles": { "type": "array", "items": { "type": "string" } }
            },
            "additionalProperties": false
        }
    """;

    public static final String SOLUTION_SCHEMA_JSON = """
        {
            "type": "object",
            "required": ["exerciseTitle","score","status","feedback","suggestions"],
            "properties": {
                "exerciseTitle":{"type":"string"},
                "score":{"type":"integer","minimum":0,"maximum":100},
                "status":{"type":"string","enum":["PASS","NEEDS_IMPROVEMENT"]},
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

    Return strictly JSON using this schema:
    %s
    """;

    public static final String TEMPLATE_PROMPT_TEMPLATE = """
    Based on the following instructions:
    %s

    Difficulty Level: %s

    Generate a multi-file Java template for the exercise.

    Difficulty Rules:

    IF Difficulty = GUIDED:
    - Provide the COMPLETE class and interface structure
    - Include class names, interfaces, and method signatures
    - Method bodies MUST be EMPTY, return default values, or throw
    UnsupportedOperationException
    - Include TODO comments ONLY to indicate WHERE logic should be written
    - DO NOT implement any business logic
    - DO NOT show control flow, conditions, or algorithmic decisions
    - This mode teaches structure, NOT the solution

    IF Difficulty = MINIMAL:
    - Provide minimal scaffolding only
    - MAY include a single entry-point class (e.g., Main or Client)
    - DO NOT define handlers, interfaces, or concrete classes
    - NO TODO comments explaining structure
    - Student must design the solution themselves

    General Requirements:
    - Keys: filenames (can include subfolders)
    - Values: Java source code only
    - Do NOT include solution logic
    - Do NOT include markdown or explanations

    Return strictly JSON using this schema:
    %s
    """;

    public static final String HELP_PROMPT_TEMPLATE = """
    You are helping a student with a Java design-pattern exercise.

    The following is the exercise context:
    -------------------------------------
    Pattern: %s

    Instructions:
    %s

    Exercise Files:
    %s
    -------------------------------------

    Now answer the student's question below.

    Requirements:
    - Provide **clear, helpful, technical explanations**
    - Use **Java terminology**
    - Do NOT reveal or generate full solutions.
    - You MAY give hints or explanations.
    - Return strictly JSON, matching this schema:

    Student question:
    %s
    
    Return strictly JSON using this schema:
    %s
    """;

    public static final String SOLUTION_PROMPT_TEMPLATE = """
    You are a code reviewer. Check the Java exercise solution and return structured feedback in JSON.

    Exercise Title: %s
    Difficulty Level: %s
    Instructions: %s
    Template Files:\n%s
    User Solution:\n%s

    The JSON must include the following fields:
    - exerciseTitle: the name of the exercise
    - feedback: constructive comments on correctness and style
    - suggestions: list of improvements or tips

    Guidance:
    - Provide clear, actionable feedback.
    - Assign a score from 0 to 100.
    - Use status:
        - PASS → solution is correct and follows the pattern well
        - NEEDS_IMPROVEMENT → missing or incorrect parts
    - Suggestions should be concise and practical.

    Return strictly JSON using this schema:
    %s
    """;
}

