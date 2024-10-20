# USE CASE: 23. Report showing the population distribution in a continent

## CHARACTERISTIC INFORMATION

### Goal in Context
As a User, I want to be able to view different population distribution reports, so that I can easily see how the population is distributed between living in cities and not living in cities within the specified area.


### Scope
The Population Information System


### Level
Primary Task


### Preconditions
User has access to the application.

Database has upto date population data.


### Success End Condition
Report is produced displaying population data to the user.


### Failed End Condition
No report is produced.


### Primary Actor
User


### Trigger
User prompts application for population distribution in a user specified continent.


## MAIN SUCCESS SCENARIO
1. User prompts application for population data.
2. Data is returned to user in the form of a report from the highest population to smallest.


## EXTENSIONS
#### 2. Database doesn't contain population data.
Application returns error message to user informing them that the population data is not available.


## SUB-VARIATIONS
None


## SCHEDULE

**DUE DATE**: Release 1.0
