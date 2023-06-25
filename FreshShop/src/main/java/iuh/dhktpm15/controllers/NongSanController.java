//package iuh.dhktpm15.controllers;
//
//
//import iuh.dhktpm15.entities.NongSan;
//import iuh.dhktpm15.services.NongSanService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequestMapping("/nongsan")
//public class NongSanController {
//    private NongSanService nongSanService;
//    public NongSanController(NongSanService nongSanService){
//        this.nongSanService = nongSanService;
//    }
//
//    @GetMapping({"","/home","/list-nongsan"})
//    public ModelAndView getAll(){
//        ModelAndView mode = new ModelAndView();
//        List<NongSan> nongSans = nongSanService.findByAll();
//
//        mode.addObject("listNongSan",nongSans);
//        mode.setViewName("home");
//        return mode;
//    }
//
//    @GetMapping("/search")
//    public ModelAndView search(@RequestParam("query") long query) {
//        ModelAndView modelAndView = new ModelAndView();
//        List<NongSan> nongSans = new ArrayList<>();
//        if(nongSanService.findById(query) != null){
//            nongSans.add(nongSanService.findById(query));
//            modelAndView.addObject("listNongSan", nongSans);
//            modelAndView.setViewName("home");
//        } else {
//            modelAndView.setViewName("redirect:home");
//        }
//        return modelAndView;
//    }
//
//    @GetMapping("/gio-hang")
//    public ModelAndView showCart(HttpSession session, Model model) {
//        ModelAndView modelAndView = new ModelAndView();
//        List<NongSan> cart = (List<NongSan>) session.getAttribute("cart");
//        modelAndView.addObject("cart", cart);
//        modelAndView.setViewName("gio-hang");
//        return modelAndView;
//    }
//
//    @PostMapping("/them-vao-gio")
//    public ModelAndView addToCart(@RequestParam("productId") long productId, HttpSession session) {
//        ModelAndView model = new ModelAndView();
//        NongSan product = nongSanService.findById(productId);
//        List<NongSan> cart = (List<NongSan>) session.getAttribute("cart");
//        if (cart == null) {
//            cart = new ArrayList<>();
//        }
//        cart.add(product);
//        session.setAttribute("cart", cart);
//        model.setViewName("redirect:home");
//        return model;
//    }
//
//
//}
