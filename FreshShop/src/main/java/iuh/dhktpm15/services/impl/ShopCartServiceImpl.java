package iuh.dhktpm15.services.impl;

import iuh.dhktpm15.entities.CartItem;
import iuh.dhktpm15.services.ShopCartService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShopCartServiceImpl implements ShopCartService {
    private Map<Long, CartItem> maps = new HashMap<>();

    @Override
    public void add(CartItem item){
        CartItem cartItem = maps.get(item.getId());
        if(cartItem == null){
            item.setSoLuong(1);
            maps.put(item.getId(),item);
        } else {
            cartItem.setSoLuong(cartItem.getSoLuong()+1);
        }
    }

    @Override
    public Collection<CartItem> getAllItem(){
        return maps.values();
    }

    @Override
    public CartItem getItemById(Long id){
        return maps.get(id);
    }

    @Override
    public void giam(Long id){
        CartItem cartItem = maps.get(id);
        if(cartItem.getSoLuong() < 2){
            maps.remove(id);
        } else {
            cartItem.setSoLuong(cartItem.getSoLuong()-1);
        }
    }
    @Override
    public void tang(Long id){
        CartItem cartItem = maps.get(id);
        cartItem.setSoLuong(cartItem.getSoLuong()+1);
    }

    @Override
    public int getCount(){
        return maps.values().size();
    }

    @Override
    public double totalGia(){
        return maps.values().stream().mapToDouble(item -> item.getSoLuong() * item.getGiaBanHienTai()).sum();
    }

    @Override
    public void remove(long id){
        maps.remove(id);
    }

    @Override
    public CartItem update(long id, float soluong){
        CartItem cartItem = maps.get(id);
        cartItem.setSoLuong(soluong);
        return cartItem;
    }

    @Override
    public void clear(){
        maps.clear();
    }
}
