// Given Root.

static ArrayList<Integer> adjList [];
    static String canonicalForm (int node, int parent) {
        ArrayList<String> childrenCanonicalForms = new ArrayList<>();
        for (int child : adjList[node]) {
            if (child != parent) {
                childrenCanonicalForms.add(canonicalForm(child, node));
            }
        }
        String res = "(";
        Collections.sort(childrenCanonicalForms);
        for (String s : childrenCanonicalForms) res += s;
        res += ")";
        return res;
    }

// If you don't have Roots : Assume that the roots are the centers of the diameters of the two trees. (CARE : If the Diameter is even.)
