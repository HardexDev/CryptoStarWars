package cryptostarwars.moteur_binaire;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * Représentation d'un mot binaire
 */
public class MotBinaire {

    private BitSet listeBits;                   //Liste des bits
    private int taille;                         //Nombre de bits
    
    //Constructeurs standard
    public MotBinaire() {
        this.listeBits = new BitSet();
        this.taille = 0;
    }
	
    //Constructeur par paramètres avec clonage du bitset
    public MotBinaire(BitSet listeBits,int taille) {
        this.listeBits = new BitSet();
        this.taille = taille;
        for(int i=0;i<this.taille;i++) {
            this.listeBits.set(i,listeBits.get(i));
        } 
    }
	
    //Constructeur à partir d'un long
    public MotBinaire(long valeur) {
        //TODO
        this.taille = 32;
        long[] tab = new long[1];
        tab[0] = valeur;
        this.listeBits = BitSet.valueOf(tab);
    }
    
    //Constructeur à partir d'un byte
    public MotBinaire(byte b) {
        //TODO
        byte[] tab = new byte[1];
        tab[0] = b;
        this.taille = 8;
        this.listeBits = BitSet.valueOf(tab);
    }
    
    //Constructeur à partir d'un caractère (UTF-8)
    public MotBinaire(char c) {
        //TODO
        this.taille = 8;
        long code = (long)c;
        long[] tab = new long[1];
        tab[0] = code;
        this.listeBits = BitSet.valueOf(tab);
    }
    
    //Constructeur à partir d'une succession de 1 et de 0 
    public MotBinaire(String S) {
        this();
        this.taille = S.length();
        for(int i=0;i<this.taille;i++) {
            this.listeBits.set(this.taille - i - 1, S.charAt(i) == '1');
        }
    }
    
    /**
     * Getter de la liste des bits
     * @return le BitSet
     */
    public BitSet getBitSet() {
        return this.listeBits;
    }
	
	
    /**
     * Getter de la taille
     * @return la taille
     */
    public int getTaille() {
        return this.taille;
    }
     
    
    /**
     * Convertion en entier non signé
     * @return un entier
     */
    public int asInteger() {
        //TODO
        int res = 0;
        for (int i=0; i<this.taille; i++) {
            int bitValue = (this.listeBits.get(i)) ? 1 : 0;
            res += bitValue * Math.pow(2, i);
        }
        return res;
    }
    /**
     * Interprète le MotBinaire comme une succession de caractère encodé chacun sur 8bits (UTF-8)
     * @return une chaine de caractères
     */
    public String asString()  {
        //TODO
        String s = "";
        BitSet b = new BitSet();
        int step = 0;
        for (int i=0; i<this.taille/8; i++) {
            for (int j=0; j<8; j++) {
                b.set(j, this.listeBits.get(j+step));
            }
            MotBinaire m = new MotBinaire(b, 8);
            s += (char) m.asInteger();
            step += 8;
            b = new BitSet();
        }

        String res = "";

        for (int i=s.length()-1; i>=0; i--) {
            res += s.charAt(i);
        }

        return res;
    }
    
    //Affichage en binaire (i.e : 6 -> "110")
    @Override
    public String toString() {
        String res = "";
        for(int i=0;i<this.taille;i++) {
            if(this.listeBits.get(this.taille-i-1)) {
                res = res+"1";
            }
            else {
                res = res + "0";
            }
        }
        return res;
    }
    
    /**
     * Renvoie le résultat de this XOR mot2
     * @param mot2 2nd mot binaire
     * @return le résultat du xor
     */
    public MotBinaire xor(MotBinaire mot2) {
        MotBinaire mb = this;
        mb.getBitSet().xor(mot2.getBitSet());
        return mb;
    }
    
    /**
     * Renvoie le résultat de this + mot2 [2^32]
     * @param mot2 2nd mot binaire
     * @return le résultat de l'addition
     */
     public MotBinaire additionMod2p32(MotBinaire mot2) {
        String resultat = ""; 
        int retenue = 0;

        for (int i = 31; i >= 0; i--) {
            int b1 = this.listeBits.get(i) == true ? 1 : 0;
            int b2 = mot2.getBitSet().get(i) == true ? 1 : 0;
            int res = b1 + b2 + retenue;
            
            if (res > 1)
                retenue = 1;
            else 
                retenue = 0;
            
            resultat += String.valueOf(res % 2);
        }
         
        return new MotBinaire(resultat);
     }
    
     /**
      * Scinde le mot binaire en une liste de mot binaire de taille donnée. 
      * @param tailleMorceau taille des morceaux
      * @return la liste des morceaux
      */
     public ArrayList<MotBinaire> scinder(int tailleMorceau) {
        BitSet b1 = new BitSet();
        BitSet b2 = new BitSet();
        
        for(int i=0; i<tailleMorceau; i++){
           b1.set(i, this.listeBits.get(i));
           b2.set(i, this.listeBits.get(i+tailleMorceau));
        }
        
        ArrayList<MotBinaire> list = new ArrayList<>();
        list.add(new MotBinaire(b1, tailleMorceau));
        list.add(new MotBinaire(b2, tailleMorceau));

        return list;
    }
     
     /**
      * Concaténation de deux mots binaires
      * @param mot le deuxième mot
      * @return le résultat de la concaténation
      */
     public MotBinaire concatenation(MotBinaire mot) {
        MotBinaire mb = new MotBinaire(new BitSet(64), 64);
        
        for (int i = 0 ; i < 32; i++) {
            mb.getBitSet().set(i, this.listeBits.get(i));
            mb.getBitSet().set(i + 32, mot.getBitSet().get(i));
        }
        
	return mb;
     }
     
}