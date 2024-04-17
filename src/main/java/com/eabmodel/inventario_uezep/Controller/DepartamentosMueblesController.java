package com.eabmodel.inventario_uezep.Controller;


import com.eabmodel.inventario_uezep.Entity.DepartamentosMuebles;
import com.eabmodel.inventario_uezep.Service.DepartamentoMuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/deffmub")
public class DepartamentosMueblesController {

    @Autowired
    private DepartamentoMuebleService departamentoMuebleService;

    @GetMapping("/mostrar-formualrio")
    public String mostrarFormulario(){
        return "departamentos-muebles/departamentos-muebles";}

    @GetMapping("/lista")
    public String listaDepartamentosMuebles(Model model){
        List<DepartamentosMuebles>departamentosMuebles = departamentoMuebleService.findAll();
        model.addAttribute("departamentosmuebles", departamentosMuebles);
        return "departamentos-muebles/departamentos-muebles-lista";
    }
    @PostMapping("/asignar-muebles-d-departamentos")
    @ResponseBody
    public String asignarMuebleDepartamento(@RequestParam("idDepartamentos") int idDepartamento, @RequestParam("idMuebles")String idMuebles){
        try {
            List<Integer> mueblesIds = Arrays.stream(idMuebles.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            departamentoMuebleService.asiganarMuebleDepartamento(idDepartamento, mueblesIds);
            return "Muebles asignados correctamente, en el departamento.";

        } catch (Exception e){
            return "Error, al momento de asiganar los muebles en el departamento: " + e.getMessage();
        }
    }

}
