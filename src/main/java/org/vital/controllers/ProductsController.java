package org.vital.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.vital.models.Product;
import org.vital.services.ProductService;


import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;

    }

    @GetMapping()
    public String index(Model model) {

        model.addAttribute("products", productService.findAllProducts());
        return "products/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("product", productService.findProduct(id));
        return "products/show";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {                     //@ModelAttribute("user") User user
        model.addAttribute("product", new Product());             // nothing
        return "products/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("product")  @Valid Product product , BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "products/new";
        }

        productService.saveProduct(product);
        return "redirect:/products";
    }

}
