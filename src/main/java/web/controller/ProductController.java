package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Product;
import web.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("product")
    public String home(Model model){
        List<Product> list = productService.listAll();
        model.addAttribute("listProducts", list);
        return "product_table";
    }

    @GetMapping("product/new")
    public ModelAndView showNewProduct(ModelAndView model){
        Product product = new Product();
        model.setViewName("new_product");
        model.addObject("product", product);
        return model;
    }

    @PostMapping("product/save")
    public String saveNewProduct(@ModelAttribute("product") Product product){
        productService.save(product);
        return "redirect:";
    }

    @RequestMapping("product/edit/{id}")
    public ModelAndView editProduct(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("edit_product");
        Product product = productService.get(id);
        mv.addObject("product", product);
        return mv;
    }

    @GetMapping("product/delete")
    public String deleteProduct(@RequestParam("id") Long id){
        productService.delete(id);

        return "redirect:";
    }
}
