/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FootballSimulator;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Stephan
 */
public class Ranking {

    private final String[] columnNames = {"Team", "GP", "W", "T", "L", "P", "GF", "GA", "GD"}; // column names of the scheme
    private final Object[][] data; // 2d array for ranking data

    // constructor
    public Ranking(Group grp) {

        // create new 2D array
        this.data = new Object[grp.getTeams().size()][this.columnNames.length];

        // fill the ranking data
        for (int i = 0; i < grp.getTeams().size(); i++) {
            data[i][0] = grp.getTeams().get(i).getName();
            data[i][1] = grp.getTeams().get(i).getPlayed();
            data[i][2] = grp.getTeams().get(i).getWins();
            data[i][3] = grp.getTeams().get(i).getTies();
            data[i][4] = grp.getTeams().get(i).getLost();
            data[i][5] = grp.getTeams().get(i).getPoints();
            data[i][6] = grp.getTeams().get(i).getGoals();
            data[i][7] = grp.getTeams().get(i).getGoalsAgainst();
            data[i][8] = grp.getTeams().get(i).getGoalDifference();
        }
        // sort the ranking from best to worst
        sortRanking.sort(data, grp);
    }

    // returns the column names
    public String[] getColumnNames() {
        return columnNames;
    }

    // returns the ranking data
    public Object[][] getData() {
        return data;
    }
}

// inner class to sort the ranking
class sortRanking {

    // sort method
    public static Object[][] sort(Object[][] array, Group grp) {
        Arrays.sort(array, new Comparator<Object[]>() {

            @Override
            public int compare(Object[] t1, Object[] t2) {
                int value; // holds value to return (-1 = t1 better, 1 = t2 better, 0 t1 and t2 are equal)

                // compare the points
                int pointsIdx = 5;
                Integer pointsTeam1 = Integer.parseInt(t1[pointsIdx].toString());
                Integer pointsTeam2 = Integer.parseInt(t2[pointsIdx].toString());
                value = compareInt(pointsTeam1, pointsTeam2);

                // compare goalDiff if points are equal
                if (value == 0) {
                    int goalDiffIdx = 8;
                    Integer goalDiffTeam1 = Integer.parseInt(t1[goalDiffIdx].toString());
                    Integer goalDiffTeam2 = Integer.parseInt(t2[goalDiffIdx].toString());
                    value = compareInt(goalDiffTeam1, goalDiffTeam2);

                    // compare goals if goalDiff is equal
                    if (value == 0) {
                        int goalsIdx = 6;
                        Integer goalsTeam1 = Integer.parseInt(t1[goalsIdx].toString());
                        Integer goalsTeam2 = Integer.parseInt(t2[goalsIdx].toString());
                        value = compareInt(goalsTeam1, goalsTeam2);

                        // compare goalsAgainst if goals are equal
                        if (value == 0) {
                            int goalsAgainstIdx = 7;
                            Integer goalsAgainstTeam1 = Integer.parseInt(t1[goalsAgainstIdx].toString());
                            Integer goalsAgainstTeam2 = Integer.parseInt(t2[goalsAgainstIdx].toString());
                            value = compareInt(goalsAgainstTeam1, goalsAgainstTeam2);

                            // check match result if everything before is equal
                            if (value == 0) {
                                int nameIdx = 0;
                                value = checkMatchResultsOnTie(grp, t1[nameIdx].toString(), t2[nameIdx].toString());
                            }
                        }
                    }
                }
                return value;
            }
        });
        return array;
    }

    // method to compare 2 ints
    private static int compareInt(int x, int y) {
        if (x > y) {
            return -1;
        } else if (x < y) {
            return 1;
        } else {
            return 0;
        }
    }

    // checks the match result between 2 teams
    private static int checkMatchResultsOnTie(Group grp, String t1, String t2) {
        int numberOfRounds = grp.getRounds().size();
        int result = -1;

        for (int i = 0; i < numberOfRounds; i++) {
            int numberOfMatches = grp.getRounds().get(i).getMatches().size();

            for (int j = 0; j < numberOfMatches; j++) {
                Match m = grp.getRounds().get(i).getMatches().get(j);
                String team1 = m.getTeam1().getName();
                String team2 = m.getTeam2().getName();

                if ((team1.equalsIgnoreCase(t1) || team2.equalsIgnoreCase(t1)) && (team1.equalsIgnoreCase(t2) || team2.equalsIgnoreCase(t2))) {

                    if (m.getGoalsT1() > m.getGoalsT2()) {
                        result = -1;
                    } else if (m.getGoalsT1() < m.getGoalsT2()) {
                        result = 1;
                    } else {
                    }
                }
            }
        }
        return result;
    }
}
