package iuh.dhktpm15.services;

import iuh.dhktpm15.entities.CartItem;

import java.util.Collection;

public interface ShopCartService {

    void add(CartItem item);

    Collection<CartItem> getAllItem();

    CartItem getItemById(Long id);

    void giam(Long id);

    void tang(Long id);

    int getCount();

    double totalGia();

    void remove(long id);

    CartItem update(long id, float soluong);

    void clear();
}
