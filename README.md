# Patto
> A hands-on workbook for practicing design patterns through terminal-based demos and AI exercise agents.

This project started out of frustration.

Whenever I read about design patterns, things made sense at first. I could follow the diagrams, understand the terminology, and recognize what each pattern was trying to solve. But a few weeks later, that understanding faded.

I would forget when a pattern is actually useful, what the code looks like in a real project, and why the pattern even matters.

Reading was not enough.
I needed repetition. I needed practice. I needed to actually build things.

So I created this project.

This project is my personal learning companion for design patterns. It helps me learn patterns the same way you learn math or music, by practicing them over and over.

## What is this project?
This project is a terminal-based Java application that generates small, hands-on coding exercises for design patterns. Java's object-oriented nature makes classic patterns explicit and easy to reason about, which makes it a great language for practicing them.

I think of it as an interactive workbook:
* each design pattern is a chapter
* each exercise is a problem to solve
* each solution is reviewed and scored
* mistakes become learning signals, not failures

To support this learning loop, this project uses several small AI agents powered by the free [Gemini](https://ai.google.dev/gemini-api) model.

## How it works?
<video src="https://github.com/user-attachments/assets/277e861c-c36b-4ed4-a40f-e94846b59896" controls muted playsinline width="100%"></video>

### Main Menu
When the application starts, it shows a list of available design patterns.

Each pattern acts like its own chapter. It is focused, isolated, and easy to return to whenever I want to refresh my understanding.

### Pattern Submenu
After selecting a pattern, I enter a submenu where I can explore example implementations or generate a new exercise for that specific pattern.

This keeps learning and practice organized, while still making it easy to jump between patterns.

### Exercise Generation
When I generate an exercise, the project runs a simple study loop using multiple agents:

* Instruction Agent: defines the problem, learning goals, and constraints.
* Template Agent: generates the starter code, folder structure, and TODO markers (depending on difficulty).
* Evaluation Agent: reviews my solution, a score based on correctness, and gives feedback on what could be improved.

I can repeat this loop as many times as I want for any pattern until it actually sticks.

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

Once it starts, the main menu will appear. From there, you can:
* choose a design pattern
* view example implementations
* generate an exercise
* select a difficulty level
* implement the solution
* ask the agent for hints
* submit your work for evaluation

Generated exercises are placed in:
```
src/main/java/io/github/ndys/patto/workspace/
```

## Project Structure
```
patto/
└── src/main/java/io/github/ndys/patto/
    ├── workspace/       # Generated exercises (temporary workspace)
    │   └── README.md
    ├── patterns/        # Pattern menus, examples, and metadata
    └── ...              # Other project directories and files
```

## Contributing
Although this project began as a personal learning tool, contributions are welcome.

If you have ideas for better exercise flows, new patterns to add, improved feedback, or alternative learning approaches, feel free to open an issue or submit a pull request.

## License
MIT
