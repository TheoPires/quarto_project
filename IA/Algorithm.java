package IA;

import model.Move;
import tree.Node;

public interface Algorithm {

    int DEPTH = 3;


    double run(Node node, int depth);

    int getWho();

}
