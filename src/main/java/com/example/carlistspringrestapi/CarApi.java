package com.example.carlistspringrestapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cars", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CarApi {

    private List<Car> carList;

    public CarApi() {
        this.carList = new ArrayList<>();
        carList.add(new Car(1L, "VW", "Touran", "Black"));
        carList.add(new Car(2L, "Honda", "Cvic", "Red"));
        carList.add(new Car(3L, "Ford", "Focus", "Blue"));
    }

    //get all
    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    //get one by id
    @GetMapping("/id/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> first = carList.stream()
                .filter(car -> car.getId() == id)
                .findFirst();

        if (first.isPresent()) {
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //add
    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        boolean add = carList.add(car);
        if (add) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //get by color
    @GetMapping("/color/{color}")
    public ResponseEntity<Car> getCarByColor(@PathVariable String color) {
        Optional<Car> first = carList.stream()
                .filter(car -> car.getColor().equals(color))
                .findFirst();

        if (first.isPresent()) {
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //edit car
    @PutMapping
    public ResponseEntity<Car> editCar(@RequestBody Car newCar) {

        Optional<Car> first = carList.stream()
                .filter(car -> car.getId() == newCar.getId())
                .findFirst();
        if (first.isPresent()) {
            carList.remove(first.get());
            carList.add(newCar);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    //edit one field from car
//    @PutMapping("/id{id}{field}")
//    public ResponseEntity<Car> editFieldCar(@PathVariable Long id, @PathVariable String field) {
//
//        Optional<Car> first = carList.stream()
//                .filter(carr -> carr.getId() == id)
//                .findFirst();
//        if (first.isPresent()) {
//            carList.get(Math.toIntExact(id)).;
//
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    //delete car
    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCarById(@PathVariable Long id) {
        Optional<Car> first = carList.stream()
                .filter(car -> car.getId() == id)
                .findFirst();

        if (first.isPresent()) {
            carList.remove(first.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
