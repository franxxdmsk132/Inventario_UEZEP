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

import java.util.List;

@Controller
@RequestMapping("/depmub")
public class DepartamentosMueblesController {

    @Autowired
    private DepartamentoMuebleService departamentoMuebleService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private MuebleService muebleService;

    @ModelAttribute("allDepartamentos")
    public List<Departamentos> populateDepartamentos() {
        return departamentoService.findAll();
    }

    @ModelAttribute("allMuebles")
    public List<Muebles> populateMuebles() {
        return muebleService.findAll();
    }

    @GetMapping("/lista")
    public String listaDepartamentosMuebles(Model model) {
        List<DepartamentosMuebles> departamentosMuebles = departamentoMuebleService.findAll();
        model.addAttribute("departamentosmueble", departamentosMuebles);
        return "departamentos-muebles/lista-departamentos-muebles";
    }

    @GetMapping("/nuevoMuebleDepartamento")
    public String mostrarFormulario() {
        return "departamentos-muebles/guardar-departamentos-muebles";
    }

    @RequestMapping("/masDetalles")
    public String viewAula(@RequestParam("id_departamento") int id_departamento, Model model) {
        List<DepartamentosMuebles> departamentosMueblesList = departamentoMuebleService.findDetailsByDepartamentoIdWrapper(id_departamento);
        model.addAttribute("deparmueb", departamentosMueblesList);
        return "departamentos-muebles/detalles-departamentos-muebles";
    }

    @GetMapping("/cambiarMueblesDepartamentos")
    public String showFormForUpdate(@RequestParam("id_departamento") int id_departamento, Model theModel) {
        List<DepartamentosMuebles> departamentosMuebles = departamentoMuebleService.findDetailsByDepartamentoIdWrapper(id_departamento);
        theModel.addAttribute("deparmueb", departamentosMuebles);
        return "departamentos-muebles/actualizar-departamentos-muebles";
    }

    @PostMapping("/save")
    public String asignarMueblesAAulaConCantidad2(@RequestParam("id_departamento") int idDepartamento,
                                                  @RequestParam("id_mueble") String[] idMuebles,
                                                  @RequestParam("cantidad") String[] cantidades) {
        try {
            departamentoMuebleService.asignarMueblesADepartamentoConCantidad(idDepartamento, idMuebles, cantidades);
            return "redirect:/depmub/lista";
        } catch (NumberFormatException e) {
            return "Error: Las cantidades deben ser números enteros válidos.";
        } catch (Exception e) {
            return "Error al asignar los muebles al departamento: " + e.getMessage();
        }
    }



    @PostMapping("/eliminarAsignacion")
    public RedirectView eliminarAsignacionPorId(@RequestParam("id") int id) {
        departamentoMuebleService.eliminarAsignacionMuebleDepartamentoById(id);
        return new RedirectView("/depmub/lista", true);
    }

    @PostMapping("/eliminar")
    public RedirectView eliminarAsignacionPorIdRegresarCantidad(@RequestParam("id") int id) {
        departamentoMuebleService.eliminarAsignacionMuebleDepartamentoByIdRegresarCantidad(id);
        return new RedirectView("/depmub/lista", true);
    }
}
