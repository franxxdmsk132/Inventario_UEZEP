package com.eabmodel.inventario_uezep.Controller;

import com.eabmodel.inventario_uezep.Entity.Departamentos;
import com.eabmodel.inventario_uezep.Entity.Muebles;
import com.eabmodel.inventario_uezep.Service.DepartamentoService;
import com.eabmodel.inventario_uezep.Service.MuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departamentos")

public class DepartamentosController {

    @Autowired
    private DepartamentoService departamentoService;
    @Autowired
    private MuebleService muebleService;

    @ModelAttribute("allMuebles")
    public List<Muebles> populateMuebles(){
        return muebleService.findAll();
    }

    public DepartamentosController(DepartamentoService departamentoService){
        this.departamentoService = departamentoService;
    }
    @GetMapping("lista")
    public String listaDepartamentos(Model model){
        List<Departamentos>departamentos = departamentoService.findAll();
        model.addAttribute("departamento", departamentos);
        return "departamento/lista-departamentos";
    }

    @RequestMapping("/masDetalles")
    public String viewDepartamento(@RequestParam("id_departamento") int id_departamentos, Model model){
        Departamentos departamentos = departamentoService.findById(id_departamentos);
        model.addAttribute("departamentos", departamentos);
        return "departamentos/detalles-departamentos";
    }

    @GetMapping("/nuevoDepartamento")
    public String nuevoDepartamento(Model theModel){
        Departamentos departamentos = new Departamentos();
        theModel.addAttribute("departamentos", departamentos);
        return "departamentos/guardar-departamentos";
    }

    @GetMapping("/cambiarDepartamento")
    public String showFormUpdate(@RequestParam("id_departamentos") int id_departamentos, Model theModel){

        Departamentos departamentos = departamentoService.findById(id_departamentos);

        theModel.addAttribute("departamentos", departamentos);
        return  "departamentos/actualizar-aulas";
    }

    @PostMapping("/save")
    public String saveDepartamento(@ModelAttribute("Departamentos") Departamentos departamentos){

        departamentoService.save(departamentos);
        return "redirect:/departamentos/lsita";
    }
    @PostMapping("/update")
    public String updateDepartamentos(@ModelAttribute("departamentos")Departamentos departamentos){

        departamentoService.update(departamentos);
        return "redirect:/departamentos/lista";
    }

    @PostMapping("/delete")
    public String deleteDepartamentos(@RequestParam("id_departamentos") int id_departamentos){
        departamentoService.deleteByIdDepartamentos(id_departamentos);
        return "redirect:/departamentos/lista";
    }
}
