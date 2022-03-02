package dk.bec.bookanything.controller;

import dk.bec.bookanything.dto.RoleCreateDto;
import dk.bec.bookanything.dto.RoleReadDto;
import dk.bec.bookanything.mapper.RoleMapper;
import dk.bec.bookanything.model.RoleEntity;
import dk.bec.bookanything.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@ResponseBody
@RequestMapping("/api")
public class RoleController {


    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    //TODO should the methods be public?

    @GetMapping("/roles")
    public ResponseEntity<List<RoleReadDto>> getRoles() {
        List<RoleReadDto> res = roleService.getRoles().stream().map(roleMapper::roleEntityToDto).collect(Collectors.toList());
        if (res.size() > 0) return new ResponseEntity<>(res, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/roles/{id}")
    public ResponseEntity<RoleReadDto> getRoleById(@PathVariable("id") Long id) {
        Optional<RoleReadDto> res = roleService.getRole(id).map(roleMapper::roleEntityToDto);
        return res.map(roleReadDto -> new ResponseEntity<>(roleReadDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/roles")
    public ResponseEntity<RoleReadDto> createRole(@RequestBody RoleCreateDto roleCreateDto) {
        RoleEntity roleEntity = roleMapper.roleDtoToEntity(roleCreateDto);
        Optional<RoleReadDto> res = roleService.createRole(roleEntity).map(roleMapper::roleEntityToDto);
        return res.map(roleReadDto -> new ResponseEntity<>(roleReadDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/roles/{id}")
    public void deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<RoleReadDto> updateRole(@PathVariable("id") Long id, @RequestBody RoleCreateDto roleCreateDto) {
        RoleEntity roleEntity = roleMapper.roleDtoToEntityWhenModified(roleCreateDto, id);
        Optional<RoleReadDto> res = roleService.updateRole(roleEntity).map(roleMapper::roleEntityToDto);
        return res.map(roleReadDto -> new ResponseEntity<>(roleReadDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
