package cryptostarwars.moteur_binaire.boxes;

import cryptostarwars.moteur_binaire.MotBinaire;
import java.util.BitSet;


/**
 * Box de Permutation
 */
public class PBox implements IBox {

    @Override
    public MotBinaire appliquer(MotBinaire entree) {
         //TODO
         MotBinaire messagePermuter = new MotBinaire(new BitSet(), 8);
         messagePermuter.getBitSet().set(0, entree.getBitSet().get(2));
         messagePermuter.getBitSet().set(1, entree.getBitSet().get(4));
         messagePermuter.getBitSet().set(2, entree.getBitSet().get(6));
         messagePermuter.getBitSet().set(3, entree.getBitSet().get(0));
         messagePermuter.getBitSet().set(4, entree.getBitSet().get(5));
         messagePermuter.getBitSet().set(5, entree.getBitSet().get(1));
         messagePermuter.getBitSet().set(6, entree.getBitSet().get(7));
         messagePermuter.getBitSet().set(7, entree.getBitSet().get(3));
         return messagePermuter;
    }

}
