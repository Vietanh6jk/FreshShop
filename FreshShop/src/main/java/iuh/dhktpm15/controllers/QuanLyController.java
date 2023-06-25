package iuh.dhktpm15.controllers;

import iuh.dhktpm15.entities.DonNhap;
import iuh.dhktpm15.entities.NongSan;
import iuh.dhktpm15.services.DonBanService;
import iuh.dhktpm15.services.DonNhapService;
import iuh.dhktpm15.services.LoaiService;
import iuh.dhktpm15.services.NongSanService;
import iuh.dhktpm15.toancuc.xulyfile.XuLyFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.Date;


@Controller
@RequestMapping("/quanly")
public class QuanLyController {

    private final NongSanService nongSanService;
    private final LoaiService loaiService;
    private final XuLyFile xuLyFile;
    private final DonNhapService donNhapService;

    private final DonBanService donBanService;

    @Value("${qlns.giaban}")
    private float tyleBanRa;

    public QuanLyController(NongSanService nongSanService, LoaiService loaiService, XuLyFile xuLyFile, DonNhapService donNhapService, DonBanService donBanService) {
        this.nongSanService = nongSanService;
        this.loaiService = loaiService;
        this.xuLyFile = xuLyFile;
        this.donNhapService = donNhapService;
        this.donBanService = donBanService;
    }

    @GetMapping("")
    public ModelAndView quanLyHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/views/adminHome");
        System.out.println("Duong dan: "+System.getProperty("${upload.path}"));
        return modelAndView;
    }

    @GetMapping("/nongsan")
    public ModelAndView getNongSan(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("nongSans", nongSanService.findByAll());
        modelAndView.setViewName("/views/products");
        return modelAndView;
    }//view all products

    @GetMapping("/nongsan/them")
    public ModelAndView themNongSan(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("nongSan", new NongSan());
        modelAndView.addObject("loais", loaiService.findByAll());
        modelAndView.setViewName("/views/productsAdd");
        return modelAndView;
    }

    @PostMapping("/nongsan/them")
    public ModelAndView saveNongSan(@ModelAttribute("nongSan") NongSan nongSan,
                             @RequestParam("giaNhap") float gia,
                             @RequestParam("soLuong") float soLuong,
                             @RequestParam("diaChiNhap") String diaChiNhap,
                             @RequestParam("productImage") MultipartFile fileProductImage,
                             @RequestParam("imgName") String imgName) throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        float giaBan = gia+gia*tyleBanRa;
        nongSan.setGiaBanHienTai(giaBan);
        System.out.println(nongSan.getTrongLuong());
        nongSan.setTrongLuong(nongSan.getTrongLuong()+soLuong);
        nongSan.setTrangThai(1);

        System.out.println("anh: "+imgName);
        if(imgName.equals("")){
            xuLyFile.init();
            xuLyFile.save(fileProductImage);
            nongSan.setHinhAnh("/image/"+fileProductImage.getOriginalFilename());
        } else {
            nongSan.setHinhAnh(imgName);
        }


        nongSanService.save(nongSan);

        java.util.Date utilDate = new java.util.Date();
        Date date = new Date(utilDate.getTime());
        DonNhap donNhap = new DonNhap(date,diaChiNhap,gia,soLuong,nongSan);
        donNhapService.save(donNhap);

        modelAndView.setViewName("redirect:/quanly/nongsan");
        return modelAndView;
    }//form add new product > do add

    @GetMapping("/nongsan/ngung/{id}")
    public ModelAndView ngungKinhDoanh(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView();
        NongSan nongSan = nongSanService.findById(id);
        nongSan.setTrangThai(0);
        nongSanService.ngungKinhDoanh(nongSan);
        modelAndView.setViewName("redirect:/quanly/nongsan");
        return modelAndView;
    }

    @GetMapping("/nongsan/ban/{id}")
    public ModelAndView kinhDoanh(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView();
        NongSan nongSan = nongSanService.findById(id);
        nongSan.setTrangThai(1);
        nongSanService.ngungKinhDoanh(nongSan);
        modelAndView.setViewName("redirect:/quanly/nongsan");
        return modelAndView;
    }

    @GetMapping("/nongsan/capnhat/{id}")
    public ModelAndView capNhat(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView();
        NongSan nongSan = nongSanService.findById(id);
        modelAndView.addObject("nongSan",nongSan);
        modelAndView.addObject("loais", loaiService.findByAll());
        modelAndView.setViewName("/views/productsAdd");
        return modelAndView;
    }

    @GetMapping("donmua")
    public ModelAndView findAllDonBan(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("donBans",donBanService.findAllDonBan());

        modelAndView.setViewName("/views/quanlyDonBan");
        return modelAndView;
    }
}
