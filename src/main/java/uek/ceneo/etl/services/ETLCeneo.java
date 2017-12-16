package uek.ceneo.etl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uek.ceneo.etl.utils.scrapper.Scrapper;

@Service("ETLCeneoService")
public class ETLCeneo implements ETL {

    private final FileService fileService;


    @Autowired
    public ETLCeneo(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void extract(String id) {
        Scrapper ceneoScrapper = new Scrapper(id);
        fileService.write(id + ".html", ceneoScrapper.getDocument());
    }

    @Override
    public void transform(String id) {

    }

    @Override
    public void load(String id) {

    }
}
