package com.eabmodel.inventario_uezep.Controller;


import com.eabmodel.inventario_uezep.Entity.Aulas;
import com.eabmodel.inventario_uezep.Entity.Muebles;
import com.eabmodel.inventario_uezep.Service.AulaService;
import com.eabmodel.inventario_uezep.Service.MuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/aulas")
public class AulaController {
    @Autowired
    private AulaService aulaService;
    @Autowired
    private MuebleService muebleService;

    @ModelAttribute("allMuebles")
    public List<Muebles> populateMuebles() {
        return muebleService.findAll();
    }


    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }
    //HTML
    //Listar
    @GetMapping("/lista")
    public String listaAulas(Model model) {
        List<Aulas> aulas = aulaService.findAll();
        model.addAttribute("aula", aulas);
        return "aulas/lista-aulas";
    }

    //Mostrar mas detalles
    @RequestMapping("/masDetalles")
    public String viewAula(@RequestParam("id_aula") int id_aula, Model model) {
        Aulas aulas = aulaService.findById(id_aula);
        model.addAttribute("aulas", aulas);
        return "aulas/detalles-aulas";

    }

    //Formulario para guardar
    @GetMapping("/nuevaAula")
    public String nuevaAula(Model theModel) {

        Aulas aulas = new Aulas();

        theModel.addAttribute("aulas", aulas);

        return "aulas/guardar-aulas";
    }

    //Formulario para Actualizar
    @GetMapping("/cambiarAula")
    public String showFormForUpdate(@RequestParam("id_aula") int id_aula, Model theModel) {

        Aulas aulas = aulaService.findById(id_aula);

        theModel.addAttribute("aulas", aulas);

        return "aulas/actualizar-aulas";
    }


    //----------------------------------------------------------------------------------------
    //Funciones
    //Guardar mueble
    @PostMapping("/save")
    public String saveAula(@ModelAttribute("aulas") Aulas aulas) {

        aulaService.save(aulas);

        return "redirect:/aulas/lista";
    }

    //Actualizar mueble
//    @PostMapping("/update")
//    public String updateaula(@ModelAttribute("aula") aula aula) {
//
//        aulaervice.update(aula);
//
//        return "redirect:/aula/lista";
//    }
    @PostMapping("/update")
    public String updateAula(@ModelAttribute("aula") Aulas aulas) {

        aulaService.update(aulas);

        return "redirect:/aulas/lista";
    }

    //Eliminar mueble permanente
    @PostMapping("/delete")
    public String deleteAula(@RequestParam("id_aula") int id_aula) {
        aulaService.deleteByIdAula(id_aula);
        return "redirect:/aulas/lista";
    }


}