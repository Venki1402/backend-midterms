package com.example.backenddevmidterms.Controllers;

import com.example.backenddevmidterms.Models.Cart;
import com.example.backenddevmidterms.Services.CartService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CartController {

    CartService cartService;
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping("/test")
    public String test(){
        return "Its Working!";
    }

    @GetMapping("/carts")
    public List<Cart> getCarts(){
        return cartService.getCarts();
    }

    @GetMapping("/carts/{id}")
    public Cart getCart(@PathVariable("id") Long id){
        return cartService.getCart(id);
    }

    @GetMapping("/carts/{start}/{end}")
    public List<Cart> getCartsinRange(@PathVariable("start")Date start, @PathVariable("end") Date end){
        return cartService.getCartsinRange(start,end);
    }

    @GetMapping("/carts/user/{userId}")
    public List<Cart> getUserCarts(@PathVariable("userId") Long userId){
        return cartService.getUserCarts(userId);
    }

    @PostMapping("/carts")
    public Cart addCart(@RequestBody Cart cart){
        return cartService.addCart(cart);
    }

    @PutMapping("/carts/{id}")
    public Cart updateCart(@PathVariable("id") Long id,@RequestBody Cart cart){
        return cartService.updateCart(id,cart);
    }

    @DeleteMapping("/carts/{id}")
    public String deleteCart(@PathVariable("id") Long id){
        cartService.deleteCart(id);
        return "Cart successfully deleted!!";
    }
}
