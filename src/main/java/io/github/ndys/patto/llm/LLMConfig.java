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
    Generate a JSON object for a Java coding exercise focused on REFACTORING
    existing code using the design pattern: %s.

    The exercise MUST assume the student is given a working but poorly-designed
    implementation that needs improvement.

    Include the following fields strictly according to the schema:
    - title
    - instructions
    - objectives
    - constraints
    - hints (optional)

    Detailed Guidance:

    - Instructions MUST describe a refactoring task, not building from scratch.
      Example wording:
      "Refactor the given implementation to remove tight coupling using the Strategy pattern."

    - Objectives MUST focus on FIXING problems, such as:
      - Reduce conditional complexity
      - Eliminate duplicated logic
      - Introduce proper abstractions
      - Improve extensibility using the pattern

    - Constraints may include:
      - Do not change external behavior
      - Do not remove existing public methods
      - No external libraries

    - Hints may point out smells (e.g., large if/else blocks), but MUST NOT
      describe the final structure.

    IMPORTANT:
    - Assume the student STARTS from inefficient code.
    - Do NOT include any solution code.
    - Return strictly JSON, nothing else.

    Return strictly JSON using this schema:
    %s
    """;

    public static final String TEMPLATE_PROMPT_TEMPLATE = """
    You are generating STARTER CODE for a Java design-pattern exercise.
    
    This starter code represents a WORKING but POORLY DESIGNED implementation
    that the student must REFACTOR using the specified design pattern.
    
    Exercise Context:
    Instructions:
    %s
    
    Difficulty Level:
    %s
    
    Primary Goal:
    - Generate NAIVE, INEFFICIENT, TIGHTLY-COUPLED Java code
    - Code MUST compile and run correctly
    - Code MUST be hard to extend or modify
    - DO NOT use the target design pattern in any form
    
    Dependency Completeness (Critical):
    - EVERY non-JDK class referenced MUST be generated as a source file
    - If a class is instantiated, called, or typed, it MUST exist
    - Do NOT reference undefined, external, or imaginary classes
    - Circular dependencies are allowed
    - Missing dependencies are NOT allowed
    
    Class Declaration Rules (Mandatory):
    - EVERY top-level class MUST be declared as public
    - File name MUST exactly match the public class name
    - Do NOT define multiple top-level classes in one file
    
    Package & Import Rules (Processor-aware):
    - DO NOT write any package declaration
    - DO NOT use fully-qualified class names
    - DO NOT import project-internal classes
    - ONLY import from java.* or javax.*
    - Assume all project classes share the same logical package hierarchy
    - Subfolders MAY be used in filenames to organize code
    - Folder names must be lowercase and meaningful
    
    Difficulty-specific Rules:
    IF Difficulty = GUIDED:
    - Generate ALL concrete classes directly
    - No interfaces or abstract classes
    - Business logic MAY exist and SHOULD be messy
    - Do NOT include TODOs or structural hints
    
    IF Difficulty = MINIMAL:
    - Generate the smallest possible working implementation
    - Still naive and rigid
    - No hints, no abstractions
    
    Forbidden Actions (Absolute):
    - Do NOT refactor or clean the code
    - Do NOT introduce the design pattern
    - Do NOT explain how to fix the code
    - Do NOT include markdown or commentary
    - Do NOT optimize readability or architecture
    
    Output Contract:
    - Output MUST be valid JSON
    - Keys = filenames (may include subfolders)
    - Values = raw Java source code only
    - Code MUST compile when placed under a single root package
    
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
    - Focus explanations on identifying problems and design smells
    - Do NOT suggest full class structures or final refactored Direction

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

