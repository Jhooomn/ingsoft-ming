package bo.ucb.edu.ingsoft.dao;

import bo.ucb.edu.ingsoft.models.Directx;
import bo.ucb.edu.ingsoft.models.Game;
import bo.ucb.edu.ingsoft.models.GameDirectx;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GamesDirectxDao {

   public void createGameDirectx(GameDirectx gameDirectx);

   public List<Directx> findByIdDirectx(List<Integer> idDirectx);

   public List<Integer> findByGame(Integer gameId);

   public List<Integer>  findByIdDirectxGame(Integer idGame);

   public void deleteOldsDirectx(List<Integer> idGameDirectx);
}