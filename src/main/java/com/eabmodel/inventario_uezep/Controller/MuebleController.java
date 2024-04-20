package com.eabmodel.inventario_uezep.Controller;

import com.eabmodel.inventario_uezep.Entity.Muebles;
import com.eabmodel.inventario_uezep.Service.MuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/muebles")
public class MuebleController {
    @Autowired
    private MuebleService muebleService;

    public MuebleController(MuebleService muebleService) {
        this.muebleService = muebleService;
    }

    //HTML
    //Listar
    @GetMapping("/lista")
    public String listaMuebles(Model model) {
        List<Muebles> muebles = muebleService.findAll();
        model.addAttribute("muebles", muebles);
        return "muebles/lista-muebles";
    }

    //Mostrar mas detalles
    @RequestMapping("/masDetalles")
    public String viewBodega(@RequestParam("id_mueble") int id_mueble, Model model) {
        Muebles muebles = muebleService.findById(id_mueble);
        model.addAttribute("muebles", muebles);
        return "muebles/detalles-muebles";

    }

    //Formulario para guardar
    @GetMapping("/nuevoMueble")
    public String nuevoMueble(Model theModel) {

        Muebles muebles = new Muebles();

        theModel.addAttribute("muebles", muebles);

        return "muebles/guardar-muebles";
    }

    //Formulario para Actualizar
    @GetMapping("/cambiarMueble")
    public String showFormForUpdate(@RequestParam("id_mueble") int id_mueble, Model theModel) {

        Muebles muebles = muebleService.findById(id_mueble);

        theModel.addAttribute("muebles", muebles);

        return "muebles/actualizar-muebles";
    }


    //----------------------------------------------------------------------------------------
    //Funciones
    //Guardar mueble
    @PostMapping("/save")
    public String saveMueble(@ModelAttribute("muebles") Muebles muebles) {

        muebleService.save(muebles);

        return "redirect:/muebles/lista";
    }

    //Actualizar mueble
//    @PostMapping("/update")
//    public String updateMuebles(@ModelAttribute("muebles") Muebles muebles) {
//
//        muebleService.update(muebles);
//
//        return "redirect:/muebles/lista";
//    }
    @PostMapping("/update")
    public String updateMuebles2(@ModelAttribute("muebles") Muebles muebles) {

        muebleService.update2(muebles);

        return "redirect:/muebles/lista";
    }

    //Eliminar mueble permanente
    @PostMapping("/delete2")
    public String deleteMueble2(@RequestParam("id_mueble") int id_mueble) {
        muebleService.deleteByIdMueble(id_mueble);
        return "redirect:/muebles/lista";
    }
    @PostMapping("/cambiarEstado")
    public String cambiarEstado(@RequestParam("id_mueble") int id_mueble) {
        muebleService.cambiarEstadoByIdMueble(id_mueble);
        return "redirect:/muebles/lista";
    }


}
