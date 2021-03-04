package cryptostarwars.moteur_binaire.boxes;

import cryptostarwars.moteur_binaire.MotBinaire;


/**
 * Interface des boxs (SBox et PBox)
 */
public interface IBox {
    public MotBinaire appliquer(MotBinaire entree);
}
