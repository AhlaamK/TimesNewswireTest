# TimesNewswireTest
A simple android app to hit the NY Times Most Popular Articles API and show a list of articles, that shows details when items on the list are tapped (a typical master/detail app).
## Approach
1. Model–view–controller (MVC) architectural pattern
2. Retrofit API call
3. UI & functions tested using JUnit & espresso
## Additional Activities
### Steps to Run Project
1.  Clone the project from GitHub repository into android studio.
2.  Sync the gradle files using sync button.
3.  Goto BUild > Rebuild project, will build the project.
4.  On successfull build, Run the project on the emulator/ android device by Run >Run ( Or Alt + Shift + f10 )
### Steps to run UI(Espressso) and Junit Test
1. Goto app>src>androidTest>java>com>timesnewswiretest>view>MainActivityTest
2. Right Click on the MainactivityTest
3. Select Option 'Run' MainActivityTest
4. The Result will be generated in the terminal below 
### Steps for Code Analysis (Linting)
1. Goto Analyze in toolbar
2. Select Inspect code, A dialog box will appear.
3. Choose the 'whole project' option and click OK .
4. Generated Report will be displayed in the Inspection Results.
## Demo
![Farmers Market Finder Demo](demo/demo.gif)
