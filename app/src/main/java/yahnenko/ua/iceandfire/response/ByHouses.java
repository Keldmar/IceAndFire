package yahnenko.ua.iceandfire.response;

import java.io.Serializable;
import java.util.List;


public class ByHouses implements Serializable {
    public String url;
    public String name;
    public String region;
    public String coatOfArms;
    public String words;
    public List<String> titles;
    public List<String> seats;
    public String currentLord;
    public String heir;
    public String overlord;
    public String founded;
    public String founder;
    public String diedOut;
    public List<String> ancestralWeapons;
    public List<String> cadetBranches;
    public List<String> swornMembers;
}
