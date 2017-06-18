/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FootballSimulator;

import java.util.ArrayList;

/**
 *
 * @author Stephan
 */
public class Group {

    private final ArrayList<Team> teams; // list of teams in the poule
    private final ArrayList<Round> rounds; // list of rounds
    private Ranking ranking; // holds the ranking scheme
    private int roundNumber; // current round to be played
    
    // constructor
    public Group() {
        this.teams = new ArrayList();
        this.rounds = new ArrayList();
        this.roundNumber = 1;
        
        // add teams to the poule
        this.teams.add(new Team("Netherlands", 600));
        this.teams.add(new Team("Spain", 900));
        this.teams.add(new Team("Australia", 400));
        this.teams.add(new Team("Chile", 600));
    }

    // add a country to the poule
    public void addTeam(String name, int score) {
        Team t = new Team(name, score);
        this.teams.add(t);
    }

    // returns a list of teams
    public ArrayList<Team> getTeams() {
        return this.teams;
    }

    // returns a list with rounds
    public ArrayList<Round> getRounds() {
        return this.rounds;
    }

    // returns current round nr
    public int getRoundNumber() {
        return this.roundNumber;
    }

    // set current round nr
    public void setRoundNumber(int n) {
        if (n > this.rounds.size()) { // never go higher than max rounds
            n = this.rounds.size();
        }
        this.roundNumber = n;
    }

    // create all matches to be played in this poule
    public void createMatches() {
        int numberOfTeams = this.teams.size();                  // nr of teams in this poule
        int numberOfRounds = numberOfTeams - 1;                 // nr of rounds to be played in this poule
        ArrayList<Team> teamsCopy = new ArrayList(this.teams);  // make a copy of the teams list
        teamsCopy.remove(0);                                    // remove team 1 from the copy list
        int teamsToPlay = teamsCopy.size();                     // nr of teams to cycle through

        for (int round = 0; round < numberOfRounds; round++) {  // for every round   
            this.rounds.add(new Round());                       // add the round
            int indexOpponent = round % teamsToPlay;            // gets the indexnr of the opponent
    
            Team t1 = this.teams.get(0);                                // team 1
            Team opponent = teamsCopy.get(indexOpponent);               // opponent of team 1
            this.rounds.get(round).addMatch(new Match(t1, opponent));   // add match to current round  

            for (int i = 1; i < numberOfTeams / 2; i++) {               // loop through remaining teams
                int team3 = (round + i) % teamsToPlay;                  // gets index of next team to match up
                int team4 = (round + teamsToPlay - i) % teamsToPlay;    // gets index of next opponent
                Team t3 = teamsCopy.get(team3);
                Team t4 = teamsCopy.get(team4);
                this.rounds.get(round).addMatch(new Match(t3, t4));     // add match to current round
            }
        }
    }

    // creates the the ranking
    public void createRanking() {
        this.ranking = new Ranking(this);
    }

    // returns the ranking
    public Ranking getRanking() {
        return ranking;
    } 
}
