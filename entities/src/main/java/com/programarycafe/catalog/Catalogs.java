package com.programarycafe.catalog;

import java.util.List;

import com.programarycafe.model.Item;

public class Catalogs {


    public List<Item> SpeciesCatalog(){        
        return List.of(
            new Item("Escherichia coli", Items.SPECIE.getDescription()), 
            new Item("Homo Sapiens", Items.SPECIE.getDescription()),
            new Item("Canis lupus", Items.SPECIE.getDescription()),
            new Item("Canis lupus familiaris", Items.SPECIE.getDescription()),
            new Item("Panthera leo", Items.SPECIE.getDescription()),
            new Item("Panthera tigris", Items.SPECIE.getDescription()));
    }

    public List<Item> MedicinesCatalog(){
        return  List.of(    
            new Item("Anemia", Items.MEDICINE.getDescription()), 
            new Item("Diabetes mellitus", Items.MEDICINE.getDescription()),
            new Item("Hipertensión arterial", Items.MEDICINE.getDescription()),
            new Item("Hipertensión arterial", Items.MEDICINE.getDescription()),
            new Item("Osteoporosis", Items.MEDICINE.getDescription()),
            new Item("Gastritis", Items.MEDICINE.getDescription()),
            new Item("Bronquitis", Items.MEDICINE.getDescription()),
            new Item("Trombocitopenia", Items.MEDICINE.getDescription()),
            new Item("Colecistectomía", Items.MEDICINE.getDescription()),
            new Item("Electrocardiograma", Items.MEDICINE.getDescription()),
            new Item("Gastroenterocolitis", Items.MEDICINE.getDescription()),
            new Item("Esplenomegalia", Items.MEDICINE.getDescription()),
            new Item("Otorrinolaringología", Items.MEDICINE.getDescription()),
            new Item("Electroencefalografista", Items.MEDICINE.getDescription()),
            new Item("Pancreatoduodenectomía", Items.MEDICINE.getDescription()),
            new Item("Feocromocitoma", Items.MEDICINE.getDescription()),
            new Item("Hipogammaglobulinemia", Items.MEDICINE.getDescription()),
            new Item("Craniopharyngioma", Items.MEDICINE.getDescription()),
            new Item("Trastorno esquizoafectivo", Items.MEDICINE.getDescription()),
            new Item("Lymphangioleiomyomatosis", Items.MEDICINE.getDescription()),
            new Item("Pseudopseudohipoparatiroidismo", Items.MEDICINE.getDescription()),
            new Item("Methylenedioxymethamphetamine", Items.MEDICINE.getDescription()),
            new Item("Osteocondrodistrofia", Items.MEDICINE.getDescription()),
            new Item("Neurofibromatosis tipo I", Items.MEDICINE.getDescription()));
    }
}
