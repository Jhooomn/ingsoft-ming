package bo.ucb.edu.ingsoft.dto;

import bo.ucb.edu.ingsoft.models.Esrb;
import bo.ucb.edu.ingsoft.models.OperatingSystem;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameDetailsRequest {
    private Integer id;
    private String title;
    private ArrayList<String> language;
    private String game_description;
    private String size;
    private Esrb esrb;
    private List<String> images;
    private List<String> genres;
    private String developer;
    private Integer players;
    private Date releaseDate;
    private String processor;
    private String memory;
    private String graphics;
    private String color;
    private Integer highlighted;
    private String download_path;
    private Integer status;
    private List<OperatingSystem> operating_systems;
    private Double sale;
    private Double price;

    public GameDetailsRequest() {
    }

    public GameDetailsRequest(Integer id, String title, ArrayList<String> language, String game_description, String size, Esrb esrb, List<String> images, List<String> genres, String developer, Integer players, Date releaseDate, String processor, String memory, String graphics, String color, Integer highlighted, String download_path, Integer status, List<OperatingSystem> operating_systems, Double sale, Double price) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.game_description = game_description;
        this.size = size;
        this.esrb = esrb;
        this.images = images;
        this.genres = genres;
        this.developer = developer;
        this.players = players;
        this.releaseDate = releaseDate;
        this.processor = processor;
        this.memory = memory;
        this.graphics = graphics;
        this.color = color;
        this.highlighted = highlighted;
        this.download_path = download_path;
        this.status = status;
        this.operating_systems = operating_systems;
        this.sale = sale;
        this.price = price;
    }

    @Override
    public String toString() {
        return "GameDetailsRequest{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", language=" + language +
                ", game_description='" + game_description + '\'' +
                ", size='" + size + '\'' +
                ", esrb=" + esrb +
                ", images=" + images +
                ", genres=" + genres +
                ", developer='" + developer + '\'' +
                ", players=" + players +
                ", release_date=" + releaseDate +
                ", processor='" + processor + '\'' +
                ", memory='" + memory + '\'' +
                ", graphics='" + graphics + '\'' +
                ", color='" + color + '\'' +
                ", highlighted=" + highlighted +
                ", download_path='" + download_path + '\'' +
                ", status=" + status +
                ", operating_systems=" + operating_systems +
                ", sale=" + sale +
                ", price=" + price +
                '}';
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getLanguage() {
        return language;
    }

    public void setLanguage(ArrayList<String> language) {
        this.language = language;
    }

    public String getGame_description() {
        return game_description;
    }

    public void setGame_description(String game_description) {
        this.game_description = game_description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Esrb getEsrb() {
        return esrb;
    }

    public void setEsrb(Esrb esrb) {
        this.esrb = esrb;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Integer getPlayers() {
        return players;
    }

    public void setPlayers(Integer players) {
        this.players = players;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getGraphics() {
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getHighlighted() {
        return highlighted;
    }

    public void setHighlighted(Integer highlighted) {
        this.highlighted = highlighted;
    }

    public String getDownload_path() {
        return download_path;
    }

    public void setDownload_path(String download_path) {
        this.download_path = download_path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<OperatingSystem> getOperating_systems() {
        return operating_systems;
    }

    public void setOperating_systems(List<OperatingSystem> operating_systems) {
        this.operating_systems = operating_systems;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }
}
