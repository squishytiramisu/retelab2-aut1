package hu.bme.aut.retelab2.controller;


import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import hu.bme.aut.retelab2.utils.SecretGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;


//LC3M9F
@RestController
@RequestMapping("/api/ads")
public class AdController {

    @Autowired
    private AdRepository adRepository;

    @PostMapping
    public Ad create(@RequestBody Ad ad) {
        ad.setId(null);
        ad.setSecret(SecretGenerator.generate());
        return adRepository.save(ad);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Ad ad){
        try{
            updateAd(ad);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Ad>> findByPriceRange(
            @RequestParam(name = "minPrice") Optional<Integer> minPrice,
            @RequestParam(name = "maxPrice") Optional<Integer> maxPrice){


        return ResponseEntity.ok(
            adRepository.findByPriceRange(
                    minPrice.orElse(0),
                    maxPrice.orElse(10000))
        );
    }

    @GetMapping("{tag}")
    public List<Ad> findByTag(@PathVariable String tag){
        return adRepository.findByTag(tag);
    }

    public Ad updateAd(Ad ad){
        Ad foundAd = adRepository.findById(ad.getId());
        if(!foundAd.getSecret().equals(ad.getSecret())){
            throw new RuntimeException();
        }
        return adRepository.save(ad);
    }
}
