/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 *
 * @author bruno
 */
class Node {

    public char caracter;
    public int frequencia;
    public Node left, right;

    public Node(char a, int f) {
        caracter = a;
        frequencia = f;
    }

    public Node() {
    }

    public String toString() {
        return caracter + " " + frequencia;
    }
}