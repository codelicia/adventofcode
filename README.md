# Advent of Code
[Advent of Code](https://adventofcode.com/) is an [Advent calendar](https://en.wikipedia.org/wiki/Advent_calendar) of small programming puzzles for a variety of skill sets and skill levels that can be solved in any programming language.

In this repository we will play around with different languages and use the PRs to review our solutions.

## Project structure

We have a separation per year on the root followed by language and input files:

```
2021
 - input
   - day1.txt
   - day2.txt
 - php
   - Makefile
 - kotlin
   - Makefile
 - python
   - Makefile
```

On the Makefile we expect at least `build` and `test` tasks that will be called by CI.
