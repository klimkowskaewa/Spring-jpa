package pl.edu.wszib.springjpa.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductRepository productRepository;



    @GetMapping("/create")
    public String create (Model model){
        Product newProduct = new Product();
        Category[] categories = Category.values();
        model.addAttribute("newProduct", newProduct);
        model.addAttribute("categories", categories);
        return "shop/create";
    }





}