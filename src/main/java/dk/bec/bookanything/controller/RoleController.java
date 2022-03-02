package dk.bec.bookanything.controller;

import dk.bec.bookanything.model.RoleEntity;
import dk.bec.bookanything.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@ResponseBody
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleEntity>> getRoles(){
        List<RoleEntity> res = roleService.getRoles();
        if(res.size()>0) return new ResponseEntity<>(res, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RoleEntity> getRoleByUUID(@PathVariable("id") Long id){
        Optional<RoleEntity> res = roleService.getRole(id);
        return res.isPresent()? new ResponseEntity<RoleEntity>(res.get(), HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<RoleEntity> createRole(@RequestBody RoleEntity roleEntity){
        Optional<RoleEntity> res = roleService.createRole(roleEntity);
        return res.isPresent()? new ResponseEntity<RoleEntity>(res.get(), HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") Long id){
        roleService.deleteRole(id);
    }

    @PutMapping
    public ResponseEntity<RoleEntity> updateRole(@RequestBody RoleEntity roleEntity){
        Optional<RoleEntity> res =roleService.updateRole(roleEntity);
        return res.isPresent()? new ResponseEntity<RoleEntity>(res.get(), HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
