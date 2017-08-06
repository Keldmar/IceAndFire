package yahnenko.ua.iceandfire.response;


import java.io.Serializable;
import java.util.List;

public class ByBooks implements Serializable{
    public String url;
    public String name;
    public String isbn;
    public List<String> authors;
    public Integer numberOfPages;
    public String publisher;
    public String country;
    public String mediaType;
    public String released;
    public List<String> characters;
    public List<String> povCharacters;
}
