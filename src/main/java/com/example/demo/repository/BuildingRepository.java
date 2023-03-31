package com.example.demo.repository;

import com.example.demo.entity.Building;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Optional;

@Repository
public class BuildingRepository implements IBuildingRepository {
    private final String XML_FILE_PATH = "C:\\Users\\miste\\Desktop\\demo\\src\\main\\resources\\BuildingFile.xml";
    private final JAXBContext context;
    public BuildingRepository(){
        try {
            context= JAXBContext.newInstance(Building.class);
        } catch (JAXBException e){
            throw new RuntimeException(e);
        }
    }
    public static class BuildingList{
        private List<Building> buildings = new ArrayList<>();
        public List<Building> getBuildings(){
            return buildings;
        }
        public void setBuildings(List<Building> buildings){
            this.buildings=buildings;
        }
    }
    @Override
    public List<Building> findAll() throws JAXBException{
        Unmarshaller unmarshaller = context.createUnmarshaller();
        BuildingList buildings = (BuildingList) unmarshaller.unmarshal(new File(XML_FILE_PATH));
        return buildings.getBuildings();
    }
    @Override
    public Optional<Building> findById(long id) throws JAXBException{
        List<Building> buildings = findAll();
        return buildings.stream().filter(i->i.getId()==id).findFirst();
    }
    @Override
    public Building save(Building building) throws JAXBException{
        List<Building> buildings = findAll();
        long maxId = buildings.stream().mapToLong(Building::getId).max().orElse(0);
        building.setId(maxId + 1);
        buildings.add(building);
        saveAll(buildings);
        return building;
    }
    @Override
    public Building delete(long id) throws JAXBException{
        List<Building> buildings = findAll();
        Optional<Building> buildingToDelete = buildings.stream().filter(i->i.getId()==id).findFirst();
        if (buildingToDelete.isPresent()){
            Building deletedBuilding = buildingToDelete.get();
            buildings.remove(deletedBuilding);
            saveAll(buildings);
            return deletedBuilding;
        }
        else {
            return null;
        }

    }
    private void saveAll(List<Building> buildings) throws JAXBException{
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(buildings, new File(XML_FILE_PATH));
    }
}
