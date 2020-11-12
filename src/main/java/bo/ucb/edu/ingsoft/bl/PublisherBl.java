package bo.ucb.edu.ingsoft.bl;

import bo.ucb.edu.ingsoft.dao.*;
import bo.ucb.edu.ingsoft.dto.*;
import bo.ucb.edu.ingsoft.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class PublisherBl {

    private PublisherDao publisherDao;
    private UserDao userDao;
    private TransactionDao transactionDao;
    private OrderDao orderDao;
    private GameDao gameDao;
    private DeveloperDao developerDao;
    private PriceDao priceDao;
    private PhotoDao photoDao;
    private CountryDao countryDao;

    @Autowired
    public PublisherBl(PublisherDao publisherDao, UserDao userDao, TransactionDao transactionDao, OrderDao orderDao, GameDao gameDao, DeveloperDao developerDao, PriceDao priceDao, PhotoDao photoDao, CountryDao countryDao) {
        this.publisherDao = publisherDao;
        this.userDao = userDao;
        this.transactionDao = transactionDao;
        this.orderDao = orderDao;
        this.gameDao = gameDao;
        this.developerDao = developerDao;
        this.priceDao = priceDao;
        this.photoDao = photoDao;
        this.countryDao = countryDao;
    }


    /*
       POST (/admin/publisher) The admin create a new publisher
    */
    public PublisherRequest createPublisher(PublisherRequest publisherRequest, Transaction transaction){

        User user=new User();
        Publisher publisher=new Publisher();

        user.setIdCountry(publisherRequest.getCountry());
        user.setUserName(publisherRequest.getUsername());
        user.setPassword(publisherRequest.getPassword());
        user.setEmail(publisherRequest.getEmail());
        user.setTxId(transaction.getTxId());
        user.setTxHost(transaction.getTxHost());
        user.setTxUserId(transaction.getTxUserId());
        user.setTxDate(transaction.getTxDate());
        userDao.createPublisher(user);

        Integer lastId=userDao.getLastInsertId();
        publisher.setIdUser(lastId);
        publisher.setPaypalMail(publisherRequest.getPaypal());
        publisher.setPublisher(publisherRequest.getPublisher());
        publisher.setTxId(transaction.getTxId());
        publisher.setTxHost(transaction.getTxHost());
        publisher.setTxUserId(transaction.getTxUserId());
        publisher.setTxDate(transaction.getTxDate());

        publisherDao.createPublisher(publisher);

        return publisherRequest;
    }
    /*
      GET (/publisher/{id}) shows publisher data
   */
    public PublisherRequest findByPublisherId (Integer idUser){
        PublisherRequest publisherRequest=new PublisherRequest();
        User user= userDao.findByUserId(idUser);
        Publisher publisher=publisherDao.findByPublisherId(idUser);

        publisherRequest.setUsername(user.getUserName());
        publisherRequest.setEmail(user.getEmail());
        publisherRequest.setPaypal(publisher.getPaypalMail());
        publisherRequest.setPublisher(publisher.getPublisher());
        publisherRequest.setCountry(user.getIdCountry());
        publisherRequest.setPassword(user.getPassword());
        publisherRequest.setRepeat_password(user.getPassword());

        return publisherRequest;
    }
    /*
        GET (/admin/publisher) The admin sees a publisher list
    */
    public List<PublisherRequest> getPublisherList(){
        List<PublisherRequest> publisherRequest =new ArrayList<PublisherRequest>();
        List<User> user=userDao.listUserMails();

        List<Integer> ids = new ArrayList<Integer>();
        for(User users : user){
            ids.add(users.getIdUser());
        }

        List<Publisher> publisher=publisherDao.listPublisher(ids);
        for(int i=0;i< user.size();i++){
            PublisherRequest publisherRequest1=new PublisherRequest();

            publisherRequest1.setIdUser(user.get(i).getIdUser());
            publisherRequest1.setEmail(user.get(i).getEmail());
            publisherRequest1.setPaypal(publisher.get(i).getPaypalMail());
            publisherRequest1.setPublisher(publisher.get(i).getPublisher());

            publisherRequest.add(publisherRequest1);
        }

        return publisherRequest;
    }
    /*
        DELETE (/admin/publisher/{id}) The admin delete a publisher account
   */
    public void deletePublisher (Integer idUser, Transaction transaction){
      userDao.deleteUserPublisher(idUser);
      publisherDao.deletePublisher(idUser);
      transactionDao.updateUserTransaction(idUser, transaction.getTxId(), transaction.getTxHost(), transaction.getTxUserId(), transaction.getTxDate());
    }

    /*
       PUT (/publisher/{id}) The publisher can update his account
   */
    public PublisherRequest updatePublisher(PublisherRequest publisherRequest, Transaction transaction, Integer userId){
        User user=new User();
        Publisher publisher=new Publisher();

        user.setIdUser(userId);
        user.setIdCountry(publisherRequest.getCountry());
        user.setUserName(publisherRequest.getUsername());
        user.setPassword(publisherRequest.getPassword());
        user.setEmail(publisherRequest.getEmail());
        user.setTxId(transaction.getTxId());
        user.setTxHost(transaction.getTxHost());
        user.setTxUserId(transaction.getTxUserId());
        user.setTxDate(transaction.getTxDate());

        String oldPassword =user.getPassword();
        String newPassword =publisherRequest.getRepeat_password();
        if((oldPassword).equals(newPassword)){
            userDao.updateUser(user);
            publisher.setIdUser(userId);
            publisher.setPaypalMail(publisherRequest.getPaypal());
            publisher.setPublisher(publisherRequest.getPublisher());
            publisher.setTxId(transaction.getTxId());
            publisher.setTxHost(transaction.getTxHost());
            publisher.setTxUserId(transaction.getTxUserId());
            publisher.setTxDate(transaction.getTxDate());

            publisherDao.updatePublisher(publisher);

        }else{
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Wrong Password");
        }

        return publisherRequest;

    }
    // Get the publisher statistics
    public DashboardRequest PublisherDashboard(Integer idUser){
        // Publisher Name, Mail and Paypalmail
        Publisher pubId=publisherDao.findByPublisherId(idUser);
        User mail=userDao.publisherMail(idUser);
        // Publisher's developers ids
        List<Integer> developer=developerDao.findByPublisher(pubId.getIdPublisher());

        //Publisher games ids
        List<Integer> game=gameDao.findGamebyPublisher(developer);
        // Publisher total sells and total earnings
        Integer totalSells=orderDao.gameSellsPublisher(game);
        Double totalEarnings=orderDao.gameEarnings(game);
        // Month list
        List<Integer> months=new ArrayList<>();
        for (int i=1;i<=12;i++){
            months.add(i);
        }
        // Monthly statistics
        List<monthlyDashboard> monthlyEarnings=orderDao.gameEarningsMonth(game,months);
        // Countries ids
        List<Integer> countries=orderDao.gameOrderCountry(game);
        // Country statistics
        List<countryDashboard> totalCountrySells=orderDao.gameOrderCountryCount(game,countries);

        // Games statistics
        List<gameDashboard> gameSells=orderDao.gameSellsGame(game);


        // Call dashboardrequest dto
        DashboardRequest dashboardRequest=new DashboardRequest();
        // Set dashboard values
        dashboardRequest.setPublisher(pubId.getPublisher());
        dashboardRequest.setEmail(mail.getEmail());
        dashboardRequest.setPaypal(pubId.getPaypalMail());
        dashboardRequest.setSells(totalSells);
        dashboardRequest.setEarnings(totalEarnings);
        dashboardRequest.setMonthlyData(monthlyEarnings);
        dashboardRequest.setCountryData(totalCountrySells);
        dashboardRequest.setGameData(gameSells);
        // Return object dashboardrequest for the method
        return dashboardRequest;

    }
    /*
     The publisher gets a list of the games they made.
     */
    public List<HomepageRequest> getAllPublisherGames(Integer idPublisher){
        List<HomepageRequest> list = new ArrayList<HomepageRequest>();
        List<Game> games = gameDao.findByPublisher(idPublisher);
        if(!games.isEmpty()) {
            List<Integer> ids = new ArrayList<Integer>();
            for (Game game : games) {
                ids.add(game.getIdGame());
            }
            for(int i = 0; i < ids.size(); i++){
                Price price = priceDao.findById(ids.get(i));
                Photo photo = photoDao.findBannerbyGame(ids.get(i));
                if(price != null && photo != null){
                    HomepageRequest homepageRequest = new HomepageRequest(games.get(i).getIdGame().toString(), games.get(i).getName(), price.getPrice(), price.getSale().doubleValue(), photo.getPhotoPath());
                    list.add(homepageRequest);
                }
            }
        }
        return list;
    }

    /*
        The admin gets a list of all the games they made.
    */
    public List<GameAdminRequest> getAllGames(){
        List<GameAdminRequest> list = new ArrayList<GameAdminRequest>();
        List<Game> games = gameDao.findAllGames();
        if(!games.isEmpty()) {
            List<Integer> ids = new ArrayList<Integer>();
            for (Game game : games) {
                ids.add(game.getIdGame());
            }
            for(int i = 0; i < ids.size(); i++){
                Price price = priceDao.findById(ids.get(i));
                Photo photo = photoDao.findBannerbyGame(ids.get(i));
                if(price != null && photo != null){
                    GameAdminRequest gameAdminRequest = new GameAdminRequest(games.get(i).getIdGame().toString(), games.get(i).getName(), price.getPrice(), price.getSale().doubleValue(), photo.getPhotoPath(),games.get(i).getHighlight());
                    list.add(gameAdminRequest);
                }
            }
        }
        return list;
    }

}
