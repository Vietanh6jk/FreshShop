package iuh.dhktpm15.controllers;


import iuh.dhktpm15.entities.NongSan;
import iuh.dhktpm15.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping()
public class HomeController {
    private final NongSanService nongSanService;
    private final LoaiService loaiService;
    private final ShopCartService shopCartService;

    private final NguoiDungService nguoiDungService;
    private final RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public HomeController(NongSanService nongSanService, LoaiService loaiService, ShopCartService shopCartService, NguoiDungService nguoiDungService, RoleService roleService){
        this.loaiService = loaiService;
        this.nongSanService = nongSanService;
        this.shopCartService = shopCartService;
        this.nguoiDungService = nguoiDungService;
        this.roleService = roleService;
    }

    @GetMapping("/403")
    public ModelAndView forbidden(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/views/403");
        return modelAndView;
    }
    @GetMapping({"/","home"})
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("gioHang", shopCartService.getCount());
        modelAndView.addObject("listLoai",loaiService.findByAll());
        modelAndView.addObject("nongsans",nongSanService.findByAll());
        modelAndView.setViewName("/views/home");

//        Roles roles = roleService.findById(1);
//        NguoiDung nguoiDung = new NguoiDung("Nguyen Y Van","0987654321",passwordEncoder.encode("12345"));
//        List<NguoiDung_Role> nguoiDung_roles = new ArrayList<>();
//
//        UserRoleKey userRoleKey = new UserRoleKey(nguoiDung.getId(),roles.getId());
//
//        NguoiDung_Role nguoiDungRole = new NguoiDung_Role();
//        nguoiDungRole.setUserRoleKey(userRoleKey);
//        nguoiDungRole.setNguoiDung(nguoiDung);
//        nguoiDungRole.setRoles(roles);
//
//        nguoiDung_roles.add(nguoiDungRole);
//        nguoiDung.setNguoiDung_roles(nguoiDung_roles);
//        nguoiDungService.save(nguoiDung);

//        nguoiDungService.save(nguoiDung);
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView findNongSan(@RequestParam("query") String word){
        ModelAndView modelAndView = new ModelAndView();
        List<NongSan> nongSans = nongSanService.findByName(word);
        modelAndView.addObject("gioHang", shopCartService.getCount());
        modelAndView.addObject("listLoai",loaiService.findByAll());
        modelAndView.addObject("nongsans",nongSans);
        modelAndView.setViewName("/views/home");
        return modelAndView;
    }




    //view Products By loai
    @GetMapping("/home/loai/{id}")
    public ModelAndView nongsanByLoai(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("gioHang", shopCartService.getCount());
        modelAndView.addObject("listLoai", loaiService.findByAll());
        modelAndView.addObject("nongsans", nongSanService.findByLoaiId(id));
        modelAndView.setViewName("/views/home");
        return modelAndView;
    }

    @GetMapping("/home/viewproduct/{id}")
    public ModelAndView viewProduct(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("gioHang", shopCartService.getCount());
        modelAndView.addObject("nongSan",nongSanService.findById(id));
        modelAndView.setViewName("/views/viewProduct");
        return modelAndView;
    } //view product Details
}
