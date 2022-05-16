package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatCardNotFoundException;
import com.techelevator.model.CatFact;
import com.techelevator.model.CatPic;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/cards")
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    @Autowired
    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }

    @GetMapping("random")
    public CatCard getRandom(){
        CatCard catCard = new CatCard();

        // Use the API service to get a random cat picture
        CatPic picture = catPicService.getPic();
        String imgUrl = picture.getFile();
        catCard.setImgUrl(imgUrl);

        // Use the API service to get a random cat fact
        CatFact fact = catFactService.getFact();
        String catFact = fact.getText();
        catCard.setCatFact(catFact);

        return catCard;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public boolean save(@Valid @RequestBody CatCard catCard){
        return catCardDao.save(catCard);
    }

    @GetMapping("{id}")
    public CatCard getACard(@PathVariable int id){
        return catCardDao.get(id);
    }

    @GetMapping
    public List<CatCard> getCatCardAll(){
        return catCardDao.list();
    }

    @PutMapping("{id}")
    public boolean update(@Valid @RequestBody CatCard catCard, @PathVariable int id) throws CatCardNotFoundException{
        return catCardDao.update(id, catCard);
    }

    @ResponseStatus (HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public boolean delete(@PathVariable int id) throws CatCardNotFoundException {
        return catCardDao.delete(id);
    }



}
