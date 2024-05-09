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

    //Formulario para guardar
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

    //Formulario para Actualizar
    @GetMapping("/cambiarMueblesAulas")
    public String showFormForUpdate(@RequestParam("id_aula") int id_aula, Model theModel) {

        List<AulasMuebles> aulasMuebles = aulasMueblesService.findMueblesByIdAula(id_aula);

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

    //Actualizar muebles en aula
    @PostMapping("/update")
    @ResponseBody
    public String actualizarAsignacionMueblesAula(@RequestParam("id_aula") int idAula,
                                                  @RequestParam("id_mueble") int[] idMuebles,
                                                  @RequestParam("cantidad") int[] cantidades) {
        try {
            // Verificar si se están agregando, quitando o actualizando la cantidad de muebles asignados
            for (int i = 0; i < idMuebles.length; i++) {
                int idMueble = idMuebles[i];
                int cantidad = cantidades[i];

                // Si la cantidad es cero, eliminar la asignación del mueble en el aula
                if (cantidad == 0) {
                    aulasMueblesService.eliminarAsignacionMuebleAula(idAula, idMueble);
                } else {
                    // Si la cantidad es mayor que cero, actualizar o agregar la asignación del mueble en el aula
                    aulasMueblesService.updateAulasMueblesAsignacion(idAula, idMueble, cantidad);
                }
            }

            // Devolver mensaje de éxito
            return "Asignación de muebles actualizada correctamente.";
        } catch (Exception e) {
            // Manejar cualquier error que pueda ocurrir durante la actualización de la asignación de muebles en el aula
            return "Error al actualizar la asignación de muebles en el aula: " + e.getMessage();
        }
    }

    //Eliminar
//    @PostMapping("/eliminarAsignacion")
//    public ResponseEntity<String> eliminarAsignacionPorId(@RequestParam("id") int id) {
//        aulasMueblesService.eliminarAsignacionMuebleAulaById(id);
//        return ResponseEntity.ok("Asignación eliminada exitosamente");
//    }
    @PostMapping("/eliminarAsignacion")
    public RedirectView eliminarAsignacionPorId(@RequestParam("id") int id) {
        aulasMueblesService.eliminarAsignacionMuebleAulaById(id);
        return new RedirectView("/defmub/lista", true);
    }



    //
}
