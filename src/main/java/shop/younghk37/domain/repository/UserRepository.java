package shop.younghk37.domain.repository;

import shop.younghk37.domain.entity.*;

public interface UserRepository {

    // CREATE
    public void insertUser(User user);

    public void insertAddress(Address address);

    public void insertBook(Book book);

    public void addToCart(BookCart bookCart);

    public void createOrder(Order order);

    public void createCart(Long user_num);

    public void addCard(Card card);

    public void createBook_order(BookOrder bookOrder);

    public void insertCoupon(Coupon coupon);

    public void insertCouponUser(CouponUser couponUser);

    // READ
    public User getUserByUserNum(Long user_num);

    public User[] selectUserByUserNumORID(User user);

    public User selectUserByIDAndPassword(User user);

    public Book[] getBookList(String book_name);

    public Book getBookByBookNum(Long book_num);

    public User getUserById(String id);

    public Cart getCart(Long user_num);

    public Card[] getCardList(Long user_num);

    public Integer checkBookPriceWithBookNum(Long book_num);

    public BookCart[] getBookCartsByCartNum(Long cart_num);

    public BookCart[] getBookCartsByBookNum(Long book_num);

    public Address[] getAddressByUserNum(Long user_num);

    public Card[] getCardsByUserNum(Long user_num);

    public Card getCardByCard_num(Long card_num);

    public Order getLatestOrderByUserNum(Long user_num);

    public Order[] getOrderList(Long user_num);

    public BookOrder[] getBookOrderByOrderNum(Long order_num);

    public CouponUser[] getCouponUserByUserNum(Long user_num);
// PATCH

    public void modifyBook(Book book);

    public void addjustBookCartPrice(Long book_cart_num, Integer book_cart_price);

    public void updatePoint(Long user_num, Integer point);
// PUT



// DELETE

    public void deleteBookByBookNum(String book_num);

    public void deleteBookCartByCartNum(Long cart_num);

    public void deleteCartByUserNum(Long user_num);

    public void deleteBookCartByBookCartNum(Long book_cart_num);

    public void deleteBookOrderByOrderNum(Long order_num);

    public void deleteOrderByOrderNum(Long order_num);
}