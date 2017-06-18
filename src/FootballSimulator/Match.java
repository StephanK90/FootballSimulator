/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FootballSimulator;

import java.util.Random;

/**
 *
 * @author Stephan
 */
public class Match {

    private final Team t1; // team 1
    private final Team t2; // team 2
    private int goalsT1; // goals scored by team 1
    private int goalsT2; // goals scored by team 2

    // constructor
    public Match(Team c1, Team c2) {
        this.t1 = c1;
        this.t2 = c2;
    }

    // returns team 1
    public Team getTeam1() {
        return this.t1;
    }

    // returns team 2
    public Team getTeam2() {
        return this.t2;
    }

    // returns goals of team 1
    public int getGoalsT1() {
        return this.goalsT1;
    }

    // returns goals of team 2
    public int getGoalsT2() {
        return this.goalsT2;
    }

    // simulates the match
    public void playMatch() {

        // calculate win chance of team 1
        double totalScore = this.t1.getScore() + this.t2.getScore();
        double scoreT1 = this.t1.getScore();
        double winChanceT1 = 100 * (scoreT1 / totalScore);

        // generate random number between 0-100
        Random rand = new Random();
        double number = rand.nextDouble() * 100;

        // chance on a tie (is taken from both teams win chance) so actually 20% chance on tie
        double chanceOnTie = 10;

        // if generated number < win chance of team 1 - 10%, team 1 is winner
        if (number <= winChanceT1 - chanceOnTie) {
            this.goalsT1 = rand.nextInt(5) + 1;             // generate goals made by winning team
            this.goalsT2 = rand.nextInt(this.goalsT1) - 1;  // generate goals made by losing team (atleast 1 goal less than winning team)
            // set losing team goals to 0 when < 0
            if (this.goalsT2 < 0) {
                this.goalsT2 = 0;
            }
            isWinner(this.t1, this.goalsT1, this.goalsT2);
            isLoser(this.t2, this.goalsT2, this.goalsT1);
        } 
        // if number > win chance of winning team + 10%, team 2 is winner
        else if (number > winChanceT1 + chanceOnTie) {
            this.goalsT2 = rand.nextInt(5) + 1;             // generate goals made by winning team
            this.goalsT1 = rand.nextInt(this.goalsT2) - 1;  // generate goals made by losing team (atleast 1 goal less than winning team)
            // set losing team goals to 0 when < 0
            if (this.goalsT1 < 0) {
                this.goalsT1 = 0;
            }
            isWinner(this.t2, this.goalsT2, this.goalsT1);
            isLoser(this.t1, this.goalsT1, this.goalsT2);
        } 
        // else its a tie, goals must be even
        else {
            this.goalsT1 = rand.nextInt(4);
            this.goalsT2 = this.goalsT1;
            isTie();
        }
    }

    // adds stats to winning team
    private void isWinner(Team t, int goals, int goalsAgainst) {
        t.addWin();
        t.addPlayed();
        t.addGoals(goals);
        t.addGoalsAgainst(goalsAgainst);
    }

    // adds stats to losing team
    private void isLoser(Team t, int goals, int goalsAgainst) {
        t.addLost();
        t.addPlayed();
        t.addGoals(goals);
        t.addGoalsAgainst(goalsAgainst);
    }

    // adds stats to both teams when result is a tie
    private void isTie() {
        this.t1.addPlayed();
        this.t1.addTie();
        this.t1.addGoals(this.goalsT1);
        this.t1.addGoalsAgainst(this.goalsT2);

        this.t2.addPlayed();
        this.t2.addTie();
        this.t2.addGoals(this.goalsT2);
        this.t2.addGoalsAgainst(this.goalsT1);
    }
}
