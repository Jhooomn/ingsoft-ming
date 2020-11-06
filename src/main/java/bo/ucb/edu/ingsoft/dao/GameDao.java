package bo.ucb.edu.ingsoft.dao;

import bo.ucb.edu.ingsoft.models.Game;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface GameDao {
    // Get Sale games data
    public List<Game> findSale();
    public ArrayList<Game> findById(ArrayList<Integer> idGame);

    //Create Game
    public Game createGame(Game game);
    public Game getGameInfo(Integer idGame);

    public ArrayList<Game> getPage(@Param("a") Integer limit, @Param("b") Integer offset);

}
