## LDTS_1103 - ATARI AIRLOCK

This game will take you back to 1982 playing on your Atari 2600 console, helping your hero escape a crashed nuclear submarine which has begun taking on water. The hero must escape by making their way up through the submarine before the water reaches out. Each level of the submarine has coins and a key that unlocks the elevator to the next level.
The holes that opened in the crash allowed sea monsters to get in, so the hero needs to avoid them, otherwise, he will die.

This project was developed by Pedro Lunet (up202207150@up.pt), António Cunha (202208862@fe.up.pt) and Allan Ferreira (202109243@fe.up.pt) for LDTS 2023⁄24.

### IMPLEMENTED FEATURES

- **Mouse and Keyboard Movement** - The game character will move left and right and also jump when the respective arrow keys are pressed.
- **Getting coins** - When the game character goes over a coin, he collects it and his score is increased.
- **Getting keys** - Keys are waiting to be caught on each floor/level . The character needs to catch them, by going over them (just like the coins), to unlock the elevator to the next floor.
- **Multiple Levels** - As the hero progresses through each elevator , 4 levels with coins and new monsters awaits him .
- **Various Monster Types** - Monsters appear in each level , some capable of shooting bullets at the hero , others racing to get him .
- **Flooded Levels** - The player will have to get through each level quick enough to not get sunk with the submarine.
- **Collisions** - The game character suffers various consequences when it collides with bullets , monsters or the water that is sinking the submarine.


## Planned Features

All the planned features were successfully implemented.

### DESIGN

#### DRAWING THE SUBMARINE

**Pre Intermidiate Delivery Problems**

When first designing the submarine structure, we were faced with 2 problems: what characters to use and, if using more than one character, how would we do that?

**The Pattern**

The first solution was to have 2 different draw methods in the Arena class and do all the operations in it. However, when trying to implement new features like collisions, we found out it wasn't a great idea. The final solution was to restructure the way we were building the submarine and implement 2 new classes: Floor and Wall. this allowed us to build the submarine in a more controlled way, having separate methods and classes for walls and floors.

**Consequences**
After implementing these changes:

- It is now easier to code the collisions between the main character and the walls, floors and monsters.
- The jump works smoother.
- The Arena class looks cleaner since the draw methods were moved to the respective classes.
- There are now 2 more classes to manage.



**Post Intermidiate Delivery Problems**

After the intermidiate delivery , we were faced with some more problems as we aspired to add complexity to the game : How could we represent various types of monsters and how could we make a running elevator

**The Pattern**
Our solution to this was to use the "Command" design pattern where we call an "activation" function in the arena class to perform the elevator methods. As for the monster types we created an abstract class called 'Monster' with an action function that we Override to each new monster type .

**IMPLEMENTATION**
![umlldts](https://github.com/FEUP-LDTS-2023/project-l11gr03/assets/142496110/db4e2837-a795-450b-942b-597860b785c7)

#### KNOWN CODE SMELLS

We did not ran error prone.

### TEST COVERAGE

<img width="461" alt="Screenshot 2023-12-23 at 23 15 44" src="https://github.com/FEUP-LDTS-2023/project-l11gr03/assets/142496110/520c5be3-25e7-43a5-9a4a-10dd0b150d34">

###PIT TEST###

We couldn't run the PITest for our project.

### SELF-EVALUATION

- Pedro Lunet: 33%
- António Cunha: 33%
- Allan Ferreira: 33%




