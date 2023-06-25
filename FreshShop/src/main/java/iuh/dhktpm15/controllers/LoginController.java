package iuh.dhktpm15.controllers;

import iuh.dhktpm15.entities.NguoiDung;
import iuh.dhktpm15.entities.NguoiDung_Role;
import iuh.dhktpm15.entities.Roles;
import iuh.dhktpm15.entities.UserRoleKey;
import iuh.dhktpm15.services.NguoiDungService;
import iuh.dhktpm15.services.RoleService;
import iuh.dhktpm15.services.ShopCartService;
import iuh.dhktpm15.toancuc.GioHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    private final ShopCartService shopCartService;
    private final RoleService roleService;
    private final NguoiDungService nguoiDungService;
    private final PasswordEncoder passwordEncoder;


    public LoginController(ShopCartService shopCartService, RoleService roleService, NguoiDungService nguoiDungService, PasswordEncoder passwordEncoder) {
        this.shopCartService = shopCartService;
        this.roleService = roleService;
        this.nguoiDungService = nguoiDungService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        GioHang.gioHang.clear();
        shopCartService.clear();
        modelAndView.setViewName("/views/login");
        return modelAndView;
    }

    @GetMapping ("/dangky")
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("nguoiDung", new NguoiDung());
        modelAndView.setViewName("/views/register");
        return modelAndView;
    }

    @PostMapping("/dangky")
    public ModelAndView saveNguoiDung(@ModelAttribute NguoiDung nguoiDung, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        if(nguoiDungService.findBySdt(nguoiDung.getSdt()) == null){
            String password = nguoiDung.getPassword();
            nguoiDung.setPassword(passwordEncoder.encode(nguoiDung.getPassword()));

            List<NguoiDung_Role> nguoiDung_roles = new ArrayList<>();
            Roles roles = roleService.findById(2);
            //them vao nguoidung_role
            NguoiDung_Role dung_role = new NguoiDung_Role();
            UserRoleKey userRoleKey = new UserRoleKey(nguoiDung.getId(),roles.getId());
            dung_role.setUserRoleKey(userRoleKey);
            dung_role.setNguoiDung(nguoiDung);
            dung_role.setRoles(roles);
            nguoiDung_roles.add(dung_role);

            nguoiDung.setNguoiDung_roles(nguoiDung_roles);

            nguoiDungService.save(nguoiDung);
            try {
                request.login(nguoiDung.getSdt(),password);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }

            modelAndView.setViewName("redirect:/");
            return modelAndView;
        } else {
            modelAndView.addObject("errorSdt","Số điện thoại đã được đăng ký");
            modelAndView.setViewName("/views/register");
            return modelAndView;
        }


    }
}
