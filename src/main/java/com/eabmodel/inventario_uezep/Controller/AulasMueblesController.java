package com.eabmodel.inventario_uezep.Controller;

import com.eabmodel.inventario_uezep.Entity.Aulas;
import com.eabmodel.inventario_uezep.Entity.AulasMuebles;
import com.eabmodel.inventario_uezep.Entity.Muebles;
import com.eabmodel.inventario_uezep.Service.AulaService;
import com.eabmodel.inventario_uezep.Service.AulasMueblesService;
import com.eabmodel.inventario_uezep.Service.MuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/defmub")
public class AulasMueblesController {

    @Autowired
    private AulasMueblesService aulasMueblesService;
    @Autowired
    private AulaService aulaService;
    @Autowired
    private MuebleService muebleService;


    @ModelAttribute("allAulas")
    public List<Aulas> populateAulas() {
        return aulaService.findAll();
    }

    @ModelAttribute("allMuebles")
    public List<Muebles> populateMuebles() {
        return muebleService.findAll();
    }

    //HTML
    //Mostrar Listado
    @GetMapping("/lista")
    public String listaAulasMuebles(Model model) {
        List<AulasMuebles> aulasMuebles = aulasMueblesService.findAll();
        model.addAttribute("aulasmueble", aulasMuebles);
        return "aulas-muebles/lista-aulas-muebles";
    }

    //Mostrar Formulario para guardar
    @GetMapping("/nuevoMuebleAula")
    public String mostrarFormulario() {
        return "aulas-muebles/guardar-aulas-muebles";
    }

    //Mostrar Muebles Asignados
    @RequestMapping("/masDetalles")
    public String viewAula(@RequestParam("id_aula") int id_aula, Model model) {
        List<AulasMuebles> aulasMueblesList = aulasMueblesService.findDetailsByAulaIdWrapper(id_aula);
        model.addAttribute("aulmue", aulasMueblesList);
        System.out.println("Aulas con cantidad" + aulasMueblesList);
        return "aulas-muebles/detalles-aulas-muebles";
    }
    //Mostrar Formulario para Actualizar
    @GetMapping("/cambiarMueblesAulas")
    public String showFormForUpdate(@RequestParam("id_aula") int id_aula, Model theModel) {
        List<AulasMuebles> aulasMuebles = aulasMueblesService.findDetailsByAulaIdWrapper(id_aula);
        theModel.addAttribute("aulasmue", aulasMuebles);
        return "aulas-muebles/actualizar-aulas-muebles";
    }


    //----------------------------------------------------------------------------------------
    //Funciones
    //Guardar muebles en aula
    @PostMapping("/save")
    public String asignarMueblesAAulaConCantidad(@RequestParam("id_aula") int idAula,
                                                 @RequestParam("id_mueble") String[] idMuebles,
                                                 @RequestParam("cantidad") String[] cantidades) {
        try {
            // Llamar al método del servicio para asignar los muebles al aula con las cantidades correspondientes
            aulasMueblesService.asignarMueblesAAulaConCantidad2(idAula, idMuebles, cantidades);

            // Devolver la redirección al listado de muebles
            return "redirect:/defmub/lista";
        } catch (NumberFormatException e) {
            // Manejar el error de conversión de cadenas a enteros
            return "Error: Las cantidades deben ser números enteros válidos.";
        } catch (Exception e) {
            // Manejar cualquier otra excepción que pueda ocurrir durante la asignación de muebles al aula
            return "Error al asignar los muebles al aula: " + e.getMessage();
        }
    }

    //Eliminar Muebles Asignados
    @PostMapping("/eliminarAsignacion")
    public RedirectView eliminarAsignacionPorId(@RequestParam("id") int id) {
        aulasMueblesService.eliminarAsignacionMuebleAulaById(id);
        return new RedirectView("/defmub/lista", true);
    }


    //
}
