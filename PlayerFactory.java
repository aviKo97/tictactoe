/**
 * The Player Factory, make the player we want
 * We have 4 types of players, human, whatever,clever,genius
 */
public class PlayerFactory {

    //#################Public methods###############

    /**
     * Builds a default constructor of a factory
     */
    PlayerFactory(){

    }

    /**
     * build the player we want
     * @param type - type of player
     * @return the player
     */
    public Player buildPlayer(String type)
    {
        if (type == null){
            return null;
        }
        Player player = null;
        type = type.toLowerCase();
        switch (type)
        {
            case "human":
                player = new HumanPlayer();
                break;
            case "whatever":
                player = new WhateverPlayer();
                break;
            case "clever":
                player = new CleverPlayer();
                break;
            case "genius":
                player = new GeniusPlayer();
                break;
            default:
                player = null;
        }
        return player;
    }
}
