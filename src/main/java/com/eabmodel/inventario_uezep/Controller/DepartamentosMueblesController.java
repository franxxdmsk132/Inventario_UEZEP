package com.eabmodel.inventario_uezep.Controller;


import com.eabmodel.inventario_uezep.Entity.Departamentos;
import com.eabmodel.inventario_uezep.Entity.DepartamentosMuebles;
import com.eabmodel.inventario_uezep.Entity.Muebles;
import com.eabmodel.inventario_uezep.Service.DepartamentoMuebleService;
import com.eabmodel.inventario_uezep.Service.DepartamentoService;
import com.eabmodel.inventario_uezep.Service.MuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/deffmub")
public class DepartamentosMueblesController {

    @Autowired
    private DepartamentoMuebleService departamentoMuebleService;
    @Autowired
    private DepartamentoService departamentoService;
    @Autowired
    private MuebleService muebleService;

    @ModelAttribute("allDepartamentos")
    public  List<Departamentos> populateDepartamentos(){
        return departamentoService.findAll();
    }
    @ModelAttribute("allMuebles")
    public List<Muebles> populateMuebles(){
        return muebleService.findAll();
    }
    @GetMapping("/lista")
public String listaDepartamentosMuebles(Model model){
    List<DepartamentosMuebles> departamentosMuebles = departamentoMuebleService.findAll();
    model.addAttribute("departamentosmuebles", departamentosMuebles);
    return "departamentos-muebles/lista-departamentos-muebles";
}
@GetMapping("/nuevoMuebleDepartamento")
public String mostrarFormulario(){
    return "departamentos-muebles/guardar-departamentos-muebles";
    }

    @RequestMapping("/masDetalles")
    public String viewDepartamento(@RequestParam("id_departamento") int id_departamentos, Model model) {
        List<DepartamentosMuebles> departamentosMueblesList = departamentoMuebleService.findDetailsByDepartamentosIdWrapper(id_departamentos);
        model.addAttribute("deparmueb", departamentosMueblesList);
        System.out.println("Departamentos" + departamentosMueblesList);
        return "departamentos-muebles/detalles-departamentos-muebles";
    }
    @GetMapping("/cambiarMueblesDepartamentos")
    public String showFormForUpdate(@RequestParam("id_departamento") int id_departamentos, Model theModel) {
        List <DepartamentosMuebles> departamentosMuebles = departamentoMuebleService.findMueblesByIdDepartamentos(id_departamentos);
        theModel.addAttribute("deparmueb", departamentosMuebles);
        return "departamentos-muebles/actualizar-departamentos-muebles";
    }

   @PostMapping("/eliminarAsignacion")
    public RedirectView eliminarAsignacionPorId(@RequestParam("id") int id) {
        departamentoMuebleService.eliminarAsigacionMueblesDepartamentosById(id);
        return new RedirectView("/departamentos");
   }

    }




