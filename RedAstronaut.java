import java.util.Arrays;

public class RedAstronaut extends Player implements Impostor{
    private final String skill;

    public RedAstronaut(String name, int susLevel, String skill) {
        super(name, susLevel);
        this.skill = skill.toLowerCase();
    }

    public RedAstronaut(String name) {
        super(name, 15);
        this.skill = "experienced";
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

    public void freeze(Player p) {
        if (isFrozen() || p.isFrozen() || p instanceof Impostor) {
            return;
        } else {
            if (getSusLevel() < p.getSusLevel()) {
                p.setFrozen(true);
            } else {
                int newSusLevel = getSusLevel()*2;
                setSusLevel(newSusLevel);
            }
        }
        gameOver();
    }
    
    public void sabotage(Player p) {
        int newSusLevel = p.getSusLevel();
        if (isFrozen() || p.isFrozen() || p instanceof Impostor) {
            return;
        } else {
            if (getSusLevel() < 20) {
                newSusLevel = (int)(newSusLevel*1.5);
                p.setSusLevel(newSusLevel);
            } else {
                newSusLevel = (int)(newSusLevel*1.25);

                p.setSusLevel(newSusLevel);
            }
        }
    }
    
    public boolean equals(Object o) {
        if (o instanceof RedAstronaut) {
            RedAstronaut ra = (RedAstronaut)o;
            if (this.equals(o) && ra.skill == this.skill) {
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

        String output = "My name is " + super.getName() + ", and I have a suslevel of "
         + String.valueOf(getSusLevel()) + ". I am currently " + isfrozen + ". I am an " + this.skill + " player!";

        if (getSusLevel() > 15) {
            return output.toUpperCase();
        } else {
            return output;
        }

    }
    
    /*public static void main(String[] args) {
        System.out.println(players);
    }*/

}
