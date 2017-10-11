import java.util.ArrayList;
import java.util.List;

/**
 * O(nlogn) 使用频率越高的编码越短， 原理是把要编码的字符看做n棵独立的树，取权重最小的两棵合成新树，新树的权重为这两棵树
 * 权重的和，重复这个过程直至成为一颗树。  编码是从根节点开始，左子树为0，右子树为1，要编码的字符是n个叶子节点
 * Created by shuoshuo on 2017/10/11.
 */
//哈夫曼树类
public class HuffmanTree {
    HuffmanNode[] nodes;
    HuffmanCode[] codes;

    HuffmanTree(int n) {
        nodes = new HuffmanNode[2 * n - 1];
        codes = new HuffmanCode[n];
        for (int i = 0; i < 2 * n - 1; i++) {
            nodes[i] = new HuffmanNode();
        }

        for (int i= 0; i < n; i++) {
            codes[i] = new HuffmanCode();
        }
    }

    public void huffman(int[] weight) {
        int n = weight.length;
        //init leave nodes
        for (int i = 0; i < n; i++) {
            nodes[i].weight = weight[i];
        }

        //init nonleaves
        for (int i = 0; i < n - 1; i++) {
            int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
            int node1 = -1, node2 = -1;
            for (int j = 0; j < n + i; j++) {
                if (nodes[j].weight < min1 && !nodes[j].isCompared) {
                    min2= min1;
                    node2 = node1;
                    min1 = nodes[j].weight;
                    node1 = j;
                } else if (nodes[j].weight < min2 && !nodes[j].isCompared) {
                    min2 = nodes[j].weight;
                    node2 = j;
                }
            }

            nodes[node1].parent = n + i;
            nodes[node2].parent = n + i;
            nodes[node1].isCompared = true;
            nodes[node2].isCompared = true;

            nodes[n + i].weight = min1 + min2;
            nodes[n + i].leftChild = node1;
            nodes[n + i].rightChild = node2;
        }
    }

    private void huffmanCode(int n) {
        for (int i=0; i < n; i++) {
            List<Integer> code = codes[i].codes;
            HuffmanNode curNode = nodes[i];
            int index = i;
            while (curNode.parent != -1) {
                int parent = curNode.parent;
                if (nodes[parent].leftChild == index) {
                    code.add(0, 0);
                } else {
                    code.add(0, 1);
                }
                curNode = nodes[parent];
                index = parent;
            }
        }
    }

    public static void main(String[] args) {
        int[] weight = {1, 3, 5, 7};
        HuffmanTree tree = new HuffmanTree(4);
        tree.huffman(weight);
        tree.huffmanCode(4);
        for (int i=0; i<4; i++) {
            System.out.println(tree.codes[i].codes);
        }
    }
}

class HuffmanNode {
    int parent;
    int leftChild, rightChild;
    int weight;
    boolean isCompared;

    HuffmanNode() {
        parent = -1;
        leftChild = -1;
        rightChild = -1;
        weight = 0;
        isCompared = false;
    }
}


class HuffmanCode {
    List<Integer> codes;
    HuffmanCode() {
        codes = new ArrayList<>();
    }
}