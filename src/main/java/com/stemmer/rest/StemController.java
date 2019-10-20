package com.stemmer.rest;

import com.stemmer.service.StemService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Map<String, String> stemGet(@RequestParam("words") String[] words) {
        return stemWords(words);
    }

    @RequestMapping(
            value = "/ru",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Map<String, String> stemPost(String[] words) {
        return stemWords(words);
    }

    private Map<String, String> stemWords(String[] words) {
        Map<String, String> result = new HashMap<>(words.length);
        for (String word : words) {
            result.put(word, ruStemService.stem(word));
        }
        return result;
    }

}
