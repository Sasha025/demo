package com.example.demo.service;

import com.example.demo.entity.Building;
import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Optional;


public interface IBuildingService {
    List<Building> getAllBuildings() throws JAXBException;
    Optional<Building> getBuildingByID(long id) throws JAXBException;
    Building createBuilding(Building building) throws JAXBException;
    Building updateBuilding(Building building) throws JAXBException;
    void deleteBuilding(long id);
}
