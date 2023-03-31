package com.example.demo.repository;

import com.example.demo.entity.Building;
import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Optional;

public interface IBuildingRepository {
    List<Building> findAll() throws JAXBException;
    Optional<Building> findById(long id) throws JAXBException;
    Building save(Building building) throws JAXBException;
    Building delete(long id) throws JAXBException;
}
