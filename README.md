# DynamicsProject
This project was for my Analytical Mechanics: Dynamics class. We were tasked with exploring a dynamic system that we didn't cover in depth in class. I choose pendulums for my topic as they are a great example of a chaotic system which is one where the initial conditions have a drastic and unpredictable affect on the result. In this example, small changes in the initial angles of each pendulum arm will result in a very large difference in the position of the pendulum at a later time. This program models multiple pendulums at once and offsets each pendulum. As time progresses, the user can observe how the position of each pendulum changes with respect to the other.

How to Run:

When the main is called, the user is prompted to input a number. The options are printed in the terminal.

1. Wave Pendulum - where multiple uncoupled pendulums of varying lengths can create amazing patterns when placed together.
2. Single Pendulum - a dynamic structure consisting of an arm and mass located at the end of the arm.
3. Double Pendulum - 2 single pendulums coupled together which creates chaotic motion.
4. Triple Pendulum - 3 single pendulums coupled together which creates the ultimate representation of chaos theory.
5. All in One - the final product, combines the single, double, and triple pendulums to one application. 

Depending on which number the user chooses, the user may be prompted for a number of pendulums and an offset value. In addition, when entering "5' expect a long pause, as it takes quite a bit to prepare all the calculations.

UI: 

The buttons allow the user to control what type of pendulum is active, the color, and the number of pendulums viewable. 

The Future?: 

I hope to further optimize the program, as it takes quite a bit to start when first opened. I also plan to allow the user to personally change many if not all of the variable that affect the chaotic motion of the pendulums. Examples include the timestep, initial angles, initial velocities, mass of each "mass", as well as the length of each arm. 
