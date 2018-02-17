import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by omar on 20/09/17.
 */
public class BridgeTree {
    int[] bridge_tree_parent, block, connected_component, size;
    int N, bridges;

    BridgeTree (int n) {
        N = n;
        visited_time = new int[N];
        bridge_tree_parent = new int[N];
        block = new int[N];
        connected_component = new int[N];
        size = new int[N];
        Arrays.fill(bridge_tree_parent, -1);
        Arrays.fill(size, 1);
        for (int i = 0; i < N; i++)
            block[i] = connected_component[i] = i;
    }

    int get_block (int v) {
        return v == -1 ? -1 : block[v] == v ? v : (block[v] = get_block(block[v]));
    }
    int get_connected_component (int v) {
        int block_rep = block[v];
        return connected_component[block_rep] == block_rep ? block_rep :
                (connected_component[block_rep] = get_connected_component(connected_component[block_rep]));
    }

    void make_root (int v) {
        v = get_block(v);
        int root = v, child = -1;
        while (v != -1) {
            int p = get_block(bridge_tree_parent[v]);
            bridge_tree_parent[v] = child;
            connected_component[v] = root;
            child = v;
            v = p;
        }
        size[root] = size[child];
    }

    int time, visited_time[];
    void merge_path (int a, int b) {
        time++;
        ArrayList<Integer> parentsA = new ArrayList<>(), parentsB = new ArrayList<>();
        int LCA = -1;
        for (;;) {
            if (a != -1) {
                a = get_block(a);
                parentsA.add(a);
                if (visited_time[a] == time) {
                    LCA = a;
                    break;
                }
                visited_time[a] = time;
                a = bridge_tree_parent[a];
            }
            if (b != -1) {
                b = get_block(b);
                parentsB.add(b);
                if (visited_time[b] == time) {
                    LCA = b;
                    break;
                }
                visited_time[b] = time;
                b = bridge_tree_parent[b];
            }
        }
        for (int parent : parentsA) {
            block[parent] = LCA;
            if (parent == LCA) break;
            --bridges;
        }
        for (int parent : parentsB) {
            block[parent] = LCA;
            if (parent == LCA) break;
            --bridges;
        }
    }

    void add_edge (int a, int b) {
        a = get_block(a);
        b = get_block(b);
        if (a == b) return;
        int component_a = get_connected_component(a), component_b = get_connected_component(b);
        if (component_a != component_b) {
            bridges++;
            if (size[component_a] > size[component_b]) {
                a ^= b;
                b ^= a;
                a ^= b;
                component_a ^= component_b;
                component_b ^= component_a;
                component_a ^= component_b;
            }
            make_root(a);
            bridge_tree_parent[a] = connected_component[a] = b;
            size[component_b] += size[a];
        } else {
            merge_path(a, b);
        }
    }

    public static void main (String[] args) {
        BridgeTree b = new BridgeTree(6);
        System.err.println(b.bridges);
        b.add_edge(0, 1);
        System.err.println(b.bridges);
        b.add_edge(1, 2);
        System.err.println(b.bridges);
        b.add_edge(0, 2);
        System.err.println(b.bridges);
        b.add_edge(4, 5);
        System.err.println(b.bridges);
        b.add_edge(4, 2);
        System.err.println(b.bridges);
        b.add_edge(4, 3);
        System.err.println(b.bridges);
        b.add_edge(3, 2);
        System.err.println(b.bridges);
        b.add_edge(5, 0);
        System.err.println(b.bridges);
    }
}
