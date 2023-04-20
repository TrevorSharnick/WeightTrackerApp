# WeightTrackerApp
Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?

- WeightTracker is an application designed to help users track their daily weight and monitor their progress towards their goal weight. The application is designed to be user-friendly and easy to navigate through its components. 

What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?

- In order to support a large userbase, the user must first create an account using a username and password, which they can then use to log into the system via authentication. Once authenticated, the main activity of the application involves a list of daily weights and three options to manage different aspects of the application including adding, editing and removing daily weights. When adding a new weight, the app automatically generates a date reflecting when the new entry was submitted into the database. Additionally, the user can press the "star" icon to set a goal weight for themselves. The settings icon upons a new view in which the user can set SMS notification preferences, which are used to notify the user when they have successfully reached their goal weight.  

How did you approach the process of coding your app? What techniques or strategies did you use? How could those be applied in the future?

- During the process of coding my application, I researched different design techniques. Given the list of weights displayed and the functionality that would need to be implemented for each weight object in the list, I decided to use a RecyclerView paired with fragments for many of the views present in my project. While fragmenting isn't applicable in every situation (and I found it quite challenging to implement in the final project), I felt it was necessary to implement fragments into this project and I do believe it will become a regular part of my approach when developing applications because it will ensure that the application can be responsive and work across many different devices.


How did you test to ensure your code was functional? Why is this process important and what did it reveal?

- When testing to ensure my code was functional, I often included Log.d statements after running important lines to code to ensure they were behaving correctly. For example, when debugging I would refer back to the Log.d statements to verify the correct behavior of the login authentication features. 

Considering the full app design and development process, from initial planning to finalization, where did you have to innovate to overcome a challenge?

- My main takeaway from building WeightTracker is realizing how developing software from scratch is an exhaustive and meticulous process of trial and error. Also, I learned that understanding ZyBooks content and applying it to a real-world application are two very different processes. When I review the initial roadmap I created for developing the different components in the application, the actual implementation of these components were completed on a very different timetable than I had planned. When new features were added, other features needed to be refactored due to the complexity associated with managing two databases. When I first began developing the application, I started with the WeightListFragment because I expected it to be the main activity of my application. In hindsight, this made developing the login authentication process very difficult to integrate into my existing codebase. That being said, when problems arose, I gradually become more effective at getting to the root of the issues by logging correct behavior and adding breakpoints throughout different components of the codebase so I was able to filter out correct behavior from incorrect behavior. 


In what specific component from your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?

- While this activity may have been possible without using fragments, I'm glad I implemented them in this project. Although it was difficult at times, applying the lessons from the course material in a real-world application helped me learn to use fragments effectively and helped me keep my processes organized as I decided how to implement them. 
