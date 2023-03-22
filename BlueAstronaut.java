import java.util.Arrays;

public class BlueAstronaut extends Player implements Crewmate{
    private int numTasks;
    private int taskSpeed;


    public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
        super(name, susLevel);
        this.numTasks = numTasks;
        this.taskSpeed = taskSpeed;
    }
    public BlueAstronaut(String name) {
        super(name, 15);
        this.numTasks = 6;
        this.taskSpeed = 10;
    }

    public void emergencyMeeting() {
        if (isFrozen()) {
            return;
        }
        Player[] players = getPlayers();
        Arrays.sort(players);

        for (int i = players.length - 1; i >= 0; i--) {
            if (players[i].isFrozen() || players[i].getName() == super.getName()) {
                continue;
            } else {
                int highestSusLevel = players[i].getSusLevel(); 
                int secondHighest =  players[i - 1].getSusLevel(); 
                if (highestSusLevel == secondHighest) {
                    return;
                } else {
                    players[i].setFrozen(true);
                }
            }
            
        }
        gameOver();
    }

    public void completeTask() {
        if (isFrozen() || numTasks <= 0) {
            return;
        }
        if (taskSpeed > 20) {
            numTasks -= 2;
        } else {
            numTasks -= 1;
        }
        if (numTasks <= 0) {
            numTasks = 0;
            System.out.println("I have completed all my tasks");
            int newSus = (int)(getSusLevel()*0.5);
            super.setSusLevel(newSus);
        }
    }

    public boolean equals(Object o) {
        if (o instanceof BlueAstronaut) {
            BlueAstronaut ba = (BlueAstronaut)o;
            if (this.equals(o) && ba.numTasks == numTasks && ba.taskSpeed == taskSpeed) {
            return true;
            }
        }
        return false;
    }

    public String toString() {
        String isfrozen;
        if (isFrozen()) {
            isfrozen = "frozen";
        } else {
            isfrozen = "not frozen";
        }

        String output = "My name is " + getName() + ", and I have a suslevel of "
         + String.valueOf(getSusLevel()) + ". I am currently " + isfrozen + ". I have " + numTasks + " left over";

        if (getSusLevel() > 15) {
            return output.toUpperCase();
        } else {
            return output;
        }

    }



}
