# Patto
> *"A hands-on workbook for practicing design patterns through terminal-based demos and AI exercise agents"*

Patto started as a frustration.

Every time I read about design patterns, things made sense briefly.
I could follow the diagrams, understand the terminology, and recognize the intent.
But a few weeks later, that clarity was gone.

I would forget, when a pattern is actually useful? how the code should look in practice? why the pattern matters in real systems?

Reading alone was not enough.
I needed repetition. I needed practice. I needed to build.

So I created this project.

Patto is my personal learning companion for design patterns. It helps me practice patterns the same way you practice math or music, by doing them repeatedly.

Instead of chapters, Patto gives me exercises.
Instead of long explanations, it gives focused TODOs.
And when I get stuck, I can ask questions and get guidance instead of staring at the screen.

## What is Patto?
Patto is a terminal-based application that generates small, hands-on coding exercises for design patterns.

I think of it as an interactive workbook:
* each design pattern is a chapter
* each exercise is a problem to solve
* each solution is reviewed so I can see what I missed

To support this loop, Patto uses several small AI agents powered by the free [Gemini](https://gemini.google.com) model.

## How Patto Works?
### Main Menu
When Patto starts, it shows a list of available design patterns.

Each pattern acts like its own chapter. It is isolated, focused, and easy to revisit at any time.

### Pattern Submenu
After selecting a pattern, I enter a dedicated submenu where I can view example implementations and generate a new exercise for that pattern.

This keeps exploration and practice organized while still making it easy to jump between them.

### Exercise Generation
When I generate an exercise, Patto runs a small study loop using multiple agents:

* Instruction Agent: Defines the problem, learning goals, and constraints.
* Template Agent: Creates the starter code, folder structure, and TODO markers.
* Evaluation Agent: Reviews my completed solution and provides feedback.

I can repeat this loop as many times as I want for any pattern until the ideas actually stick.

## Installation
```bash
git clone https://github.com/ndy-s/patto
cd patto
mvn clean install
```

Before running Patto, copy the example environment file:
```bash
cp .env.example .env
```

Open `.env` and add your Gemini API key:
```env
GEMINI_API_KEY=your_key_here
```

You can get a free API key from Google AI Studio.

## Running Patto
```bash
mvn exec:java
```

Once it starts, the main menu will appear.

From there, I can:
* choose a design pattern
* view examples
* generate a new exercise
* work through the TODOs
* ask the Agent for help
* submit my solution for review

Generated exercises live here:
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
I did not build Patto to teach others from the start.
I built it because I needed a better way to learn.

Design patterns only became meaningful to me once I started implementing them repeatedly and seeing where they help, where they do not, and how small design decisions compound over time.

## Contributing
Although Patto began as a personal learning assistant, contributions are welcome.

If you have ideas for better exercise workflows, new patterns, improved feedback loops, alternative learning approaches, feel free to open an issue or submit a pull request.


## License
MIT
