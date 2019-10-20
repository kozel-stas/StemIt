package com.stemmer.rest;

import com.stemmer.service.StemService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stem/v2")
public class StemControllerV2 {

    private final StemService ruStemService;

    public StemControllerV2(StemService ruStemService) {
        this.ruStemService = ruStemService;
    }

    @RequestMapping(
            value = "/ru",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public List<StemResult> stemGet(@RequestParam("words") String[] words) {
        return stemWords(words);
    }

    @RequestMapping(
            value = "/ru",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public List<StemResult> stemPost(String[] words) {
        return stemWords(words);
    }

    private List<StemResult> stemWords(String[] words) {
        List<StemResult> results = new ArrayList<>();
        Map<String, String> stem = new HashMap<>(words.length);
        Map<String, Integer> baseWordNumber = new HashMap<>(words.length);
        for (String word : words) {
            String base = ruStemService.stem(word);
            stem.put(word, base);
            baseWordNumber.computeIfPresent(base, (k, v) -> ++v);
            baseWordNumber.putIfAbsent(base, 1);
        }
        for (Map.Entry<String, String> entry : stem.entrySet()) {
            results.add(new StemResult(new ImmutablePair<>(entry.getKey(), entry.getValue()), baseWordNumber.get(entry.getValue())));
        }
        return results;
    }

}
