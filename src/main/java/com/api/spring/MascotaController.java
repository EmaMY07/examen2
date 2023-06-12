package com.api.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;
import java.lang.String;
import java.lang.Object;



@Controller
@RequestMapping(path="/mascotas")
public class MascotaController {
  @Autowired
  private MascotaRepository mascotaRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @PostMapping(path="/add")
  public @ResponseBody String addNewMascota (@RequestParam String nombre,@RequestParam String raza, @RequestParam String propietario) {

    Mascota mascota = new Mascota();
    mascota.setNombre(nombre);
    mascota.setPropietario(propietario);
    mascota.setRaza(raza);
    mascotaRepository.save(mascota);
    return "Saved";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Mascota> getAllMascotas() {
    return mascotaRepository.findAll();
  }

  @PutMapping(path="/edit")
  public @ResponseBody String editMascota(@RequestParam Long id,@RequestParam String nombre,@RequestParam String raza, @RequestParam String propietario) {
    Mascota mascota = new Mascota();
    mascota.setId(id);
    mascota.setNombre(nombre);
    mascota.setPropietario(propietario);
    mascota.setRaza(raza);
    mascotaRepository.save(mascota);
    return "Updated";
  }

  @GetMapping(path="/ver/{id}")
  public @ResponseBody Mascota getOneMascota(@PathVariable Long id) {
    return mascotaRepository.findById(id).orElse(null);
  }

  @DeleteMapping(path="/del")
  public @ResponseBody String deleteMascota(@RequestParam Long id) {
    Mascota mascota = new Mascota();
    mascota.setId(id);
    mascotaRepository.delete(mascota);
    return "Deleted";
  }

  @GetMapping(path="/get/report")
  public @ResponseBody List reporte() {
    String sql = "SELECT CONCAT(nombre, ' ==> ', raza , ' ==> ', propietario) as mycol FROM mascota";
    List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
    return results;
  }





}