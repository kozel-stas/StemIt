package com.stemmer.rest;

import com.stemmer.service.StemService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stem")
public class StemController {

    private final StemService ruStemService;

    public StemController(StemService ruStemService) {
        this.ruStemService = ruStemService;
    }

    @RequestMapping(
            value = "/ru",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Map<String, String> stem(@RequestParam("words") String[] words) {
        Map<String, String> result = new HashMap<>(words.length);
        for (String word : words) {
            result.put(word, ruStemService.stem(word));
        }
        return result;
    }

}
