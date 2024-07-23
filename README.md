# sportradar
Sportradar Coding Exercise

# How to use Score Board
1. Initialize ScoreBoard with constructor providing persistence adapter.
2. Add already started match using addMatch method.
3. Change score using updateMatchScore method.
4. Finish match to remove it from Score Board.
5. You can get all matches sorted or print them using ScoreBoardPrinter.

# My thoughts about internal application design.
* I've created Match as entity. In real scenarion it would have some kind of identifier but it was not needed to fulfill this code exercise requirements.
* In my opinion ScoreBoard should not be responsible for updating matches scores. This should happen in context of match service. ScoreBoard class should only display list of matches according to provided rules.
