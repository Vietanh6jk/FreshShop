package iuh.dhktpm15.controllers;


import iuh.dhktpm15.entities.*;
import iuh.dhktpm15.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Controller
@RequestMapping("/gio")
public class GioHangController {
    private final NongSanService nongSanService;
    private final NguoiDungService nguoiDungService;
    private final DonBanService donBanService;
    private final CT_DonBanService ct_donBanService;

    private final ShopCartService shopCartService;

    public GioHangController(NongSanService nongSanService, NguoiDungService nguoiDungService, DonBanService donBanService, CT_DonBanService ctDonBanService, ShopCartService shopCartService) {
        this.nongSanService = nongSanService;
        this.nguoiDungService = nguoiDungService;
        this.donBanService = donBanService;
        ct_donBanService = ctDonBanService;
        this.shopCartService = shopCartService;
    }

    @GetMapping("")
    public ModelAndView gioHang(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("gioHang", shopCartService.getCount());
        modelAndView.addObject("nsTrongGio",shopCartService.getAllItem());
        modelAndView.addObject("tongGia",shopCartService.totalGia());
        modelAndView.setViewName("/views/cart");
        return modelAndView;
    }

    @GetMapping("/them-vao-gio/{id}")
    public ModelAndView themVaoGio(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView();
        NongSan nongSan = nongSanService.findById(id);

        CartItem itemGio = new CartItem();
        itemGio.setId(nongSan.getId());
        itemGio.setTenNongSan(nongSan.getTenNongSan());
        itemGio.setHinhAnh(nongSan.getHinhAnh());
        itemGio.setTrongLuong(nongSan.getTrongLuong());
        itemGio.setGiaBanHienTai(nongSan.getGiaBanHienTai());

        shopCartService.add(itemGio);

        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }//click add from page viewProduct

    @GetMapping("/giam/{idNongSan}")
    public ModelAndView giamSoLuong(@PathVariable long idNongSan){
        ModelAndView modelAndView = new ModelAndView();
        shopCartService.giam(idNongSan);
        modelAndView.setViewName("redirect:/gio");
        return modelAndView;
    }
    @GetMapping("/tang/{idNongSan}")
    public ModelAndView tangSoLuong(@PathVariable long idNongSan){
        ModelAndView modelAndView = new ModelAndView();
        CartItem cartItem = shopCartService.getItemById(idNongSan);
        NongSan nongSan = nongSanService.findById(idNongSan);

        if(nongSan.getTrongLuong() == cartItem.getSoLuong()){
            modelAndView.addObject("gioHang", shopCartService.getCount());
            modelAndView.addObject("nsTrongGio",shopCartService.getAllItem());
            modelAndView.addObject("tongGia",shopCartService.totalGia());
            modelAndView.addObject("quaNhieu","nông sản " +nongSan.getTenNongSan() +" vượt quá số lượng");
            modelAndView.setViewName("/views/cart");
            return modelAndView;
        } else {
            shopCartService.tang(idNongSan);
            modelAndView.setViewName("redirect:/gio");
            return modelAndView;
        }
    }

    @GetMapping("/xoa/{index}")
    public ModelAndView xoa(@PathVariable long index){
        ModelAndView modelAndView = new ModelAndView();
        shopCartService.remove(index);
        modelAndView.setViewName("redirect:/gio");
        return modelAndView;
    }



    @GetMapping("/checkout")
    public ModelAndView checkout(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("gioHang", shopCartService.getCount());
        modelAndView.addObject("nsTrongGio",shopCartService.getAllItem());
        modelAndView.addObject("tongGia",shopCartService.totalGia());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String sdt = authentication.getName();
        NguoiDung nguoiDung = nguoiDungService.findBySdt(sdt).get();
        modelAndView.addObject("nguoiDung",nguoiDung);

        modelAndView.setViewName("/views/checkout");
        return modelAndView;
    }

    @PostMapping("/thanhtoan")
    public ModelAndView pay(@ModelAttribute NguoiDung nguoiDung,
                            @RequestParam("diaChiGiao") String diaChiGiao){

        ModelAndView modelAndView = new ModelAndView();
        DonBan donBan = new DonBan();
        donBan.setDiaChiGiao(diaChiGiao);
        java.util.Date utilDate = new java.util.Date();
        Date date = new Date(utilDate.getTime());
        donBan.setNgayLap(date);
        donBan.setNguoiDung(nguoiDung);

        CT_DonBan ct_donBan = new CT_DonBan();

        //check số lượng trong giỏ để lưu hóa đơn
        AtomicInteger fladSoLuong = new AtomicInteger();
        shopCartService.getAllItem().stream().forEach(item -> {
            NongSan nongSan = nongSanService.findById(item.getId());
            if(item.getSoLuong() <= nongSan.getTrongLuong()) {
                fladSoLuong.set(1);
            }
        });
        if(fladSoLuong.get()==1){
            donBanService.save(donBan);
        }


        //check số lượng trong giỏ để lưu chi tiết hóa đơn
        AtomicInteger flad = new AtomicInteger();
        shopCartService.getAllItem().stream().forEach(item -> {
            CTDonBanKey donBanKey = new CTDonBanKey(item.getId(), donBan.getId());
            ct_donBan.setKey(donBanKey);
            ct_donBan.setDonBan(donBan);
            NongSan nongSan = nongSanService.findById(item.getId());
            if(item.getSoLuong() <= nongSan.getTrongLuong()) {
                ct_donBan.setNongSan(nongSan);
                ct_donBan.setSoLuong(item.getSoLuong());
                nongSanService.updateTrongLuong(nongSan, nongSan.getTrongLuong() - item.getSoLuong());
                ct_donBan.setGiaNhap(item.getGiaBanHienTai());
                ct_donBanService.save(ct_donBan);
            } else {
                flad.set(1);
            }
        });

        // thông báo
        if(flad.get() == 1){
            modelAndView.addObject("gioHang", shopCartService.getCount());
            modelAndView.addObject("nsTrongGio",shopCartService.getAllItem());
            modelAndView.addObject("tongGia",shopCartService.totalGia());
            modelAndView.addObject("quaNhieu","nông sản vượt quá số lượng");
            modelAndView.setViewName("/views/cart");
            return modelAndView;
        } else {
            modelAndView.setViewName("redirect:/home");
            shopCartService.clear();
            return modelAndView;
        }


    }

    @GetMapping("/damua")
    public ModelAndView daMua(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String sdt = authentication.getName();
        NguoiDung nguoiDung = nguoiDungService.findBySdt(sdt).get();

        modelAndView.addObject("donBans",donBanService.findDonBanByKH(nguoiDung.getId()));

        modelAndView.setViewName("/views/viewsDonBan");
        return modelAndView;
    }

    @GetMapping("/hoadon/xemchitiet/{id}")
    public ModelAndView viewChiTiet(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ct_donban",ct_donBanService.findByDonBan(id));

        modelAndView.setViewName("/views/donBanCT");

        return modelAndView;
    }
}
