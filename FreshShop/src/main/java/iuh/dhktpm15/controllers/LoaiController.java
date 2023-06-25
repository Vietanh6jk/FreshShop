package iuh.dhktpm15.controllers;

import iuh.dhktpm15.entities.Loai;
import iuh.dhktpm15.services.LoaiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/loai")
public class LoaiController {
    private final LoaiService loaiService;

    public LoaiController(LoaiService loaiService) {
        this.loaiService = loaiService;
    }

    @GetMapping("/get")
    public ModelAndView getAll(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loais",loaiService.findByAll());
        modelAndView.setViewName("/views/categories");
        return modelAndView;
    }

    @GetMapping("/them")
    public ModelAndView showSave(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loai", new Loai());
        modelAndView.setViewName("/views/categoriesAdd");
        return modelAndView;
    }

    @PostMapping("/them")
    public ModelAndView save(@ModelAttribute Loai loai){
        ModelAndView modelAndView = new ModelAndView();
        loaiService.save(loai);
        modelAndView.setViewName("redirect:/loai/get");
        return modelAndView;
    }

    @GetMapping("/capnhat/{id}")
    public ModelAndView update(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loai",loaiService.findById(id));
        modelAndView.setViewName("/views/categoriesAdd");
        return modelAndView;
    }
}
