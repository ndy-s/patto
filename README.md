# Patto
"*A hands-on workbook for practicing design patterns through terminal-based demos and AI exercise agents*"

This project started out of frustration.

Whenever I read about design patterns, things made sense at first. I could follow the diagrams, understand the terminology, and recognize what each pattern was trying to solve. But a few weeks later, that understanding faded.

I would forget when a pattern is actually useful, what the code looks like in a real project, and why the pattern even matters.

Reading was not enough.
I needed repetition. I needed practice. I needed to actually build things.

So I created this project.

This project is my personal learning companion for design patterns. It helps me learn patterns the same way you learn math or music, by practicing them over and over.

Instead of chapters, it gives me exercises.
Instead of long explanations, it gives me clear TODOs.
And when I get stuck, I can ask questions and get guidance instead of staring at the screen.

## What is this project?
This project is a terminal-based Java application that generates small, hands-on coding exercises for design patterns. Java's object-oriented nature makes classic patterns explicit and easy to reason about, which makes it a great language for practicing them.

I think of it as an interactive workbook:
* each design pattern is a chapter
* each exercise is a problem to solve
* each solution is reviewed so I can see what I missed or misunderstood

To support this learning loop, this project uses several small AI agents powered by the free [Gemini](https://aistudio.google.com) model.

## How it works?

### Main Menu
When the application starts, it shows a list of available design patterns.

Each pattern acts like its own chapter. It is focused, isolated, and easy to return to whenever I want to refresh my understanding.

### Pattern Submenu
After selecting a pattern, I enter a submenu where I can explore example implementations or generate a new exercise for that specific pattern.

This keeps learning and practice organized, while still making it easy to jump between patterns.

### Exercise Generation
When I generate an exercise, the project runs a simple study loop using multiple agents:

* **Instruction Agent**: defines the problem, learning goals, and constraints.
* **Template Agent**: generates the starter code, folder structure, and TODO markers.
* **Evaluation Agent**: reviews my solution and gives feedback on what could be improved.

I can repeat this loop as many times as I want for any pattern until it actually sticks.

## Installation
```bash
git clone https://github.com/ndy-s/patto
cd patto
mvn clean install
```

Before running the project, copy the example environment file:
```bash
cp .env.example .env
```

Open `.env` and add your Gemini API key:
```env
GEMINI_API_KEY=your_key_here
```

You can get a free API key from Google AI Studio.

## Running
```bash
mvn exec:java
```

Once it starts, the main menu will appear.

From there, I can:
* choose a design pattern
* view example implementations
* generate a new exercise
* work through the TODOs
* ask the agent for help
* submit my solution for review

Generated exercises are placed here:
```
src/main/java/io/github/ndys/patto/exercise/
```

## Project Structure
```
patto/
└── src/main/java/io/github/ndys/patto/
    ├── exercise/        Auto-generated design pattern exercises
    │   └── README.md
    └── patterns/        Pattern menus, examples, and metadata
```

## Why I Built This?
I did not build this project to teach others at first.
I built it because I needed a better way to learn.

Design patterns only started to make sense once I implemented them repeatedly and saw where they helped, where they added complexity, and how small design decisions add up over time.

## Contributing
Although this project began as a personal learning tool, contributions are welcome.

If you have ideas for better exercise flows, new patterns to add, improved feedback, or alternative learning approaches, feel free to open an issue or submit a pull request.

## License
MIT

