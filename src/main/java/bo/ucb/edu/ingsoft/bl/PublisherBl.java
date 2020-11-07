package bo.ucb.edu.ingsoft.bl;

import bo.ucb.edu.ingsoft.dao.PublisherDao;
import bo.ucb.edu.ingsoft.dao.TransactionDao;
import bo.ucb.edu.ingsoft.dao.UserDao;
import bo.ucb.edu.ingsoft.dto.PublisherRequest;
import bo.ucb.edu.ingsoft.dto.Transaction;
import bo.ucb.edu.ingsoft.models.Publisher;
import bo.ucb.edu.ingsoft.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PublisherBl {

    private PublisherDao publisherDao;
    private UserDao userDao;
    private TransactionDao transactionDao;


    @Autowired
    public PublisherBl(PublisherDao publisherDao, UserDao userDao, TransactionDao transactionDao) {
        this.publisherDao = publisherDao;
        this.userDao = userDao;
        this.transactionDao = transactionDao;
    }
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
        System.out.println("sfjkheskhlf"+lastId);

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
}
