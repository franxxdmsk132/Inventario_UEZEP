package com.eabmodel.inventario_uezep.Controller;

import com.eabmodel.inventario_uezep.Entity.Muebles;
import com.eabmodel.inventario_uezep.Service.MuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/muebles")
public class MuebleController {
    @Autowired
    private MuebleService muebleService;

    public MuebleController(MuebleService muebleService) {
        this.muebleService = muebleService;
    }

    @GetMapping("/lista")
    public String listaMuebles(Model model){
        List<Muebles> muebles = muebleService.findAll();
        model.addAttribute("muebles",muebles);
        return "muebles/lista-muebles";
    }
}
