package com.example.demo.controller;


import com.example.demo.entity.Building;
import com.example.demo.service.IBuildingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("buildings")
public class BuildingConroller {
    private IBuildingService iBuildingService;
    @Autowired
    public BuildingConroller(IBuildingService iBuildingService){
        this.iBuildingService=iBuildingService;
    }
    @GetMapping
    public List<Building> list() throws JAXBException{
        return iBuildingService.getAllBuildings();
    }
    @GetMapping("{id}")
    public Optional<Building> getOne(@PathVariable("id") Building building) throws JAXBException{
        return iBuildingService.getBuildingByID(building.getId());
    }
    @PostMapping
    public Building create(@RequestBody Building building) throws JAXBException{
        return iBuildingService.createBuilding(building);
    }
    @PutMapping("{id}")
    public Building update(@PathVariable("id")Building buildingFromFile,
                           @RequestBody Building building) throws JAXBException{
        BeanUtils.copyProperties(building,buildingFromFile, "id");
        return iBuildingService.updateBuilding(buildingFromFile);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Building building){
        iBuildingService.deleteBuilding(building.getId());
    }
}
