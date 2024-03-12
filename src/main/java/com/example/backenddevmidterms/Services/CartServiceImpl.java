package com.example.backenddevmidterms.Services;

import com.example.backenddevmidterms.Models.Cart;
import com.example.backenddevmidterms.dto.CartDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    RestTemplate restTemplate = new RestTemplate();

    public Cart mapToCart(CartDTO cartDTO){
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setUserId(cartDTO.getUserId());
        cart.setDate(cartDTO.getDate());
        cart.setProducts(cartDTO.getProducts());
        return cart;
    }


    @Override
    public List<Cart> getCarts() {
        CartDTO[] list = restTemplate.getForObject("https://fakestoreapi.com/carts",CartDTO[].class);
        List<Cart> listOfCarts = new ArrayList<>();
        for(int i=0;i<list.length;i++){
            listOfCarts.add(mapToCart(list[i]));
        }
        return listOfCarts;
    }

    @Override
    public Cart getCart(Long id) {
        CartDTO cartDTO = restTemplate.getForObject("https://fakestoreapi.com/carts/"+id,CartDTO.class);
        return mapToCart(cartDTO);
    }

    @Override
    public List<Cart> getCartsinRange(Date start, Date end) {
        CartDTO[] list = restTemplate.getForObject("https://fakestoreapi.com/carts?startdate={start}&enddate={end}",CartDTO[].class);
        List<Cart> finalList = new ArrayList<>();
        for(int i=0;i<list.length;i++){
            if(end.after(list[i].getDate()) && start.before(list[i].getDate())){
                finalList.add((mapToCart(list[i])));
            }
        }
        return finalList;
    }

    @Override
    public List<Cart> getUserCarts(Long userId) {
        CartDTO[] list = restTemplate.getForObject("https://fakestoreapi.com/carts/user/"+userId,CartDTO[].class);
        List<Cart> finalList = new ArrayList<>();
        for(int i=0;i<list.length;i++){
            finalList.add((mapToCart(list[i])));
        }
        return finalList;
    }

//    @Override
//    public Cart addCart(Cart cart) {
//        CartDTO cartDTO = new CartDTO();
//        cartDTO.setId(cart.getId());
//        cartDTO.setUserId(cart.getUserId());
//        cartDTO.setDate(cart.getDate());
//        cartDTO.setProducts(cart.getProducts());
//        CartDTO response = restTemplate.patchForObject("https://fakestoreapi.com/carts",cartDTO,CartDTO.class);
//        return mapToCart(response);
//    }

    @Override
    public Cart addCart(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUserId());
        cartDTO.setDate(cart.getDate());
        cartDTO.setProducts(cart.getProducts());
        CartDTO response = restTemplate.postForObject("https://fakestoreapi.com/carts", cart, CartDTO.class);
        return mapToCart(response);
    }

    @Override
    public Cart updateCart(Long id, Cart cart) {
        CartDTO cartDTO = restTemplate.getForObject("https://fakestoreapi.com/carts/"+id,CartDTO.class);
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUserId());
        cartDTO.setDate(cart.getDate());
        cartDTO.setProducts(cart.getProducts());
        restTemplate.put("https://fakestoreapi.com/carts/"+id,cartDTO);
        return mapToCart(cartDTO);
    }

    @Override
    public String deleteCart(Long id) {
        restTemplate.delete("https://fakestoreapi.com/carts/"+id);
        return "Product successfully deleted!!";
    }
}
