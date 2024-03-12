package com.example.backenddevmidterms.Services;

import com.example.backenddevmidterms.Models.Cart;
import com.example.backenddevmidterms.Models.Items;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

public interface CartService {

    public List<Cart> getCarts();
    public Cart getCart(Long id);
    public List<Cart> getCartsinRange(Date start, Date end);
    public List<Cart> getUserCarts(Long userId);
    public Cart addCart(Cart cart);
    public Cart updateCart(Long id,Cart cart);
    public String deleteCart(Long id);

}
