
package main;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
 

class Node
{
    Character ch;
    Integer freq;
    Node left = null, right = null;
 
    Node(Character ch, Integer freq)
    {
        this.ch = ch;
        this.freq = freq;
    }
 
    public Node(Character ch, Integer freq, Node left, Node right)
    {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}
 
class Main
{
 
    public static void encode(Node root, String str,
                        Map<Character, String> huffmankodu)
    {
        if (root == null) {
            return;
        }
 
     
        if (isLeaf(root)) {
            huffmankodu.put(root.ch, str.length() > 0 ? str : "1");
        }
 
        encode(root.left, str + '0', huffmankodu);
        encode(root.right, str + '1', huffmankodu);
    }
 
   
    public static int decode(Node root, int index, StringBuilder sb)
    {
        if (root == null) {
            return index;
        }
 
     
        if (isLeaf(root))
        {
            System.out.print(root.ch);
            return index;
        }
 
        index++;
 
        root = (sb.charAt(index) == '0') ? root.left : root.right;
        index = decode(root, index, sb);
        return index;
    }
 
    public static boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
    }
 

    public static void buildHuffmanTree(String text)
    {
        
        if (text == null || text.length() == 0) {
            return;
        }
 
       
 
        Map<Character, Integer> freq = new HashMap<>();
        for (char c: text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
 
 
        PriorityQueue<Node> pq;
        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));
 
     
 
        for (var entry: freq.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }
 
     
        while (pq.size() != 1)
        {
    
 
            Node left = pq.poll();
            Node right = pq.poll();
 
   
 
            int sum = left.freq + right.freq;
            pq.add(new Node(null, sum, left, right));
        }
 
     
        Node root = pq.peek();
 
       
        Map<Character, String> huffmanCode = new HashMap<>();
        encode(root, "", huffmanCode);
 
  
        System.out.println("Huffman kodlari: " + huffmanCode);
        System.out.println("Orjinal dizi: " + text);
 
      
        StringBuilder sb = new StringBuilder();
        for (char c: text.toCharArray()) {
            sb.append(huffmanCode.get(c));
        }
 
        System.out.println("Kodlanmis dizi: " + sb);
        System.out.print("Kodu cozulmus dizi: ");
 
        if (isLeaf(root))
        {
           
            while (root.freq-- > 0) {
                System.out.print(root.ch);
            }
        }
        else {
           
            int index = -1;
            while (index < sb.length() - 1) {
                index = decode(root, index, sb);
            }
        }
    }
 
  
    public static void main(String[] args)
    {
        String text = "Mirac Hanbay";
        buildHuffmanTree(text);
    }
}
