package bo.ucb.edu.ingsoft.dao;

import bo.ucb.edu.ingsoft.dto.Transaction;
import bo.ucb.edu.ingsoft.models.Game;
import bo.ucb.edu.ingsoft.models.Publisher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface GameDao {
    // Get Sale games data
    public List<Game> findSale();

    // Get Sale games data
    public List<Integer> findSaleIds();

    //Find page for the homepage
    public List<Game> findPage(@Param("a") Integer limit, @Param("b") Integer offset);

    //Gets all the highlights for the projects
    public List<Game> findHighlight();

    //Find latest releases (last week)
    public List<Game> findLatestReleases();

    //Create Game
    public void createGame(Game game);

    //Get game info
    public Game getGameInfo(Integer idGame);

    public ArrayList<Game> getPage(@Param("a") Integer limit, @Param("b") Integer offset);

    //Search Game for User Library
    public List<Game> findLibraryGames(List<Integer> idGame);

    //Last Id game
    public Integer getLastInsertId();

    //Get publisher developer games
    public List<Integer> findGamebyPublisher(List<Integer> idDeveloper);

    public void updateGame(Game game);

    public Integer findByDeveloperGame(Integer gameId);



    //delete  delete
    public void deleteGame(Integer idGame);

    //Search Query
    public List<Game> searchQuery(String query);

    //Get a list of the games by a developer
    public List<Game> findByPublisher(Integer idPublisher);

    //Get a list of all the games in the database
    public List<Game> findAllGames();

    //Get a list of the games with a list of ids.
    public List<String> listGameNames(List<Integer> idGame);

    //Update Highlight
    public void updateHighlight(Game game);

}
