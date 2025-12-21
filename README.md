# Patto
> A hands-on workbook for practicing design patterns through terminal-based demos and AI exercise agents.

## Why this project exists?
This project started out of frustration.

Whenever I read about design patterns, things made sense at first. I could follow the diagrams, understand the terminology, and recognize what each pattern was trying to solve. But a few weeks later, that understanding faded.

I would forget **when** a pattern is actually useful, **what** the code looks like in a real project, and **why** the pattern even matters.

Reading was not enough.
I needed repetition. I needed practice. I needed to actually build things.

So I created Patto.

Patto is my personal learning companion for design patterns. It helps me learn patterns the same way you learn math or music by practicing them over and over.

## What is Patto?
Patto is a terminal-based Java application that generates hands-on refactoring exercises for design patterns.

Instead of starting from clean or ideal code, Patto deliberately generates working but badly designed code:
* tightly coupled
* rigid and hard to extend
* full of conditionals and duplication

Your task is to refactor that smelly code and apply the correct design pattern to improve it.

Java's object-oriented nature makes classic design patterns explicit and easy to reason about, which makes it a great language for this kind of practice.

You can think of Patto as an interactive workbook:
* each design pattern is a chapter
* each exercise starts with bad code
* your job is to refactor it
* your solution is reviewed and scored
* mistakes become learning signals, not failures

## How it works?
<video src="https://github.com/user-attachments/assets/277e861c-c36b-4ed4-a40f-e94846b59896" controls muted playsinline width="100%"></video>

### 1. Main Menu
When the application starts, it shows a list of available design patterns.

Each pattern acts like its own chapter: focused, isolated, and easy to return to whenever you want to refresh your understanding.

### 2. Pattern Submenu
After selecting a pattern, you enter a submenu where you can:

* explore example implementations
* generate a new refactoring exercise for that pattern

This keeps learning and practice organized, while still allowing you to jump between patterns easily.

### 3. Exercise Generation & Refactoring Loop
When you generate an exercise, Patto runs a simple learning loop using multiple AI agents powered by the free [Gemini](https://ai.google.dev/gemini-api) model:

* **Instruction Agent**: Defines the learning goal, constraints, and difficulty level.

* **Template Agent**: Generates intentionally smelly but functional Java code.
  The code:
  * compiles and runs
  * is poorly designed on purpose
  * does *not* use the target design pattern
  * is meant to be refactored by the learner

* **Evaluation Agent**: Reviews your refactored solution, scores it based on correctness and pattern usage, and provides feedback on what could be improved.

You can repeat this loop as many times as you want for any pattern until it actually sticks.

## Quick Setup
Clone the repository and install dependencies:
```bash
git clone https://github.com/ndy-s/patto
cd patto
mvn clean install
```

Copy the example environment file and add your Gemini API key:
```bash
cp .env.example .env
```

Open `.env` and set your key:
```env
GEMINI_API_KEY=your_key_here
```
You can get a free API key from [Google AI Studio](https://aistudio.google.com).

Run the project:
```bash
mvn exec:java
```

## Using Patto
Once the application starts, the main menu will appear. From there, you can:
* choose a design pattern
* view example implementations
* generate a smelly-code exercise
* select a difficulty level
* refactor the code using the pattern
* ask the agent for hints
* submit your solution for evaluation

Generated exercises are placed in:
```
src/main/java/io/github/ndys/patto/workspace/
```
This directory acts as a temporary sandbox where you work on each exercise.

## Project Structure
```
patto/
└── src/main/java/io/github/ndys/patto/
    ├── workspace/       # Generated exercises (refactoring workspace)
    │   └── README.md
    ├── patterns/        # Pattern menus, examples, and metadata
    └── ...              # Other project directories and files
```

## License
MIT
