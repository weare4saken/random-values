package com.weare4saken.random.values.rest;


import com.weare4saken.random.values.rest.converter.RandomsConverters;
import com.weare4saken.random.values.rest.model.in.ValueRestIn;
import com.weare4saken.random.values.rest.model.out.ValueRestOut;
import com.weare4saken.random.values.service.RandomValuesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/1/random/value")
@RequiredArgsConstructor
public class ValueController implements RandomsConverters {

    private final RandomValuesService randomValuesService;

    @GetMapping("/by/{categoryId}")
    ValueRestOut getValueByCategory(@PathVariable UUID categoryId){
        return convert(
                randomValuesService.getValueByCategoryId(categoryId)
        );
    }

    @PostMapping("/add")
    ValueRestOut addNewValue(@RequestBody ValueRestIn valueRestIn) {
        return convert(
                randomValuesService.addNewValue(
                        convert(valueRestIn)
                )
        );
    }

    @PutMapping("/update/{valueId}")
    ValueRestOut updateValue(@RequestBody ValueRestIn valueRestIn, @PathVariable UUID valueId) {
        return convert(
                randomValuesService.updateValue(
                        convert(valueRestIn, valueId)
                )
        );
    }

    @DeleteMapping("/delete/{valueId}")
    void deleteValue(@PathVariable UUID valueId) {
        randomValuesService.deleteValue(valueId);
    }

}
