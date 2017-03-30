/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.Comparator;

/**
 *
 * @author bruno
 */
class FrequencyComparator implements Comparator<Node> {

    public int compare(Node a, Node b) {
        int freqA = a.frequencia;
        int freqB = b.frequencia;
        return freqA - freqB;
    }

}
    

