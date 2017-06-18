/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FootballSimulator;

/**
 *
 * @author Stephan
 */
public class Team {

    private final String name; // name of the team
    private final int score; // average score of the team
    private int played; // nr of matches played
    private int goals; // nr of goals
    private int goalsAgainst; // nr of goals against
    private int points; // nr of points
    private int wins; // nr of wins
    private int lost; // nr of losses
    private int ties; // nr of ties

    // constructor
    public Team(String name, int score) {

        this.name = name; // set name of the team
        this.score = score; // set score of the team
    }

    // returns the team
    public String getName() {
        return this.name;
    }

    // returns the score
    public int getScore() {
        return this.score;
    }

    // add a match played
    public void addPlayed() {
        this.played++;
    }

    // returns nr of played matches
    public int getPlayed() {
        return this.played;
    }

    // add number of goals made
    public void addGoals(int g) {
        this.goals = this.goals + g;
    }

    // returns nr of goals
    public int getGoals() {
        return this.goals;
    }

    // add goals against
    public void addGoalsAgainst(int g) {
        this.goalsAgainst = this.goalsAgainst + g;
    }

    // returns nr of goals against
    public int getGoalsAgainst() {
        return this.goalsAgainst;
    }

    // returns the goal difference
    public int getGoalDifference() {
        return this.goals - this.goalsAgainst;
    }

    // return points
    public int getPoints() {
        return this.points;
    }

    // adds a win with given points
    public void addWin() {
        this.wins++;
        this.points = this.points + 3;
    }

    // returns nr of won matches
    public int getWins() {
        return this.wins;
    }

    // adds a loss
    public void addLost() {
        this.lost++;
    }

    // returns nr of lost matches
    public int getLost() {
        return this.lost;
    }

    // add a tie with given point
    public void addTie() {
        this.ties++;
        this.points++;
    }

    // returns the nr of tied matches
    public int getTies() {
        return this.ties;
    }
}
