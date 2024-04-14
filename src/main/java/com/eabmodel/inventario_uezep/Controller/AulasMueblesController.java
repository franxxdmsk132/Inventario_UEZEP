package com.eabmodel.inventario_uezep.Controller;

import com.eabmodel.inventario_uezep.Entity.Aulas;
import com.eabmodel.inventario_uezep.Entity.AulasMuebles;
import com.eabmodel.inventario_uezep.Service.AulasMueblesService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/mostrar-formulario")
    public String mostrarFormulario() {
        return "aulas-muebles/aulas-muebles";
    }

    //HTML
    //Listar
    @GetMapping("/lista")
    public String listaAulasMuebles(Model model) {
        List<AulasMuebles> aulasMuebles = aulasMueblesService.findAll();
        model.addAttribute("aulasmuebles", aulasMuebles);
        return "aulas-muebles/aulas-muebles-lista";
    }

    @PostMapping("/asignar-muebles-a-aula")
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

    // Otros métodos del controlador, si es necesario
}
