package yahnenko.ua.iceandfire.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dront on 21.07.2017.
 */

public class ByName implements Serializable {
    public String url;
    public String name;
    public String gender;
    public String culture;
    public String born;
    public String died;
    public List<String> titles;
    public List<String> aliases;
    public String father;
    public String mother;
    public String spouse;
    public List<String> allegiances;
    public List<String> books;
    public List<String> povBooks;
    public List<String> tvSeries;
    public List<String> playedBy;
}
