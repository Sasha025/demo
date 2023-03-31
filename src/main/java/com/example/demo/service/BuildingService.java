package com.example.demo.service;

import com.example.demo.entity.Building;
import com.example.demo.repository.IBuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingService implements IBuildingService{
    private final IBuildingRepository iBuildingRepository;

    @Autowired
    public BuildingService(IBuildingRepository iBuildingRepository){
        this.iBuildingRepository = iBuildingRepository;
    }
    @Override
    public List<Building> getAllBuildings() throws JAXBException{
        return iBuildingRepository.findAll();
    }
    @Override
    public Optional<Building> getBuildingByID(long id) throws JAXBException{
        return iBuildingRepository.findById(id);
    }
    @Override
    public Building createBuilding(Building building) throws JAXBException{
        return iBuildingRepository.save(building);
    }
    @Override
    public Building updateBuilding(Building building) throws JAXBException{
        return iBuildingRepository.save(building);
    }
    @Override
    public void deleteBuilding(long id){}
}
