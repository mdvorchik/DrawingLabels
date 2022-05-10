package org.mipt.drawer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TwoSATSolver {
    private final List<List<Integer>> clauseGraph;
    private final List<List<Integer>> revClauseGraph;
    private final boolean[] used;
    private final int[] comp;
    private final List<Integer> order = new ArrayList<>();

    public TwoSATSolver(List<List<Integer>> clauseGraph, List<List<Integer>> revClauseGraph) {
        this.clauseGraph = clauseGraph;
        this.revClauseGraph = revClauseGraph;
        for (int i = 0; i < clauseGraph.size(); i++) {
            clauseGraph.set(i, clauseGraph.get(i).stream().distinct().collect(Collectors.toList()));
            revClauseGraph.set(i, revClauseGraph.get(i).stream().distinct().collect(Collectors.toList()));
        }
        used = new boolean[clauseGraph.size()];
        Arrays.fill(used, false);
        comp = new int[clauseGraph.size()];
        Arrays.fill(comp, -1);
    }

    void defineOrder(int v) {
        used[v] = true;
        for (int i = 0; i < clauseGraph.get(v).size(); ++i) {
            int to = clauseGraph.get(v).get(i);
            if (!used[to])
                defineOrder(to);
        }
        order.add(v);
    }

    void defineComp(int v, int compVal) {
        comp[v] = compVal;
        for (int i = 0; i < revClauseGraph.get(v).size(); ++i) {
            int to = revClauseGraph.get(v).get(i);
            if (comp[to] == -1)
                defineComp(to, compVal);
        }
    }

    public List<Integer> solve() {
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < clauseGraph.size(); ++i) {
            if (!used[i]) {
                defineOrder(i);
            }
        }

        for (int i = 0, j = 0; i < clauseGraph.size(); ++i) {
            int v = order.get(clauseGraph.size() - i - 1);
            if (comp[v] == -1) {
                defineComp(v, j++);
            }
        }

        for (int i = 0; i < clauseGraph.size() / 2; ++i) {
            if (comp[i] == comp[i + clauseGraph.size() / 2]) {
                return null;
            }
        }

        for (int i = 0; i < clauseGraph.size() / 2; ++i) {
            int v = comp[i] > comp[i + clauseGraph.size() / 2] ? i : i + clauseGraph.size() / 2;
            answer.add(v);
        }

        return answer;
    }
}
