package com.eabmodel.inventario_uezep.Controller;

import com.eabmodel.inventario_uezep.Entity.Aulas;
import com.eabmodel.inventario_uezep.Entity.AulasMuebles;
import com.eabmodel.inventario_uezep.Entity.Muebles;
import com.eabmodel.inventario_uezep.Service.AulaService;
import com.eabmodel.inventario_uezep.Service.AulasMueblesService;
import com.eabmodel.inventario_uezep.Service.MuebleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
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



    @GetMapping("/show")
    public String mostrarFormulario() {
        return "aulas-muebles/aulas-muebles";
    }

    @ModelAttribute("allAulas")
    public List<Aulas> populateAulas() {
        return aulaService.findAll();
    }
    @ModelAttribute("allMuebles")
    public List<Muebles> populateMuebles() {
        return muebleService.findAll();
    }

    //HTML
    //Listar
    @GetMapping("/lista")
    public String listaAulasMuebles(Model model) {
        List<AulasMuebles> aulasMuebles = aulasMueblesService.findAll();
        model.addAttribute("aulasmueble", aulasMuebles);
        return "aulas-muebles/lista-aulas-muebles";
    }
    @RequestMapping("/masDetalles")
    public String viewAula(@RequestParam("id_aula") int id_aula, Model model) {
        List<AulasMuebles> aulasMueblesList = aulasMueblesService.findDetailsByAulaIdWrapper(id_aula);
        model.addAttribute("aulmue", aulasMueblesList);
        return "aulas-muebles/detalles-aulas-muebles";
    }
    @RequestMapping(value = "/masDetalles2", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AulasMuebles> viewAula(@RequestParam("id_aula") int id_aula) {
        return aulasMueblesService.findDetailsByAulaIdWrapper(id_aula);
    }


    @PostMapping("/save")
    @ResponseBody
    public String asignarMueblesAAula(@RequestParam("idAula") int idAula, @RequestParam("idMuebles") String idMuebles) {
        try {
            // Convertir la cadena de IDs de muebles a una lista de enteros
            List<Integer> mueblesIds = Arrays.stream(idMuebles.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            // Llamar al método del servicio para asignar los muebles al aula
            aulasMueblesService.asignarMueblesAAula(idAula, mueblesIds);
            return "Muebles asignados correctamente al aula.";
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir durante la asignación de muebles al aula
            return "Error al asignar los muebles al aula: " + e.getMessage();
        }
    }
//    @PostMapping("/save2")
//    @ResponseBody
//    public String asignarMueblesAAulaConCantidad(@RequestParam("id_aula") int idAula,
//                                                 @RequestParam("cantidad") int cantidadTotal,
//                                                 @RequestParam("id_mueble") String idMuebles) {
//        try {
//            // Convertir la cadena de IDs de muebles a una lista de enteros
//            List<Integer> mueblesIds = Arrays.stream(idMuebles.split(","))
//                    .map(Integer::parseInt)
//                    .collect(Collectors.toList());
//
//            // Llamar al método del servicio para asignar los muebles al aula con la cantidad total
//            aulasMueblesService.asignarMueblesAAulaConCantidad(idAula, cantidadTotal, mueblesIds);
//
//            return "Muebles asignados correctamente al aula.";
//        } catch (Exception e) {
//            // Manejar cualquier excepción que pueda ocurrir durante la asignación de muebles al aula
//            return "Error al asignar los muebles al aula: " + e.getMessage();
//        }
//    }
@PostMapping("/save2")
@ResponseBody
public String asignarMueblesAAulaConCantidad(@RequestParam("id_aula") int idAula,
                                             @RequestParam("id_mueble") String[] idMuebles,
                                             @RequestParam("cantidad") String[] cantidades) {
    try {
        // Llamar al método del servicio para asignar los muebles al aula con las cantidades correspondientes
        aulasMueblesService.asignarMueblesAAulaConCantidad2(idAula, idMuebles, cantidades);

        // Devolver mensaje de éxito solo si no hay excepciones
        return "Muebles asignados correctamente al aula.";
    } catch (NumberFormatException e) {
        // Manejar el error de conversión de cadenas a enteros
        return "Error: Las cantidades deben ser números enteros válidos.";
    } catch (Exception e) {
        // Manejar cualquier otra excepción que pueda ocurrir durante la asignación de muebles al aula
        return "Error al asignar los muebles al aula: " + e.getMessage();
    }
}





    // Otros métodos del controlador, si es necesario
}
