/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.*;
import java.util.*;

public class HuffMain {

    public static PriorityQueue<Node> fila;
    public static HashMap<Character, String> charToCode;
    public static HashMap<String, Character> codeToChar;

    @SuppressWarnings("resource")
    public static void main(String[] args) throws FileNotFoundException {
        // lê todo o arquivo
        String texto = new Scanner(new File("entrada.txt")).useDelimiter("\\A").next();
        //conta a frequencia dos caracteres
        HashMap<Character, Integer> dicionario = new HashMap<Character, Integer>();
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (dicionario.containsKey(caracter)) {
                dicionario.put(caracter, dicionario.get(caracter) + 1);
            } else {
                dicionario.put(caracter, 1);
            }
        }

        // Adiciona cada folha na lista de prioridades
        fila = new PriorityQueue<Node>(100, new FrequencyComparator());
        int n = 0;
        for (Character caracter : dicionario.keySet()) {
            fila.add(new Node(caracter, dicionario.get(caracter)));
            n++;
        }
        Node root = huffmain(n);
        criarTabela(root);

        String txt_comprimido = comprimir(texto);
        salvarArquivo(txt_comprimido);

        String txt_descomp = descromprimir(txt_comprimido);
        escreverArquivo(txt_descomp);
    }

    // // Cria a árvore de acordo com a frequencia da lista de prioridade
    public static Node huffmain(int n) {
        Node x, y;
        for (int i = 1; i <= n - 1; i++) {
            Node node = new Node();
            node.left = x = fila.poll();
            node.right = y = fila.poll();
            node.frequencia = x.frequencia + y.frequencia;
            fila.add(node);
        }
        return fila.poll();
    }

    //Cria uma tabela para poder fazer as conversões
    public static void criarTabela(Node root) {
        charToCode = new HashMap<Character, String>();
        codeToChar = new HashMap<String, Character>();
        posOrdem(root, new String());
    }

    // Percorre a árvore da raiz para as folhas
    public static void posOrdem(Node node, String s) {
        if (node == null) {
            return;
        }
        posOrdem(node.left, s + "0");
        posOrdem(node.right, s + "1");

        //se o nó é um caracter, atribui a frequencia na frente
        if (!Character.toString(node.caracter).equals("&#092;&#048;")) {
            System.out.println("{" + node.caracter + ":" + s + "}");
            charToCode.put(node.caracter, s);
            codeToChar.put(s, node.caracter);
        }
    }

    //comprime se a árvore ja estiver criada
    public static String comprimir(String s) {
        String c = new String();
        for (int i = 0; i < s.length(); i++) {
            c = c + charToCode.get(s.charAt(i));
        }
        return c;
    }

    //descomprime se a árvore e o dicionário ja estiverem carregados
    public static String descromprimir(String s) {
        String temp = new String();
        String result = new String();
        for (int i = 0; i < s.length(); i++) {
            temp = temp + s.charAt(i);
            Character c = codeToChar.get(temp);
            if (c != null && c != 0) {
                result = result + c;
                temp = "";
            }
        }
        return result;
    }
    //salva um aquivo formatado
    public static void salvarArquivo(String l) throws FileNotFoundException {
        PrintWriter oFile = new PrintWriter("saida.txt");
        oFile.print(charToCode.size());
        for (char s : charToCode.keySet()) {
            oFile.println("{" + s + ":" + charToCode.get(s) + "}");
        }
        oFile.println(l);
        oFile.close();
    }

    public static void escreverArquivo(String i) throws FileNotFoundException {
        PrintWriter iFile = new PrintWriter("arvore.txt");
        iFile.print(codeToChar.size());
        for (String s : codeToChar.keySet()) {
            iFile.println("{" + s + ":" + codeToChar.get(s) + "}");
        }
        iFile.println(i);
        iFile.close();
    }
}





