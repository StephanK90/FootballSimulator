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
public class Round {

    private final ArrayList<Match> matches; // list of matches in this round
    private Boolean played; // true if round is finished

    public Round() {
        this.matches = new ArrayList();
        this.played = false; // set default false
    }

    // add new match to a round
    public void addMatch(Match m) {
        this.matches.add(m);
    }

    // returns list of matches in this round
    public ArrayList<Match> getMatches() {
        return this.matches;
    }

    // sets round as played
    public void isPlayed() {
        this.played = true;
    }

    // returns status of the round (true if finished, false if not)
    public Boolean getPlayed() {
        return this.played;
    }
}
