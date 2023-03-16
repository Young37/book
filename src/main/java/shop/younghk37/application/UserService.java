package shop.younghk37.application;

import org.springframework.beans.factory.annotation.Autowired;
import shop.younghk37.domain.entity.*;
import shop.younghk37.domain.repository.UserRepository;
import shop.younghk37.presentation.dto.UserDTO;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface UserService {

    public boolean dupleCheckUser(UserDTO.SignUpReq req);

    public Address[] getAddressByUserNum	(Long user_num);

    public User getUserById(String id);

    public Book[] getBookList(String book_name);

    public Book getBookByBookNum(Long book_num);

    public BookCart[] getBookCartsByCartNum(Long cart_num);

    public Cart getCart(String id);

    public Card[] getCardList(String id);

    public Order[] getOrderList(String id);

    public BookOrder[] getBookOrderByOrderNum(Long order_num);

    public User getUserByUserNum(Long user_num);

    public void deleteBookByBookNum(String book_num);

    public void modifyBook(UserDTO.ModifyBookReq req);

    public void addUser(User user);

    public void addAddress(Address address);

    public boolean validCheckUser(UserDTO.SignInReq req);

    public void add1000point(User invite_user);

    public void addBook(UserDTO.AddBookReq req);

    public void buyWithCart(Integer point,String id, Long user_num, BookCart[] bookCarts);

    public void addToCart(Long book_num,Integer book_cart_amount, String id);


    public void addCard(Long card_num, Long user_num, Date card_valid_date, String card_type);

    public void deleteOrder(Long order_num);
}