package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB
{

    private Hashtable<String, SoccerPlayer> hashtable1 = new Hashtable<String, SoccerPlayer>();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName)
    {
        SoccerPlayer temp_player = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
        String key_1 = firstName + "##" + lastName;

        if(hashtable1.containsKey(key_1))
        {
            return false;
        }
        else
        {
            hashtable1.put(key_1, temp_player);
            return true;
        }

	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName)
    {
        String key3 = firstName + "##" + lastName;
        if(hashtable1.containsKey(key3))
        {
            hashtable1.remove(key3);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName)
    {
        String key_2 = firstName + "##" + lastName;
        if (hashtable1.containsKey(key_2))
        {
            return hashtable1.get(key_2);
        }
        else
        {
            return null;
        }
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName)
    {
        String key3 = firstName+"##"+lastName;
        SoccerPlayer player1;
//        if(getPlayer(firstName, lastName) != null)
        if(hashtable1.containsKey(key3))
        {
//            player1 = getPlayer(firstName, lastName);
            player1 = hashtable1.get(key3);
            player1.bumpGoals();
            hashtable1.put(key3, player1);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName)
    {
        String key4 = firstName + "##" + lastName;
        if(hashtable1.containsKey(key4))
        {
            SoccerPlayer temp = hashtable1.get(key4);
            temp.bumpAssists();
            hashtable1.put(key4,temp);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName)
    {
        String key5 = firstName + "##" + lastName;
        if(hashtable1.containsKey(key5))
        {
            SoccerPlayer temp = hashtable1.get(key5);
            temp.bumpShots();
            hashtable1.put(key5,temp);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName)
    {
        String key6 = firstName+"##"+lastName;
        SoccerPlayer player3;
        if(hashtable1.containsKey(key6))
        {
            player3 = hashtable1.get(key6);
            player3.bumpSaves();
            hashtable1.put(key6, player3);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName)
    {
        String key6 = firstName + "##" + lastName;
        if(hashtable1.containsKey(key6))
        {
            SoccerPlayer temp = hashtable1.get(key6);
            temp.bumpFouls();
            hashtable1.put(key6,temp);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName)
    {
        String key7 = firstName + "##" + lastName;
        if(hashtable1.containsKey(key7))
        {
            SoccerPlayer temp = hashtable1.get(key7);
            temp.bumpYellowCards();
            hashtable1.put(key7,temp);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName)
    {
        String key8 = firstName+"##"+lastName;
        SoccerPlayer player4 = hashtable1.get(key8);
        if(hashtable1.containsKey(key8))
        {
            player4.bumpRedCards();
            hashtable1.put(key8, player4);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName)
    {
        int playerCount = 0;
        Set<String> keys = hashtable1.keySet();
        if(teamName == null)
        {
            return hashtable1.size();
        }
        else
        {
            for(String key: keys)
            {
                SoccerPlayer temp = hashtable1.get(key);
                if(temp.getTeamName().equals(teamName))
                {
                    playerCount++;
                }
            }
            return playerCount;
        }
	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName)
    {
        if(idx >= hashtable1.size())
        {
            return null;
        }
        else if (teamName == null)
        {
            Set<String> keys = hashtable1.keySet();
            List<SoccerPlayer> noTeam = new ArrayList<SoccerPlayer>();
            for(String key: keys)
            {
                SoccerPlayer temp = hashtable1.get(key);
                noTeam.add(temp);

            }
            return noTeam.get(idx);
        }
        else
        {
            Set<String> keys = hashtable1.keySet();
            List<SoccerPlayer> team = new ArrayList<SoccerPlayer>();
            for(String key: keys)
            {
                SoccerPlayer temp = hashtable1.get(key);
                if(temp.getTeamName().equals(teamName))
                {
                    team.add(temp);
                }
            }
            return team.get(idx);
        }

    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        return file.exists();
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {
        return false;
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        return new HashSet<String>();
	}

}
