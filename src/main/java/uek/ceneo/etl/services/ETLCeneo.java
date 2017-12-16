package uek.ceneo.etl.services;

import org.springframework.stereotype.Service;
import uek.ceneo.etl.utils.scrapper.Scrapper;

@Service("ETLCeneoService")
public class ETLCeneo implements ETL {
    @Override
    public void extract(String id) {
        Scrapper ceneoScrapper = new Scrapper(id);
        System.out.println(ceneoScrapper.getDocument());
    }

    @Override
    public void transform(String id) {

    }

    @Override
    public void load(String id) {

    }
}
