## LDTS_1103 - ATARI AIRLOCK

This game will take you back to 1982 playing on your Atari 2600 console, helping your hero escape a crashed nuclear submarine which has begun taking on water. The hero must escape by making their way up through the submarine before the water reaches out. Each level of the submarine has coins and a key that unlocks the elevator to the next level.
The holes that opened in the crash allowed sea monsters to get in, so the hero needs to avoid them, otherwise, he will die.

This project was developed by Pedro Lunet (up202207150@up.pt), António Cunha (202208862@fe.up.pt) and Allan Ferreira (202109243@fe.up.pt) for LDTS 2023⁄24.

### IMPLEMENTED FEATURES

- **Walking** - The game character will move left and right when the respective arrow keys are pressed.
- **Jumping** - The game character will jump when the arrow up key is pressed.
- **Getting coins** - When the game character goes over a coin, he collects it and his score is increased.
- **Getting keys** - Keys are waiting to be caught on each floor. The character needs to catch them, by going over them (just like the coins), to unlock the elevator to the next floor.
- **Collisions** - The game character dies when he touches monsters.

### PLANNED FEATURES

- **Different Monsters** - Implement different monsters, that should have different attack strategies.

### DESIGN

#### DRAWING THE SUBMARINE

**Problem in Context**

When first designing the submarine structure, we were faced with 2 problems: what characters to use and, if using more than one character, how would we do that?

**The Pattern**

The first solution was to have 2 different draw methods in the Arena class and do all the operations in it. However, when trying to implement new features like collisions, we found out it wasn't a great idea. The final solution was to restructure the way we were building the submarine and implement 2 new classes: Floor and Wall. this allowed us to build the submarine in a more controlled way, having separate methods and classes for walls and floors.

**Implementation**


**Consequences**

After implementing these changes:

- It is now easier to code the collisions between the main character and the walls, floors and monsters.
- The jump works smoother.
- The Arena class looks cleaner since the draw methods were moved to the respective classes.
- There are now 2 more classes to manage.

![diagram](https://github.com/FEUP-LDTS-2023/project-l11gr03/assets/118676706/49ec7532-834b-42e1-8502-fdbf0d70dbb2)

------

#### KNOWN CODE SMELLS

> This section should describe 3 to 5 different code smells that you have identified in your current implementation.

### TESTING

![image](https://github.com/FEUP-LDTS-2023/project-l11gr03/assets/118676706/9350540c-6ea6-40a2-ade0-1fab8410a5b6)

### SELF-EVALUATION

- Pedro Lunet: 33%
- António Cunha: 33%
- Allan Ferreira: 33%




